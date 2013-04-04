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



public class SearchTest{
	/**
	 * Prints a VideoEntry, optionally showing its responses and comment feeds.
	 * 
	 * @param prefix                   a string to be shown before each entry
	 * @param videoEntry               the VideoEntry to be printed
	 * @param showCommentsAndResponses true if the comments and responses feeds
	 *                                 should be printed
	 * @throws ServiceException
	 *                                 If the service is unable to handle the
	 *                                 request.
	 * @throws IOException             error sending request or reading the feed.
	 */
	private static void printVideoEntry(String prefix, VideoEntry videoEntry,
			boolean showCommentsAndResponses) throws IOException {
		System.out.println(prefix);
		if (videoEntry.getTitle() != null) {
			System.out.printf("Title: %s\n", videoEntry.getTitle().getPlainText());
		}
		if (videoEntry.getSummary() != null) {
			System.out.printf("Summary: %s\n",
					videoEntry.getSummary().getPlainText());
		}
		YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();
		if(mediaGroup != null) {
			MediaPlayer mediaPlayer = mediaGroup.getPlayer();
			System.out.println("Web Player URL: " + mediaPlayer.getUrl());
			MediaKeywords keywords = mediaGroup.getKeywords();
			System.out.print("Keywords: ");
			for(String keyword : keywords.getKeywords()) {
				System.out.print(keyword + ",");
			}
			System.out.println();
			System.out.println("\tThumbnails:");
			for(MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
				System.out.println("\t\tThumbnail URL: " + mediaThumbnail.getUrl());
				System.out.println("\t\tThumbnail Time Index: " +
						mediaThumbnail.getTime());
				System.out.println();
			}
			System.out.println("\tMedia:");
			for(YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
				System.out.println("\t\tMedia Location: "+mediaContent.getUrl());
				System.out.println("\t\tMedia Type: "+mediaContent.getType());
				System.out.println("\t\tDuration: " + mediaContent.getDuration());
				System.out.println();
			}
			System.out.println();
		}
	}

	/**
	 * Fetches a feed known to be a VideoFeed, printing each VideoEntry with in
	 * a numbered list, optionally prompting the user for the number of a video
	 * entry which should have its comments and responses printed.
	 *
	 * @param service a YouTubeService object
	 * @param feedUrl the url of the video feed to print
	 * @param showCommentsAndResponses true if the user should be prompted for
	 *                                 a video whose comments and responses should
	 *                                 printed
	 * @throws ServiceException
	 *                     If the service is unable to handle the request.
	 * @throws IOException error sending request or reading the feed.
	 */
	private static void printVideoFeed(VideoFeed videoFeed) throws IOException, ServiceException {
		// printUnderlined(title);
		List<VideoEntry> videoEntries = videoFeed.getEntries();
		if (videoEntries.size() == 0) {
			System.out.println("This feed contains no entries.");
			return;
		}
		int count = 1;
		for (VideoEntry ve : videoEntries) {
			printVideoEntry("(Video #" + String.valueOf(count) + ")", ve, false);
			count++;
		}

		System.out.println();
	}

	public static String XmlEntries(String prefix, VideoEntry videoEntry){
		String entryText = "<prefix>"+prefix+"</prefix>";

		if (videoEntry.getTitle() != null) {
			entryText += "<title>";
			entryText += "Title: "+ videoEntry.getTitle().getPlainText();
			entryText += "</title>";
		}
		if (videoEntry.getSummary() != null) {
			entryText += "<summary>";
			entryText += "Summary: "+videoEntry.getSummary().getPlainText();
			entryText += "</summary>";
		}
		YouTubeMediaGroup mediaGroup = videoEntry.getMediaGroup();
		if(mediaGroup != null) {
			MediaPlayer mediaPlayer = mediaGroup.getPlayer();
			entryText += "<url>";
			entryText += mediaPlayer.getUrl();
			entryText += "</url>";
			MediaKeywords keywords = mediaGroup.getKeywords();
			entryText += "<keywords>";
			for(String keyword : keywords.getKeywords()) {
				entryText += keyword + ",";
			}
			entryText += "</keywords>";
			entryText += "<thumbnails>";
			for(MediaThumbnail mediaThumbnail : mediaGroup.getThumbnails()) {
				entryText += "<url>" + mediaThumbnail.getUrl() + "</url>";
				entryText += "<time_index>" + mediaThumbnail.getTime() + "<time_index>";
			}
			entryText += "<media>";
			for(YouTubeMediaContent mediaContent : mediaGroup.getYouTubeContents()) {
				entryText += "<media_location>"+mediaContent.getUrl()+"</media_location>";
				entryText += "<media_type>"+mediaContent.getType()+"</media_type>";
				entryText += "<duration>" + mediaContent.getDuration() + "</duration>";
			}
		}
		return entryText;
	}

	public static String FeedToXml(VideoFeed videoFeed){
		String Xml = "<video_feed>";
		List<VideoEntry> videoEntries = videoFeed.getEntries();

		// In case videoFeed is empty
		if (videoEntries.size() == 0) {
			return Xml;
		}
		int count = 1;
		for(VideoEntry ve : videoEntries){
			Xml += "<entry>";
			Xml += XmlEntries("(Video #" + String.valueOf(count) + ")", ve);
			Xml += "</entry>";
			count++;
		}
		Xml += "</video_feed>";

		return Xml;

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
			//printVideoFeed(videoFeed);
		}catch(Exception e){
		}
	}
}