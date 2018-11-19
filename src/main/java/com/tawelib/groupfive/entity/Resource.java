package com.tawelib.groupfive.entity;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * File name: User.java
 *    User class stores personal information for the account holder (either the Customer
 *    or Librarian).
 * @author Shree Desai
 * @version 0.2
 */
public abstract class Resource implements Serializable {

  private String id;
  private String title;
  private int year;
  private Image thumbnailImage;

}
