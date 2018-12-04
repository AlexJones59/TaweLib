package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Lease;
import java.util.Date;

/**
 * This class wraps information about Copies that is shown in an FXML table.
 */
public class CopyTableWrapper {

  public CopyTableWrapper(Lease lease) {
    this.lease = lease;
  }

  private Lease lease;

  public Copy getCopy() {
    return lease.getBorrowedCopy();
  }

  public String getResourceId() {
    return lease.getBorrowedCopy().getResource().getResourceId();
  }

  public String getCopyId() {
    return lease.getBorrowedCopy().getId();
  }

  public String getTitle() {
    return lease.getBorrowedCopy().getResource().getTitle();
  }

  public Date getDueDate() {
    return lease.getDueDate();
  }

  public String getStatus() {
    return lease.getBorrowedCopy().getStatus().toString();
  }
}
