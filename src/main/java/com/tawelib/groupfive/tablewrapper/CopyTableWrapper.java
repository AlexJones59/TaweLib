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
    return lease.getCopy();
  }

  public String getResourceId() {
    return lease.getCopy().getResource().getResourceId();
  }

  public String getCopyId() {
    return lease.getCopy().getId();
  }

  public String getTitle() {
    return lease.getCopy().getResource().getTitle();
  }

  public Date getDueDate() {
    return lease.getDueDate();
  }

  public String getStatus() {
    return lease.getCopy().getStatus().toString();
  }
}
