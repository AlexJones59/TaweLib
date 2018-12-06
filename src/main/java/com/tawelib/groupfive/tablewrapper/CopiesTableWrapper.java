package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.repository.LeaseRepository;
import java.time.LocalDateTime;

/**
 * This class wraps information about Resources that is shown in an FXML table.
 */
public class CopiesTableWrapper {

  public CopiesTableWrapper(Copy copy, LeaseRepository leaseRepository) {
    this.copy = copy;
    this.leaseRepository = leaseRepository;
  }

  private Copy copy;
  private LeaseRepository leaseRepository;

  public String getId() {
    return copy.getId();
  }

  public CopyStatus getStatus() {
    return copy.getStatus();
  }

  public String getTitle() {
    return copy.getResource().getTitle();
  }

  /**
   * Returns a due date where appropriate.
   *
   * @return Due date or null.
   */
  public LocalDateTime getDueDate() {
    if (copy.getStatus() == CopyStatus.BORROWED) {
      Lease currentLease = leaseRepository.getCopyCurrentLease(copy);

      return currentLease.getDueDate();
    } else {
      return null;
    }
  }
}
