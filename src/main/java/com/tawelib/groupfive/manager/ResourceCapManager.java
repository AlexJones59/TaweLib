package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;

/**
 * This is the resource cap method. It checks how many items has the user already borrowed and
 * returns false if the number + the resource they are trying to borrow is under the cap and true
 * otherwise.
 *
 * @author Boris Andreev
 * @version 1.0
 */

public class ResourceCapManager {

  private static final int cap = 5;

  /**
   * Method which calculates how many resources the user has borrowed.
   *
   * @param library Current library
   * @param customer Customer whose borrowed resources are checked
   * @param resource The resource which the customer is about to borrow
   * @return True if the number of borrowed resources is under the cap false otherwise
   */
  public static boolean isOverResourceCap(Library library, Customer customer, Resource resource) {
    int borrowedItems = 0;
    for (Lease lease : library.getLeaseRepository().getCustomerCurrentLeases(customer)) {
      if (lease.getBorrowedCopy().getResource() instanceof Laptop) {
        borrowedItems += 3;
      } else {
        borrowedItems++;
      }
    }
    if (resource instanceof Laptop) {
      borrowedItems += 3;
    } else {
      borrowedItems += 1;
    }
    if (borrowedItems <= cap) {
      return false;
    } else {
      return true;
    }
  }
}
