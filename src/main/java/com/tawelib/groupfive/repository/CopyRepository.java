package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;

public class CopyRepository extends BaseRepository {

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
}
