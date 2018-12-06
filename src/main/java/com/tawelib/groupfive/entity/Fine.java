package com.tawelib.groupfive.entity;

import static java.time.temporal.ChronoUnit.DAYS;

import java.io.Serializable;
import java.util.Date;

/**
 * File Name - Fine.java The ‘Fine’ class is the class that stores information
 * regarding a fine, which is created automatically when a copy is overdue.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Fine implements Serializable {

  private final int amount;
  private final Lease lease;

  /**
   * Instantiates a new Fine for resources that overdue.
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
   * Gets amount.
   *
   * @return the amount
   */
  public int getAmount() {
    return amount;
  }

  /**
   * Returns Lease.
   *
   * @return lease
   */
  public Lease getLease() {
    return lease;
  }

  /**
   * Gets days overdue.
   *
   * @param lease lease
   */
  public static int getDaysOverdue(Lease lease) {

    long diff = DAYS.between(lease.getDueDate(), lease.getDateReturned());
    return (int) diff;
  }


}
