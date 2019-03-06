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
import com.tawelib.groupfive.exception.OverResourceCapException;
import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.time.LocalDateTime;

/**
 * File Name - CopyManager.java The Copy Manager class controls data flow between the Copy
 * Repository and the GUI interfaces.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class CopyManager {

  /**
   * Creates a resource copy and persists it to the repository.
   *
   * @param library the library
   * @param resource the resource
   */
  public static void createResourceCopy(Library library, Resource resource) {
    library.getCopyRepository().add(new Copy(resource));
  }

  /**
   * Borrow resource Copy.
   *
   * @param library the library
   * @param copyId the copy id
   * @param customerUsername the customer username
   */
  public static void borrowResourceCopy(
      Library library,
      String copyId,
      String customerUsername
  ) {
    //Sets Copy Status to borrowed.
    Copy borrowedCopy = library.getCopyRepository().getSpecific(copyId);

    borrowedCopy.setStatus(CopyStatus.BORROWED);

    //Sets customer as Borrowing Customer.
    Customer borrowingCustomer = library.getCustomerRepository().getSpecific(customerUsername);
    borrowedCopy.setBorrowingCustomer(borrowingCustomer);

    createLease(library, borrowingCustomer, borrowedCopy);
  }

  /**
   * Return resource copy.
   *
   * @param library the library
   * @param copyId the copy id
   */
  public static void returnResourceCopy(Library library, String copyId) {
    Copy returnedCopy = library.getCopyRepository().getSpecific(copyId);

    //Sets date returned in Lease.
    library.getLeaseRepository().getCopyCurrentLease(returnedCopy).setDateReturned();

    Lease currentLease = library.getLeaseRepository().getCopyCurrentLease(returnedCopy);

    /* Creates Fine if book is returned late, and decrease account balance of
       customer by fine amount.*/
    if (
        currentLease.getDueDate() != null
            && currentLease.getDueDate().isBefore(
            library.getLeaseRepository().getCopyCurrentLease(returnedCopy)
                .getDateReturned()
        )
    ) {
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
   * Pick up reserved Copy.
   *
   * @param library the library
   * @param resourceId resourceId
   * @param customerUsername the customer username
   * @throws OverResourceCapException When over the resource cap.
   */
  public static void pickUpReservedCopy(Library library, String resourceId,
      String customerUsername) throws OverResourceCapException {
    //Gets info of copy and customer.
    Resource reservedResource = library.getResourceRepository()
        .getSpecific(resourceId);
    Customer customer = library.getCustomerRepository()
        .getSpecific(customerUsername);
    Copy reservedCopy = library.getCopyRepository()
        .getSpecificReserved(customer, reservedResource);
    if (ResourceCapManager.isUnderResourceCap(library, customer, reservedCopy.getResource())) {
      //Sets Copy to Borrowed
      library.getCopyRepository().getSpecific(reservedCopy.getId())
          .setStatus(CopyStatus.BORROWED);
      //Closes the request.
      library.getRequestRepository()
          .getSpecificReserved(customer, reservedCopy.getResource())
          .setStatus(RequestStatus.CLOSED);

      createLease(library, customer, reservedCopy);
    } else {
      throw new OverResourceCapException();
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

    //Sets  date returned.
    library.getLeaseRepository().getCopyCurrentLease(lostCopy)
        .setDateReturned();

    /* Creates Fine with max fine amount, and decreases it from Customer's
    account */
    int amount = (lostCopy.getResource().getType().getMaxFine()) * 100;
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
   * Generates Due date for minimum loan duration for that resource type or tomorrow, if they have
   * already have had it for that duration.
   *
   * @param newLease new lease
   */
  public static void generateDueDate(Lease newLease) {
    ResourceType resourceType = newLease.getBorrowedCopy().getResource().getType();
    LocalDateTime dueDate = newLease.getDateLeased()
        .plusDays(resourceType.getLoanDuration());
    if (dueDate.isAfter(SimulatedLocalDateTime.now())) {
      newLease.setDueDate(dueDate);
    } else {
      newLease.setDueDate((SimulatedLocalDateTime.now()).plusDays(1));
    }

  }

  /**
   * Generates fine amount, based on days overdue and resource type, while being being capped by max
   * fine amount for that type of resource.
   *
   * @param lease lease
   * @return fine amount
   */
  public static int generateFineAmount(Lease lease) {
    ResourceType resourceType = lease.getBorrowedCopy().getResource().getType();
    int amount = ((resourceType.getFine()) * (getDaysOverdue(lease)) * 100);
    if (amount <= (resourceType.getMaxFine() * 100)) {
      return amount;
    } else {
      amount = resourceType.getMaxFine() * 100;
      return amount;
    }
  }

  /**
   * Gets days overdue.
   *
   * @param lease lease
   * @return the days overdue
   */
  public static int getDaysOverdue(Lease lease) {
    if (lease.getDueDate() == null) {
      return 0;
    } else {
      long diff = DAYS.between(lease.getDueDate(), lease.getDateReturned());
      if (diff > 0) {
        return (int) diff;
      } else {
        return 0;
      }
    }
  }

  /**
   * Creates lease while checking to see if due date needs to be added.
   *
   * @param library library
   * @param customer borrowing customer
   * @param copy borrowed copy
   */
  private static void createLease(
      Library library,
      Customer customer,
      Copy copy
  ) {
    Lease newLease = new Lease(customer, copy);

    //Checks to see if there are any requests for that resource.
    if (!library.getRequestRepository()
        .getOpenResourceRequests(copy.getResource()).isEmpty()
    ) {
      generateDueDate(newLease);
    }

    library.getLeaseRepository().add(newLease);
  }

}