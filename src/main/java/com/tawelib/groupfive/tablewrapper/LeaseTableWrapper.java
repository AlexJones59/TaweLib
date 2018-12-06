package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Request;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * This class wraps information about Copies that is shown in an FXML table.
 */
public class LeaseTableWrapper {

  /**
   * Instantiates a new Lease table wrapper, while storing what the parameter's
   * class type is.
   *
   * @param lease the lease
   */
  public LeaseTableWrapper(Lease lease) {
    this.lease = lease;
    this.argument = lease.getClass().getSimpleName();
  }

  /**
   * Instantiates a new Lease table wrapper, while storing what the parameter's
   * class type is.
   *
   * @param request the request
   */
  public LeaseTableWrapper(Request request) {
    this.request = request;
    this.argument = request.getClass().getSimpleName();
  }

  private Lease lease;

  private Request request;

  private String argument;

  /**
   * Gets resource id.
   *
   * @return the resource id
   */
  public String getResourceId() {
    switch (argument) {
      case "Lease": {
        return lease.getBorrowedCopy().getResource().getResourceId();
      }
      case "Request": {
        return request.getRequestedResource().getResourceId();
      }
      default: {
        return "";
      }
    }
  }

  /**
   * Gets copy id.
   *
   * @return the copy id
   */
  public String getCopyId() {
    switch (argument) {
      case "Lease": {
        return lease.getBorrowedCopy().getId();
      }
      default: {
        return "";
      }
    }
  }

  /**
   * Gets title.
   *
   * @return the title
   */
  public String getTitle() {
    switch (argument) {
      case "Lease": {
        return lease.getBorrowedCopy().getResource().getTitle();
      }
      case "Request": {
        return request.getRequestedResource().getTitle();
      }
      default: {
        return "";
      }
    }
  }

  /**
   * Gets due date.
   *
   * @return the due date
   */
  public LocalDateTime getDueDate() {
    switch (argument) {
      case "Lease": {
        return lease.getDueDate();
      }
      default: {
        return null;
      }
    }
  }

  public LocalDateTime getLeaseDate() {
    return lease.getDateLeased();
  }

  public LocalDateTime getReturnDate() {
    return lease.getDateReturned();
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public String getStatus() {
    switch (argument) {
      case "Lease": {
        if (lease.getDateReturned() != null) {
          return "RETURNED";
        } else {
          return lease.getBorrowedCopy().getStatus().toString();
        }
      }
      case "Request": {
        return request.getStatus().toString();
      }
      default: {
        return "";
      }
    }


  }

  public String getUsername() {
    return lease.getBorrowingCustomer().getUsername();
  }

  public String getArgument() {
    return argument;
  }
}
