package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.repository.CopyRepository;
import java.util.Date;

/**
 * Lease.java The ‘Lease’ class is the class that stores the information about
 * every loan, linking the relevant instance of the ‘Customer’ class and
 * instance of the ‘Copy’ class.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Lease {

  private Date dateLeased;
  private Date dateReturned;
  private Date dueDate;
  private String borrowingCustomerUsername;
  private String borrowedCopyId;



  /**
   * Instantiates a new Lease.
   *
   * @param borrowingCustomerUsername the borrowing customer username
   * @param borrowedCopyId the borrowed copy id
   */
  public Lease(String borrowingCustomerUsername, String borrowedCopyId) {
    this.dateLeased = new Date();
    this.borrowingCustomerUsername = borrowingCustomerUsername;
    this.borrowedCopyId = borrowedCopyId;
  }

  /**
   * Gets date leased.
   *
   * @return the date leased
   */
  public Date getDateLeased() {
    return dateLeased;
  }

  /**
   * Gets date returned.
   *
   * @return the date returned
   */
  public Date getDateReturned() {
    return dateReturned;
  }

  /**
   * Sets date returned.
   */
  public void setDateReturned() {
    this.dateReturned = new Date();
  }

  /**
   * Gets due date.
   *
   * @return the due date
   */
  public Date getDueDate() {
    return dueDate;
  }

  /**
   * Sets due date.
   *
   * @param dueDate the due date
   */
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * Gets borrowing customer.
   *
   * @return the borrowing customer username
   */
  public String getBorrowingCustomerUsername() {
    return borrowingCustomerUsername;
  }

  /**
   * Gets borrowed copy.
   *
   * @return the borrowed copy id
   */
  public String getBorrowedCopyId() {
    return borrowedCopyId;
  }

}
