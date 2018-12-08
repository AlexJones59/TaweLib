package com.tawelib.groupfive.entity;

import javafx.scene.image.Image;

/**
 * Book.java Book class saves all  info pertaining to a book resource.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Book extends Resource {

  private String author;
  private String publisher;
  private String genre;
  private String isbn;
  private String language;

  /**
   * Instantiates a new Book.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param author the author
   * @param publisher the publisher
   * @param genre the genre
   * @param isbn the isbn
   * @param language the language
   */
  public Book(String title, int year, Image thumbnailImage,
      String author, String publisher, String genre, String isbn,
      String language) {
    super(title, year, thumbnailImage, ResourceType.BOOK);
    this.author = author;
    this.publisher = publisher;
    this.genre = genre;
    this.isbn = isbn;
    this.language = language;
  }

  /**
   * Gets author.
   *
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Sets author.
   *
   * @param author the author
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Gets publisher.
   *
   * @return the publisher
   */
  public String getPublisher() {
    return publisher;
  }

  /**
   * Sets publisher.
   *
   * @param publisher the publisher
   */
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  /**
   * Gets genre.
   *
   * @return the genre
   */
  public String getGenre() {
    return genre;
  }

  /**
   * Sets genre.
   *
   * @param genre the genre
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * Gets ISBN.
   *
   * @return the ISBN
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Sets isbn.
   *
   * @param isbn the isbn
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Gets language.
   *
   * @return the language
   */
  public String getLanguage() {
    return language;
  }

  /**
   * Sets language.
   *
   * @param language the language
   */
  public void setLanguage(String language) {
    this.language = language;
  }
}
