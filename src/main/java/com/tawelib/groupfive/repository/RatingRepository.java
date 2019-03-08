package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * RatingRepository.java The rating repository class that handles all ratings and reviews
 *
 * @author Alex Jones
 * @version 1.0
 */
public class RatingRepository implements BaseRepository<Rating> {

  private ArrayList<Rating> ratings;

  /**
   * Instantiates a new RatingRepository.
   */
  public RatingRepository() {
    ratings = new ArrayList<>();
  }

  /**
   * Returns all ratings for a particular resource.
   *
   * @param resource resource being searched for
   * @return List of all ratings for resource
   */
  public List<Rating> getResourcesRatings(Resource resource) {
    ArrayList<Rating> results = new ArrayList<>();

    for (Rating rating : ratings) {
      if (rating.getRatedResource() == resource) {
        results.add(rating);
      }
    }

    return results;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Rating> getAll() {
    return ratings;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Rating rating) {
    if (!ratings.contains(rating)) {
      ratings.add(rating);
    }
  }
}
