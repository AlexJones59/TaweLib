package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Fine;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.manager.CopyManager;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

/**
 * The type Transactions fines table wrapper.
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
  public String getTimeIssued() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy hh:mm:ss");
    switch (argument) {
      case "Fine" : {
        return fine.getLease().getDueDate().format(formatter);
      }
      case "Transaction" : {
        return transaction.getDatePaid().format(formatter);
      }
      default : {
        return "";
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
      case "Fine" : {
        return fine.getLease().getBorrowedCopy().getResource().getResourceId();
      }
      default : {
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
      case "Fine" : {
        return fine.getLease().getBorrowedCopy().getResource().getTitle();
      }
      default : {
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
      case "Fine" : {
        return fine.getLease().getBorrowedCopy().getResource()
            .getType().toString();
      }
      default : {
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
      case "Fine" : {
        return Integer.toString(CopyManager.getDaysOverdue(fine.getLease()));
      }
      default : {
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
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    switch (argument) {
      case "Fine" : {
        return formatter.format(fine.getAmount());
      }
      case "Transaction" : {
        return formatter.format(transaction.getAmount());
      }
      default : {
        return "";
      }
    }
  }

}
