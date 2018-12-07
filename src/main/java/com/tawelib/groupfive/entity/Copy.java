package com.tawelib.groupfive.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Copy.java The ‘Copy’ class is the class that stores information about a particular copy of a
 * resource.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Copy implements Serializable {

  private String id;
  private CopyStatus status;
  private Resource resource;
  private Customer borrowingCustomer;


  /**
   * Instantiates a new Copy.
   *
   * @param resource the resource
   */
  public Copy(Resource resource) {
    this.resource = resource;
    this.status = CopyStatus.AVAILABLE;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Gets whether the copy is available or not.
   *
   * @return the status
   */
  public CopyStatus getStatus() {
    return status;
  }

  /**
   * Sets whether a copy is available or not.
   *
   * @param status the status
   */
  public void setStatus(CopyStatus status) {
    this.status = status;
  }


  /**
   * Gets resource.
   *
   * @return the resource
   */
  public Resource getResource() {
    return resource;
  }

  /**
   * Gets customer who has taken out the copy.
   *
   * @return the customer who is borrowing the copy
   */
  public Customer getBorrowingCustomer() {
    return borrowingCustomer;
  }

  /**
   * Sets customer who has taken out the copy.
   *
   * @param borrowingCustomer the customer who is borrowing the copy
   */
  public void setBorrowingCustomer(Customer borrowingCustomer) {
    this.borrowingCustomer = borrowingCustomer;
  }
}



