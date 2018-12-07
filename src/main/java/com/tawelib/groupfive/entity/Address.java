package com.tawelib.groupfive.entity;

import java.io.Serializable;

/**
 * Address.java This class stores all the elements needed for an address.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Address implements Serializable {

  private String houseNumber;
  private String street;
  private String city;
  private String postCode;

  /**
   * Creates new instance of the Address Class.
   *
   * @param houseNumber the house number
   * @param street the street
   * @param city the city
   * @param postCode the post code
   */
  public Address(String houseNumber, String street, String city,
      String postCode) {
    this.houseNumber = houseNumber;
    this.street = street;
    this.city = city;
    this.postCode = postCode;
  }

  /**
   * Gets house number.
   *
   * @return the house number
   */
  public String getHouseNumber() {
    return houseNumber;
  }

  /**
   * Gets street.
   *
   * @return the street
   */
  public String getStreet() {
    return street;
  }

  /**
   * Gets city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Gets post code.
   *
   * @return the post code
   */
  public String getPostCode() {
    return postCode;
  }

  /**
   * Brings all the individual address elements into one statement to output {@inheritDoc}
   */
  @Override
  public String toString() {
    return houseNumber + ", " + street + ", " + city + ", " + postCode;
  }
}
