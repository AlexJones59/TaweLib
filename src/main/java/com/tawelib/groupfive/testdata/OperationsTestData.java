package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Library;
import java.util.LinkedList;
import java.util.List;

class OperationsTestData {

  private OperationsTestData() {
    throw new UnsupportedOperationException();
  }

  private static List<String> borrowedCopyIds = new LinkedList<>();

  /**
   * Generates operations test data.
   *
   * @param library Library.
   */
  static void generate(Library library) {
    // operation choice: the more copies borrowed, the higher chance of return

    //
  }
}
