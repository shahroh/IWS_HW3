package edu.upenn.cis455.youtube;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class YouTubeSearch
 */
@WebServlet("/YouTubeSearch")
public class YouTubeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public YouTubeSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myForm = "<HTML><HEAD><TITLE>Rohan's DHT based YouTube Search Machine</TITLE></HEAD><BODY>"+
				"<form name=\"input\" action=\"YouTubeSearch\" method=\"post\">"+
				"<h1> Full name: Rohan Shah SEAS login: shahroh"+
				" Rohan's DHT based YouTube Search Machine </h1><br/>"+
				"<h3> enter the search keyword and hit submit button!</h3>"+
				"Search for: "+ 
				"<br/><textarea rows=\"10\" cols=\"30\" name=\"keyword\">"+
				"</textarea>"+"<br/>"+
				"<br/>"+
				"<input type=\"submit\" value=\"Submit\">"+
				"</form>";

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(myForm);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword").trim();
		System.out.println(keyword);
		// Get the config params (thru web.xml)
		ServletConfig config = getServletConfig();
		
		int daemonPort = Integer.parseInt(getServletContext().getInitParameter("cacheServerPort"));
		
        String ipAddress = getServletContext().getInitParameter("cacheServer");
        System.out.println("daemon and port: "+ipAddress + daemonPort);
        // Open socket to the designated daemon thread 
		try{
			Socket clientSocket = new Socket(ipAddress, daemonPort); // I DONT KNOW IF THE NAME IS RIGHT
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			// Send the QUERY to the daemon thread
			out.println("GET /keyword/"+keyword);
			out.println("/n");
			out.flush();

			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String responseStr = "";
			responseStr += in.readLine();

			// Display the results as the final result of the POST request
			String resultTitle = "<HTML><HEAD><TITLE>Rohan's DHT based YouTube Search Machine Results</TITLE></HEAD><BODY>"+
					"<form name=\"input\" action=\"YouTubeSearch\" method=\"post\">"+
					"<h1> Full name: Rohan Shah SEAS login: shahroh"+
					" Rohan's DHT based YouTube Search Machine Results </h1><br/>";

			String resultEnd = "</BODY></HTML>";
			response.setContentType("text/html");
			PrintWriter outBack = response.getWriter();
			//outBack.println(resultTitle + resultList);
						
			// DOM parser to parse the returned XML doc
			// Adding xml  to top of string 
			String str = "<?xml version=\"1.0\"?>" + responseStr;
			str = str.replaceAll("[&]", "&amp;");
			System.out.println(str);
			InputStream is = new ByteArrayInputStream(str.getBytes());
			
			/*
			DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbFac.newDocumentBuilder();
			
			System.out.println("before db prse");
			Document d = db.parse(is);
			System.out.println("after db prse");
			
			String outParsed = "	";
			NodeList nl = d.getElementsByTagName("video-feed");
			System.out.println("length of nodelist: "+nl.getLength());
			for(int i=0; i<nl.getLength(); i++){
				System.out.println("in the loop");
				// for each entry, get title, url.
				Node n = nl.item(i);
				if(n.getNodeType() == n.ELEMENT_NODE){
					Element e = (Element) n;
					outParsed += "<p>" + e.getElementsByTagName("title") + ", " + e.getElementsByTagName("url") + "<p><br/>";
				}
			}
			
			outBack.println(resultTitle + outParsed + resultEnd);
			System.out.println(resultTitle + outParsed + resultEnd);
			*/
			
			outBack.println(resultTitle + responseStr + resultEnd);
			clientSocket.close();
		}
		catch(Exception e){

		}
		
	}

}
