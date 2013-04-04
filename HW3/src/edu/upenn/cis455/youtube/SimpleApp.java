package edu.upenn.cis455.youtube;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rice.p2p.commonapi.Id; 
import rice.p2p.commonapi.Message; 
import rice.p2p.commonapi.Node; 
import rice.p2p.commonapi.NodeHandle; 
import rice.p2p.commonapi.Endpoint; 
import rice.p2p.commonapi.Application; 
import rice.p2p.commonapi.RouteMessage; 

public class SimpleApp implements Application {
	NodeFactory nodeFactory; 
	Node node; 
	Endpoint endpoint;

	public SimpleApp(NodeFactory nodeFactory) { 
		this.nodeFactory = nodeFactory; 
		this.node = nodeFactory.getNode(); 
		this.endpoint = node.buildEndpoint(this, "Simple App"); 
		this.endpoint.register(); 
	} 

	void sendMessage(Id idToSendTo, String msgToSend) { 
		OurMessage m = new OurMessage(node.getLocalNodeHandle(), msgToSend); 
		endpoint.route(idToSendTo, m, null);
	}

	public void deliver(Id id, Message message) { 
		OurMessage om = (OurMessage) message; 
		YouTubeClient youTubeClient = YouTubeClient.GetSingleton(null);
		System.out.println("Received message " + om.content +  
				" from " + om.from); 

		if(om.content.equals("PING")){
			OurMessage pong = new OurMessage(node.getLocalNodeHandle(), "PONG");
			pong.wantResponse = false;
			endpoint.route(null, pong, om.from);
		}
		else if(om.content.startsWith("SEARCH")){
			Pattern contentPatt = Pattern.compile("SEARCH /(.*)/");
			Matcher m = contentPatt.matcher(om.content);
			OurMessage result = new OurMessage(node.getLocalNodeHandle(), "RESULT "+youTubeClient.SearchVideos(m.group(1)));
			result.wantResponse = false;
			endpoint.route(null, result, om.from);
		}
		else if(om.content.startsWith("RESULT")){
			// send the content back to the servlet thru the socket
		}
		if (om.wantResponse) { 
			OurMessage reply = new OurMessage(node.getLocalNodeHandle(), 
					"Message received"); 
			reply.wantResponse = false; 
			endpoint.route(null, reply, om.from); 
		} 
	}

	public void update(NodeHandle handle, boolean joined) { 
		// This method will always be empty in your assignment 
	} 
	public boolean forward(RouteMessage routeMessage) { 
		// This method will always return true in your assignment 
		return true; 
	} 

} 