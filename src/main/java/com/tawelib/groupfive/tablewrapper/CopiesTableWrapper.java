package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.repository.LeaseRepository;
import java.time.LocalDateTime;

/**
 * This class wraps information about Resources that is shown in an FXML table.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class CopiesTableWrapper {

  private Copy copy;
  private LeaseRepository leaseRepository;

  /**
   * Constructs a new wrapper for a copy.
   *
   * @param copy Copy.
   * @param leaseRepository Lease repository.
   */
  public CopiesTableWrapper(Copy copy, LeaseRepository leaseRepository) {
    this.copy = copy;
    this.leaseRepository = leaseRepository;
  }

  /**
   * Returns the copy ID.
   *
   * @return Copy ID.
   */
  public String getId() {
    return copy.getId();
  }

  /**
   * Returns the copy status.
   *
   * @return Copy status.
   */
  public CopyStatus getStatus() {
    return copy.getStatus();
  }

  /**
   * Returns the resource title.
   *
   * @return Resource title.
   */
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

  /**
   * Returns the wrapped item (Copy).
   *
   * @return The wrapped copy.
   */
  public Copy getCopy() {
    return copy;
  }
}
