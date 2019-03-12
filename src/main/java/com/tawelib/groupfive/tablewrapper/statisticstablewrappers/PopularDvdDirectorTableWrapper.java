package com.tawelib.groupfive.tablewrapper.statisticstablewrappers;

/**
 * This Class wraps information about the most Popular Directors that is shown in an FXML table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class PopularDvdDirectorTableWrapper {

  private int rank;
  private String director;

  /**
   * Instantiates a new Popular dvd director table wrapper.
   *
   * @param rank the rank
   * @param director the director
   */
  public PopularDvdDirectorTableWrapper(int rank, String director) {
    this.rank = rank;
    this.director = director;
  }

  /**
   * Get director rank int.
   *
   * @return the int
   */
  public int getDirectorRank() {
    return rank;
  }

  /**
   * Get director name string.
   *
   * @return the string
   */
  public String getDirectorName() {
    return director;
  }

}
