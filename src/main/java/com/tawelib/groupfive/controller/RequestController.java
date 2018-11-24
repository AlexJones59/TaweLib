package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;
import java.util.List;

/**
 * File Name - RequestController.java The Request Controller class handles data flow between the
 * Request Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
public class RequestController {

  /**
   * Create request.
   *
   * @param customerUsername the customer username
   * @param requestedResource the requested resource
   */
  public void createRequest(String customerUsername, Resource requestedResource) {}


  /**
   * Gets all reserved resources from specific customer.
   *
   * @param customerId the customer id
   * @return all reserved resources from specific customer
   */
  public List<Resource> getCustomerReserved(String customerId) {
    return null;
  }


  /**
   * Gets earliest requesting customer.
   *
   * @param resource the resource
   * @return the earliest requesting customer
   */
  public Customer getEarliestRequestingCustomer(Resource resource) {
    return null;
  }

}
