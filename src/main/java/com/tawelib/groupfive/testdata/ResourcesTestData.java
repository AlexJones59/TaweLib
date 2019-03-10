package com.tawelib.groupfive.testdata;

import com.github.javafaker.Faker;
import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.repository.ResourceRepository;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Generates resources for testing. These are fictional.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
class ResourcesTestData {

  private static final int NUMBER_OF_RESOURCES = 32;
  private static final String[] LANGUAGES = {
      "English",
      "Czech",
      "Bulgarian",
      "Russian",
      "French",
      "German",
      "Latvian"
  };
  private static final String[] OPERATING_SYSTEMS = {
      "Pop!_OS 18.10",
      "Ubuntu 18.04 LTS",
      "Debian 9",
      "Windows 10 Professional",
      "Windows 7 Ultimate"
  };
  private static final String[] LAPTOP_MANUFACTURERS = {
      "Dell",
      "Lenovo",
      "Acer",
      "Asus",
      "Alienware",
      "Razer",
      "HP",
      "Microsoft",
      "MSI",
      "Samsung",

  };

  private static final Random random = new Random();

  private ResourcesTestData() {
    throw new UnsupportedOperationException("Util class.");
  }

  /**
   * Generates random resources for the library.
   *
   * @param library Library.
   */
  static void generate(Library library) {
    ResourceRepository resourceRepository = library.getResourceRepository();

    Faker faker = new Faker();

    for (int i = 0; i < NUMBER_OF_RESOURCES; i++) {
      Date bookDate = faker.date().past(25 * 365, TimeUnit.DAYS);
      Date dvdDate = faker.date().past(6 * 365, TimeUnit.DAYS);
      Date laptopDate = faker.date().past(3 * 365, TimeUnit.DAYS);

      resourceRepository.add(
          new Book(
              faker.book().title(),
              bookDate.toInstant().atZone(ZoneId.systemDefault()).getYear(),
              null,
              faker.book().author(),
              faker.book().publisher(),
              faker.book().genre(),
              faker.code().isbn10(),
              randomFrom(LANGUAGES)
          )
      );

      resourceRepository.add(
          new Dvd(
              faker.book().title(),
              dvdDate.toInstant().atZone(ZoneId.systemDefault()).getYear(),
              null,
              faker.name().fullName(),
              random.nextInt(100) + 50,
              randomLanguages(),
              randomLanguages()
          )
      );

      resourceRepository.add(
          new Laptop(
              faker.ancient().god(),
              laptopDate.toInstant().atZone(ZoneId.systemDefault()).getYear(),
              null,
              randomFrom(LAPTOP_MANUFACTURERS),
              faker.regexify("[A-Z]{1,3}-[1-9][0-9]?"),
              randomFrom(OPERATING_SYSTEMS)
          )
      );

      //TODO: Populate Games.
    }
  }

  /**
   * Generates a random list of languages.
   *
   * @return Random languages.
   */
  private static ArrayList<String> randomLanguages() {
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

  /**
   * Returns a random element from the provided array.
   *
   * @param array Source.
   * @return Random element.
   */
  private static String randomFrom(String[] array) {
    int index = random.nextInt(array.length);

    return array[index];
  }
}
