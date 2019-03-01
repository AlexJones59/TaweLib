package com.tawelib.groupfive.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Rating.java A rating given to a resource by a customer that has previously loaned the resource.
 *
 * @author Alex Jones
 * @version 1.0
 */
public class Rating implements Serializable {

  private int value; //1-5 stars
  private String ratedResourceID;
  private String raterID;
  private LocalDateTime dateRated;

  /**
   * Instantiates a new Rating.
   *
   * @param value the rating out of 5
   * @param ratedResourceID the rated resource
   * @param raterID the rater
   */
  public Rating(int value, String ratedResourceID, String raterID) {
    this.value = value;
    this.ratedResourceID = ratedResourceID;
    this.raterID = raterID;
    this.dateRated = LocalDateTime.now();
  }


  /**
   * Gets rating value.
   *
   * @return rating value
   */
  public int getRatingValue() {
    return value;
  }

  /**
   * Gets rated resource.
   *
   * @return rated resource
   */
  public String getRatedResource() {
    return ratedResourceID;
  }

  /**
   * Gets rater.
   *
   * @return reviewer
   */
  public String getReviewer() {
    return raterID;
  }

  /**
   * Gets date of rating.
   *
   * @return LocalDateTime date of rating
   */
  public LocalDateTime getDateRated() {
    return dateRated;
  }
}
