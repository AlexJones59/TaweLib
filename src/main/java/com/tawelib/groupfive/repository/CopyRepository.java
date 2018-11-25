package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;
import java.util.List;

public class CopyRepository implements BaseRepository<Copy> {

  private static long copyNumber = 0;

  private static final String COPY_PREFIX = "C";

  private void generateId(Copy copy) {
    String generatedUsername = String.format(
        "%s%s",
        COPY_PREFIX,
        copyNumber
    );

    copyNumber++;
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

  @Override
  public List<Copy> getAll() {
    return null;
  }

  @Override
  public void add(Copy entity) {

  }
}
