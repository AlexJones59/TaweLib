package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;
import java.util.List;

public class CopyRepository implements BaseRepository<Copy> {

  private void generateId(Copy copy) {

  }

  public void getOldestCopyWithoutDueDate(Resource resource) {

  }

  public Copy getBorrowedCopies(Customer customer) {
    return null;
  }

  public Copy getRequestedCopies(Customer customer) {
    return null;
  }

  public Copy getReservedCopies(Customer customer) {
    return null;
  }

  public Copy getOverdueCopies() {
    return null;
  }

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  @Override
  public List<Copy> getAll() {
    return null;
  }

  /**
   * This method persists an entity in the repository.
   *
   * @param entity Entity to be added
   */
  @Override
  public void add(Copy entity) {

  }
}
