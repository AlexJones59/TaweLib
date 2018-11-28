package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.exception.AuthenticationException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * File Name - CustomerRepository.java The Customer repository class handles customer details.
 *
 * @author Created by Themis
 * @version 0.2
 */
public class CustomerRepository implements UserRepository<Customer> {

  private ArrayList<Customer> customers;

  private static long userNumber = 0;

  private static final String USER_PREFIX = "US";

  private static Hashtable<String, Customer> CustomerTable = new Hashtable<String, Customer>();

  /**
   * Instantiates a new Customer repository.
   */
  public CustomerRepository() {
    customers = new ArrayList<>();
  }

  /**
   * Gets specific.
   *
   * @param customerUsername the customer username
   * @return the specific
   */
  public Customer getSpecific(String customerUsername) {
    return null;
  }

  /**
   * Checks if the customer is in the list by its username.
   *
   * @param username Username.
   * @return the customer
   */
  @Override
  public Customer authenticate(String username) {
    for (Customer customer : customers) {
      if (true) { //TODO: check if the username is the one we are looking for.
        // replace true with: customers.getUsername() == username;
        return customer;
      }
    }

    throw new AuthenticationException();
  }

  /**
   * Generates a customer unique username.
   */
  @Override
  public void generateUsername(Customer customer) {
    //TODO: set the customer's username to a generated one making sure it's unique.
    String generatedUsername = String.format(
        "%s%s", USER_PREFIX, userNumber);
    userNumber++;
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
    customers.add(customer);
  }

}
