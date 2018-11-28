package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * File Name - ResourceController.java
 * The resource controller class  handles controls data flow between the Resource Repository
 * and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
public class ResourceController {

  /**
   * Create book.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param type the type
   * @param author the author
   * @param publisher the publisher
   * @param genre the genre
   */
  public void createBook(Library library,String title, int year,
      Image thumbnailImage,
      ResourceType type, String author, String publisher, String genre,
      String isbn, String language) {
    Book newBook = new Book(title, year, thumbnailImage, type, author,
        publisher, genre, isbn, language);
    library.getResourceRepository().add(newBook);
  }

  /**
   * Create dvd.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param director the director
   * @param runtime the runtime
   * @param languages the languages
   * @param subtitleLanguages the subtitle languages
   */
  public void createDvd(Library library,String title, int year,
      Image thumbnailImage, ResourceType type, String director, int runtime,
      ArrayList<String> languages, ArrayList<String> subtitleLanguages) {
    Dvd newDvd = new Dvd( title, year, thumbnailImage, type, director,runtime);
    newDvd.addLanguages(languages);
    newDvd.addSubtitleLanguages(subtitleLanguages);
    library.getResourceRepository().add(newDvd);
  }

  /**
   * Create laptop.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param type the type
   * @param manufacturer the manufacturer
   * @param model the model
   * @param installedOperatingSystem the installed operating system
   */
  public void createLaptop(Library library, String title, int year,
      Image thumbnailImage, ResourceType type,
      String manufacturer, String model, String installedOperatingSystem) {
    Laptop newLaptop = new Laptop( title, year, thumbnailImage, type,
        manufacturer, model, installedOperatingSystem);
    library.getResourceRepository().add(newLaptop);
  }

  /**
   * Update book.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param type the type
   * @param author the author
   * @param publisher the publisher
   * @param genre the genre
   */
  public void updateBook(Library library, String resourceId, String title,
      int year, Image thumbnailImage, ResourceType type, String author,
      String publisher, String genre, String isbn, String language) {
    library.getResourceRepository().getSpecificBook(resourceId).setTitle(title);
    library.getResourceRepository().getSpecificBook(resourceId).setYear(year);
    library.getResourceRepository().getSpecificBook(resourceId)
        .setThumbnailImage(thumbnailImage);
    library.getResourceRepository().getSpecificBook(resourceId).setType(type);
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
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param director the director
   * @param runtime the runtime
   * @param languages the languages
   * @param subtitleLanguages the subtitle languages
   */
  public void updateDvd(Library library, String resourceId, String title,
      int year, Image thumbnailImage, ResourceType type, String director,
      int runtime, ArrayList<String> languages,
      ArrayList<String> subtitleLanguages) {
    library.getResourceRepository().getSpecificDvd(resourceId).setTitle(title);
    library.getResourceRepository().getSpecificDvd(resourceId).setYear(year);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setThumbnailImage(thumbnailImage);
    library.getResourceRepository().getSpecificDvd(resourceId).setType(type);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setDirector(director);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .setRuntime(runtime);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .addLanguages(languages);
    library.getResourceRepository().getSpecificDvd(resourceId)
        .addSubtitleLanguages(subtitleLanguages);
  }

  /**
   * Update laptop.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param type the type
   * @param manufacturer the manufacturer
   * @param model the model
   * @param installedOperatingSystem the installed operating system
   */
  public void updateLaptop(Library library, String resourceId, String title,
      int year, Image thumbnailImage, ResourceType type, String manufacturer,
      String model, String installedOperatingSystem) {
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setTitle(title);
    library.getResourceRepository().getSpecificLaptop(resourceId).setYear(year);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setThumbnailImage(thumbnailImage);
    library.getResourceRepository().getSpecificLaptop(resourceId).setType(type);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setManufacturer(manufacturer);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setModel(model);
    library.getResourceRepository().getSpecificLaptop(resourceId)
        .setInstalledOperatingSystem(installedOperatingSystem);
  }

}
