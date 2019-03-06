package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;

/**
 * RatingManager.java - Manager class handles logic behind retrieving ratings and reviews.
 *
 * @author Alex Jones
 * @version 1.0
 */
public class RatingManager {

  /**
   * Creates a rating and persists it to the repository.
   *
   * @param library the library
   * @param rating the rating
   *
   */
  public static void createRating(Library library, Rating rating) {
    library.getRatingRepository().add(rating);
  }

  /**
   * Checks if the customer trying to leave a rating for a resource has leased the
   * resource in the past and has not previously left a rating for the resource.
   *
   * @param library The library.
   * @param customer The Customer.
   * @param resource The Resource.
   * @return True if the customer can leave a rating for the resource.
   */
  public static boolean validRater(Library library, Customer customer, Resource resource) {
    boolean resourceLeased = false;
    boolean customerNotRated = true;

    for (Rating rating : library.getRatingRepository()
        .getResourcesRatings(resource.getResourceId())) {
      if (rating.getRater().equals(customer.getUsername())) {
        customerNotRated = false;
      }
    }

    for (Lease lease : library.getLeaseRepository()
        .getCustomerLeaseHistory(customer)) {
      if (lease.getBorrowedCopy().getResource() == resource) {
        resourceLeased = true;
      }
    }

    return resourceLeased && customerNotRated;
  }


}