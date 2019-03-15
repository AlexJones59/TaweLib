package com.tawelib.groupfive.testdata;

import com.github.javafaker.Faker;
import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Contains reusable functions for classes generating test and showcase data.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
abstract class BaseTestData {

  static final String[] LANGUAGES = {
      "English",
      "Czech",
      "Bulgarian",
      "Russian",
      "French",
      "German",
      "Latvian"
  };

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

  /**
   * Returns a random element from the provided array.
   *
   * @param array Source.
   * @return Random element.
   */
  static String randomFrom(String[] array) {
    int index = random.nextInt(array.length);

    return array[index];
  }

  /**
   * Generates a random list of languages.
   *
   * @return Random languages.
   */
  static ArrayList<String> randomLanguages() {
    ArrayList<String> languages = new ArrayList<>();

    for (String language : LANGUAGES) {
      if (random.nextBoolean()) {
        languages.add(language);
      }
    }

    if (languages.isEmpty()) {
      languages.add(LANGUAGES[0]);
    }

    return languages;
  }
}
