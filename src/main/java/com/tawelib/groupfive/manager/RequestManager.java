package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;
import java.util.List;

/**
 * File Name - RequestManager.java The Request Manager class handles data
 * flow between the Request Repository and the GUI interfaces.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class RequestManager {

  /**
   * Create request for a resource while also setting due date of oldest
   * borrowed copy.
   *
   * @param library the library
   * @param customer the customer
   * @param requestedResource the requested resource
   */
  public static void createRequest(Library library, Customer customer,
      Resource requestedResource) {
    Request newRequest = new Request(customer, requestedResource);
    library.getRequestRepository().add(newRequest);

    //Sets Due Date of oldest borrowed copy.
    List<Copy> resourceCopies = library.getCopyRepository()
        .getResourceCopies(requestedResource);
    Copy oldestCopy = resourceCopies.get(0);
    for (Copy copy : resourceCopies) {
      if (library.getLeaseRepository().getCopyCurrentLease(copy).getDateLeased()
          .isBefore(library.getLeaseRepository().getCopyCurrentLease(oldestCopy)
              .getDateLeased())
          && library.getLeaseRepository().getCopyCurrentLease(copy).getDueDate()
          != null) {
        oldestCopy = copy;

      }
    }

    CopyManager.generateDueDate(
        library.getLeaseRepository().getCopyCurrentLease(oldestCopy));
  }
}
