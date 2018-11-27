package com.tawelib.groupfive.entity;

/**
 * ResourceType is an Enum to differentiate between different types of resources.
 *
 * @author Shree Desai
 * @version 0.1
 */

public enum ResourceType { BOOK(28), DVD(14), LAPTOP(2);
  private int loanDuration;

  private ResourceType(int value) {
    this.loanDuration = value;
  }

  public int getLoanDuration() {
    return loanDuration;
  }
}



