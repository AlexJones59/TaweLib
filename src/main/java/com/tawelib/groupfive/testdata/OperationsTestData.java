package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.exception.CopyUnavailableException;
import com.tawelib.groupfive.exception.OverResourceCapException;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.runtime.SimulatedClock;
import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates test and showcase data for user interactions.
 *
 * @author Petr Hoffmann
 * @version 1.1
 */
class OperationsTestData extends BaseTestData {

  private static final double IDEAL_LEASES_CONCENTRATION = 0.2;
  private static final double LATE_RETURNS_CONCENTRATION = 0.075;
  private static final double RATING_REVIEW_CONCENTRATION = 0.25;
  private static final int TARGET_RETURN_DELAY = 5; // in days

  private static List<String> borrowedCopyIds = new ArrayList<>();
  private static List<String> borrowedCopyIdsForLateReturn = new ArrayList<>();

  private static int missesCounter = 0;
  private static int overCapCounter = 0;
  private static int borrowsCounter = 0;
  private static int returnsCounter = 0;
  private static int lateReturnsCounter = 0;

  /**
   * Generates operations test data. Simulates the users' behaviour by randomly deciding each next
   * action.
   *
   * @param library Library.
   */
  static void generate(Library library) {
    final int targetNumberOfLeases = (int) (library.getCopyRepository().getAll().size()
        * IDEAL_LEASES_CONCENTRATION);

    // While the simulated time hasn't caught up with the actual current time.
    while (SimulatedLocalDateTime.now().isBefore(LocalDateTime.now())) {

      if (!borrowedCopyIds.isEmpty()
          && random.nextInt(borrowedCopyIds.size() + borrowedCopyIdsForLateReturn.size())
          > targetNumberOfLeases / 2) {

        simulateLateReturn(library);
        simulateReturn(library);
      } else {
        simulateLoan(library);
      }

      if (random.nextDouble() < RATING_REVIEW_CONCENTRATION) {
        RatingTestData.rate(library);
      }

      SimulatedClock.addMinutes(random.nextInt(30));
    }

    System.out.println("Borrows: " + borrowsCounter);
    System.out.println("Returns: " + returnsCounter);
    System.out.println("Late Returns: " + lateReturnsCounter);
    System.out.println("Over Cap:" + overCapCounter);
    System.out.println("Misses: " + missesCounter);

    System.out.println("Operations Simulation finished.");
  }

  /**
   * Simulates a loan for a random copy of a random resource by a random customer.
   *
   * @param library Library.
   */
  private static void simulateLoan(Library library) {
    String copyIdToBeBorrowed = getRandomCopyId(library);
    String randomCustomerUsername = getRandomCustomerUsername(library);

    try {
      CopyManager.borrowResourceCopy(
          library,
          copyIdToBeBorrowed,
          randomCustomerUsername
      );

      if (random.nextDouble() < LATE_RETURNS_CONCENTRATION) {
        borrowedCopyIdsForLateReturn.add(copyIdToBeBorrowed);
      } else {
        borrowedCopyIds.add(copyIdToBeBorrowed);
      }

      borrowsCounter++;
    } catch (CopyUnavailableException e) {
      missesCounter++;
    } catch (OverResourceCapException e) {
      overCapCounter++;
    }
  }

  /**
   * Simulates a return of a random borrowed copy.
   *
   * @param library Library.
   */
  private static void simulateReturn(Library library) {
    int randomIndex = random.nextInt(borrowedCopyIds.size());

    CopyManager.returnResourceCopy(
        library,
        borrowedCopyIds.get(randomIndex)
    );

    borrowedCopyIds.remove(randomIndex);

    returnsCounter++;
  }

  /**
   * Simulates a late return of one of the copies designated to be returned late.
   *
   * @param library Library.
   */
  private static void simulateLateReturn(Library library) {
    if (!borrowedCopyIdsForLateReturn.isEmpty()) {
      int randomIndex = random.nextInt(borrowedCopyIdsForLateReturn.size());

      String copyId = borrowedCopyIdsForLateReturn.get(randomIndex);

      Copy copy = library.getCopyRepository().getSpecific(copyId);
      Lease currentLease = library.getLeaseRepository().getCopyCurrentLease(copy);

      LocalDateTime dueDate = currentLease.getDueDate();

      if (dueDate != null
          && dueDate.isAfter(SimulatedLocalDateTime.upToXDaysAgo(TARGET_RETURN_DELAY))
      ) {
        CopyManager.returnResourceCopy(
            library,
            copyId
        );

        borrowedCopyIdsForLateReturn.remove(randomIndex);

        lateReturnsCounter++;
      }
    }
  }
}
