package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;
import java.util.List;

/**
 * File Name - RequestManager.java The Request Controller class handles data
 * flow between the Request Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
public class RequestManager {

  /**
   * Create request.
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
