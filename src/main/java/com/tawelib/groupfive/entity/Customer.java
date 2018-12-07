package com.tawelib.groupfive.entity;

/**
 * Customer.java Customer class stores personal information for the Customer account.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Customer extends User {

  private int accountBalance;

  /**
   * Instantiates a new Customer.
   *
   * @param firstName   the first name
   * @param lastName    the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street      the street
   * @param city        the city
   * @param postCode    the post code
   */
  public Customer(String firstName, String lastName, String phoneNumber,
                  String houseNumber, String street, String city,
                  String postCode) {
    super(firstName, lastName, phoneNumber, houseNumber, street, city,
        postCode);
  }

  /**
   * Gets account balance.
   *
   * @return the account balance
   */
  public int getAccountBalance() {
    return accountBalance;
  }

  /**
   * Changes the format of the account balance into pounds.
   *
   * @return the account balance in pounds
   */
  public double getAccountBalanceInPounds() {
    return ((double) accountBalance) / 100;
  }

  /**
   * Increases account balance by 'amount'.
   *
   * @param amount increments to account balance
   */
  public void increaseAccountBalance(int amount) {
    this.accountBalance += Math.abs(amount);
  }

  /**
   * Decreases account balance by amount.
   *
   * @param amount decrements to account balance
   */
  public void decreaseAccountBalance(int amount) {
    this.accountBalance -= Math.abs(amount);
  }
}
