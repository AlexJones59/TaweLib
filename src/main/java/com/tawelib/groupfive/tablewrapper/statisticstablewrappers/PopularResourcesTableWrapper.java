package com.tawelib.groupfive.tablewrapper.statisticstablewrappers;

import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.ResourceType;

/**
 * This Class wraps information about the most Popular Resources that is shown in an FXML table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class PopularResourcesTableWrapper {

  private int rank;
  private Rating avgRating;


  /**
   * Instantiates a new Popular resources table wrapper.
   *
   * @param rank the rank
   * @param avgRating the avg rating
   */
  public PopularResourcesTableWrapper(int rank, Rating avgRating) {
    this.rank = rank;
    this.avgRating = avgRating;
  }

  /**
   * Gets res rank.
   *
   * @return the res rank
   */
  public int getResRank() {
    return rank;
  }

  /**
   * Get res resource id string.
   *
   * @return the string
   */
  public String getResResourceId() {
    return avgRating.getRatedResource().getResourceId();
  }

  /**
   * Get res title string.
   *
   * @return the string
   */
  public String getResTitle() {
    return avgRating.getRatedResource().getTitle();
  }

  /**
   * Get res type resource type.
   *
   * @return the resource type
   */
  public ResourceType getResType() {
    return avgRating.getRatedResource().getType();
  }

  /**
   * Get res avg rating int.
   *
   * @return the int
   */
  public int getResAvgRating() {
    return avgRating.getRatingValue();
  }

}
