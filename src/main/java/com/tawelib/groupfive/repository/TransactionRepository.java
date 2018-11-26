package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Transaction;
import java.util.List;

/**
 * File Name - TransactionRepository.java The Transaction repository class hadles transaction
 * details.
 *
 * @author Created by Themis
 * @version 0.1
 */
public class TransactionRepository implements BaseRepository<Transaction> {

  public Transaction getAllTransactions(Customer customer) {
    return null;
  }

  @Override
  public List<Transaction> getAll() {
    return null;
  }

  @Override
  public void add(Transaction entity) {

  }
}
