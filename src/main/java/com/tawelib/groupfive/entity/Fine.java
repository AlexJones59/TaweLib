package com.tawelib.groupfive.entity;

import java.util.Date;

/**
 * File Name - Fine.java The ‘Fine’ class is the class that stores information
 * regarding a fine, which is created automatically when a copy is overdue.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Fine {

  private String fineId;
  private int amount;
  private Date dateIssued;
  private Lease specificLease;

  /**
   * Instantiates a new Fine for resources that overdue.
   */
  public Fine(Lease specificLease) {
    this.dateIssued = new Date();
    this.specificLease = specificLease;
  }

  /**
   * Instantiates a new Fine, for specific fines that the librarian can define
   * straight away.
   *
   * @param amount the amount
   */
  public Fine(int amount, Lease specificLease) {
    this.amount = amount;
    this.dateIssued = new Date();
    this.specificLease = specificLease;
  }

  /**
   * Gets fine id.
   *
   * @return the fine id
   */
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
  public Lease getSpecificLease() {
    return specificLease;
  }

  /**
   * Gets days overdue.
   */
  public int getDaysOverdue() {
    //TODO: optimise.
    Date currentDate = new Date();
    long diffInMilli = currentDate.getTime() - dateIssued.getTime();
    return (int) ((((diffInMilli / 1000) / 60) / 60) / 24);

  }


}
