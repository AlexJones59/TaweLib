package com.tawelib.groupfive.tablewrapper.statisticstablewrappers;

/**
 * This Class wraps information about the most Popular Authors that is shown in an FXML table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class PopularBookAuthorTableWrapper {

  private int rank;
  private String author;

  /**
   * Instantiates a new Popular book author table wrapper.
   *
   * @param rank the rank
   * @param author the author
   */
  public PopularBookAuthorTableWrapper(int rank, String author) {
    this.rank = rank;
    this.author = author;
  }

  /**
   * Gets author rank.
   *
   * @return the author rank
   */
  public int getAuthorRank() {
    return rank;
  }

  /**
   * Gets author name.
   *
   * @return the author name
   */
  public String getAuthorName() {
    return author;
  }

}
