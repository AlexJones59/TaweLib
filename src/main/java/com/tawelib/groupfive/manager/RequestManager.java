package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.exception.CopyAvailableException;
import com.tawelib.groupfive.exception.OverResourceCapException;
import com.tawelib.groupfive.util.AlertHelper;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * File Name - RequestManager.java The Request Manager class handles data flow between the Request
 * Repository and the GUI interfaces.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class RequestManager {

  /**
   * Create request for a resource while also setting due date of oldest borrowed copy.
   *
   * @param library the library
   * @param customer the customer
   * @param requestedResource the requested resource
   * @throws OverResourceCapException This exception gets thrown whenever this request and
   * subsequent lending of an item would exceed this customers resource cap.
   */
  public static void createRequest(Library library, Customer customer,
      Resource requestedResource) throws OverResourceCapException, CopyAvailableException {
    if (library.getCopyRepository().getAvailableResourceCopies(requestedResource).isEmpty()) {
      if (ResourceCapManager.isUnderResourceCap(library, customer, requestedResource)) {
        Request newRequest = new Request(customer, requestedResource);
        library.getRequestRepository().add(newRequest);
        library.getLeaseRepository().getCustomerCurrentLeases(customer);
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
      } else {
        throw new OverResourceCapException();
      }
    } else {
      throw new CopyAvailableException();
    }

  }
}