package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.lang.reflect.Field;
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

  private int lastTransactionId = 0;

  /**
   * Checks for customer transaction.
   *
   * @return the trnsaction
   */
  public Transaction getSpecific(String transactionId) {
    for (Transaction transaction : transactions) {
      if (transaction.getTransactionId().equals(transactionId)) {
        return transaction;
      }
    }
    return null;
  }

  /**
   * Generates a unique id for the request.
   *
   * @param transaction transaction
   */
  private void generateTransactionId(Transaction transaction) {
    try {
      Field idField = transaction.getClass().getDeclaredField("transactionId");
      idField.setAccessible(true);
      idField.set(transaction, lastTransactionId);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    } finally {
      lastTransactionId++;
    }
  }

  @Override
  public List<Transaction> getAll() {
    return transactions;
  }

  @Override
  public void add(Transaction transaction) {
    if (!transactions.contains(transaction)) {
      generateTransactionId(transaction);
      transactions.add(transaction);
    }
  }

}
