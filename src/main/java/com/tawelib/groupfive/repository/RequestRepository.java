package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.RequestStatus;
import com.tawelib.groupfive.entity.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - RequestRepository.java The Request repository class handles all
 * instances of requests.
 *
 * @author Themis Mouyiasis, Modified by Shree Desai.
 */
public class RequestRepository implements BaseRepository<Request> {

  private ArrayList<Request> requests;

  public RequestRepository() {
    requests = new ArrayList<>();
  }

  /**
   * Gets all open customer requests.
   *
   * @param customerUsername the customer username
   * @return open customer requests
   */
  @Deprecated
  public List<Request> getOpenCustomerRequests(String customerUsername) {
    ArrayList<Request> customerRequests = new ArrayList<Request>();
    for (Request request : requests) {
      if (request.getCustomerUsername().equals(customerUsername)
          && !request.getStatus().equals(RequestStatus.CLOSED)) {
        customerRequests.add(request);
      }
    }
    if (customerRequests.isEmpty()) {
      return customerRequests;
    }
    return null;
  }

  /**
   * Gets all open customer requests.
   *
   * @param customer the customer username
   * @return open customer requests
   */
  public List<Request> getOpenCustomerRequests(Customer customer) {
    ArrayList<Request> result = new ArrayList<>();

    for (Request request : requests) {
      if (
          request.getCustomer() == customer
              && !request.getStatus().equals(RequestStatus.CLOSED)
      ) {
        result.add(request);
      }
    }

    return result;
  }

  /**
   * Gets all open requests for a specific resource.
   *
   * @param requestedResource the requested resource
   * @return the resource request
   */
  public List<Request> getOpenResourceRequests(Resource requestedResource) {
    ArrayList<Request> result = new ArrayList<>();

    for (Request request : requests) {
      if (
          request.getRequestedResource() == requestedResource
              && request.getStatus().equals(RequestStatus.REQUESTED)
      ) {
        result.add(request);
      }
    }

    return result;
  }

  /**
   * Gets all requests, where status has been changed to Reserved, for a
   * specific customer.
   *
   * @param customerUsername the customer id
   * @return all reserved requests from specific customer
   */
  @Deprecated
  public List<Request> getCustomerReserved(String customerUsername) {
    ArrayList<Request> customerReserved = new ArrayList<Request>();
    for (Request request : requests) {
      if (request.getCustomerUsername().equals(customerUsername)
          && request.getStatus().equals(RequestStatus.RESERVED)) {
        customerReserved.add(request);
      }
    }
    if (customerReserved.isEmpty()) {
      return customerReserved;
    }
    return null;
  }

  /**
   * Gets all requests, where status has been changed to Reserved, for a
   * specific customer.
   *
   * @param customer the customer id
   * @return all reserved requests from specific customer
   */
  public List<Request> getCustomerReserved(Customer customer) {
    ArrayList<Request> result = new ArrayList<>();

    for (Request request : requests) {
      if (
          request.getCustomer() == customer
              && request.getStatus().equals(RequestStatus.RESERVED)
      ) {
        result.add(request);
      }
    }

    return result;
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

    return null;
  }

  /**
   * Get specific reserved request.
   *
   * @param customerUsername the customer username
   * @param requestedResource the requested resource
   * @return the request
   */
  @Deprecated
  public Request getSpecificReserved(String customerUsername,
      Resource requestedResource) {
    for (Request request : requests) {
      if (request.getCustomerUsername().equals(customerUsername)
          | request.getRequestedResource().equals(requestedResource)) {
        return request;
      }
    }
    return null;
  }


  /**
   * Gets specific.
   *
   * @param requestId the request id
   * @return the specific
   */
  @Deprecated
  public Request getSpecific(String requestId) {
    for (Request request : requests) {
      if (request.getRequestId().equals(requestId)) {
        return request;
      }
    }
    return null;
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
