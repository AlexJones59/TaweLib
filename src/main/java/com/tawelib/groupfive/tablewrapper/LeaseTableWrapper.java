package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Request;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * This class wraps information about Copies that is shown in an FXML table.
 */
public class LeaseTableWrapper {

  public LeaseTableWrapper(Lease lease) {
    this.lease = lease;
    this.argument = lease.getClass().getSimpleName();
  }

  public LeaseTableWrapper(Request request) {
    this.request = request;
  }

  private Lease lease;

  private Request request;

  private String argument;

  /*public Copy getCopy() {
    return lease.getBorrowedCopy();
  }*/

  public String getResourceId() {
    switch (argument){
      case "Lease" :{
        return lease.getBorrowedCopy().getResource().getResourceId();
      }
      case "Request" : {
        return request.getRequestedResource().getResourceId();
      }
      default : {
        return " ";
      }
    }
  }

  public String getCopyId() {
    return lease.getBorrowedCopy().getId();
  }

  public String getTitle() {
    switch (argument){
      case "Lease" :{
        return lease.getBorrowedCopy().getResource().getTitle();
      }
      case "Request" : {
        return request.getRequestedResource().getTitle();
      }
      default : {
        return " ";
      }
    }
  }

  public Date getDueDate() {
    return lease.getDueDate();
  }

  public String getStatus() {
    switch (argument){
      case "Lease" :{
        return lease.getBorrowedCopy().getStatus().toString();
      }
      case "Request" : {
        return request.getStatus().toString();
      }
      default : {
        return " ";
      }
    }



  }


}
