package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Transaction;
import java.util.List;

public class TransactionRepository implements BaseRepository<Transaction> {

  public Transaction getAllTransactions(Customer customer) {
    return null;
  }

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  @Override
  public List<Transaction> getAll() {
    return null;
  }

  /**
   * This method persists an entity in the repository.
   *
   * @param entity Entity to be added
   */
  @Override
  public void add(Transaction entity) {

  }
}
