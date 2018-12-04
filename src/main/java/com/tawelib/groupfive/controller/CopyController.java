package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
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
import java.util.List;

/**
 * File Name - CopyController.java The CopyController class controls data flow
 * between the Copy Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */

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
    //Sets Copy Status to borrowed.
    Copy borrowedCopy = library.getCopyRepository().getSpecific(copyId);
    library.getCopyRepository().getSpecific(copyId)
        .setStatus(CopyStatus.BORROWED);

    //Sets customer as Borrowing Customer.
    Customer borrowingCustomer =
        library.getCustomerRepository().getSpecific(customerUsername);
    library.getCopyRepository().getSpecific(copyId)
        .setBorrowingCustomer(borrowingCustomer);


    //Creates new Lease.
    Lease newLease = new Lease(borrowingCustomer, borrowedCopy);

    //Sets Due Date if there are any requests.
    if (library.getRequestRepository()
        .getOpenResourceRequests(borrowedCopy.getResource()) != null) {
      generateDueDate(newLease);
    }

    //Adds lease to repository.
    library.getLeaseRepository().add(newLease);

  }

  /**
   * Return resource copy.
   *
   * @param library the library
   * @param copyId the copy id
   */
  public void returnResourceCopy(Library library, String copyId) {
    Date dateReturned = new Date();
    Copy returnedCopy = library.getCopyRepository().getSpecific(copyId);

    //Sets date returned in Lease.
    library.getLeaseRepository().getCopyCurrentLease(returnedCopy)
        .setDateReturned();

    Lease currentLease = library.getLeaseRepository()
        .getCopyCurrentLease(returnedCopy);

    /* Creates Fine if book is returned late, and decrease account balance of
       customer by fine amount.*/
    if (currentLease.getDueDate().before(dateReturned)) {
      int amount = generateFineAmount(currentLease);
      Fine newFine = new Fine(currentLease, amount);
      library.getFineRepository().add(newFine);
      Customer returningCustomer = currentLease
          .getBorrowingCustomer();
      library.getCustomerRepository()
          .getSpecific(returningCustomer.getUsername())
          .decreaseAccountBalance(newFine.getAmount());
    }

    /* Checks if there are open requests for the resource, to reserve for next
       customer. */
    Resource returnedResource = library.getCopyRepository()
        .getSpecific(currentLease.getBorrowedCopy().getId()).getResource();
    if (library.getRequestRepository().getOpenResourceRequests(returnedResource)
        .isEmpty()) {
      //If no requests, sets copy as available.
      library.getCopyRepository()
          .getSpecific(currentLease.getBorrowedCopy().getId())
          .setStatus(CopyStatus.AVAILABLE);
      library.getCopyRepository()
          .getSpecific(currentLease.getBorrowedCopy().getId())
          .setBorrowingCustomer(null);
    } else {
      //If requests exists, reserves for next customer.
      library.getCopyRepository()
          .getSpecific(currentLease.getBorrowedCopy().getId())
          .setStatus(CopyStatus.RESERVED);
      Request reservingRequest = library.getRequestRepository()
          .getEarliestResourceRequest(returnedResource);
      library.getCopyRepository()
          .getSpecific(currentLease.getBorrowedCopy().getId())
          .setBorrowingCustomer(reservingRequest.getCustomer());
      library.getRequestRepository()
          .getEarliestResourceRequest(returnedResource)
          .setStatus(RequestStatus.RESERVED);
    }


  }

  /**
   * Pick Up reserved Copy.
   *
   * @param library the library
   * @param copyId the copy id
   * @param customerUsername the customer username
   */
  public void pickUpReservedCopy(Library library, String copyId,
      String customerUsername) {

    Copy reservedCopy = library.getCopyRepository().getSpecific(copyId);
    library.getCopyRepository().getSpecific(copyId)
        .setStatus(CopyStatus.BORROWED);
    library.getRequestRepository().getSpecificReserved(customerUsername,
        reservedCopy.getResource()).setStatus(RequestStatus.RESERVED);
    Lease newLease = new Lease(copyId, customerUsername);
    if (library.getRequestRepository()
        .getOpenResourceRequests(reservedCopy.getResource()) != null) {
      generateDueDate(newLease);
    }
    library.getLeaseRepository().add(newLease);

  }

  /**
   * Generate Due Date.
   * @param newLease new lease
   */
  private static void generateDueDate(Lease newLease) {
    ResourceType resourceType =
        newLease.getBorrowedCopy().getResource().getType();
    long dueDateMilli = newLease.getDateLeased().getTime()
        + ((resourceType.getLoanDuration()) * DAYTOMILLISECONDS);
    Date dueDate = new Date(dueDateMilli);
    newLease.setDueDate(dueDate);

  }

  private static int generateFineAmount (Lease lease){
    ResourceType resourceType = lease.getBorrowedCopy().getResource().getType();
    int amount = (resourceType.getFine()) * (getDaysOverdue(lease));
    if (amount <= resourceType.getMaxFine()){
      return amount;
    } else {
      return resourceType.getMaxFine();
    }


  }

  /**
   * Gets days overdue.
   * TODO: Comment well.
   */
  private static int getDaysOverdue(Lease lease) {
    long diffInMilli =
        lease.getDueDate().getTime() - lease.getDateReturned().getTime();

    if (diffInMilli > 84600 * 1000) {
      return (int) ((((diffInMilli / 1000) / 60) / 60) / 24);
    } else {
      return 0;
    }
  }

}