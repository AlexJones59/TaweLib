package com.tawelib.groupfive.entity;

/**
 * Address.java This class stores all the elements needed for an address.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Address {

  private String houseNumber;
  private String street;
  private String city;
  private String postCode;

  /**
   * Creates new instance of the Address Class.
   *
   * @param houseNumber the house number
   * @param street      the street
   * @param city        the city
   * @param postCode    the post code
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
   * Sets house number.
   *
   * @param houseNumber the house number
   */
  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
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
   * Sets street.
   *
   * @param street the street
   */
  public void setStreet(String street) {
    this.street = street;
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
   * Sets city.
   *
   * @param city the city
   */
  public void setCity(String city) {
    this.city = city;
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
   * Sets post code.
   *
   * @param postCode the post code
   */
  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }
}
