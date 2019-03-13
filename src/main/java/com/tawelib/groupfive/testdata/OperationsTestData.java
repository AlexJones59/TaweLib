package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.exception.CopyUnavailableException;
import com.tawelib.groupfive.exception.OverResourceCapException;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.runtime.SimulatedClock;
import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates test and showcase data for user interactions.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
class OperationsTestData {

  private OperationsTestData() {
    throw new UnsupportedOperationException();
  }

  private static final double IDEAL_LEASES_CONCENTRATION = 0.5;
  private static final Random random = new Random();
  private static List<String> borrowedCopyIds = new ArrayList<>();

  private static int missesCounter = 0;
  private static int borrowsCounter = 0;
  private static int returnsCounter = 0;

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
    // TODO: Add more operations?
    while (SimulatedLocalDateTime.now().isBefore(LocalDateTime.now())) {
      if (!borrowedCopyIds.isEmpty()
          && random.nextInt(borrowedCopyIds.size()) > targetNumberOfLeases / 2) {
        simulateReturn(library);
      } else {
        simulateLoan(library);
      }

      SimulatedClock.addMinutes(random.nextInt(30));
    }

    System.out.println("Borrows: " + borrowsCounter);
    System.out.println("Returns: " + returnsCounter);
    System.out.println("Misses: " + missesCounter);
  }

  private static void simulateLoan(Library library) {
    String copyIdToBeBorrowed = getRandomCopyId(library);

    try {
      CopyManager.borrowResourceCopy(
          library,
          copyIdToBeBorrowed,
          getRandomCustomerUsername(library)
      );

      borrowedCopyIds.add(copyIdToBeBorrowed);

      borrowsCounter++;
    } catch (CopyUnavailableException e) {
      missesCounter++;
    } catch (OverResourceCapException e) {
      //Return something
    }
  }

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
   * Returns a random customer username.
   *
   * @param library Library.
   * @return Username.
   */
  private static String getRandomCustomerUsername(Library library) {
    List<Customer> customers = library.getCustomerRepository().getAll();

    int randomIndex = random.nextInt(customers.size());

    return customers.get(randomIndex).getUsername();
  }

  /**
   * Returns a random copy id of a random resource.
   *
   * @param library Library.
   * @return ID.
   */
  private static String getRandomCopyId(Library library) {
    List<Copy> copies = library.getCopyRepository().getAll();

    int randomIndex = random.nextInt(copies.size());

    return copies.get(randomIndex).getId();
  }
}
