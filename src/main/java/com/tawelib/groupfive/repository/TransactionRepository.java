package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.util.ArrayList;
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

  public Transaction getAllTransactions(Customer customer) {
    for (Transaction allTransactions : transactions) {
      //Todo checks for all the transactions of the customer.
      return allTransactions;
    }
    throw new AuthenticationException();
  }

  @Override
  public List<Transaction> getAll() {
    return transactions;
  }

  @Override
  public void add(Transaction transaction) {
    transactions.add(transaction);
  }
}
