package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.runtime.SimulatedClock;

/**
 * Generates test and showcase data for the library..
 *
 * @author Petr Hoffmann
 * @version 1.0
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
    // Simulated start of the library (10/01/2018)
    SimulatedClock.addSeconds(1515574000);

    UsersTestData.generate(library);
    ResourcesTestData.generate(library);
    CopiesTestData.generate(library);
    OperationsTestData.generate(library);
  }
}
