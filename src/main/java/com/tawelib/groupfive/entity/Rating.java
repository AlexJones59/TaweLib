package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
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
  private Resource ratedResource;
  private Customer rater;
  private LocalDateTime dateRated;

  /**
   * Instantiates a new Rating.
   *
   * @param value the rating out of 5
   * @param ratedResource the rated resource
   * @param rater the rater
   */
  public Rating(int value, Resource ratedResource, Customer rater) {
    if (value < 1 || value > 5) {
      throw new IllegalArgumentException("Rating value has to be between 1 and 5.");
    }

    this.value = value;
    this.ratedResource = ratedResource;
    this.rater = rater;
    this.dateRated = SimulatedLocalDateTime.now();
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
  public Resource getRatedResource() {
    return ratedResource;
  }

  /**
   * Gets rater.
   *
   * @return reviewer
   */
  public User getRater() {
    return rater;
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
