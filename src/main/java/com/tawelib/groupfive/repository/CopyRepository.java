package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - CopyRepository.java The Copy repository class handles copy
 * details.
 *
 * @author Created by Themis, Modified by Shree Desai
 * @version 0.2
 */
public class CopyRepository implements BaseRepository<Copy> {

  private ArrayList<Copy> copies;

  private long lastCopyId = 0;

  public CopyRepository() {
    copies = new ArrayList<>();
  }

  /**
   * Generates a unique id for copies.
   */
  private void generateId(Copy copy) {
    String generatedCopyId = String.format(
        "C%d",
        lastCopyId
    );

    try {
      Field idField = copy.getClass().getDeclaredField("id");
      idField.setAccessible(true);
      idField.set(copy, generatedCopyId);
      idField.setAccessible(false);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    } finally {
      lastCopyId++;
    }

  }

  /**
   * Gets specific.
   *
   * @param copyId the copy id
   * @return the specific
   */
  @Deprecated
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
   * @deprecated Use getBorrowedCopies(User user) in the future.
   */
  @Deprecated
  public List<Copy> getBorrowedCopies(String customerUsername) {
    ArrayList<Copy> borrowedCopies = new ArrayList<>();
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
   * Gets borrowed copies.
   *
   * @param customer The Customer.
   * @return the borrowed copies.
   */
  public List<Copy> getBorrowedCopies(Customer customer) {
    ArrayList<Copy> result = new ArrayList<>();

    for (Copy copy : copies) {
      if (copy.getBorrowingCustomer() == customer) {
        result.add(copy);
      }
    }

    return result;
  }

  /**
   * Gets resource copies.
   *
   * @param resourceId the resource id
   * @return the resource copies
   * @deprecated Use getResourceCopies(Resource resource)
   */
  @Deprecated
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
   * Gets resource copies.
   *
   * @param resource Resource.
   * @return the resource copies
   */
  public List<Copy> getResourceCopies(Resource resource) {
    ArrayList<Copy> result = new ArrayList<>();

    for (Copy copy : copies) {
      if (copy.getResource() == resource) {
        result.add(copy);
      }
    }

    return result;
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
