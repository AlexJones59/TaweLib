package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Fine;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.manager.CopyManager;
import java.time.LocalDateTime;

/**
 * Wraps transactions and fines to be shown in a table.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class TransactionsFinesTableWrapper {

  private String argument;

  private Transaction transaction;

  private Fine fine;

  /**
   * Instantiates a new Transactions fines table wrapper, while storing what the
   * parameter's class type is.
   *
   * @param transaction the transaction
   */
  public TransactionsFinesTableWrapper(Transaction transaction) {
    this.transaction = transaction;
    this.argument = transaction.getClass().getSimpleName();
  }

  /**
   * Instantiates a new Transactions fines table wrapper, while storing what the
   * parameter's class type is.
   *
   * @param fine the fine
   */
  public TransactionsFinesTableWrapper(Fine fine) {
    this.fine = fine;
    this.argument = fine.getClass().getSimpleName();
  }

  /**
   * Gets time issued.
   *
   * @return the time issued
   */
  public LocalDateTime getTimeIssued() {
    switch (argument) {
      case "Fine": {
        return fine.getLease().getDueDate();
      }
      case "Transaction": {
        return transaction.getDatePaid();
      }
      default: {
        return null;
      }
    }
  }

  /**
   * Gets resource id.
   *
   * @return the resource id
   */
  public String getResourceId() {
    switch (argument) {
      case "Fine": {
        return fine.getLease().getBorrowedCopy().getResource().getResourceId();
      }
      default: {
        return "";
      }
    }
  }

  /**
   * Gets resource name.
   *
   * @return the resource name
   */
  public String getResourceName() {
    switch (argument) {
      case "Fine": {
        return fine.getLease().getBorrowedCopy().getResource().getTitle();
      }
      default: {
        return "";
      }
    }
  }

  /**
   * Gets resource type.
   *
   * @return the resource type
   */
  public String getResourceType() {
    switch (argument) {
      case "Fine": {
        return fine.getLease().getBorrowedCopy().getResource().getType()
            .toString();
      }
      default: {
        return "";
      }
    }
  }

  /**
   * Gets days overdue.
   *
   * @return the days overdue
   */
  public String getDaysOverdue() {
    switch (argument) {
      case "Fine": {
        return Integer.toString(CopyManager.getDaysOverdue(fine.getLease()));
      }
      default: {
        return "";
      }
    }
  }

  /**
   * Gets amount.
   *
   * @return the amount
   */
  public String getAmount() {
    switch (argument) {
      case "Fine": {

        return String.format(Fine.CURRENCY_FORMAT, fine.getAmountInPounds());
      }
      case "Transaction": {
        return String.format(Fine.CURRENCY_FORMAT, transaction.getAmountInPounds());
      }
      default: {
        return "";
      }
    }
  }
}
