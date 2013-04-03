package edu.upenn.cis455.youtube;

import rice.p2p.commonapi.NodeHandle; 

public class OurMessage implements rice.p2p.commonapi.Message { 

	static final long serialVersionUID = 0;
	
	NodeHandle from; 
	String content; 
	boolean wantResponse = true;
	public OurMessage(NodeHandle from, String content) { 
		this.from = from; 
		this.content = content; 
	} 
	
	// dummy implementation of abstract method
	public int getPriority(){
		return -1;
	}
} 