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

  /**
   * Instantiates a new Request repository.
   */
  public RequestRepository() {
    requests = new ArrayList<>();
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
      if ( request.getCustomer() == customer
              && request.getStatus().equals(RequestStatus.REQUESTED)
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
   * @param customer the customer
   * @param requestedResource the requested resource
   * @return the request
   */
  public Request getSpecificReserved(Customer customer,
      Resource requestedResource) {
    for (Request request : getCustomerReserved(customer)) {
      if (request.getRequestedResource().equals(requestedResource)) {
        return request;
      }
    }
    return null;
  }

  /**
   * Get specific request.
   *
   * @param customer the customer
   * @param requestedResource the requested resource
   * @return the request
   */
  public Request getSpecificRequest(Customer customer,
      Resource requestedResource) {
    for (Request request : getOpenCustomerRequests(customer)) {
      if (request.getRequestedResource().equals(requestedResource)) {
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
