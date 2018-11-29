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

  private String requestId;
  private RequestStatus status;
  private String customerUsername;
  private Resource requestedResource;

  /**
   * Instantiates a new Request.
   *
   * @param customerUsername the customer username
   * @param requestedResource the requested resource
   */
  public Request(String customerUsername, Resource requestedResource) {
    this.customerUsername = customerUsername;
    this.status = RequestStatus.REQUESTED;
    this.requestedResource = requestedResource;
  }

  /**
   * Gets request id.
   *
   * @return the request id
   */
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
  public String getCustomerUsername() {
    return customerUsername;
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
