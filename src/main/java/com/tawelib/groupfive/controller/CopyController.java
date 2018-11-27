package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.repository.CopyRepository;
import com.tawelib.groupfive.repository.LeaseRepository;
import com.tawelib.groupfive.repository.RequestRepository;
import java.util.Date;

/**
 * File Name - CopyController.java The CopyController class controls data flow between the Copy
 * Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
//TODO: Finish implementation of this class.
public class CopyController {


  /**
   * The constant DAYTOMILLIS.
   */
  public static final int DAYTOMILLIS = 86400000;

  /**
   * Create resource copy.
   *
   * @param library the library
   * @param resource the resource
   * @param amount the amount
   */
  public static void createResourceCopy(Library library, Resource resource, int amount) {
    for (int i = 1; i <= amount; i++) {
      library.getCopyRepository().add(new Copy(resource));
    }

  }

  /**
   * Borrow resource copy.
   *
   * @param library the library
   * @param copyId the copy id
   * @param customerUsername the customer username
   */
  public static void borrowResourceCopy(Library library, String copyId, String customerUsername) {
    Copy borrowedCopy = library.getCopyRepository().getSpecificCopy(copyId);
    library.getCopyRepository().getSpecificCopy(copyId).setStatus(CopyStatus.BORROWED);
    Lease newLease = new Lease(copyId, customerUsername);
    if (library.getRequestRepository().getResourceRequests(borrowedCopy.getResource())
        != null) {
      generateDueDate(newLease, borrowedCopy.getResource().getType());
    }
    library.getLeaseRepository().add(newLease);

  }

  /**
   * Return resource copy.
   *
   * @param copyId the copy id
   */
  public static void returnResourceCopy(String copyId) {

  }

  private static void generateDueDate(Lease newLease, ResourceType resourceType) {
    long dueDateMilli =
        newLease.getDateLeased().getTime() + ((resourceType.getLoanDuration()) * DAYTOMILLIS);
    Date dueDate = new Date(dueDateMilli);
    newLease.setDueDate(dueDate);

  }

}