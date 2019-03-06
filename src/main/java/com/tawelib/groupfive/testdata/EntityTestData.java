package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Library;

/**
 * Entity Test Data Class is used to populate the library for demonstration purposes.
 *
 * @author Nayeem Mohammed, Shree Desai
 */
public class EntityTestData {

  /**
   * Populates the Library with test data.
   *
   * @param library Library.
   */
  public static void populateLibrary(Library library) {
    UsersTestData.generate(library);
    ResourcesTestData.generate(library);
  }
}
