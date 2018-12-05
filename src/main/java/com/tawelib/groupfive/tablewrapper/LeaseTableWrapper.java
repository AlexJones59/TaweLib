package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Request;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * This class wraps information about Copies that is shown in an FXML table.
 */
public class LeaseTableWrapper {

  /**
   * Instantiates a new Lease table wrapper, while storing what the
   * parameter's class type is.
   *
   * @param lease the lease
   */
  public LeaseTableWrapper(Lease lease) {
    this.lease = lease;
    this.argument = lease.getClass().getSimpleName();
  }

  /**
   * Instantiates a new Lease table wrapper, while storing what the
   * parameter's class type is.
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
      case "Lease" : {
        return lease.getBorrowedCopy().getResource().getResourceId();
      }
      case "Request" : {
        return request.getRequestedResource().getResourceId();
      }
      default : {
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
      case "Lease" : {
        return lease.getBorrowedCopy().getId();
      }
      default : {
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
      case "Lease" : {
        return lease.getBorrowedCopy().getResource().getTitle();
      }
      case "Request" : {
        return request.getRequestedResource().getTitle();
      }
      default : {
        return "";
      }
    }
  }

  /**
   * Gets due date.
   *
   * @return the due date
   */
  public String getDueDate() {
    switch (argument) {
      case "Lease" : {
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        return dateFormat.format(lease.getDueDate());
      }
      default : {
        return null;
      }
    }
  }

  /**
   * Gets status.
   *
   * @return the status
   */
  public String getStatus() {
    switch (argument) {
      case "Lease" : {
        return lease.getBorrowedCopy().getStatus().toString();
      }
      case "Request" : {
        return request.getStatus().toString();
      }
      default : {
        return "";
      }
    }



  }


}
