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
				
				// Instatntiate you tube client 
				YouTubeClient youTubeClient = YouTubeClient.GetSingleton(null);
				
				// Instantiate nodefactory
				InetSocketAddress bootAddr = new InetSocketAddress(bootInetAddr, bootPortNum);
				NodeFactory nodeFac = new NodeFactory(localPortNum, bootAddr);
				SimpleApp simpleApp = new SimpleApp(nodeFac);
				
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
