package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;
import java.util.List;

public class RequestRepository implements BaseRepository<Request> {

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

  @Override
  public List<Request> getAll() {
    return null;
  }
  
  @Override
  public void add(Request entity) {

  }
}
