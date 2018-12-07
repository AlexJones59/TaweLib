package com.tawelib.groupfive.entity;

//import com.tawelib.groupfive.repository.LeaseRepository;

import java.io.Serializable;
import java.util.List;

/**
 * File Name - Copy.java The ‘Copy’ class is the class that stores information
 * about a particular copy of a resource.
 *
 * @author Shree Desai
 * @version 0.2
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
   * Gets status.
   *
   * @return the status
   */
  public CopyStatus getStatus() {
    return status;
  }

  /**
   * Sets status.
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

  public Customer getBorrowingCustomer() {
    return borrowingCustomer;
  }

  public void setBorrowingCustomer(Customer borrowingCustomer) {
    this.borrowingCustomer = borrowingCustomer;
  }
}



