package com.tawelib.groupfive.contentprovider;

import com.tawelib.groupfive.exception.ContentProviderException;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ResourceContentProvider {

  private static final String SCRAPE_URL_TEMPLATE =
      "https://www.youtube.com/results?search_query=%s";

  private static final String YOUTUBE_VIDEO_LINK_PATTERN = "href=\"\\/watch\\?v=([a-zA-Z0-9]*)\"";
  private static final String YOUTUBE_EMBED_CODE = "<iframe src=\"https://www.youtube.com/embed/%s\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";

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
        YOUTUBE_EMBED_CODE,
        extractedLink
    );
  }

  protected static String fetchUrl(String requestUrl) throws IOException {

    URL url = new URL(requestUrl);
    Scanner scanner = new Scanner(
        url.openStream(),
        StandardCharsets.UTF_8.toString()
    );

    scanner.useDelimiter("\\A");
    return scanner.hasNext() ? scanner.next() : "";
  }

  private static String extractFirstYoutubeVideoLink(String websiteContent)
      throws ContentProviderException {
    Pattern pattern = Pattern.compile(YOUTUBE_VIDEO_LINK_PATTERN);
    Matcher matcher = pattern.matcher(websiteContent);

    if (!matcher.find()) {
      throw new ContentProviderException();
    }

    return matcher.group(1);
  }
}
