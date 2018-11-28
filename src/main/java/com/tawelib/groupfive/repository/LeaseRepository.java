package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Lease;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * File Name - LeaseRepository.java The Lease repository class handles lease
 * details.
 *
 * @author Created by Themis, Modified by Shree Desai
 * @version 0.4
 */
public class LeaseRepository implements BaseRepository<Lease> {

  private static ArrayList<Lease> leases;

  private static Hashtable<String, Lease> LeaseTable = new Hashtable<String, Lease>();

  /**
   * Gets copy leases.
   *
   * @param copyId the copy id
   * @return the copy leases
   */
  public List<Lease> getCopyLeases(String copyId) {
    return null;
  }

  /**
   * Gets copy lease history.
   *
   * @param copyId the copy id
   * @return the copy lease history
   */
  public List<Lease> getCopyLeaseHistory(String copyId) {
    return null;
  }

  /**
   * Gets copy current lease.
   *
   * @param copyId the copy id
   * @return the copy current lease
   */
  public Lease getCopyCurrentLease(String copyId) {
    return null;
  }

  /**
   * Gets customer requested leases.
   *
   * @param customerUsername the customer username
   * @return the customer requested leases
   */
  public List<Lease> getCustomerRequestedLeases(String customerUsername) {
    return null;
  }

  /**
   * Gets customer current leases.
   *
   * @param customerUsername the customer username
   * @return the customer current leases
   */
  public List<Lease> getCustomerCurrentLeases(String customerUsername) {
    return null;
  }

  /**
   * Gets customer overdue leases.
   *
   * @param customerUsername the customer username
   * @return the customer overdue leases
   */
  public List<Lease> getCustomerOverdueLeases(String customerUsername) {
    return null;
  }

  /**
   * Gets overdue leases.
   *
   * @return the overdue leases
   */
  public List<Lease> getOverdueLeases() {
    return null;
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
    leases.add(lease);
  }
}
