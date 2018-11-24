package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Lease;
import java.util.List;

public class LeaseRepository implements BaseRepository<Lease> {

  public List<Lease> getCopyLeases(String copyId) {
    return null;
  }

  public List<Lease> getCopyLeaseHistory(String copyId) {
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
    return null;
  }

  @Override
  public void add(Lease entity) {}
}
