package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Library;
import org.junit.Test;

public class EntityTestDataTest {

  @Test
  public void test1() {
    Library library = new Library("Test Library");

    EntityTestData.populateLibrary(library);

    System.out.println("Library generated successfully.");
  }
}
