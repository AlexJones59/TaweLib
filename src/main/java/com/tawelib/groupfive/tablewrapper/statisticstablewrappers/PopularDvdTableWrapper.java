package com.tawelib.groupfive.tablewrapper.statisticstablewrappers;

import com.tawelib.groupfive.entity.AverageRating;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Rating;

/**
 * This Class wraps information about the most Popular Dvds that is shown in an FXML table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class PopularDvdTableWrapper {


  private int rank;
  private AverageRating avgRating;
  private Dvd dvd;


  /**
   * Instantiates a new Popular dvd table wrapper.
   *
   * @param rank the rank
   * @param avgRating the avg rating
   */
  public PopularDvdTableWrapper(int rank, AverageRating avgRating) {
    this.rank = rank;
    this.avgRating = avgRating;
    this.dvd = (Dvd) avgRating.getResource();
  }

  /**
   * Gets dvd rank.
   *
   * @return the dvd rank
   */
  public int getDvdRank() {
    return rank;
  }

  /**
   * Gets dvd resource id.
   *
   * @return the dvd resource id
   */
  public String getDvdResourceId() {
    return dvd.getResourceId();
  }

  /**
   * Gets dvd title.
   *
   * @return the dvd title
   */
  public String getDvdTitle() {
    return dvd.getTitle();
  }

  /**
   * Gets dvd director.
   *
   * @return the dvd director
   */
  public String getDvdDirector() {
    return dvd.getDirector();
  }

  /**
   * Gets dvd avg rating.
   *
   * @return the dvd avg rating
   */
  public String getDvdAvgRating() {
    return String.format("%.1f",avgRating.getRating());
  }

}
