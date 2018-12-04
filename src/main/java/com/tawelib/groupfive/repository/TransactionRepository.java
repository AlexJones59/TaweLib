package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - TransactionRepository.java The Transaction repository class
 * handles transaction details.
 *
 * @author Created by Themis
 * @version 0.1
 */
public class TransactionRepository implements BaseRepository<Transaction> {

  private ArrayList<Transaction> transactions;

  public TransactionRepository() {
    transactions = new ArrayList<>();
  }

  public List<Transaction> getTransactions(Customer customer) {
    ArrayList<Transaction> result = new ArrayList<>();

    for (Transaction transaction : transactions) {
      if (transaction.getPayee() == customer) {
        result.add(transaction);
      }
    }

    return result;
  }

  @Override
  public List<Transaction> getAll() {
    return transactions;
  }

  @Override
  public void add(Transaction transaction) {
    if (!transactions.contains(transaction)) {
      transactions.add(transaction);
    }
  }
}
