package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.repository.CustomerRepository;
import com.tawelib.groupfive.repository.RequestRepository;
import com.tawelib.groupfive.util.AlertHelper;
import javafx.scene.control.Alert;

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
     * @param library           the library
     * @param customer          the customer
     * @param requestedResource the requested resource
     */
    public static void createRequest(Library library, Customer customer,
                                     Resource requestedResource) {

        if (!ResourceCapManager.isOverResourceCap(library, customer, requestedResource)) {

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
            System.out.println("You have exceeded the resource cap. " +
                    "An item must be returned before another can be borrowed.");
            AlertHelper.alert(Alert.AlertType.ERROR, "You have exceeded the resource cap. " +
                    "An item must be returned before another can be borrowed.");
        }
    }
}