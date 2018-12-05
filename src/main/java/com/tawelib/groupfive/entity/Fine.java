package com.tawelib.groupfive.entity;

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
    this.lease = lease;
    this.amount = amount;
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




}
