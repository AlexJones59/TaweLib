package com.tawelib.groupfive.exception;

/**
 * This exception is thrown when it is not possible to verify activation of the
 * software product.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class UnsupportedSystemException extends RuntimeException {

  /**
   * Thrown when unable to verify product activation.
   *
   * @param message Exception message.
   */
  public UnsupportedSystemException(String message) {
    super(message);
  }

  /**
   * Thrown when unable to verify product activation.
   *
   * @param e Previous exception.
   */
  public UnsupportedSystemException(Exception e) {
    super(e);
  }
}
