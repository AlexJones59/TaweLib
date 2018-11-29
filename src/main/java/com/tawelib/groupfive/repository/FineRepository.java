package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Fine;
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

  /**
   * Gets customer fines.
   *
   * @param customerUsername the customer username
   * @return the customer fines
   */
  public List<Fine> getCustomerFines(String customerUsername) {
    for (Fine fine : fines) {
      if (fine.getSpecificLease().getBorrowingCustomerUsername()
          .equals(customerUsername)) {
        return (List<Fine>) fine;
      }
    }
    throw new IllegalStateException(
        "Error message"
    );
  }

  /**
   * Gets specific.
   *
   * @param fineId the fine id
   * @return the specific
   */
  public Fine getSpecific(String fineId) {
    for (Fine fine : fines) {
      if (fine.getSpecificLease().equals(fineId)) {
        return fine;
      }
    }
    throw new IllegalStateException(
        "Error message"
    );
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
      fines.add(fine);
    }
  }


}
