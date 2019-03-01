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
  private String ratedResourceId;
  private String raterId;
  private LocalDateTime dateRated;

  /**
   * Instantiates a new Rating.
   *
   * @param value the rating out of 5
   * @param ratedResourceId the rated resource
   * @param raterId the rater
   */
  public Rating(int value, String ratedResourceId, String raterId) {
    this.value = value;
    this.ratedResourceId = ratedResourceId;
    this.raterId = raterId;
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
    return ratedResourceId;
  }

  /**
   * Gets rater.
   *
   * @return reviewer
   */
  public String getRater() {
    return raterId;
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
