package com.tawelib.groupfive.entity;

import java.util.Date;

/**
 * File Name: Librarian.java Librarian class stores personal information for
 * the Librarian account.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Librarian extends User {

  private final Date employmentDate;
  private int staffNumber;

  /**
   * Instantiates a new Librarian.
   *
   * @param firstName      the first name
   * @param lastName       the last name
   * @param phoneNumber    the phone number
   * @param houseNumber    the house number
   * @param street         the street
   * @param city           the city
   * @param postCode       the post code
   * @param employmentDate the employment date
   */
  public Librarian(String firstName, String lastName, String phoneNumber,
                   String houseNumber, String street, String city,
                   String postCode, Date employmentDate) {
    super(firstName, lastName, phoneNumber, houseNumber, street, city,
        postCode);
    this.employmentDate = employmentDate;
  }

  /**
   * Gets employment date.
   *
   * @return the employment date
   */
  public Date getEmploymentDate() {
    return employmentDate;
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
