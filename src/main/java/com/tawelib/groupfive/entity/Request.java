package com.tawelib.groupfive.entity;

import java.io.Serializable;

/**
 * File Name - Request.java The Request class is the  class that stores all the
 * information pertaining to a request.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Request implements Serializable {

  @Deprecated
  private String requestId;

  private RequestStatus status;

  @Deprecated
  private String customerUsername;

  private Customer customer;
  private Resource requestedResource;

  /**
   * Instantiates a new Request.
   *
   * @param customerUsername the customer username
   * @param requestedResource the requested resource
   */
  @Deprecated
  public Request(String customerUsername, Resource requestedResource) {
    this.customerUsername = customerUsername;
    this.status = RequestStatus.REQUESTED;
    this.requestedResource = requestedResource;
  }

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
   * Gets request id.
   *
   * @return the request id
   */
  @Deprecated
  public String getRequestId() {
    return requestId;
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
   * Gets customer username.
   *
   * @return the customer username
   */
  @Deprecated
  public String getCustomerUsername() {
    return customerUsername;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
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
