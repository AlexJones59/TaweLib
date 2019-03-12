package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Lease.java Stores the information about every loan, linking the relevant customer and copy
 * information.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Lease implements Serializable {

  private final Copy copy;
  private final Customer borrowingCustomer;
  private LocalDateTime dateLeased;
  private LocalDateTime dateReturned;
  private LocalDateTime dueDate;


  /**
   * Instantiates a new Lease.
   *
   * @param customer the borrowing customer username
   * @param copy the borrowed copy id
   */
  public Lease(Customer customer, Copy copy) {
    this.borrowingCustomer = customer;
    this.copy = copy;
    this.dateLeased = SimulatedLocalDateTime.now();
  }

  /**
   * Set date leased.
   *
   * @param dateLeased the date leased
   */
  public void setDateLeased(LocalDateTime dateLeased) {
    this.dateLeased = dateLeased;
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
    this.dateReturned = SimulatedLocalDateTime.now();
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
