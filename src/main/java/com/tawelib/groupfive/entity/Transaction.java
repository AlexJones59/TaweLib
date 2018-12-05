package com.tawelib.groupfive.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * File Name - Transaction.java The ‘Transaction’ class is the class that
 * records every time a customer pays funds to the Library to change their
 * account balance.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Transaction implements Serializable {

  private int amount;
  private LocalDateTime datePaid;
  private Customer payee;

  /**
   * Instantiates a new Transaction.
   *
   * @param amount the amount
   * @param payee  the customer paying
   */
  public Transaction(int amount, Customer payee) {
    this.amount = amount;
    this.payee = payee;
    this.datePaid = LocalDateTime.now();
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
   * Gets date paid.
   *
   * @return the date paid
   */
  public LocalDateTime getDatePaid() {
    return datePaid;
  }

  /**
   * Gets payee.
   *
   * @return the payee
   */
  public Customer getPayee() {
    return payee;
  }
}
