package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.exception.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name - CopyRepository.java The Copy repository class handles copy details.
 *
 * @author Created by Themis
 * @version 0.2
 */
public class CopyRepository implements BaseRepository<Copy> {


  private static ArrayList<Copy> copies;

  private static long copyNumber = 0;

  private static final String COPY_PREFIX = "C";

  /**
   * Generates a unique id for copies.
   */
  private void generateId(Copy copy) {
    String generatedCopyId = String.format("%s%s", COPY_PREFIX, copyNumber);

    copyNumber++;
  }

  /**
   * Gets oldest copy without due date.
   *
   * @param resource the resource
   */
  public void getOldestCopyWithoutDueDate(Resource resource) {
    //Todo implement the method to get the oldest due date.
  }


  /**
   * Search for the reserved copies by a customer username.
   *
   * @param customerUsername the customer username
   * @return the list of reserved copies
   */
  public List<Copy> getReservedCopies(String customerUsername) {
    for (Copy reserved : copies) {
      if (reserved.getBorrowingCustomerUsername().equals(customerUsername)) {
        return (List<Copy>) reserved;
      }
    }
    throw new AuthenticationException();
  }

  /**
   * Gets specific.
   *
   * @param copyId the copy id
   * @return the specific
   */
  public Copy getSpecific(String copyId) {
    for (Copy copy : copies) {
      if (copy.getId().equals(copyId)) {
        return copy;
      }
    }
    throw new AuthenticationException();
  }

  /**
   * Gets borrowed copies.
   *
   * @param customerUsername the customer username
   * @return the borrowed copies
   */
  public List<Copy> getBorrowedCopies(String customerUsername) {
    for (Copy borrowed : copies) {
      if (borrowed.getBorrowingCustomerUsername().equals(customerUsername)) {
        return (List<Copy>) borrowed;
      }
    }
    throw new AuthenticationException();
  }

  /**
   * Gets overdue copies.
   *
   * @return the overdue copies
   */
  public List<Copy> getOverdueCopies() {
    for (Copy copy : copies) {
      if (copy.getStatus().equals("Overdue")) {
        return (List<Copy>) copy;
      }
    }
    throw new AuthenticationException();
  }

  @Override
  public List<Copy> getAll() {
    return copies;
  }

  @Override
  public void add(Copy copy) {
    if (!copies.contains((copy))) {
      generateId(copy);
      copies.add(copy);
    }
  }
}
