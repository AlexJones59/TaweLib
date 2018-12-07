package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * File Name - CustomerRepository.java The Customer repository class hadles
 * customer details.
 *
 * @author Created by Themis, Modified by Shree Desai
 * @version 1.0
 */
public class CustomerRepository implements UserRepository<Customer> {

  private ArrayList<Customer> customers;

  /**
   * Instantiates a new Customer repository.
   */
  public CustomerRepository() {
    customers = new ArrayList<>();
  }


  /**
   * Checks if the customer is in the list by its username.
   *
   * @param username Username.
   * @return the customer
   */
  @Override
  public Customer authenticate(String username) {
    Customer customer = getSpecific(username);
    if (customer == null) {
      throw new AuthenticationException();
    } else {
      return customer;
    }
  }

  /**
   * Gets a specific customer.
   *
   * @param username Username.
   * @return the customer
   */
  public Customer getSpecific(String username) {
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username)) {
        return customer;
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends User> search(String query) {
    ArrayList<User> foundUsers = new ArrayList<>();

    for (Customer customer : customers) {
      if (
          customer.getUsername().contains(query)
              || customer.getFullName().contains(query)
      ) {
        foundUsers.add(customer);
      }
    }

    return foundUsers;
  }

  /**
   * Generates a customer unique username.
   */
  private void generateUsername(Customer customer) {
    String baseUsername = String.format("%s.%s",
        customer.getFirstName().toLowerCase(),
        customer.getLastName().toLowerCase()
    );
    String usernameSuffix = "";
    int suffixBase = 1;
    String generatedUsername = baseUsername + usernameSuffix;

    while (getSpecific(generatedUsername) != null) {
      usernameSuffix = String.format(".%d", suffixBase);
      generatedUsername = baseUsername + usernameSuffix;
      suffixBase++;
    }

    assignGeneratedId(
        customer,
        generatedUsername
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Customer> getAll() {
    return customers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Customer customer) {
    if (!customers.contains(customer)) {
      generateUsername(customer);
      customers.add(customer);
    }
  }

}
