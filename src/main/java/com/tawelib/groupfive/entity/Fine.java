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

  @Deprecated
  private String fineId;

  private int amount;
  private Date dateIssued;
  private int daysOverdue;
  private Lease lease;

  /**
   * Instantiates a new Fine for resources that overdue.
   */
  @Deprecated
  public Fine(Lease specificLease) {
    this.dateIssued = new Date();
  }

  /**
   * Instantiates a new Fine for resources that overdue.
   *
   * @param amount Amount.
   * @param daysOverdue Days overdue.
   */
  public Fine(Lease lease, int amount, int daysOverdue) {
    this.lease = lease;
    this.amount = amount;
    this.daysOverdue = daysOverdue;
    this.dateIssued = new Date();
  }

  /**
   * Instantiates a new Fine, for specific fines that the librarian can define
   * straight away.
   *
   * @param amount the amount
   */
  @Deprecated
  public Fine(int amount, Lease specificLease) {
    this.amount = amount;
    this.dateIssued = new Date();
  }

  /**
   * Gets fine id.
   *
   * @return the fine id
   */
  @Deprecated
  public String getFineId() {
    return fineId;
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
   * Gets date issued.
   *
   * @return the date issued
   */
  public Date getDateIssued() {
    return dateIssued;
  }

  /**
   * Gets instance of Lease that this fine is specific to.
   */
  @Deprecated
  public Lease getSpecificLease() {
    return null;
  }

  /**
   * Returns days overdue.
   *
   * @return Days overdue.
   */
  public int getDaysOverdue() {
    return daysOverdue;
  }

  public Lease getLease() {
    return lease;
  }

  //  /**
  //   * Gets days overdue.
  //   *
  //   * NOTE: Wrong class
  //   */
  //  public int getDaysOverdue() {
  //    //TODO: optimise.
  //    Date currentDate = new Date();
  //    long diffInMilli = currentDate.getTime() - dateIssued.getTime();
  //    return (int) ((((diffInMilli / 1000) / 60) / 60) / 24);
  //
  //  }
}
