package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.manager.CopyManager;
import java.util.Random;

/**
 * Generates test data for copies.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
class CopiesTestData {

  private static final int MIN_NUMBER_OF_COPIES = 3;
  private static final int MAX_NUMBER_OF_COPIES = 7;
  private static Random random = new Random();

  private CopiesTestData() {
    throw new UnsupportedOperationException();
  }

  /**
   * Generated a random number of copies for each resource in the library.
   *
   * @param library Library.
   */
  static void generate(Library library) {
    for (Resource resource : library.getResourceRepository().getAll()) {
      int copiesToGenerate = random.nextInt(
          MAX_NUMBER_OF_COPIES - MIN_NUMBER_OF_COPIES
      ) + MIN_NUMBER_OF_COPIES;

      for (int i = 0; i < copiesToGenerate; i++) {
        CopyManager.createResourceCopy(library, resource);
      }
    }
  }
}
