package com.tawelib.groupfive.tablewrapper.statisticstablewrappers;

import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Rating;

/**
 * This Class wraps information about the most Popular Laptops that is shown in an FXML table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class PopularLaptopTableWrapper {


  private int rank;
  private Rating avgRating;
  private Laptop laptop;


  /**
   * Instantiates a new Popular laptop table wrapper.
   *
   * @param rank the rank
   * @param avgRating the avg rating
   */
  public PopularLaptopTableWrapper(int rank, Rating avgRating) {
    this.rank = rank;
    this.avgRating = avgRating;
    this.laptop = (Laptop) avgRating.getRatedResource();
  }

  /**
   * Gets laptop rank.
   *
   * @return the laptop rank
   */
  public int getLaptopRank() {
    return rank;
  }

  /**
   * Gets laptop resource id.
   *
   * @return the laptop resource id
   */
  public String getLaptopResourceId() {
    return laptop.getResourceId();
  }

  /**
   * Gets laptop title.
   *
   * @return the laptop title
   */
  public String getLaptopTitle() {
    return laptop.getTitle();
  }

  /**
   * Gets laptop os.
   *
   * @return the laptop os
   */
  public String getLaptopOs() {
    return laptop.getInstalledOperatingSystem();
  }

  /**
   * Gets laptop avg rating.
   *
   * @return the laptop avg rating
   */
  public int getLaptopAvgRating() {
    return avgRating.getRatingValue();
  }

}
