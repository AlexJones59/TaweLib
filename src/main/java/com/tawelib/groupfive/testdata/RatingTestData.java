package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Review;
import com.tawelib.groupfive.manager.RatingManager;

/**
 * Generates test and showcase data for ratings and reviews.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
class RatingTestData extends BaseTestData {

  private static final double REVIEW_RATIO = 0.5;

  /**
   * Generates a random rating or a review.
   *
   * @param library Library.
   */
  static void rate(Library library) {
    if (random.nextDouble() < REVIEW_RATIO) {
      RatingManager.createRating(
          library,
          new Review(
              random.nextInt(4) + 1,
              getRandomResource(library),
              getRandomCustomer(library),
              faker.lorem().paragraph()
          )
      );
    } else {
      RatingManager.createRating(
          library,
          new Rating(
              random.nextInt(4) + 1,
              getRandomResource(library),
              getRandomCustomer(library)
          )
      );
    }
  }
}
