package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.contentprovider.FetchableTrailer;
import javafx.scene.image.Image;

/**
 * Encapsulates information about a video game.
 *
 * @author Petr Hoffman, Oskars Dervinis
 * @version 1.0
 */
public class Game extends Resource implements FetchableTrailer {

  private String publisher;
  private String genre;
  private String rating;
  private boolean multiplayer;

  /**
   * Constructs a new video game.
   *
   * @param title Game title.
   * @param year Publication year.
   * @param thumbnailImage Image.
   * @param publisher Game publisher.
   * @param genre Game genre.
   * @param rating Game certificate rating.
   * @param multiplayer Multiplayer support.
   */
  public Game(
      String title,
      int year,
      Image thumbnailImage,
      String publisher,
      String genre,
      String rating,
      boolean multiplayer
  ) {
    super(
        title,
        year,
        thumbnailImage,
        ResourceType.GAME
    );

    this.publisher = publisher;
    this.genre = genre;
    this.rating = rating;
    this.multiplayer = multiplayer;
  }

  /**
   * Returns the publisher of the game.
   *
   * @return String, name of publisher
   */
  public String getPublisher() {
    return publisher;
  }

  /**
   * Sets the publisher of the game to the given string.
   *
   * @param publisher Name of publisher
   */
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  /**
   * Returns the genre of the game.
   *
   * @return String, genre of the game
   */
  public String getGenre() {
    return genre;
  }

  /**
   * Sets the genre of the game.
   *
   * @param genre Genre of the game
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * Returns the certificate rating of the game.
   *
   * @return String, certificate rating of the game
   */
  public String getRating() {
    return rating;
  }

  /**
   * Sets the certificate rating of the game.
   *
   * @param rating Certificate rating of the game
   */
  public void setRating(String rating) {
    this.rating = rating;
  }

  /**
   * Returns true if the game is multiplayer, false otherwise.
   *
   * @return Boolean, true if game is multiplayer, false otherwise
   */
  public boolean isMultiplayer() {
    return multiplayer;
  }

  /**
   * Sets boolean value for game; true if game is multiplayer, false otherwise.
   *
   * @param multiplayer Boolean, true if game is multiplayer, false otherwise.
   */
  public void setMultiplayer(boolean multiplayer) {
    this.multiplayer = multiplayer;
  }

  //TODO Fill out javadoc for this method
  @Override
  public String getTrailerSearchQuery() {
    return String.format(
        "%s (%s)",
        getTitle(),
        getPublisher()
    );
  }
}
