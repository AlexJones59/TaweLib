package com.tawelib.groupfive.entity;

import java.io.Serializable;
import javafx.scene.image.Image;

/**
 * File name: Resource.java Resource class saves all  info pertaining to a resource.
 *
 * @author Shree Desai
 * @version 0.2
 */
public abstract class Resource implements Serializable {

  private String id;
  private String title;
  private int year;
  private Image thumbnailImage;
  private ResourceType type;

  /**
   * Instantiates a new Resource.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param type the type
   */
  public Resource(String title, int year, Image thumbnailImage, ResourceType type) {
    this.title = title;
    this.year = year;
    this.thumbnailImage = thumbnailImage;
    this.type = type;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets title.
   *
   * @param title the title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets year.
   *
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * Sets year.
   *
   * @param year the year
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * Gets thumbnail image.
   *
   * @return the thumbnail image
   */
  public Image getThumbnailImage() {
    return thumbnailImage;
  }

  /**
   * Sets thumbnail image.
   *
   * @param thumbnailImage the thumbnail image
   */
  public void setThumbnailImage(Image thumbnailImage) {
    this.thumbnailImage = thumbnailImage;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public ResourceType getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  public void setType(ResourceType type) {
    this.type = type;
  }

  /**
   * Gets number of copies of the specific Resource available.
   */
  public int getNumberofCopiesAvailable() {
    //TODO: Once CopyRepository is made, need to call the method.
    return 0; //also needs to be changed.
  }
}
