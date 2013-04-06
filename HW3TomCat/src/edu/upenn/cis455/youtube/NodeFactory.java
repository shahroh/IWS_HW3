package edu.upenn.cis455.youtube;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import rice.p2p.commonapi.Node;
import rice.environment.Environment;
import rice.pastry.NodeHandle;
import rice.pastry.Id;
import rice.pastry.NodeIdFactory;
import rice.pastry.PastryNode;
import rice.pastry.socket.SocketPastryNodeFactory;
import rice.pastry.standard.RandomNodeIdFactory;


public class NodeFactory {
	Environment env;
	NodeIdFactory nidFactory;
	SocketPastryNodeFactory factory;
	NodeHandle bootHandle;
	int createdCount = 0;
	int port;

	NodeFactory(int port) {
		this(new Environment(), port);
	}	

	NodeFactory(int port, InetSocketAddress bootPort) {
		this(port);
		bootHandle = factory.getNodeHandle(bootPort);
	}

	NodeFactory(Environment env, int port) {
		this.env = env;
		this.port = port;
		nidFactory = new RandomNodeIdFactory(env);		
		try {
			factory = new SocketPastryNodeFactory(nidFactory, port, env);
		} catch (java.io.IOException ioe) {
			throw new RuntimeException(ioe.getMessage(), ioe);
		}

	}

	public Node getNode() {
		try {
			System.out.println("getnode: 1");
			synchronized (this) {
				if (bootHandle == null && createdCount > 0) {
					System.out.println("getnode: if1");
					InetAddress localhost = InetAddress.getLocalHost();
					InetSocketAddress bootaddress = new InetSocketAddress(localhost, port);
					bootHandle = factory.getNodeHandle(bootaddress);
				}
			}
			System.out.println("getnode: 2");

			PastryNode node =  factory.newNode(bootHandle);
			System.out.println("getnode: 3");

			synchronized (node) {
				while (!node.isReady() && ! node.joinFailed()) {
					System.out.println("getnode: while");
					node.wait(500);
					if (node.joinFailed()) {
						System.out.println("getnode: if2");
						throw new IOException("Could not join the FreePastry ring. Reason:"+node.joinFailedReason());
					}	
				}
			}
			System.out.println("getnode: 4");

			synchronized (this) {
				++createdCount;
			}
			System.out.println("getnode: 5");
			return node;
		} catch (Exception e) {
			System.out.println("getnode: ex");
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void shutdownNode(Node n) {
		((PastryNode) n).destroy();

	}

	public Id getIdFromBytes(byte[] material) {
		return Id.build(material);
	}

	public Id getIdFromString(String keyString) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] content = keyString.getBytes();
		md.update(content);
		byte shaDigest[] = md.digest();

		return Id.build(shaDigest);
	}

	public Id generateRandomId(){
		return nidFactory.generateNodeId();
	}
}
