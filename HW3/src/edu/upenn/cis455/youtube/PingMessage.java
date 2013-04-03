package edu.upenn.cis455.youtube;

import rice.p2p.commonapi.NodeHandle; 

public class PingMessage implements rice.p2p.commonapi.Message { 

	static final long serialVersionUID = 0;
	
	NodeHandle from; 
	String content; 
	boolean wantResponse = true;
	public PingMessage(NodeHandle from, String content) { 
		this.from = from; 
		this.content = content; 
	} 
	
	// dummy implementation of abstract method
	public int getPriority(){
		return -1;
	}
} 