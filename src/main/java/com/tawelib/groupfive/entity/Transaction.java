package com.tawelib.groupfive.entity;

import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Transaction.java Records every time a customer pays funds to the Library to change their account
 * balance.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Transaction implements Serializable {

  private int amount; //Stored in pennies.
  private LocalDateTime datePaid;
  private Customer payee;

  /**
   * Instantiates a new Transaction.
   *
   * @param amount the amount
   * @param payee the customer paying
   */
  public Transaction(int amount, Customer payee) {
    this.amount = amount;
    this.payee = payee;
    this.datePaid = SimulatedLocalDateTime.now();
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
   * Gets amount in pounds.
   *
   * @return the amount
   */
  public float getAmountInPounds() {
    return ((float) amount) / 100;
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
