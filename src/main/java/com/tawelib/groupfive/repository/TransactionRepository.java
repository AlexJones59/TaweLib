package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Transaction;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * File Name - TransactionRepository.java The Transaction repository class hadles transaction
 * details.
 *
 * @author Created by Themis
 * @version 0.1
 */
public class TransactionRepository implements BaseRepository<Transaction> {

  private static ArrayList<Transaction> transactions;

  private static Hashtable<String, Transaction> LeaseTable = new Hashtable<String, Transaction>();

  public Transaction getAllTransactions(Customer customer) {
    return null;
  }

  @Override
  public List<Transaction> getAll() {
    return transactions;
  }

  @Override
  public void add(Transaction transaction) {
    transactions.add(transaction);
  }

  @Override
  public Transaction getSpecific(String entityId) {
    return null;
  }
}
