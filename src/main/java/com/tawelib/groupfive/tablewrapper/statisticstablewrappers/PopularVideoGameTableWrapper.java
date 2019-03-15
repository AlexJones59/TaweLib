package com.tawelib.groupfive.tablewrapper.statisticstablewrappers;

import com.tawelib.groupfive.entity.AverageRating;
import com.tawelib.groupfive.entity.Game;
import com.tawelib.groupfive.entity.Rating;

/**
 * This Class wraps information about the most Popular Video Games that is shown in an FXML table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class PopularVideoGameTableWrapper {

  private int rank;
  private AverageRating avgRating;
  private Game game;


  /**
   * Instantiates a new Popular video game table wrapper.
   *
   * @param rank the rank
   * @param avgRating the avg rating
   */
  public PopularVideoGameTableWrapper(int rank, AverageRating avgRating) {
    this.rank = rank;
    this.avgRating = avgRating;
    this.game = (Game) avgRating.getResource();
  }

  /**
   * Gets video game rank.
   *
   * @return the video game rank
   */
  public int getVideoGameRank() {
    return rank;
  }

  /**
   * Gets video game resource id.
   *
   * @return the video game resource id
   */
  public String getVideoGameResourceId() {
    return game.getResourceId();
  }

  /**
   * Gets video game title.
   *
   * @return the video game title
   */
  public String getVideoGameTitle() {
    return game.getTitle();
  }

  /**
   * Gets video game genre.
   *
   * @return the video game genre
   */
  public String getVideoGameGenre() {
    return game.getGenre();
  }

  /**
   * Gets video game cert rating.
   *
   * @return the video game cert rating
   */
  public String getVideoGameCertRating() {
    return game.getRating();
  }

  /**
   * Gets video game multi player.
   *
   * @return the video game multi player
   */
  public Boolean getVideoGameMultiPlayer() {
    return game.isMultiplayer();
  }

  /**
   * Gets video game avg rating.
   *
   * @return the video game avg rating
   */
  public String getVideoGameAvgRating() {
    return String.format("%.1f",avgRating.getRating());
  }
}
