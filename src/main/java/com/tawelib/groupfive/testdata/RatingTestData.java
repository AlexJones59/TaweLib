package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;
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
  private static final double RESOURCE_RATING_WEIGHT = 5.0;
  private static final double CUSTOMER_RATING_WEIGHT = 1.0;
  private static final double RATING_MULTIPLIER = 5.0;
  private static final double TOTAL_RATING_WEIGHT = RESOURCE_RATING_WEIGHT + CUSTOMER_RATING_WEIGHT;

  /**
   * Generates a random* rating or a review.
   *
   * <p>
   * Uses hash functions with the resource and the customer as inputs to simulate both certain
   * resources to be more or less popular as well as certain users giving different ratings on
   * average.
   * </p>
   *
   * @param library Library.
   */
  static void rate(Library library) {
    Customer customer = getRandomCustomer(library);
    Resource resource = getRandomResource(library);

    double customerFlatHash = flatifyDoubleHash(customer.hashCode());
    double resourceFlatHash = flatifyDoubleHash(resource.hashCode());

    double resourceFactor = resourceFlatHash * RESOURCE_RATING_WEIGHT / TOTAL_RATING_WEIGHT;
    double customerFactor = customerFlatHash * CUSTOMER_RATING_WEIGHT / TOTAL_RATING_WEIGHT;

    double rawRating = (resourceFactor + customerFactor) * RATING_MULTIPLIER + 1;

    rawRating = Math.min(rawRating, 5);
    rawRating = Math.max(rawRating, 1);

    int rating = (int) rawRating;

    if (random.nextDouble() < REVIEW_RATIO) {
      RatingManager.createRating(
          library,
          new Review(
              rating,
              resource,
              customer,
              faker.lorem().paragraph()
          )
      );
    } else {
      RatingManager.createRating(
          library,
          new Rating(
              rating,
              resource,
              customer
          )
      );
    }
  }

  /**
   * Flattens an integer hashcode value to a double between 0 and 1.
   *
   * @param input Integer hashcode value.
   * @return Double hashcode between 0 and 1.
   */
  private static double flatifyDoubleHash(int input) {
    if (input < 0) {
      input *= -1;
    }

    return Double.parseDouble(
        String.format(
            "0.%d",
            input
        )
    );
  }
}
