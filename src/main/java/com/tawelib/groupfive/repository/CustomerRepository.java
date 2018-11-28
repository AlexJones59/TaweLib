package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - CustomerRepository.java The Customer repository class hadles customer details.
 *
 * @author Created by Themis
 * @version 0.2
 */
public class CustomerRepository implements UserRepository<Customer> {

  private ArrayList<Customer> customers;

  private int lastCustomerNumber = 0;

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
   * Gets a pecific customer.
   * @param username
   * @return the customer
   */
  private Customer getSpecific(String username) {
    for (Customer customer : customers) {
      if (customer.getUsername().equals(username)) {
        return customer;
      }
    }
    return null;
  }

  /**
   * Generates a customer unique username.
   */
  public void generateUsername(Customer customer) {
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

    try {
      Field usernameField = customer.getClass().getSuperclass().getDeclaredField("username");
      usernameField.setAccessible(true);
      usernameField.set(customer, generatedUsername);
      usernameField.setAccessible(false);
    } catch (Exception e) {
      throw new IllegalStateException(
          "Error message"
      );
    }
  }

  /**
   * Generates a customer number.
   * @param customer
   */
  private void generateCustomerNumber(Customer customer) {
    try {
      Field usernameField = customer.getClass().getDeclaredField("staffNumber");
      usernameField.setAccessible(true);
      usernameField.set(customer, lastCustomerNumber);
    } catch (Exception e) {
      throw new IllegalStateException(
          "Error message"
      );
    } finally {
      lastCustomerNumber++;
    }
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
      generateCustomerNumber(customer);
      customers.add(customer);
    }
  }

}
