package edu.upenn.cis455.youtube;

import java.io.BufferedReader;
import java.io.IOException;
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
		System.out.println();
        String ipAddress = getServletContext().getInitParameter("cacheServer");
		System.out.println("IP and Port:" + ipAddress + daemonPort);
        // Open socket to the designated daemon thread 
		try{
			Socket clientSocket = new Socket(ipAddress, daemonPort); // I DONT KNOW IF THE NAME IS RIGHT
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			// Send the QUERY to the daemon thread
			System.out.println("GET /keyword/"+keyword);
			out.println("GET /keyword/"+keyword);
			out.println("/n");
			out.flush();
			System.out.println("out done");

			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String responseStr = "";
			while((responseStr += in.readLine()) != null){
				System.out.println("inside readline: "+responseStr);
			}
			System.out.println(responseStr);

			System.out.println("in done");
			// Display the results as the final result of the POST request
			String resultTitle = "<HTML><HEAD><TITLE>Rohan's DHT based YouTube Search Machine Results</TITLE></HEAD><BODY>"+
					"<form name=\"input\" action=\"YouTubeSearch\" method=\"post\">"+
					"<h1> Full name: Rohan Shah SEAS login: shahroh"+
					" Rohan's DHT based YouTube Search Machine Results </h1><br/>";

			response.setContentType("text/html");
			PrintWriter outBack = response.getWriter();
			//outBack.println(resultTitle + resultList);
			outBack.println(resultTitle + response);

		}
		catch(Exception e){

		}
		
	}

}
