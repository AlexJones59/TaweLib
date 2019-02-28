package com.tawelib.groupfive.entity;

/**
 * Review.java A review given to a resource, is-kind-of rating
 *
 * @author Alex Jones
 * @version 1.0
 */

public class Review extends Rating {

  private String reviewBody;

  /**
   * Instantiates a new Review.
   *
   * @param value the rating out of 5
   * @param ratedResource the rated resource
   * @param rater the rater
   * @param reviewBody the body of the review
   */
  public Review(int value, String ratedResource, String rater, String reviewBody) {
    super(value, ratedResource, rater);
    this.reviewBody = reviewBody;
  }

  /**
   * Gets review body.
   *
   * @return review body
   */
  public String getReviewBody() {
    return reviewBody;
  }
}
