package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Address;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.entity.User;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * File Name - UserManager.java The User Controller class controls data flow
 * between the Customer and Librarian Repository and the GUI interfaces.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class UserManager {

  /**
   * Create customer account.
   *
   * @param library the library
   * @param firstName the first name
   * @param lastName the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  public static void createCustomerAccount(Library library, String firstName,
      String lastName, String phoneNumber, String houseNumber, String street,
      String city, String postcode) {
    Customer newCustomer = new Customer(firstName, lastName, phoneNumber,
        houseNumber, street, city, postcode);
    library.getCustomerRepository().add(newCustomer);
  }

  /**
   * Create librarian account.
   *
   * @param library the library
   * @param firstName the first name
   * @param lastName the last name
   * @param employmentDate the employment date
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  public static void createLibrarianAccount(Library library, String firstName,
      String lastName, LocalDateTime employmentDate, String phoneNumber,
      String houseNumber, String street, String city, String postcode) {
    Librarian newLibrarian = new Librarian(firstName, lastName, phoneNumber,
        houseNumber, street, city, postcode, employmentDate);
    library.getLibrarianRepository().add(newLibrarian);
  }


  /**
   * Update customer account.
   *
   * @param library the library
   * @param username the username
   * @param firstName the first name
   * @param lastName the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  @Deprecated
  public static void updateCustomerAccount(Library library,
      String username, String firstName, String lastName, String phoneNumber,
      String houseNumber, String street, String city, String postcode) {
    library.getCustomerRepository().getSpecific(username)
        .setFirstName(firstName);
    library.getCustomerRepository().getSpecific(username).setLastName(lastName);
    library.getCustomerRepository().getSpecific(username)
        .setPhoneNumber(phoneNumber);
    Address newAddress = new Address(houseNumber, street, city, postcode);
    library.getCustomerRepository().getSpecific(username)
        .setAddress(newAddress);
  }

  /**
   * Update customer account.
   *
   * @param library the library
   * @param customer the user
   * @param firstName the first name
   * @param lastName the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  public static void updateCustomerAccount(Library library,
      Customer customer, String firstName, String lastName, String phoneNumber,
      String houseNumber, String street, String city, String postcode) {
    updateUserAccount(
        library, customer, firstName, lastName, phoneNumber, houseNumber,
        street, city, postcode
    );
  }

  /**
   * Update librarian account.
   *
   * @param library the library
   * @param username the username
   * @param firstName the first name
   * @param lastName the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  @Deprecated
  public static void updateLibrarianAccount(Library library,
      String username, String firstName, String lastName, String phoneNumber,
      String houseNumber, String street, String city, String postcode) {
    library.getLibrarianRepository().getSpecific(username)
        .setFirstName(firstName);
    library.getLibrarianRepository().getSpecific(username)
        .setLastName(lastName);
    library.getLibrarianRepository().getSpecific(username)
        .setPhoneNumber(phoneNumber);
    Address newAddress = new Address(houseNumber, street, city, postcode);
    library.getLibrarianRepository().getSpecific(username)
        .setAddress(newAddress);
  }

  /**
   * Update librarian account.
   *
   * @param library the library
   * @param librarian the librarian
   * @param firstName the first name
   * @param lastName the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postcode the postcode
   */
  public static void updateLibrarianAccount(Library library,
      Librarian librarian, String firstName, String lastName,
      String phoneNumber,
      String houseNumber, String street, String city, String postcode) {
    updateUserAccount(library, librarian, firstName, lastName, phoneNumber,
        houseNumber, street, city, postcode);
  }

  /**
   * Updates user account.
   *
   * @param library Library.
   * @param user User.
   * @param firstName First name.
   * @param lastName Last name.
   * @param phoneNumber Phone number.
   * @param houseNumber House number.
   * @param street Street.
   * @param city City.
   * @param postcode Post code.
   */
  public static void updateUserAccount(Library library,
      User user, String firstName, String lastName, String phoneNumber,
      String houseNumber, String street, String city, String postcode) {
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setPhoneNumber(phoneNumber);
    Address newAddress = new Address(houseNumber, street, city, postcode);
    user.setAddress(newAddress);
  }

  /**
   * Top up account balance.
   *
   * @param library the library
   * @param customerUsername the customer username
   * @param amount the amount
   */
  public static void topUpAccountBalance(Library library,
      String customerUsername,
      int amount) {
    Customer payee = library.getCustomerRepository()
        .getSpecific(customerUsername);
    Transaction newTransaction = new Transaction(amount, payee);
    library.getTransactionRepository().add(newTransaction);
    library.getCustomerRepository().getSpecific(customerUsername)
        .increaseAccountBalance(amount);
  }

}
