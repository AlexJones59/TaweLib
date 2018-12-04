package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Resource;
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

  private ArrayList<Lease> leases;

  private int lastLeaseId = 0;

  public LeaseRepository() {
    leases = new ArrayList<>();
  }

  /**
   * Gets specific.
   *
   * @param leaseId the lease id
   * @return the specific
   *
   * @deprecated Use reference.
   */
  @Deprecated
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
   *
   * @deprecated Use reference.
   */
  @Deprecated
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
   * Gets Copy lease history.
   *
   * @param copy The Copy.
   * @return The leases.
   */
  public List<Lease> getCopyLeaseHistory(Copy copy) {
    ArrayList<Lease> result = new ArrayList<>();

    for (Lease lease : leases) {
      if (lease.getCopy() == copy) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Gets Customer lease history.
   *
   * @param customer The Copy.
   * @return The leases.
   */
  public List<Lease> getCopyLeaseHistory(Customer customer) {
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
   * @param copyId the copy id
   * @return the copy current lease
   *
   * @deprecated Use reference.
   */
  @Deprecated
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

        if (lease.getCopy() == copy) {
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
   *
   * @deprecated Use reference.
   */
  @Deprecated
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
   *
   * @deprecated Use reference.
   */
  @Deprecated
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
   * Gets customer overdue leases.
   *
   * @param customer the customer username
   * @return the customer overdue leases
   */
  public List<Lease> getOverdueLeases(Customer customer) {
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
    Date currentDate = new Date();

    for (Lease lease : leases) {
      if (lease.getDueDate().after(currentDate)) {
        result.add(lease);
      }
    }

    return result;
  }

  /**
   * Generates a unique id for the request.
   *
   * @param lease lease
   *
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


