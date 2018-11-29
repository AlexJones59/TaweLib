package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.exception.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name - CopyRepository.java The Copy repository class handles copy details.
 *
 * @author Created by Themis, Modified by Shree Desai
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
    return null;
  }

  /**
   * Gets borrowed copies.
   *
   * @param customerUsername the customer username
   * @return the borrowed copies
   */
  public List<Copy> getBorrowedCopies(String customerUsername) {
    ArrayList<Copy> borrowedCopies = new ArrayList<Copy>();
    for (Copy borrowed : copies) {
      if (borrowed.getBorrowingCustomerUsername().equals(customerUsername)) {
        borrowedCopies.add(borrowed);
      }
    }
    if (borrowedCopies.isEmpty()) {
      return borrowedCopies;
    }
    return null;

  }

  /**
   * Gets resource copies.
   *
   * @param resourceId the resource id
   * @return the resource copies
   */
  public List<Copy> getResourceCopies(String resourceId) {
    ArrayList<Copy> resourceCopies = new ArrayList<Copy>();
    for (Copy resourceCopy : copies) {
      if (resourceCopy.getResource().equals(resourceId)) {
        resourceCopies.add(resourceCopy);
      }
    }
    if (resourceCopies.isEmpty()) {
      return resourceCopies;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Copy> getAll() {
    return copies;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Copy copy) {
    if (!copies.contains((copy))) {
      generateId(copy);
      copies.add(copy);
    }
  }
}
