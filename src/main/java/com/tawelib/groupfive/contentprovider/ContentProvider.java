package com.tawelib.groupfive.contentprovider;

import com.tawelib.groupfive.exception.ContentProviderException;
import java.io.IOException;

public class ContentProvider {

  /**
   * Returns URL address for the trailer player.
   *
   * @param resourceWithFetchableTrailer name of the resource to be fetched
   * @return URL address
   * @throws ContentProviderException when unable to find any relevant content
   * @throws IOException when cannot connect to the media provider
   */
  public static String fetchContent(FetchableTrailer resourceWithFetchableTrailer)
      throws ContentProviderException, IOException {

    return ResourceContentProvider.fetch(resourceWithFetchableTrailer.getTrailerSearchQuery());
  }
}
