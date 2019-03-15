package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Game;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.repository.ResourceRepository;

class HardcodedResourcesTestData extends BaseTestData {

  /**
   * Creates hardcoded resources test data.
   */
  private HardcodedResourcesTestData() {
    throw new UnsupportedOperationException();
  }

  private static final Book[] HARDCODED_BOOKS = {
      new Book(
          "Lord of the Flies",
          1954,
          null,
          "William Golding",
          "Faber and Faber",
          "Novel",
          "0-571-05686-5",
          "English"
      ),
      new Book(
          "Animal Farm",
          1945,
          null,
          "George Orwell",
          "Secker and Warburg",
          "Satire",
          "9789587470345",
          "English"
      ),
      new Book(
          "The Great Gatsby",
          1925,
          null,
          "F. Scott Fitzgerald",
          "Charles Scribner's Sons",
          "Novel",
          "9781471229015",
          "English"
      )
  };
  private static final Dvd[] HARDCODED_DVDS = {
      new Dvd(
          "Avatar",
          2009,
          null,
          "James Cameron",
          162,
          randomLanguages(),
          randomLanguages()
      ),
      new Dvd(
          "Shutter Island",
          2010,
          null,
          "Martin Scorsese",
          139,
          randomLanguages(),
          randomLanguages()
      ),
      new Dvd(
          "Inception",
          2010,
          null,
          "Christopher Nolan",
          148,
          randomLanguages(),
          randomLanguages()
      )
  };
  private static final Laptop[] HARDCODED_LAPTOPS = {
      new Laptop(
          "X1 Carbon",
          2018,
          null,
          "Lenovo",
          "X1",
          "Windows 10 Pro"
      ),
      new Laptop(
          "XPS 15",
          2018,
          null,
          "Dell",
          "9570",
          "Windows 10 Pro"
      ),
      new Laptop(
          "Macbook Pro",
          2018,
          null,
          "Apple",
          "2018",
          "MacOS"
      )
  };
  private static final Game[] HARDCODED_GAMES = {
      new Game(
          "Deus Ex: Human Revolution",
          2011,
          null,
          "Square Enix",
          "RPG",
          "Pegi 18",
          false
      ),
      new Game(
          "Minecraft",
          2009,
          null,
          "Mojang",
          "Sandbox",
          "PEGI 12",
          true
      ),
      new Game(
          "Counter Strike: Global Offensive",
          2012,
          null,
          "Valve",
          "FPS",
          "Pegi 18",
          true
      )
  };

  /**
   * Creates hardcoded resources test data.
   *
   * @param resourceRepository Resource repository.
   */
  static void create(ResourceRepository resourceRepository) {
    addFromArray(resourceRepository, HARDCODED_BOOKS);
    addFromArray(resourceRepository, HARDCODED_DVDS);
    addFromArray(resourceRepository, HARDCODED_LAPTOPS);
    addFromArray(resourceRepository, HARDCODED_GAMES);
  }

  /**
   * Adds resources to the resource repository from an array.
   *
   * @param resourceRepository Resource repository.
   * @param resources Resources.
   */
  private static void addFromArray(ResourceRepository resourceRepository, Resource[] resources) {
    for (Resource resource : resources) {
      resourceRepository.add(resource);
    }
  }
}
