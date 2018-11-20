package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.exception.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements UserRepository<Customer> {

    private ArrayList<Customer> customers;

    private static long userNumber = 0;

    private static String userPrefix = "US";

    public CustomerRepository() {
        customers = new ArrayList<>();
    }

    @Override
    public Customer authenticate(String username) {
        for (Customer customer : customers) {
            if (true) { //TODO: check if the username is the one we are looking for.
                customers.contains(username);
                return customer;
            }
        }

        throw new AuthenticationException();
    }

    @Override
    public void generateUsername(Customer customer) {
        //TODO: set the customer's username to a generated one making sure it's unique.
        String userId = String.valueOf(userPrefix + userNumber++);
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }
}
