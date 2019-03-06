package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.runtime.SimulatedClock;

/**
 * Entity Test Data Class is used to populate the library for demonstration purposes.
 *
 * @author Petr Hoffmann
 */
public class EntityTestData {

  private EntityTestData() {
    throw new UnsupportedOperationException();
  }

  /**
   * Populates the Library with test data.
   *
   * @param library Library.
   */
  public static void populateLibrary(Library library) {
    SimulatedClock.addTime(1515596400);

    UsersTestData.generate(library);
    ResourcesTestData.generate(library);
  }
}
