package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - LeaseRepository.java The Lease repository class handles lease details.
 *
 * @author Created by Themis, Modified by Shree Desai
 * @version 1.0
 */
public class LeaseRepository implements BaseRepository<Lease> {

  private ArrayList<Lease> leases;

  private int lastLeaseId = 0;

  /**
   * Initializes a new LeaseRepository.
   */
  public LeaseRepository() {
    leases = new ArrayList<>();
  }

  /**
   * Gets Copy lease history.
   *
   * @param copy The Copy.
   * @return The leases.
   */
  public List<Lease> getCopyLeaseHistory(Copy copy) {
    ArrayList<Lease> result = new ArrayList<>();

    for (Lease lease : leases) {
      if (lease.getBorrowedCopy() == copy) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Gets Customer lease history.
   *
   * @param customer The customer.
   * @return The leases.
   */
  public List<Lease> getCustomerLeaseHistory(Customer customer) {
    ArrayList<Lease> result = new ArrayList<>();

    for (Lease lease : leases) {
      if (lease.getBorrowingCustomer() == customer) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Gets copy current lease.
   *
   * @param copy The Copy.
   * @return the copy current lease
   */
  public Lease getCopyCurrentLease(Copy copy) {
    if (copy.getStatus() == CopyStatus.BORROWED) {
      Lease lease;

      //NOTE: Counting backwards.
      for (int i = leases.size() - 1; i >= 0; i--) {
        lease = leases.get(i);

        if (lease.getBorrowedCopy() == copy) {
          return lease;
        }
      }
    }

    throw new RuntimeException("Lease not found.");
  }

  /**
   * Gets customer current leases.
   *
   * @param customer the customer
   * @return the customer current leases
   */

  public List<Lease> getCustomerCurrentLeases(Customer customer) {
    ArrayList<Lease> customerCurrentLeases = new ArrayList<>();
    for (Lease lease : getCustomerLeaseHistory(customer)) {
      if (lease.getDateReturned() == null) {
        customerCurrentLeases.add(lease);
      }
    }
    return customerCurrentLeases;

  }

  /**
   * Gets customer overdue leases.
   *
   * @param customer the customer username
   * @return the customer overdue leases
   */
  public List<Lease> getCustomerOverdueLeases(Customer customer) {
    ArrayList<Lease> result = new ArrayList<>();

    for (Lease lease : getOverdueLeases()) {
      if (lease.getBorrowingCustomer() == customer) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Gets overdue leases.
   *
   * @return the overdue leases
   */
  public List<Lease> getOverdueLeases() {
    ArrayList<Lease> result = new ArrayList<>();
    LocalDateTime currentDate = SimulatedLocalDateTime.now();

    for (Lease lease : leases) {
      if (
          lease.getDueDate() != null
              && currentDate.isAfter(lease.getDueDate())
              && lease.getDateReturned() == null
      ) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Gets overdue leases.
   *
   * @param query Query.
   * @return the overdue leases
   */
  public List<Lease> searchOverdueLeases(String query) {
    ArrayList<Lease> result = new ArrayList<>();

    for (Lease lease : getOverdueLeases()) {
      if (query.isEmpty()
          || lease.getBorrowingCustomer().getUsername().contains(query)
          || lease.getBorrowedCopy().getId().contains(query)
          || lease.getBorrowedCopy().getResource().getTitle()
          .contains(query)
      ) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Gets resource type leases.
   *
   * @param resourceType the resource type
   * @return the resource type leases
   */
  public List<Lease> getResourceTypeLeases(ResourceType resourceType) {
    ArrayList<Lease> result = new ArrayList<>();

    for (Lease lease : leases) {
      if (lease.getBorrowedCopy().getResource().getType() == resourceType) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Gets customer resource type leases.
   *
   * @param resourceType the resource type
   * @param customer the customer
   * @return the customer resource type leases
   */
  public List<Lease> getCustomerResourceTypeLeases(ResourceType resourceType, Customer customer) {
    ArrayList<Lease> result = new ArrayList<>();

    for (Lease lease : getResourceTypeLeases(resourceType)) {
      if (lease.getBorrowingCustomer() == customer) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Generates a unique id for the request.
   *
   * @param lease lease
   * @deprecated Use reference.
   */
  @Deprecated
  private void generateLeaseId(Lease lease) {
    try {
      Field idField = lease.getClass().getDeclaredField("leaseId");
      idField.setAccessible(true);
      idField.set(lease, lastLeaseId);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    } finally {
      lastLeaseId++;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Lease> getAll() {
    return leases;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Lease lease) {
    if (!leases.contains(lease)) {
      leases.add(lease);
    }
  }
}


