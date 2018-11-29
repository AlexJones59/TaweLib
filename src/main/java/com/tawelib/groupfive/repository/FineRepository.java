package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Fine;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - FineRepository.java The Fine repository class handles instances of Fines.
 *
 * @author Shree Desai
 * @version 0.1
 */
public class FineRepository implements BaseRepository<Fine> {

  private ArrayList<Fine> fines;

  private int lastFineId = 0;

  /**
   * Gets customer fines.
   *
   * @param customerUsername the customer username
   * @return the customer fines
   */
  public List<Fine> getCustomerFines(String customerUsername) {
    ArrayList<Fine> customerFines = new ArrayList<Fine>();
    for (Fine fine : fines) {
      if (fine.getSpecificLease().getBorrowingCustomerUsername()
          .equals(customerUsername)) {
        customerFines.add(fine);
      }
    }
    if (customerFines != null) {
      return customerFines;
    }

    return null;
  }

  /**
   * Gets specific.
   *
   * @param fineId the fine id
   * @return the specific
   */
  public Fine getSpecific(String fineId) {
    for (Fine fine : fines) {
      if (fine.getFineId().equals(fineId)) {
        return fine;
      }
    }
    return null;
  }

  /**
   * Generates a unique id for the request.
   *
   * @param fine fine
   */
  private void generateFineId(Fine fine) {
    try {
      Field usernameField = fine.getClass().getDeclaredField("fineId");
      usernameField.setAccessible(true);
      usernameField.set(fine, lastFineId);
    } catch (Exception e) {
      throw new IllegalStateException(
          "Request ID could not be set."
      );
    } finally {
      lastFineId++;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Fine> getAll() {
    return fines;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Fine fine) {
    if (!fines.contains(fine)) {
      generateFineId(fine);
      fines.add(fine);
    }
  }


}
