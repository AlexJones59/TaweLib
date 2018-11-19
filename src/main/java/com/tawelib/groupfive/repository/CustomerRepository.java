package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements UserRepository<Customer> {

  private ArrayList<Customer> Customers;

  public CustomerRepository() {
    Customers = new ArrayList<>();
  }

  /**
   * This method returns a User with a given username.
   *
   * @param username Username
   * @return User
   */
  @Override
  public Customer authenticate(String username) {
    for (Customer Customer : Customers) {
      if (true) { //TODO: check if the username is the one we are looking for.
        return Customer;
      }
    }

    throw new AuthenticationException();
  }

  /**
   * This method generates a username for a given user.
   *
   * @param Customer Customer or Customer
   */
  @Override
  public void generateUsername(Customer Customer) {
    //TODO: set the Customer's username to a generated one making sure it's unique.
  }

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  @Override
  public List<Customer> getAll() {
    return Customers;
  }

  /**
   * This method persists an entity in the repository.
   *
   * @param Customer Entity to be added
   */
  @Override
  public void add(Customer Customer) {
    Customers.add(Customer);
  }
}
