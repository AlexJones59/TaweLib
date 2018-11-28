package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * File Name - RequestRepository.java The Request repository class handles all
 * instances of requests.
 *
 * @author Themis Mouyiasis, Modified by Nayeem Mohammed & Shree Desai.
 */
public class RequestRepository implements BaseRepository<Request> {

  private static ArrayList<Request> requests;

  private static Hashtable<String, Request> RequestTable = new Hashtable<String, Request>();

  /**
   * Gets all open customer requests.
   *
   * @param customerId the customer id
   * @return open customer requests
   */
  public List<Request> getCustomerRequests(String customerId) {
    return null;
  }

  /**
   * Gets all requests for a specific resource.
   *
   * @param requestedResource the requested resource
   * @return the resource request
   */
  public List<Request> getResourceRequests(Resource requestedResource) {
    return null;
  }

  /**
   * Gets all requests, where status has been changed to Reserved, for a
   * specific customer.
   *
   * @param customerId the customer id
   * @return all reserved requests from specific customer
   */
  public List<Request> getCustomerReserved(String customerId) {
    return null;
  }

  /**
   * Gets earliest resource request.
   *
   * @param resource the resource
   * @return the earliest resource request
   */
  public Request getEarliestResourceRequest(Resource resource) {
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
    requests.add(request);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Request getSpecific(String requestId) {
    return null;
  }
}
