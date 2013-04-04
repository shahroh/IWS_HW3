package edu.upenn.cis455.youtube;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import rice.environment.Environment;
import rice.p2p.commonapi.Id;
import rice.pastry.NodeIdFactory;

public class PingPongTest {

	static int localPortNum;
	static InetAddress bootInetAddr;
	static int bootPortNum;
	static int daemonPortNum;
	static String pathToBDB;

	public static void main(String[] args){
		try{
			if(args.length == 5){
				// Read all the arguments
				localPortNum = Integer.parseInt(args[0]);
				bootInetAddr = InetAddress.getByName(args[1]);
				bootPortNum = Integer.parseInt(args[2]);
				daemonPortNum = Integer.parseInt(args[3]);
				pathToBDB = args[4];

				// Instantiate nodefactory
				InetSocketAddress bootAddr = new InetSocketAddress(bootInetAddr, bootPortNum);
				NodeFactory nodeFac = new NodeFactory(localPortNum, bootAddr);
				SimpleApp simpleApp = new SimpleApp(nodeFac);
				Environment env = new Environment();
				
				String msg = "PING";

				for (int i = 0; i < 10; i++) {
					Id randId = nodeFac.generateRandomId();
					simpleApp.sendMessage(randId, msg);
					env.getTimeSource().sleep(3000);
				}


			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
