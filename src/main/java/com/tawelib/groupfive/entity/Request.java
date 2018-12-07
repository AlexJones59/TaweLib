package com.tawelib.groupfive.entity;

import java.io.Serializable;

/**
 * Request.java The Request class is the  class that stores all the information pertaining
 * to a request.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Request implements Serializable {

  private RequestStatus status;
  private final Customer customer;
  private final Resource requestedResource;

  /**
   * Instantiates a new Request.
   *
   * @param customer the customer username
   * @param requestedResource the requested resource
   */
  public Request(Customer customer, Resource requestedResource) {
    this.customer = customer;
    this.status = RequestStatus.REQUESTED;
    this.requestedResource = requestedResource;
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public RequestStatus getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(RequestStatus status) {
    this.status = status;
  }

  /**
   * Gets customer.
   *
   * @return the customer
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Gets requested resource.
   *
   * @return the requested resource
   */
  public Resource getRequestedResource() {
    return requestedResource;
  }
}
