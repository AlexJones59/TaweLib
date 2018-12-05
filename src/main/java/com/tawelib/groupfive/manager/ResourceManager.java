package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Library;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * File Name - ResourceManager.java
 * The resource manager class  handles controls data flow between the
 * Resource Repository
 * and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
public class ResourceManager {

  /**
   * Create book.
   *
   * @param title          the title
   * @param year           the year
   * @param thumbnailImage the thumbnail image
   * @param author         the author
   * @param publisher      the publisher
   * @param genre          the genre
   */
  public static void createBook(Library library,String title, int year,
      Image thumbnailImage, String author, String publisher,
      String genre, String isbn, String language) {
    Book newBook = new Book(title, year, thumbnailImage, author,
        publisher, genre, isbn, language);
    library.getResourceRepository().add(newBook);
  }

  /**
   * Create dvd.
   *
   * @param title             the title
   * @param year              the year
   * @param thumbnailImage    the thumbnail image
   * @param director          the director
   * @param runtime           the runtime
   * @param languages         the languages
   * @param subtitleLanguages the subtitle languages
   */
  public static void createDvd(Library library,String title, int year,
      Image thumbnailImage,String director, int runtime,
      ArrayList<String> languages, ArrayList<String> subtitleLanguages) {
    Dvd newDvd = new Dvd(title, year, thumbnailImage, director, runtime,
        languages, subtitleLanguages);
    library.getResourceRepository().add(newDvd);
  }

  /**
   * Create laptop.
   *
   * @param title                    the title
   * @param year                     the year
   * @param thumbnailImage           the thumbnail image
   * @param manufacturer             the manufacturer
   * @param model                    the model
   * @param installedOperatingSystem the installed operating system
   */
  public static void createLaptop(Library library, String title, int year,
      Image thumbnailImage, String manufacturer, String model,
      String installedOperatingSystem) {
    Laptop newLaptop = new Laptop(title, year, thumbnailImage,
        manufacturer, model, installedOperatingSystem);
    library.getResourceRepository().add(newLaptop);
  }

  /**
   * Update book.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param author the author
   * @param publisher the publisher
   * @param genre the genre
   */
  public static void updateBook(Book book, String title,
      int year, Image thumbnailImage, String author,
      String publisher, String genre, String isbn, String language) {
    book.setTitle(title);
    book.setYear(year);
    book.setThumbnailImage(thumbnailImage);
    book.setAuthor(author);
    book.setPublisher(publisher);
    book.setGenre(genre);
    book.setIsbn(isbn);
    book.setLanguage(language);

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
  public static void updateDvd(Dvd dvd, String title,
      int year, Image thumbnailImage, String director, int runtime,
      ArrayList<String> languages, ArrayList<String> subtitleLanguages) {
    dvd.setTitle(title);
    dvd.setYear(year);
    dvd.setThumbnailImage(thumbnailImage);
    dvd.setDirector(director);
    dvd.setRuntime(runtime);
    dvd.setLanguages(languages);
    dvd.setSubtitleLanguages(subtitleLanguages);
  }

  /**
   * Update laptop.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param manufacturer the manufacturer
   * @param model the model
   * @param installedOperatingSystem the installed operating system
   */
  public static void updateLaptop(Laptop laptop, String title,
      int year, Image thumbnailImage, String manufacturer,
      String model, String installedOperatingSystem) {
    laptop.setTitle(title);
    laptop.setYear(year);
    laptop.setThumbnailImage(thumbnailImage);
    laptop.setManufacturer(manufacturer);
    laptop.setModel(model);
    laptop.setInstalledOperatingSystem(installedOperatingSystem);
  }

}
