package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.*;
import com.tawelib.groupfive.exception.ResourceNotFoundException;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * File Name - ResourceManager.java The Resource Manager class  handles controls data flow between
 * the Resource Repository and the GUI interfaces.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class ResourceManager {

  /**
   * Create new instance of a book resource and persists it to the resource repository.
   *
   * @param library the library
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param author the author
   * @param publisher the publisher
   * @param genre the genre
   * @param isbn the isbn
   * @param language the language
   */
  public static void createBook(Library library, String title, int year,
      Image thumbnailImage, String author, String publisher, String genre,
      String isbn, String language) {
    Book newBook = new Book(title, year, thumbnailImage, author, publisher,
        genre, isbn, language);
    library.getResourceRepository().add(newBook);
  }

  /**
   * Create new instance of a DVD resource and persists it to the resource repository.
   *
   * @param library the library
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param director the director
   * @param runtime the runtime
   * @param languages the languages
   * @param subtitleLanguages the subtitle languages
   */
  public static void createDvd(Library library, String title, int year,
      Image thumbnailImage, String director, int runtime,
      ArrayList<String> languages, ArrayList<String> subtitleLanguages) {
    Dvd newDvd = new Dvd(title, year, thumbnailImage, director, runtime,
        languages, subtitleLanguages);
    library.getResourceRepository().add(newDvd);
  }

  /**
   * Create new instance of a laptop resource and persists it to the resource repository.
   *
   * @param library the library
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param manufacturer the manufacturer
   * @param model the model
   * @param installedOperatingSystem the installed operating system
   */
  public static void createLaptop(Library library, String title, int year,
      Image thumbnailImage, String manufacturer, String model,
      String installedOperatingSystem) {
    Laptop newLaptop = new Laptop(title, year, thumbnailImage, manufacturer,
        model, installedOperatingSystem);
    library.getResourceRepository().add(newLaptop);
  }

  /**
   * Create new instance of a Game resource and persists it to the resource repository.
   *
   * @param library the library
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param publisher the publisher
   * @param genre the genre
   * @param rating the rating
   * @param multiplayer the availability of multiplayer
   */
  public static void createGame(Library library, String title, int year,
      Image thumbnailImage, String publisher, String genre, String rating,
      boolean multiplayer) {
    Game newGame = new Game(title, year, thumbnailImage, publisher, genre, rating, multiplayer);
    library.getResourceRepository().add(newGame);
  }

  /**
   * Update book.
   *
   * @param library the library
   * @param resourceId the resource id
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param author the author
   * @param publisher the publisher
   * @param genre the genre
   * @param isbn the isbn
   * @param language the language
   */
  public static void updateBook(Library library, String resourceId,
      String title, int year, Image thumbnailImage, String author,
      String publisher, String genre, String isbn, String language) {
    library.getResourceRepository().getSpecificBook(resourceId).setTitle(title);
    library.getResourceRepository().getSpecificBook(resourceId).setYear(year);
    library.getResourceRepository().getSpecificBook(resourceId)
        .setThumbnailImage(thumbnailImage);
    library.getResourceRepository().getSpecificBook(resourceId)
        .setAuthor(author);
    library.getResourceRepository().getSpecificBook(resourceId)
        .setPublisher(publisher);
    library.getResourceRepository().getSpecificBook(resourceId).setGenre(genre);
    library.getResourceRepository().getSpecificBook(resourceId).setIsbn(isbn);
    library.getResourceRepository().getSpecificBook(resourceId)
        .setLanguage(language);

  }

  /**
   * Update dvd.
   *
   * @param library the library
   * @param resourceId the resource id
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param director the director
   * @param runtime the runtime
   * @param languages the languages
   * @param subtitleLanguages the subtitle languages
   */
  public static void updateDvd(Library library, String resourceId, String title,
      int year, Image thumbnailImage, String director, int runtime,
      ArrayList<String> languages, ArrayList<String> subtitleLanguages) {
    library.getResourceRepository().getSpecificDvd(resourceId).setTitle(title);
    library.getResourceRepository().getSpecificDvd(resourceId).setYear(year);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setThumbnailImage(thumbnailImage);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setDirector(director);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setRuntime(runtime);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setLanguages(languages);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setSubtitleLanguages(subtitleLanguages);
  }

  /**
   * Update laptop.
   *
   * @param library the library
   * @param resourceId the resource id
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param manufacturer the manufacturer
   * @param model the model
   * @param installedOperatingSystem the installed operating system
   */
  public static void updateLaptop(Library library, String resourceId,
      String title, int year, Image thumbnailImage, String manufacturer,
      String model, String installedOperatingSystem) {
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setTitle(title);
    library.getResourceRepository().getSpecificLaptop(resourceId).setYear(year);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setThumbnailImage(thumbnailImage);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setManufacturer(manufacturer);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setModel(model);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setInstalledOperatingSystem(installedOperatingSystem);
  }

  /**
   * Update Game.
   *
   * @param library the library
   * @param resourceId the resource id
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param publisher the publisher
   * @param genre the genre
   * @param rating the rating
   * @param multiplayer the availability of multiplayer
   */
  public static void updateGame(Library library, String resourceId, String title, int year,
      Image thumbnailImage, String publisher, String rating, String genre,
      boolean multiplayer) throws ResourceNotFoundException {

    library.getResourceRepository().getSpecificGame(resourceId)
        .setTitle(title);
    library.getResourceRepository().getSpecificGame(resourceId).setYear(year);
    library.getResourceRepository().getSpecificGame(resourceId)
        .setThumbnailImage(thumbnailImage);
    library.getResourceRepository().getSpecificGame(resourceId)
        .setPublisher(publisher);
    library.getResourceRepository().getSpecificGame(resourceId)
        .setGenre(genre);
    library.getResourceRepository().getSpecificGame(resourceId)
        .setRating(rating);
    library.getResourceRepository().getSpecificGame(resourceId)
        .setMultiplayer(multiplayer);
  }

}
