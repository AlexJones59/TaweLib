package com.tawelib.groupfive.entity;

/**
 * File Name: Customer.java Customer class stores personal information for the
 * Customer account.
 *
 * @author Shree Desai
 * @version 0.2
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
                  int houseNumber, String street, String city,
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
   * Increase account balance.
   *
   * @param amount increments to account balance
   */
  public void increaseAccountBalance(int amount) {
    this.accountBalance += accountBalance;
  }

  /**
   * Decrease account balance.
   *
   * @param amount increments to account balance
   */
  public void decreaseAccountBalance(int amount) {
    this.accountBalance -= accountBalance;
  }
}
