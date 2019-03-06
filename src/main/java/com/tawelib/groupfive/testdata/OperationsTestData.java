package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.exception.CopyUnavailableException;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.runtime.SimulatedClock;
import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class OperationsTestData {

  private OperationsTestData() {
    throw new UnsupportedOperationException();
  }

  private static final Random random = new Random();
  private static List<String> borrowedCopyIds = new ArrayList<>();

  /**
   * Generates operations test data.
   *
   * @param library Library.
   */
  static void generate(Library library) {
    final int targetNumberOfLeases = library.getCopyRepository().getAll().size();

    int missesCounter = 0;

    while (SimulatedLocalDateTime.now().isBefore(LocalDateTime.now())) {
      if (!borrowedCopyIds.isEmpty()
          && random.nextInt(borrowedCopyIds.size()) > targetNumberOfLeases / 2) {
        int randomIndex = random.nextInt(borrowedCopyIds.size());

        CopyManager.returnResourceCopy(
            library,
            borrowedCopyIds.get(randomIndex)
        );

        borrowedCopyIds.remove(randomIndex);
      } else {
        String copyIdToBeBorrowed = getRandomCopyId(library);

        try {
          CopyManager.borrowResourceCopy(
              library,
              copyIdToBeBorrowed,
              getRandomCustomerUsername(library)
          );

          borrowedCopyIds.add(copyIdToBeBorrowed);
        } catch (CopyUnavailableException e) {
          missesCounter++;
        }
      }

      SimulatedClock.addMinutes(random.nextInt(60));
    }

    System.out.println("Misses: " + missesCounter);
  }

  private static String getRandomCustomerUsername(Library library) {
    List<Customer> customers = library.getCustomerRepository().getAll();

    int randomIndex = random.nextInt(customers.size());

    return customers.get(randomIndex).getUsername();
  }

  private static String getRandomCopyId(Library library) {
    List<Copy> copies = library.getCopyRepository().getAll();

    int randomIndex = random.nextInt(copies.size());

    return copies.get(randomIndex).getId();
  }
}
