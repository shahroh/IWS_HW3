package edu.upenn.cis455.youtube;

import java.io.OutputStream;
import com.google.gdata.client.Query;

import com.google.gdata.client.Service;
import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.Category;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.data.TextContent;
import com.google.gdata.data.extensions.Comments;
import com.google.gdata.data.extensions.FeedLink;
import com.google.gdata.data.media.mediarss.MediaKeywords;
import com.google.gdata.data.media.mediarss.MediaPlayer;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.FeedLinkEntry;
import com.google.gdata.data.youtube.PlaylistEntry;
import com.google.gdata.data.youtube.PlaylistFeed;
import com.google.gdata.data.youtube.PlaylistLinkEntry;
import com.google.gdata.data.youtube.PlaylistLinkFeed;
import com.google.gdata.data.youtube.SubscriptionEntry;
import com.google.gdata.data.youtube.SubscriptionFeed;
import com.google.gdata.data.youtube.UserProfileEntry;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.data.youtube.YouTubeMediaContent;
import com.google.gdata.data.youtube.YouTubeMediaGroup;
import com.google.gdata.data.youtube.YouTubeNamespace;
import com.google.gdata.util.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;



import java.net.Socket;
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
		
		else if(om.content.startsWith("QUERY")){
			OurMessage result = new OurMessage(node.getLocalNodeHandle(), "RESULT "+youTubeClient.SearchVideos((om.content).substring(6)));
			result.wantResponse = false;
			endpoint.route(null, result, om.from);
		}
		else if(om.content.startsWith("RESULT")){
			try{
				System.out.println("RESULT******************");
				Socket clientSocket = P2PCache.GetClientSocket();
				OutputStream out = clientSocket.getOutputStream();
				out.write((om.content.substring(7)).getBytes());
				out.write("\n".getBytes());
			}
			catch(Exception e){
				
			}
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