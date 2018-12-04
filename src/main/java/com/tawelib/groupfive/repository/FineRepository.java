package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Fine;
import com.tawelib.groupfive.entity.Lease;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - FineRepository.java The Fine repository class handles instances of Fines.
 *
 * @author Shree Desai
 * @version 0.5
 */
public class FineRepository implements BaseRepository<Fine> {

  private ArrayList<Fine> fines;

  public FineRepository() {
    fines = new ArrayList<>();
  }


  /**
   * Gets customer fines.
   *
   * @param customer the customer
   * @return the customer fines
   */
  public List<Fine> getCustomerFines(Customer customer) {
    ArrayList<Fine> result = new ArrayList<>();

    for (Fine fine : fines) {
      if (fine.getLease().getBorrowingCustomer() == customer) {
        result.add(fine);
      }
    }

    return result;
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
