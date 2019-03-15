package com.tawelib.groupfive.entity;

/**
 * An average of all the ratings for a specific resource.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class AverageRating {

  private double rating;
  private Resource resource;

  /**
   * Instantiates a new Average rating.
   *
   * @param rating the rating
   * @param resource the resource
   */
  public AverageRating(double rating, Resource resource) {
    this.rating = rating;
    this.resource = resource;
  }

  /**
   * Gets rating.
   *
   * @return the rating
   */
  public double getRating() {
    return rating;
  }

  /**
   * Gets resource.
   *
   * @return the resource
   */
  public Resource getResource() {
    return resource;
  }
}
