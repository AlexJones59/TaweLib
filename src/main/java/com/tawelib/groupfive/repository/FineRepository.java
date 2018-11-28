package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Fine;
import java.util.List;

/**
 * File Name - FineRepository.java The Fine repository class handles instances
 * of Fines.
 *
 * @author Shree Desai
 * @version 0.1
 */
public class FineRepository implements BaseRepository<Fine> {

  /**
   * Gets customer fines.
   *
   * @param customerUsername the customer username
   * @return the customer fines
   */
  public List<Fine> getCustomerFines(String customerUsername) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Fine> getAll() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Fine fine) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Fine getSpecific(String fineId) {
    return null;
  }
}
