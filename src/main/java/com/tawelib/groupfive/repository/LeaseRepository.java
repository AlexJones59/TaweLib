package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Lease;
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
  
  /**
   * Gets specific.
   *
   * @param leaseId the lease id
   * @return the specific
   */
  public Lease getSpecific(String leaseId) {
    for (Lease lease : leases) {
      if (lease.getBorrowedCopyId().equals(leaseId)) {
        return lease;
      }
    }
    throw new IllegalStateException(
        "Error message"
    );
  }

  /**
   * Gets copy leases.
   *
   * @param copyId the copy id
   * @return the copy leases
   */
  public List<Lease> getCopyLeases(String copyId) {
    for (Lease lease : leases) {
      //TODO check for a copy leases by a copy id.
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
    for (Lease lease : leases) {
      if (lease.getBorrowedCopyId().equals(copyId)) {
        return (List<Lease>) lease;
      }
    }
    throw new IllegalStateException(
        "Error message"
    );
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
    throw new IllegalStateException(
        "Error message"
    );
  }

  /**
   * Gets customer current leases.
   *
   * @param customerUsername the customer username
   * @return the customer current leases
   */
  public List<Lease> getCustomerCurrentLeases(String customerUsername) {
    for (Lease lease : leases) {
      if (lease.getBorrowingCustomerUsername().equals(customerUsername)) {
        return (List<Lease>) lease;
      }
    }

    throw new IllegalStateException(
        "Error message"
    );
  }

  /**
   * Gets customer overdue leases.
   *
   * @param customerUsername the customer username
   * @return the customer overdue leases
   */
  public List<Lease> getCustomerOverdueLeases(String customerUsername) {
    Date currentDate = new Date();
    for (Lease lease : leases) {
      if (lease.getBorrowingCustomerUsername().equals(customerUsername) && lease.getDueDate()
          .after(currentDate)) {
        return (List<Lease>) lease;
      }
    }
    throw new IllegalStateException(
        "Error message"
    );
  }

  /**
   * Gets overdue leases.
   *
   * @return the overdue leases
   */
  public List<Lease> getOverdueLeases() {
    Date currentDate = new Date();
    for (Lease lease : leases) {
      if (lease.getDueDate().after(currentDate)) {
        return (List<Lease>) lease;
      }
    }
    throw new IllegalStateException(
        "Error message"
    );
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


