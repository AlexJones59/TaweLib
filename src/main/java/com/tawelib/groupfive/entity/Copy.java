package com.tawelib.groupfive.entity;

//import com.tawelib.groupfive.repository.LeaseRepository;

import java.util.List;

/**
 * File Name - Copy.java The ‘Copy’ class is the class that stores information about a particular
 * copy of a resource.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Copy {

  private String id;
  private CopyStatus status;
  private Resource resource;
  private String borrowingCustomerUsername;


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
   * Gets borrowing customer username.
   *
   * @return the borrowing customer username
   */
  public String getBorrowingCustomerUsername() {
    return borrowingCustomerUsername;
  }

  /**
   * Sets borrowing customer username.
   *
   * @param borrowingCustomerUsername the borrowing customer username
   */
  public void setBorrowingCustomerUsername(String borrowingCustomerUsername) {
    this.borrowingCustomerUsername = borrowingCustomerUsername;
  }

  /**
   * Gets resource.
   *
   * @return the resource
   */
  public Resource getResource() {
    return resource;
  }
}



