package com.tawelib.groupfive.contentprovider;

/**
 * Ensures that the implementing entity can be used to fetch trailers/previews for it.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public interface FetchableTrailer {

  String getTrailerSearchQuery();
}
