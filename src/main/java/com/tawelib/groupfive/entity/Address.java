package com.tawelib.groupfive.entity;

/**
 * This class stores all the elements needed for an address.
 */
public class Address {

  private int houseNumber;
  private String street;
  private String city;
  private String postCode;

  /**
   * Creates new instance of the Address Class
   */
  public Address(int houseNumber, String street, String city, String postCode) {
    this.houseNumber = houseNumber;
    this.street = street;
    this.city = city;
    this.postCode = postCode;
  }
}
