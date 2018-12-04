package com.tawelib.groupfive.entity;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * File name: Resource.java Resource class saves all  info pertaining to a
 * resource.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Resource implements Serializable {

  private String resourceId;
  private String title;
  private int year;
  private Image thumbnailImage;
  @Deprecated
  private ResourceType type;

  /**
   * Instantiates a new Resource.
   *
   * @param title          the title
   * @param year           the year
   * @param thumbnailImage the thumbnail image
   * @param type           the type
   */
  @Deprecated
  public Resource(String title, int year, Image thumbnailImage,
                  ResourceType type) {
    this.title = title;
    this.year = year;
    this.thumbnailImage = thumbnailImage;
    this.type = type;
  }

  /**
   * Instantiates a new Resource.
   *
   * @param title          the title
   * @param year           the year
   * @param thumbnailImage the thumbnail image
   */
  public Resource(String title, int year, Image thumbnailImage) {
    this.title = title;
    this.year = year;
    this.thumbnailImage = thumbnailImage;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getResourceId() {
    return resourceId;
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
  @Deprecated
  public ResourceType getType() {
    return type;
  }

  /**
   * Sets type.
   *
   * @param type the type
   */
  @Deprecated
  public void setType(ResourceType type) {
    this.type = type;
  }

}
