package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Book;
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
  public void createDvd(String title, int year, Image thumbnailImage, String director,
       int runtime, ArrayList<String> languages, ArrayList<String> subtitleLanguages) {}

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
  public void createLaptop(String title, int year, Image thumbnailImage, ResourceType type,
      String manufacturer, String model, String installedOperatingSystem) {}

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
  public void updateBook(Library libarary, String title, int year,
      Image thumbnailImage, ResourceType type, String author, String publisher,
      String genre, String isbn, String language) {

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
  public void updateDvd(String title, int year, Image thumbnailImage,
      String director,
      int runtime, ArrayList<String> languages, ArrayList<String> subtitleLanguages) {}

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
  public void updateLaptop(String title, int year, Image thumbnailImage,
      ResourceType type,
      String manufacturer, String model, String installedOperatingSystem) {}


  /**
   * Search resources list.
   *
   * @param query the query
   * @param types the types
   * @param searchAttribute the search attribute
   * @return the list
   */
  public List<Resource> searchResources(String query, ArrayList<ResourceType> types,
      String searchAttribute) {
    return null;
  }





}
