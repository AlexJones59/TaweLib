package com.tawelib.groupfive.contentprovider;

import com.tawelib.groupfive.exception.ContentProviderException;
import java.io.IOException;

/**
 * Provides content for resources in the library.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class ContentProvider {

  private ContentProvider() {
    throw new UnsupportedOperationException();
  }

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
