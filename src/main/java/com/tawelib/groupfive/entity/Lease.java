package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.repository.CopyRepository;
import java.util.Date;

/**
 * Lease.java The ‘Lease’ class is the class that stores the information about every loan, linking
 * the relevant instance of the ‘Customer’ class and instance of the ‘Copy’ class.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Lease {

  private Date dateLeased;
  private Date dateReturned;
  private Date dueDate;
  private String borrowingCustomerId;
  private String borrowedCopyId;
  //private Fine overdueFine;


  /**
   * Instantiates a new Lease.
   *
   * @param borrowingCustomerId the borrowing customer id
   * @param borrowedCopyId the borrowed copy id
   */
  public Lease(String borrowingCustomerId, String borrowedCopyId) {
    this.dateLeased = new Date();
    this.borrowingCustomerId = borrowingCustomerId;
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
   *
   * @param dateReturned the date returned
   */
  public void setDateReturned(Date dateReturned) {
    this.dateReturned = dateReturned;
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
   * @return the borrowing customer
   */
  public Customer getBorrowingCustomer() {
    //TODO: after Repository class is done, needs to call searchCustomer function.
    return null;
  }

  /**
   * Gets borrowed copy.
   *
   * @return the borrowed copy
   */
  public Copy getBorrowedCopy() {
    //TODO: after Repository class is done, needs to call searchCopy function.
    return null;
  }
}
