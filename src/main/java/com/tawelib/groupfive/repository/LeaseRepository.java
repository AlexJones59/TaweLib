package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Lease;
import java.util.List;

public class LeaseRepository implements BaseRepository<Lease> {

  public Lease getLeases(Copy copy) {
    return null;
  }

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  @Override
  public List<Lease> getAll() {
    return null;
  }

  /**
   * This method persists an entity in the repository.
   *
   * @param entity Entity to be added
   */
  @Override
  public void add(Lease entity) {

  }
}
