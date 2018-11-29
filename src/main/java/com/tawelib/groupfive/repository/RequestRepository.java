package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.RequestStatus;
import com.tawelib.groupfive.entity.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - RequestRepository.java The Request repository class handles all
 * instances of requests.
 *
 * @author Themis Mouyiasis, Modified by Shree Desai.
 */
public class RequestRepository implements BaseRepository<Request> {

  private static ArrayList<Request> requests;

  private int lastRequestId = 0;


  /**
   * Gets all open customer requests.
   *
   * @param customerUsername the customer username
   * @return open customer requests
   */
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
    throw new IllegalStateException(
        "The universe is about to end!!! No, but the class reflection is broken..."
    );
  }

  /**
   * Gets all open requests for a specific resource.
   *
   * @param requestedResource the requested resource
   * @return the resource request
   */
  public List<Request> getOpenResourceRequests(Resource requestedResource) {
    ArrayList<Request> resourceRequests = new ArrayList<Request>();
    for (Request request : requests) {
      if (request.getRequestedResource().equals(requestedResource)
          && request.getStatus().equals(RequestStatus.REQUESTED)) {
        resourceRequests.add(request);
      }
    }
    if (resourceRequests.isEmpty()) {
      return resourceRequests;
    }
    return null;
  }

  /**
   * Gets all requests, where status has been changed to Reserved, for a
   * specific customer.
   *
   * @param customerUsername the customer id
   * @return all reserved requests from specific customer
   */
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
   * Gets earliest resource request.
   *
   * @param resource the resource
   * @return the earliest resource request
   */
  public Request getEarliestResourceRequest(Resource resource) {
    for (Request request : requests) {
      if (request.getRequestedResource().equals(resource)) {
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
  public Request getSpecific(String requestId) {
    for (Request request : requests) {
      if (request.getRequestId().equals(requestId)) {
        return request;
      }
    }
    return null;
  }

  /**
   * Generates a unique id for the request.
   *
   * @param request request
   */
  private void generateRequestId(Request request) {
    try {
      Field usernameField = request.getClass().getDeclaredField("requestId");
      usernameField.setAccessible(true);
      usernameField.set(request, lastRequestId);
    } catch (Exception e) {
      throw new IllegalStateException(
          "Request ID could not be set."
      );
    } finally {
      lastRequestId++;
    }
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
      generateRequestId(request);
      requests.add(request);
    }

  }

}
