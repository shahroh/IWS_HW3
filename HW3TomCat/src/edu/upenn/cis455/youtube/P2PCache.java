package edu.upenn.cis455.youtube;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import rice.environment.Environment;
import rice.p2p.commonapi.Id;

public class P2PCache {

	static int localPortNum;
	static InetAddress bootInetAddr;
	static int bootPortNum;
	static int daemonPortNum;
	static String pathToBDB;
	static Socket clientSocket;

	public static Socket GetClientSocket(){
		return clientSocket;
	}

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
				Environment env = new Environment();

				// server socket (daemon thread)
				System.out.println("Reached deamon port num usage");
				ServerSocket serve = new ServerSocket(daemonPortNum);
				OutputStream out;
				BufferedReader in;
				while(true){
					clientSocket = serve.accept();
					out = clientSocket.getOutputStream();
					in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

					String inQuery;
					String kword;
					if((inQuery = in.readLine()) != null){
						System.out.println("Inside P2P, recieved something on socket");
						if(inQuery.matches("GET /keyword/.*")){
							System.out.println("received GET request");
							kword = inQuery.substring(inQuery.lastIndexOf('/')+1);
							
							// construct SOAP message for DHT
							String Query = "QUERY "+kword;
							System.out.println("About to throw into DHT");
							Id destId = nodeFac.getIdFromString(kword);
							simpleApp.sendMessage(destId, Query);
						}
					}

				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
