package com.tawelib.groupfive.exception;

public class UnsupportedSystemException extends RuntimeException {

  public UnsupportedSystemException(String message) {
    super(message);
  }

  public UnsupportedSystemException(Exception e) {
    super(e);
  }
}
