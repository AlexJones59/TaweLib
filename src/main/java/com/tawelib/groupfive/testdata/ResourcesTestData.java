package com.tawelib.groupfive.testdata;

import com.github.javafaker.Faker;
import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Game;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.repository.ResourceRepository;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Generates resources for testing. These are fictional.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
class ResourcesTestData extends BaseTestData {

  private static final int NUMBER_OF_RESOURCES = 128;
  
  private static final int NUMBER_OF_RESOURCE_TYPES = 4;

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
  private static final String[] GAME_GENRES = {
      "Strategy",
      "Action",
      "FPS",
      "RTS",
      "Platform",
      "Adventure",
      "RPG",
      "Sports",
      "VR"
  };
  private static final String[] GAME_RATINGS = {
      "PEGI 3",
      "PEGI 6",
      "PEGI 12",
      "PEGI 15",
      "PEGI 18"
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

    HardcodedResourcesTestData.create(resourceRepository);

    Faker faker = new Faker();

    for (int i = 0; i < NUMBER_OF_RESOURCES; i++) {

      switch (random.nextInt(NUMBER_OF_RESOURCE_TYPES)) {
        case 0:
          Date bookDate = faker.date().past(25 * 365, TimeUnit.DAYS);
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
          break;
        case 1:
          Date dvdDate = faker.date().past(6 * 365, TimeUnit.DAYS);
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
          break;
        case 2:
          Date laptopDate = faker.date().past(3 * 365, TimeUnit.DAYS);
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
          break;
        case 3:
          Date gameDate = faker.date().past(4 * 365, TimeUnit.DAYS);
          resourceRepository.add(
              new Game(
                  faker.app().name(),
                  gameDate.toInstant().atZone(ZoneId.systemDefault()).getYear(),
                  null,
                  faker.app().author(),
                  randomFrom(GAME_GENRES),
                  randomFrom(GAME_RATINGS),
                  random.nextBoolean()
              )
          );
          break;
        default:
          break;
      }
    }
  }
}
