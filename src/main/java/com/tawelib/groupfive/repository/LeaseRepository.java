package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Lease;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * File Name - LeaseRepository.java The Lease repository class hadles lease details.
 *
 * @author Created by Themis
 * @version 0.1
 */
public class LeaseRepository implements BaseRepository<Lease> {

  private static ArrayList<Lease> leases;

  private static Hashtable<String, Lease> LeaseTable = new Hashtable<String, Lease>();

  public List<Lease> getCopyLeases(String copyId) {
    //Todo checks for leases.
    return null;
  }


  public List<Lease> getCopyLeaseHistory(String copyId) {
    //Todo implement the least history.
    return null;
  }

  public List<Lease> getCopyCurrentLease(String copyId) {
    return null;
  }

  public List<Lease> getRequestedLeases(String customerUsername) {
    return null;
  }

  public List<Lease> getCurrentLeases(String customerUsername) {
    return null;
  }

  public List<Lease> getOverdueLeases(String customerUsername) {
    return null;
  }

  public List<Lease> getOverdueLeases() {
    return null;
  }

  @Override
  public List<Lease> getAll() {
    return leases;
  }

  @Override
  public void add(Lease lease) {
    leases.add(lease);
  }
}
