package com.tawelib.groupfive.exception;

/**
 * Requested resource cannot be found in the system.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class ResourceNotFoundException extends Exception {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
