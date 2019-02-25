package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.contentprovider.FetchableTrailer;
import javafx.scene.image.Image;

/**
 * Encapsulates information about a video game.
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

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getRating() {
    return rating;
  }

  public void setRating(String rating) {
    this.rating = rating;
  }

  public boolean isMultiplayer() {
    return multiplayer;
  }

  public void setMultiplayer(boolean multiplayer) {
    this.multiplayer = multiplayer;
  }

  @Override
  public String getTrailerSearchQuery() {
    return String.format(
        "%s (%s)",
        getTitle(),
        getPublisher()
    );
  }
}
