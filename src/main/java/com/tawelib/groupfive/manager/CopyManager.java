package com.tawelib.groupfive.manager;

import static java.time.temporal.ChronoUnit.DAYS;

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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * File Name - CopyManager.java The CopyManager class controls data flow between
 * the Copy Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
public class CopyManager {

  /**
   * The constant DAYTOMILLISECONDS.
   */
  //public static final int DAYTOMILLISECONDS = 86400000;

  /**
   * Create resource copy.
   *
   * @param library the library
   * @param resource the resource
   * @param amount the amount
   */
  public static void createResourceCopy(Library library, Resource resource,
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
  public static void borrowResourceCopy(Library library, String copyId,
      String customerUsername) {
    //Sets Copy Status to borrowed.
    Copy borrowedCopy = library.getCopyRepository().getSpecific(copyId);
    library.getCopyRepository().getSpecific(copyId)
        .setStatus(CopyStatus.BORROWED);

    //Sets customer as Borrowing Customer.
    Customer borrowingCustomer = library.getCustomerRepository()
        .getSpecific(customerUsername);
    library.getCopyRepository().getSpecific(copyId)
        .setBorrowingCustomer(borrowingCustomer);

    createLease(library, borrowingCustomer, borrowedCopy);

  }

  /**
   * Return resource copy.
   *
   * @param library the library
   * @param copyId the copy id
   */
  public static void returnResourceCopy(Library library, String copyId) {
    LocalDateTime dateReturned = LocalDateTime.now();
    Copy returnedCopy = library.getCopyRepository().getSpecific(copyId);

    //Sets date returned in Lease.
    library.getLeaseRepository().getCopyCurrentLease(returnedCopy)
        .setDateReturned();

    Lease currentLease = library.getLeaseRepository()
        .getCopyCurrentLease(returnedCopy);

    /* Creates Fine if book is returned late, and decrease account balance of
       customer by fine amount.*/
    if (currentLease.getDueDate().isBefore(dateReturned)) {
      int amount = generateFineAmount(currentLease);
      Fine newFine = new Fine(currentLease, amount);
      library.getFineRepository().add(newFine);
      Customer returningCustomer = currentLease.getBorrowingCustomer();
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
   * Deals with lost copies.
   *
   * @param library library
   * @param copyId lost copy ID
   */

  public static void lostCopy(Library library, String copyId) {
    //Gets Copy
    Copy lostCopy = library.getCopyRepository().getSpecific(copyId);
    Lease currentLease = library.getLeaseRepository()
        .getCopyCurrentLease(lostCopy);

    /* Creates Fine with max fine amount, and decreases it from Customer's
    account */
    int amount = lostCopy.getResource().getType().getMaxFine();
    Fine newFine = new Fine(currentLease, amount);
    library.getFineRepository().add(newFine);
    library.getCustomerRepository()
        .getSpecific(currentLease.getBorrowingCustomer().getUsername())
        .decreaseAccountBalance(newFine.getAmount());

    //Sets Copy as Lost.
    library.getCopyRepository()
        .getSpecific(currentLease.getBorrowedCopy().getId())
        .setStatus(CopyStatus.LOST);
  }

  /**
   * Pick up reserved Copy.
   *
   * @param library the library
   * @param resourceId resourceId
   * @param customerUsername the customer username
   */
  public static void pickUpReservedCopy(Library library, String resourceId,
      String customerUsername) {
    Resource reservedResource = library.getResourceRepository()
        .getSpecific(resourceId);

    Customer customer = library.getCustomerRepository()
        .getSpecific(customerUsername);

    Copy reservedCopy = library.getCopyRepository()
        .getSpecificReserved(customer, reservedResource);

    //Sets Copy to Borrowed

    library.getCopyRepository().getSpecific(reservedCopy.getId())
        .setStatus(CopyStatus.BORROWED);

    library.getRequestRepository()
        .getSpecificReserved(customer, reservedCopy.getResource())
        .setStatus(RequestStatus.CLOSED);

    createLease(library, customer, reservedCopy);
  }

  /**
   * Generate Due Date.
   *
   * @param newLease new lease
   */
  public static void generateDueDate(Lease newLease) {
    ResourceType resourceType = newLease.getBorrowedCopy().getResource()
        .getType();
    LocalDateTime dueDate = newLease.getDateLeased()
        .plusDays(resourceType.getLoanDuration());
    if (dueDate.isAfter(LocalDateTime.now())) {
      newLease.setDueDate(dueDate);
    } else {
      newLease.setDueDate((LocalDateTime.now()).plusDays(1));
    }

  }

  /**
   * Generates fine amount.
   *
   * @param lease lease
   * @return fine amount
   */
  public static int generateFineAmount(Lease lease) {
    ResourceType resourceType = lease.getBorrowedCopy().getResource().getType();
    int amount = (resourceType.getFine()) * (getDaysOverdue(lease));
    if (amount <= resourceType.getMaxFine()) {
      return amount;
    } else {
      return resourceType.getMaxFine();
    }
  }

  /**
   * Gets days overdue.
   *
   * @param lease lease
   */
  public static int getDaysOverdue(Lease lease) {

    long diff = DAYS.between(lease.getDueDate(), lease.getDateReturned());
    return (int) diff;
  }


  /**
   * Creates lease while checking to see if due date needs to be added.
   *
   * @param library library
   * @param customer borrowing customer
   * @param copy borrowed copy
   */
  private static void createLease(Library library, Customer customer,
      Copy copy) {
    Lease newLease = new Lease(customer, copy);
    if (library.getRequestRepository()
        .getOpenResourceRequests(copy.getResource()) != null) {
      generateDueDate(newLease);
    }
    library.getLeaseRepository().add(newLease);
  }

}