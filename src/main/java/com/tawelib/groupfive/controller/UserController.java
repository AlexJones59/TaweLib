package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Customer;
import java.util.Date;

/**
 * File Name - UserController.java
 * The User Controller class controls data flow between the Customer and Librarian Repository
 * and the GUI interfaces.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class UserController {

  /**
   * Create customer account.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  public void createCustomerAccount(String firstName, String lastName, String phoneNumber,
      String houseNumber, String street, String city, String postcode) {}

  /**
   * Create librarian account.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param employmentDate the employment date
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  public void createLibrarianAccount(String firstName, String lastName, Date employmentDate,
      String phoneNumber, String houseNumber, String street, String city, String postcode) {}

  /**
   * Top up account balance.
   *
   * @param customer the customer
   * @param amount the amount
   */
  public void topUpAccountBalance(Customer customer, int amount){}

}
