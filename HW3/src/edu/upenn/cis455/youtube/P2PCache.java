package edu.upenn.cis455.youtube;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class P2PCache {

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
				
				String msg1 = "Madhura is cute";
				String msg2 = "Madhura is really cute";
				String msg3 = "Madhura is really really cute";
				simpleApp.sendMessage(nodeFac.getIdFromString(msg1), msg1);
				simpleApp.sendMessage(nodeFac.getIdFromString(msg2), msg2);
				simpleApp.sendMessage(nodeFac.getIdFromString(msg3), msg3);
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
