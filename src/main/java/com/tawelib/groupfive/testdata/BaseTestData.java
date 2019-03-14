package com.tawelib.groupfive.testdata;

import com.github.javafaker.Faker;
import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;
import java.util.List;
import java.util.Random;

/**
 * Contains reusable functions for classes generating test and showcase data.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
abstract class BaseTestData {

  BaseTestData() {
    throw new UnsupportedOperationException();
  }

  static final Random random = new Random();
  static final Faker faker = new Faker();

  /**
   * Returns a random customer username.
   *
   * @param library Library.
   * @return Username.
   */
  static Customer getRandomCustomer(Library library) {
    List<Customer> customers = library.getCustomerRepository().getAll();

    int randomIndex = random.nextInt(customers.size());

    return customers.get(randomIndex);
  }

  /**
   * Returns a random customer username.
   *
   * @param library Library.
   * @return Username.
   */
  static String getRandomCustomerUsername(Library library) {
    return getRandomCustomer(library).getUsername();
  }

  /**
   * Returns a random copy id of a random resource.
   *
   * @param library Library.
   * @return ID.
   */
  static String getRandomCopyId(Library library) {
    List<Copy> copies = library.getCopyRepository().getAll();

    int randomIndex = random.nextInt(copies.size());

    return copies.get(randomIndex).getId();
  }

  /**
   * Returns a random resource.
   *
   * @param library Library.
   * @return ID.
   */
  static Resource getRandomResource(Library library) {
    List<Resource> copies = library.getResourceRepository().getAll();

    int randomIndex = random.nextInt(copies.size());

    return copies.get(randomIndex);
  }
}
