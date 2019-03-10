package com.tawelib.groupfive.exception;

/**
 * This exception is thrown when it was not possible to provide media content for a given Resource.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class ContentProviderException extends Exception {

  public ContentProviderException(String message) {
    super(message);
  }
}
