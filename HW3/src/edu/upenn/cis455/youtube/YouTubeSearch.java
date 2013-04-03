package edu.upenn.cis455.youtube;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
public class YouTubeSearch extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException 
	{   
		String myForm = "<HTML><HEAD><TITLE>Rohan's DHT based YouTube Search Machine</TITLE></HEAD><BODY>"+
				"<form name=\"input\" action=\"youtubesearch\" method=\"post\">"+
				"<h1> Full name: Rohan Shah SEAS login: shahroh"+
				" Rohan's DHT based YouTube Search Machine </h1><br/>"+
				"<h3> enter the search keyword and hit submit button!</h3>"+
				"Search for: "+ 
				"<br/><textarea rows=\"10\" cols=\"30\" name=\"keyword\">"+
				"</textarea>"+"<br/>"+
				"<br/>"+
				"<input type=\"submit\" value=\"Submit\">"+
				"</form>";

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println(myForm);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException 
	{       
		String keyword = req.getParameter("keyword").trim();
		
		// Get the config params (thru web.xml)
		ServletConfig config = getServletConfig();
		String Cserver = config.getInitParameter("cacheServer");
		int Pserver = Integer.parseInt(config.getInitParameter("cacheServerPort"));
		
		
		// Use YouTube API to search for url results
		
		// Display the results as the final result of the POST request
		String resultTitle = "<HTML><HEAD><TITLE>Rohan's DHT based YouTube Search Machine Results</TITLE></HEAD><BODY>"+
				"<form name=\"input\" action=\"youtubesearch\" method=\"post\">"+
				"<h1> Full name: Rohan Shah SEAS login: shahroh"+
				" Rohan's DHT based YouTube Search Machine Results </h1><br/>";
		
		String resultList = "";
		String[] APIresults = {"", ""}; // say this is the string array returned by the API (the urls)
		for(int i=0; i<APIresults.length; i++){
			resultList += "<br/>" + APIresults[i];
		}
				
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println(resultTitle + resultList);
		
	}
}
