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

  public Copy getSpecificCopy(String id) {return Copy}

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

  @Override
  public List<Copy> getAll() {
    return null;
  }

  @Override
  public void add(Copy entity) {

  }
}
