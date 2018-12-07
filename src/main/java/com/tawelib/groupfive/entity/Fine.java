package com.tawelib.groupfive.entity;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.Serializable;
import java.util.Date;

/**
 * Fine.java The ‘Fine’ class is the class that stores information regarding a fine, which is
 * created automatically when a copy is overdue.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Fine implements Serializable {

  private final int amount;// Stored in pennies.
  private final Lease lease;

  /**
   * Instantiates a new Fine for resources that is overdue.
   *
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
   * @return lease
   */
  public Lease getLease() {
    return lease;
  }
}
