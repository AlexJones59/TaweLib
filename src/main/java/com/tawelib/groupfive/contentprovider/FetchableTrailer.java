package com.tawelib.groupfive.contentprovider;

/**
 * Ensures that the implementing entity can be used to fetch trailers/previews for it.
 */
public interface FetchableTrailer {

  String getTrailerSearchQuery();
}
