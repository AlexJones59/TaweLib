package com.tawelib.groupfive.entity;

import java.time.LocalDateTime;

/**
 * Librarian.java Stores personal information for the Librarian account, inherits from superclass
 * User.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Librarian extends User {

  private final LocalDateTime employmentDate;
  private int staffNumber;

  /**
   * Instantiates a new Librarian.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @param phoneNumber the phone number
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postCode the post code
   * @param employmentDate the employment date
   */
  public Librarian(String firstName, String lastName, String phoneNumber,
      String houseNumber, String street, String city,
      String postCode, LocalDateTime employmentDate) {
    super(firstName, lastName, phoneNumber, houseNumber, street, city,
        postCode);
    this.employmentDate = employmentDate;
  }

  /**
   * Gets staff number.
   *
   * @return the staff number
   */
  public int getStaffNumber() {
    return staffNumber;
  }
}
