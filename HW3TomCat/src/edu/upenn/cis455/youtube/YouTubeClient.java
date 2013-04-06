package edu.upenn.cis455.youtube;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.net.URL;

import com.google.gdata.client.Query;
import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoFeed;
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



public class YouTubeClient {
	private static String pathToBDB = "";
	private static HashMap<String, String> nodeCache = new HashMap<String, String>();
	private static YouTubeClient youTubeClient;
	
	private YouTubeClient(String pathToBDB) {
		this.pathToBDB = pathToBDB;
	}
	
	public static YouTubeClient GetSingleton(String pathToBDB){
		if(youTubeClient == null){
			youTubeClient = new YouTubeClient(pathToBDB);
		}
		return youTubeClient;
	}

	public static String XmlEntries(String prefix, VideoEntry videoEntry){
		String entryText = ""; 
		//String entryText = "<prefix>"+prefix+"</prefix>";

		if (videoEntry.getTitle() != null) {
			entryText += "<td>";
			entryText += "Title: "+ videoEntry.getTitle().getPlainText();
			entryText += "</td>";
		}
		/*
		if (videoEntry.getSummary() != null) {
			entryText += "<summary>";
			entryText += "Summary: "+videoEntry.getSummary().getPlainText();
			entryText += "</summary>";
		}
		*/
		YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();
		if(mediaGroup != null) {
			MediaPlayer mediaPlayer = mediaGroup.getPlayer();
			entryText += "<td>";
			entryText += mediaPlayer.getUrl();
			entryText += "</td>";
			//MediaKeywords keywords = mediaGroup.getKeywords();
			//entryText += "<keywords>";
			//for(String keyword : keywords.getKeywords()) {
				//entryText += keyword + ",";
			//}
			//entryText += "</keywords>";
			//entryText += "<thumbnails>";
			/*
			for(MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
				entryText += "<url>" + mediaThumbnail.getUrl() + "</url>";
				entryText += "<time_index>" + mediaThumbnail.getTime() + "</time_index>";
			}
			entryText += "</thumbnails>";
			entryText += "<media>";
			for(YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
				entryText += "<media_location>"+mediaContent.getUrl()+"</media_location>";
				entryText += "<media_type>"+mediaContent.getType()+"</media_type>";
				entryText += "<duration>" + mediaContent.getDuration() + "</duration>";
			}
			entryText += "</media>";
			*/
		}
		return entryText;
	}

	public static String FeedToXml(VideoFeed videoFeed){
		String Xml = "<table>";
		List<VideoEntry> videoEntries = videoFeed.getEntries();

		// In case videoFeed is empty
		if (videoEntries.size() == 0) {
			return Xml;
		}
		int count = 1;
		for(VideoEntry ve : videoEntries){
			Xml += "<tr>";
			Xml += XmlEntries("(Video #" + String.valueOf(count) + ")", ve);
			Xml += "</tr>";
			count++;
		}
		Xml += "</table>";

		return Xml;

	}

	public String SearchVideos(String keyword){
		YouTubeService myService = new YouTubeService("gdataSample-YouTube-1");
		// check if the keyword is in the local cache and print appropriate message
		if(nodeCache.containsKey(keyword)){
			System.err.print("Query for "+keyword+" resulted in a cache hit");

			// return the RESULT message containing the cached data
			return nodeCache.get(keyword);
		}
		else{
			System.err.print("Query for "+keyword+" resulted in a cache miss");
			try{
				YouTubeQuery query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
				// order the results by the number of views
				query.setOrderBy(YouTubeQuery.OrderBy.VIEW_COUNT);

				// include restricted content in the search results
				query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);

				// search for puppies and include restricted content in the search results
				query.setFullTextQuery(keyword);

				VideoFeed videoFeed = myService.query(query, VideoFeed.class);
				String Xml = FeedToXml(videoFeed);
				
				// Enter this into the local cache
				nodeCache.put(keyword, Xml);
				
				//System.out.println(Xml);
				//printVideoFeed(videoFeed);
				return Xml;
			}catch(Exception e){
			}
		}
		return null;
	}

	public static void main(String[] args) {
		YouTubeService myService = new YouTubeService("gdataSample-YouTube-1");

		try{
			YouTubeQuery query = new YouTubeQuery(new URL("http://gdata.youtube.com/feeds/api/videos"));
			// order the results by the number of views
			query.setOrderBy(YouTubeQuery.OrderBy.VIEW_COUNT);

			// include restricted content in the search results
			query.setSafeSearch(YouTubeQuery.SafeSearch.NONE);

			// a category filter holds a collection of categories to limit the search
			Query.CategoryFilter categoryFilter = new Query.CategoryFilter();

			// search for puppies and include restricted content in the search results
			query.setFullTextQuery("coldplay");

			VideoFeed videoFeed = myService.query(query, VideoFeed.class);
			String Xml = FeedToXml(videoFeed);
			System.out.println(Xml);
			// printVideoFeed(videoFeed);
		}catch(Exception e){
		}
	}
}
