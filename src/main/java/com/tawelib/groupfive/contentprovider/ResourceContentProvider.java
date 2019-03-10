package com.tawelib.groupfive.contentprovider;

import com.tawelib.groupfive.exception.ContentProviderException;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides trailers for resources by scraping a video provider website.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
class ResourceContentProvider {

  private static final String SCRAPE_URL_TEMPLATE =
      "https://www.youtube.com/results?search_query=%s";

  private static final String YOUTUBE_VIDEO_LINK_PATTERN = "href=\"/watch\\?v=([a-zA-Z0-9]*)\"";
  private static final String YOUTUBE_EMBED_LINK_TEMPLATE = "https://www.youtube.com/embed/%s";

  private ResourceContentProvider() {
    throw new UnsupportedOperationException();
  }

  /**
   * Wraps the trailer fetching functionality. Returns the URL of the trailer.
   *
   * @param resourceName Resource name.
   * @return URL address.
   * @throws IOException When encountering networking issues.
   * @throws ContentProviderException When unable to scrape the trailer.
   */
  static String fetch(String resourceName) throws IOException, ContentProviderException {
    String requestUrl = String.format(
        SCRAPE_URL_TEMPLATE,
        URLEncoder.encode(
            resourceName,
            StandardCharsets.UTF_8
        )
    );

    String websiteContent = fetchUrl(requestUrl);

    String extractedLink = extractFirstYoutubeVideoLink(websiteContent);

    return String.format(
        YOUTUBE_EMBED_LINK_TEMPLATE,
        extractedLink
    );
  }

  /**
   * Downloads a website's content by URL.
   *
   * @param requestUrl Website URL.
   * @return Website content.
   * @throws IOException When encountering networking issues.
   */
  private static String fetchUrl(String requestUrl) throws IOException {

    URL url = new URL(requestUrl);

    Scanner scanner = new Scanner(
        url.openStream(),
        StandardCharsets.UTF_8.toString()
    );

    scanner.useDelimiter("\\A"); //Denotes the end of the file
    return scanner.hasNext() ? scanner.next() : "";
  }

  /**
   * Extracts the first youtube video link from the provided website content.
   *
   * @param websiteContent Website content.
   * @return Youtube trailer URL.
   * @throws ContentProviderException When unable to scrape the trailer.
   */
  private static String extractFirstYoutubeVideoLink(String websiteContent)
      throws ContentProviderException {
    Pattern pattern = Pattern.compile(YOUTUBE_VIDEO_LINK_PATTERN);
    Matcher matcher = pattern.matcher(websiteContent);

    if (!matcher.find()) {
      throw new ContentProviderException("Unable to obtain trailer from the provider.");
    }

    return matcher.group(1);
  }
}
