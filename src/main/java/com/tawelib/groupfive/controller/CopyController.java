package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Fine;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.RequestStatus;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.repository.CopyRepository;
import com.tawelib.groupfive.repository.LeaseRepository;
import com.tawelib.groupfive.repository.RequestRepository;
import java.util.Date;

/**
 * File Name - CopyController.java The CopyController class controls data flow
 * between the Copy Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
//TODO: Finish implementation of this class.
public class CopyController {


  /**
   * The constant DAYTOMILLIS.
   */
  public static final int DAYTOMILLISECONDS = 86400000;

  /**
   * Create resource copy.
   *
   * @param library the library
   * @param resource the resource
   * @param amount the amount
   */
  public void createResourceCopy(Library library, Resource resource,
      int amount) {
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
  public void borrowResourceCopy(Library library, String copyId,
      String customerUsername) {
    Copy borrowedCopy = library.getCopyRepository().getSpecificCopy(copyId);
    library.getCopyRepository().getSpecificCopy(copyId)
        .setStatus(CopyStatus.BORROWED);
    Lease newLease = new Lease(copyId, customerUsername);
    if (library.getRequestRepository()
        .getResourceRequests(borrowedCopy.getResource()) != null) {
      generateDueDate(newLease, borrowedCopy.getResource().getType());
    }
    library.getLeaseRepository().add(newLease);

  }

  /**
   * Return resource copy.
   *
   * @param copyId the copy id
   */
  public void returnResourceCopy(Library library, String copyId) {
    Date dateReturned = new Date();
    library.getLeaseRepository().getCopyCurrentLease(copyId).setDateReturned();
    Lease currentLease = library.getLeaseRepository()
        .getCopyCurrentLease(copyId);

    if (currentLease.getDueDate().before(dateReturned) == true) {
      Fine newFine = new Fine(currentLease);
      library.getFineRepository().add(newFine);
      String returningCustomerUsername = currentLease
          .getBorrowingCustomerUsername();
      library.getCustomerRepository()
          .getSpecificCustomer(returningCustomerUsername)
          .decreaseAccountBalance(newFine.getAmount());
    }

    Resource returnedResource = library.getCopyRepository()
        .getSpecificCopy(currentLease.getBorrowedCopyId()).getResource();
    if (library.getRequestRepository().getResourceRequests(returnedResource)
        .isEmpty() == true) {
      library.getCopyRepository()
          .getSpecificCopy(currentLease.getBorrowedCopyId())
          .setStatus(CopyStatus.AVAILABLE);
      library.getCopyRepository()
          .getSpecificCopy(currentLease.getBorrowedCopyId())
          .setBorrowingCustomerUsername(null);
    } else {
      library.getCopyRepository()
          .getSpecificCopy(currentLease.getBorrowedCopyId())
          .setStatus(CopyStatus.RESERVED);
      Request reservingRequest = library.getRequestRepository()
          .getEarliestResourceRequest(returnedResource);
      library.getCopyRepository()
          .getSpecificCopy(currentLease.getBorrowedCopyId())
          .setBorrowingCustomerUsername(reservingRequest.getCustomerUsername());
      library.getRequestRepository()
          .getEarliestResourceRequest(returnedResource).setStatus(RequestStatus.RESERVED);
    }


  }

  private static void generateDueDate(Lease newLease,
      ResourceType resourceType) {
    long dueDateMilli =
        newLease.getDateLeased().getTime() + ((resourceType.getLoanDuration())
            * DAYTOMILLISECONDS);
    Date dueDate = new Date(dueDateMilli);
    newLease.setDueDate(dueDate);

  }

}