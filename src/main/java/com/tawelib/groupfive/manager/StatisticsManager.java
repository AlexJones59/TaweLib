package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * File name - Statistics manager. The Statistics Manager class controls data flow between the
 * Repositories and the Statistics GUI interface.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class StatisticsManager {

  /**
   * Gets specific user statistics.
   *
   * @param library the library
   * @param customer the customer
   * @param resourceType the resource type
   * @param timePeriod the time period
   * @return the specific user statistics
   */
  public static int[] getSpecificUserStatistics(Library library, Customer customer,
      ResourceType resourceType, String timePeriod) {
    List<Lease> customerLeases;
    int[] result = new int[5];

    switch (resourceType) {
      case BOOK:
      case DVD:
      case LAPTOP:
      case GAME:
        customerLeases = library.getLeaseRepository()
            .getCustomerResourceTypeLeases(resourceType, customer);
        break;
      default:
        customerLeases = library.getLeaseRepository().getCustomerLeaseHistory(customer);
    }
    Collections.reverse(customerLeases);

    switch (timePeriod) {
      case "Day":
        result = getUserStatsDay(customerLeases);
        break;
      case "Week":
        //result = getUserStatsWeek(customerLeases);
        break;
      case "Month":
        result = getUserStatsMonth(customerLeases);
        break;
      default:
    }

    return result;

  }

  /**
   * Gets average user statistics.
   *
   * @param library the library
   * @param resourceType the resource type
   * @param timePeriod the time period
   * @return the average user statistics
   */
  public static int[] getAverageUserStatistics(Library library, ResourceType resourceType,
      String timePeriod) {

    List<Lease> leases;
    int[] result = new int[5];
    switch (resourceType) {
      case BOOK:
      case DVD:
      case LAPTOP:
      case GAME:
        leases = library.getLeaseRepository().getResourceTypeLeases(resourceType);
        break;
      default:
        leases = library.getLeaseRepository().getAll();
    }
    Collections.reverse(leases);

    switch (timePeriod) {
      case "Day":

        result = getUserStatsDay(leases);
        break;
      case "Week":
        //result = getUserStatsWeek(leases);
        break;
      case "Month":
        result = getUserStatsMonth(leases);
        break;
      default:
    }

    return result;
  }

  private static int[] getUserStatsDay(List<Lease> leases) {
    Map<LocalDateTime, Map<Customer, List<Lease>>> leasesMappedperDay = leases.stream()
        .filter(item -> item.getDateLeased().isAfter(LocalDateTime.now().minusDays(4)))
        .collect(Collectors.groupingBy(Lease::getDateLeased,
            Collectors.groupingBy(Lease::getBorrowingCustomer)));

    int[] totalByDate = new int[5];

    for (int count = 0; count < 5; count++) {
      int numberOfCustomers = leasesMappedperDay.get(LocalDateTime.now().minusDays(count)).size();
      int numberOfLeases = 0;
      for (int i = 0; i < numberOfCustomers; i++) {
        numberOfLeases =
            numberOfLeases + leasesMappedperDay.get(LocalDateTime.now().minusDays(count)).get(i)
                .size();
      }
      numberOfLeases = numberOfLeases / numberOfCustomers;
      totalByDate[count] = numberOfLeases;

    }

    return totalByDate;

  }

  /*
  private static int[] getUserStatsWeek(List<Lease> leases) {

  Map<LocalDateTime, Map<Customer, List<Lease>>> leasesMappedperWeek = leases.stream()
        .filter(item -> item.getDateLeased().isAfter(LocalDateTime.now().minusWeeks(4)))
        .collect( Collectors.groupingBy(Lease::getDateLeased,
            Collectors.groupingBy(Lease::getBorrowingCustomer)));

    return leases;
  }
  */

  private static int[] getUserStatsMonth(List<Lease> leases) {
    leases.sort(Comparator.comparing(Lease::getDateLeased));

    int[] totalByDate = new int[5];

    for (int i = 0; i < 5; i++) {
      LocalDateTime dateTo = LocalDateTime.now().minusMonths(i);
      LocalDateTime dateFrom = LocalDateTime.now().minusMonths(i+1);

      Map<LocalDateTime, Map<Customer, List<Lease>>> leasesMappedPerMonth = leases.stream()
          .filter(item -> (item.getDateLeased().isBefore(dateTo)) && (item.getDateLeased()
              .isAfter(dateFrom))).collect(Collectors.groupingBy((Lease::getDateLeased),
                  Collectors.groupingBy(Lease::getBorrowingCustomer)));
       int size = leasesMappedPerMonth.size();

      for (int j = 0 ; j < size; j++){


      }



    }

    return totalByDate;
  }


  private static List<?> getPopularResources(Library library, String timePeriod,
      ResourceType resourceType) {
    List<Lease> leases = library.getLeaseRepository().getResourceTypeLeases(resourceType);
    Predicate<Lease> streamsPredicate;
    switch (timePeriod) {
      case "Day":
        streamsPredicate = item -> item.getDateLeased().isAfter(LocalDateTime.now().minusDays(1));
        break;
      case "Week":
        streamsPredicate = item -> item.getDateLeased().isAfter(LocalDateTime.now().minusDays(7));
        break;
      case "Month":
        streamsPredicate = item -> item.getDateLeased().isAfter(LocalDateTime.now().minusMonths(1));
        break;
      default:
    }
    leases.stream().filter(streamsPredicate).collect(Collectors.toList());
    // so a this point we have a list of leases of same type that were borrowed within given period


    // Map<Resource, List<Lease>> leasesSortedByResource;
    // ArrayList<Resource> popularResources = new ArrayList<>();
    Map<Resource, Integer> map = new Map<Resource, Integer>();
    for (Lease lease : leases) {
      Resource key = lease.getBorrowedCopy().getResource(); //get a key

      if (map.containsKey(key)) { //check if resource been inserted into the map
        map.put(key, map.get(key)++) //incrament the counter
      } else {
        map.put(key, 0); //add key to the map
      }
    }
    // at this point we have how many times, each reasource, within given time period, been leased



    //TODO: Change return instructions.
    return leases;
  }


}
