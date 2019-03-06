package com.tawelib.groupfive.entity;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Fine.java The ‘Fine’ class is the class that stores information regarding a fine, which is
 * created automatically when a copy is overdue.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Fine implements Serializable {

  /**
   * The constant CURRENCY_FORMAT.
   */
  public static final String CURRENCY_FORMAT = "£ %.2f";

  private final int amount;// Stored in pennies.
  private final Lease lease;

  /**
   * Instantiates a new Fine for resources that is overdue.
   *
   * @param lease lease.
   * @param amount Amount.
   */
  public Fine(Lease lease, int amount) {
    if (lease.getDateReturned() != null) {
      this.lease = lease;
      this.amount = amount;
    } else {
      throw new IllegalStateException();
    }
  }

  /**
   * Gets the amount of the fine.
   *
   * @return the amount
   */
  public int getAmount() {
    return amount;
  }

  /**
   * Gets the amount of the fine in pounds.
   *
   * @return the amount
   */
  public float getAmountInPounds() {
    return ((float) amount) / 100;
  }

  /**
   * Returns Lease.
   *
   * @return lease lease
   */
  public Lease getLease() {
    return lease;
  }


  /**
   * Gets date accrued.
   *
   * @return the date accrued
   */
  public LocalDateTime getDateAccrued() {
    return lease.getDateReturned();
  }

  /**
   * Gets fined customer.
   *
   * @return the fined customer
   */
  public Customer getFinedCustomer() {
    return lease.getBorrowingCustomer();
  }
}
