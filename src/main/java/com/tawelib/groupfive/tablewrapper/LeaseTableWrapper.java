package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.ResourceType;
import java.time.LocalDateTime;


/**
 * This class wraps information about Copies that is shown in an FXML table.
 *
 * @author Petr Hoffmann, Shree Desai
 * @version 1.0
 */
public class LeaseTableWrapper {

  private Lease lease;

  private Request request;

  private String argument;

  Customer selectedCustomer;

  /**
   * Instantiates a new Lease table wrapper, while storing what the parameter's
   * class type is.
   *
   * @param lease the lease
   */
  public LeaseTableWrapper(Lease lease, Customer selectedCustomer) {
    this.lease = lease;
    this.argument = lease.getClass().getSimpleName();
    this.selectedCustomer = selectedCustomer;
  }

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

  /**
   * Returns the lease date.
   *
   * @return The lease date.
   */
  public LocalDateTime getLeaseDate() {
    return lease.getDateLeased();
  }

  /**
   * Returns the return date.
   *
   * @return Return date.
   */
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
        Copy copy = lease.getBorrowedCopy();
        if (
            copy.getStatus() == CopyStatus.AVAILABLE
                || copy.getStatus() == CopyStatus.RESERVED
                ||
                (
                    copy.getStatus() == CopyStatus.BORROWED
                        && copy.getBorrowingCustomer() != selectedCustomer
                )
        ) {
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

  /**
   * Returns the resource type.
   *
   * @return Resource type.
   */
  public ResourceType getType() {
    return lease.getBorrowedCopy().getResource().getType();
  }

  /**
   * Returns the username of the borrowing customer.
   *
   * @return Username of the borrowing customer.
   */
  public String getUsername() {
    return lease.getBorrowingCustomer().getUsername();
  }

  /**
   * Returns the argument.
   *
   * @return The argument.
   */
  public String getArgument() {
    return argument;
  }
}
