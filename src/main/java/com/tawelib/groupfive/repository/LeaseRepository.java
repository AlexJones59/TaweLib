package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Lease;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * File Name - LeaseRepository.java The Lease repository class handles lease details.
 *
 * @author Created by Themis, Modified by Shree Desai
 * @version 0.4
 */
public class LeaseRepository implements BaseRepository<Lease> {

  private static ArrayList<Lease> leases;

  private int lastLeaseId = 0;
  
  /**
   * Gets specific.
   *
   * @param leaseId the lease id
   * @return the specific
   */
  public Lease getSpecific(String leaseId) {
    for (Lease lease : leases) {
      if (lease.getLeaseId().equals(leaseId)) {
        return lease;
      }
    }
    return null;
  }

  /**
   * Gets copy lease history.
   *
   * @param copyId the copy id
   * @return the copy lease history
   */
  public List<Lease> getCopyLeaseHistory(String copyId) {
    ArrayList<Lease> copyLeaseHistory = new ArrayList<Lease>();
    for (Lease lease : leases) {
      if (lease.getBorrowedCopyId().equals(copyId)) {
        copyLeaseHistory.add(lease);
      }
    }
    if (copyLeaseHistory.isEmpty()) {
      return copyLeaseHistory;
    }
    return null;

  }

  /**
   * Gets copy current lease.
   *
   * @param copyId the copy id
   * @return the copy current lease
   */
  public Lease getCopyCurrentLease(String copyId) {
    for (Lease lease : leases) {
      if (lease.getBorrowedCopyId().equals(copyId)) {
        if (lease.getDateReturned() == null) {
          return lease;
        }
      }
    }
    return null;
  }

  /**
   * Gets customer current leases.
   *
   * @param customerUsername the customer username
   * @return the customer current leases
   */
  public List<Lease> getCustomerCurrentLeases(String customerUsername) {
    ArrayList<Lease> customerCurrentLeases = new ArrayList<Lease>();
    for (Lease lease : leases) {
      if (lease.getBorrowingCustomerUsername().equals(customerUsername)) {
        customerCurrentLeases.add(lease);
      }
    }
    if (customerCurrentLeases.isEmpty()) {
      return customerCurrentLeases;
    }
    return null;
  }

  /**
   * Gets customer overdue leases.
   *
   * @param customerUsername the customer username
   * @return the customer overdue leases
   */
  public List<Lease> getCustomerOverdueLeases(String customerUsername) {
    ArrayList<Lease> customerOverdue = new ArrayList<Lease>();
    Date currentDate = new Date();
    for (Lease lease : leases) {
      if (lease.getBorrowingCustomerUsername().equals(customerUsername) && lease.getDueDate()
          .after(currentDate)) {
        customerOverdue.add(lease);
      }
    }
    if (customerOverdue.isEmpty()) {
      return customerOverdue;
    }
    return null;
  }

  /**
   * Gets overdue leases.
   *
   * @return the overdue leases
   */
  public List<Lease> getOverdueLeases() {
    ArrayList<Lease> overdueLeases = new ArrayList<Lease>();
    Date currentDate = new Date();
    for (Lease lease : leases) {
      if (lease.getDueDate().after(currentDate)) {
        overdueLeases.add(lease);
      }
    }
    if (overdueLeases.isEmpty()) {
      return overdueLeases;
    }
    return null;
  }

  /**
   * Generates a unique id for the request.
   *
   * @param lease lease
   */
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
      generateLeaseId(lease);
      leases.add(lease);
    }
  }


}


