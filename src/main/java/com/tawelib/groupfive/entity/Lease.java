package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.Main;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Lease.java The ‘Lease’ class is the class that stores the information about
 * every loan, linking the relevant instance of the ‘Customer’ class and
 * instance of the ‘Copy’ class.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Lease implements Serializable {

  private LocalDateTime dateLeased;
  private LocalDateTime dateReturned;
  private LocalDateTime dueDate;
  private final Copy copy;
  private final Customer borrowingCustomer;


  /**
   * Instantiates a new Lease.
   *
   * @param customer the borrowing customer username
   * @param copy the borrowed copy id
   */
  public Lease(Customer customer, Copy copy) {
    this.borrowingCustomer = customer;
    this.copy = copy;
    this.dateLeased = LocalDateTime.now();
  }

  /**
   * Gets date leased.
   *
   * @return the date leased
   */
  public LocalDateTime getDateLeased() {
    return dateLeased;
  }

  /**
   * Development method only.
   *
   * @param date Date.
   */
  public void dev_setDateLeased(LocalDateTime date) {
    if (Main.DEV_MODE) {
      dateLeased = date;
    }
  }

  /**
   * Gets date returned.
   *
   * @return the date returned
   */
  public LocalDateTime getDateReturned() {
    return dateReturned;
  }

  /**
   * Sets date returned.
   */
  public void setDateReturned() {
    this.dateReturned = LocalDateTime.now();
  }

  /**
   * Development method only.
   *
   * @param date Date.
   */
  public void dev_setDateReturned(LocalDateTime date) {
    if (Main.DEV_MODE) {
      dateReturned = date;
    }
  }

  /**
   * Gets due date.
   *
   * @return the due date
   */
  public LocalDateTime getDueDate() {
    return dueDate;
  }

  /**
   * Sets due date.
   *
   * @param dueDate the due date
   */
  public void setDueDate(LocalDateTime dueDate) {
    this.dueDate = dueDate;
  }


  /**
   * Returns the borrowed copy.
   *
   * @return the borrowed Copy.
   */
  public Copy getBorrowedCopy() {
    return copy;
  }


  /**
   * Gets borrowing customer.
   *
   * @return the borrowing customer
   */
  public Customer getBorrowingCustomer() {
    return borrowingCustomer;
  }


}
