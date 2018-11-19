package com.tawelib.groupfive.entity;

import java.io.Serializable;

public class Library implements Serializable {

  /**
   * The name of the library.
   */
  private String name;

  /**
   * Creates a new library with a given name.
   *
   * @param name The name of the library.
   */
  public Library(String name) {
    this.name = name;
  }

  /**
   * Retrieves the name of the library.
   *
   * @return The name of the library.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the library.
   *
   * @param name New name of the library.
   */
  public void setName(String name) {
    this.name = name;
  }
}
