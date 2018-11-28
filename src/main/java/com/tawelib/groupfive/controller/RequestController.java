package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;

/**
 * File Name - RequestController.java The Request Controller class handles data
 * flow between the Request Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
public class RequestController {

  /**
   * Create request.
   *
   * @param customerUsername  the customer username
   * @param requestedResource the requested resource
   */
  public void createRequest(Library library, String customerUsername,
      Resource requestedResource) {
    Request newRequest = new Request(customerUsername, requestedResource);
    library.getRequestRepository().add(newRequest);
  }

}
