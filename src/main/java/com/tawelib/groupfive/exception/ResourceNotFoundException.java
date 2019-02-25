package com.tawelib.groupfive.exception;

/**
 * Requested resource cannot be found.
 */
public class ResourceNotFoundException extends Exception {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
