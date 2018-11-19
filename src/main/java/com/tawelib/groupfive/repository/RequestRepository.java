package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;

public class RequestRepository extends BaseRepository {

  public Request getResourceRequests(Customer customer) {
    return null;
  }

  public Request getRequestingCustomers(Resource customer) {
    return null;
  }

  public Resource getReservedResources(Customer customer) {
    return null;
  }

  public Customer getReservingCustomer(Resource resource) {
    return null;
  }

  public Request getEarliestResourceRequest(Resource recourse) {
    return null;
  }
}
