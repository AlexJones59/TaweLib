package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - RequestRepository.java The Request repository class handles all instances of
 * requests.
 *
 * @author Themis Mouyiasis, Modified by Nayeem Mohammed & Shree Desai.
 */
public class RequestRepository implements BaseRepository<Request> {

  private static ArrayList<Request> requests;


  /**
   * Gets all open customer requests.
   *
   * @param customerId the customer id
   * @return open customer requests
   */
  public List<Request> getCustomerRequests(String customerId) {
    for (Request request : requests) {
      if (request.getCustomerUsername().equals(customerId)) {
        return (List<Request>) request;
      }
    }
    throw new IllegalStateException(
        "The universe is about to end!!! No, but the class reflection is broken..."
    );
  }

  /**
   * Gets all requests for a specific resource.
   *
   * @param requestedResource the requested resource
   * @return the resource request
   */
  public List<Request> getResourceRequests(Resource requestedResource) {
    for (Request request : requests) {
      if (request.getRequestedResource() == requestedResource) {
        return (List<Request>) request;
      }
    }
    throw new IllegalStateException(
        "Error message.");
  }

  /**
   * Gets all requests, where status has been changed to Reserved, for a specific customer.
   *
   * @param customerId the customer id
   * @return all reserved requests from specific customer
   */
  public List<Request> getCustomerReserved(String customerId) {
    for (Request request : requests) {
      if (request.getCustomerUsername().equals(customerId)) {
        return (List<Request>) request;
      }
    }
    throw new IllegalStateException(
        "Error message.");
  }

  /**
   * Gets earliest resource request.
   *
   * @param resource the resource
   * @return the earliest resource request
   */
  public Request getEarliestResourceRequest(Resource resource) {
    for (Request request : requests) {
      if (request.getRequestedResource() == resource) {
        return request;
      }
    }
    throw new IllegalStateException(
        "Error message.");
  }

  /**
   * Gets specific.
   *
   * @param requestId the request id
   * @return the specific
   */
  public Request getSpecific(String requestId) {
    for (Request request : requests) {
      if (request.getRequestedResource().equals(requestId)) {
        return request;
      }
    }
    throw new IllegalStateException(
        "Error message"
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Request> getAll() {
    return requests;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Request request) {
    if (!requests.contains((request))) {
      requests.add(request);
    }

  }

}
