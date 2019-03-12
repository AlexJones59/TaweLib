package com.tawelib.groupfive.tablewrapper.statisticstablewrappers;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Rating;

/**
 * This Class wraps information about the most Popular Books that is shown in an FXML table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class PopularBookTableWrapper {

  private int rank;
  private Rating avgRating;
  private Book book;


  /**
   * Instantiates a new Popular book table wrapper.
   *
   * @param rank the rank
   * @param avgRating the avg rating
   */
  public PopularBookTableWrapper(int rank, Rating avgRating) {
    this.rank = rank;
    this.avgRating = avgRating;
    this.book = (Book) avgRating.getRatedResource();
  }

  /**
   * Gets book rank.
   *
   * @return the book rank
   */
  public int getBookRank() {
    return rank;
  }

  /**
   * Gets book resource id.
   *
   * @return the book resource id
   */
  public String getBookResourceId() {
    return book.getResourceId();
  }

  /**
   * Gets book title.
   *
   * @return the book title
   */
  public String getBookTitle() {
    return book.getTitle();
  }

  /**
   * Gets book author.
   *
   * @return the book author
   */
  public String getBookAuthor() {
    return book.getAuthor();
  }

  /**
   * Gets book genre.
   *
   * @return the book genre
   */
  public String getBookGenre() {
    return book.getGenre();
  }

  /**
   * Gets book avg rating.
   *
   * @return the book avg rating
   */
  public int getBookAvgRating() {
    return avgRating.getRatingValue();
  }

}
