package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.*;

public class ResourceCapManager {

    private static final int cap = 5;

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
