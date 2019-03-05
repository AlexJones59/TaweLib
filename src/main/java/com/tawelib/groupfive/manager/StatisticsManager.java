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
        result = getUserStatsWeek(customerLeases);
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
        result = getUserStatsWeek(leases);
        break;
      case "Month":
        result = getUserStatsMonth(leases);
        break;
      default:
    }

    return result;
  }

  /**
   * Works out amount of leases per User per day for last 5 days.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 days
   */
  private static int[] getUserStatsDay(List<Lease> leases) {

    //Groups all Leases by Borrowing Customer, then by Date Leased.
    //It then filters out any leases that was before 4 days ago.
    Map<LocalDateTime, Map<Customer, List<Lease>>> leasesMappedperDay = leases.stream()
        .filter(item -> item.getDateLeased().isAfter(LocalDateTime.now().minusDays(4)))
        .collect(Collectors.groupingBy(Lease::getDateLeased,
            Collectors.groupingBy(Lease::getBorrowingCustomer)));

    //Makes the array that shall be returned
    int[] totalByDate = new int[5];

    // Iterates through the leases for each Day
    for (int count = 0; count < 5; count++) {
      int numberOfCustomers = leasesMappedperDay.get(LocalDateTime.now().minusDays(count)).size();
      int numberOfLeases = 0;
      //Iterates through leases per customer and finds number of leases per customer per day
      for (int i = 0; i < numberOfCustomers; i++) {
        numberOfLeases =
            numberOfLeases + leasesMappedperDay.get(LocalDateTime.now().minusDays(count)).get(i)
                .size();
      }
      //Averages it per customer.
      numberOfLeases = numberOfLeases / numberOfCustomers;
      //Assigns Number of Leases of that day.
      totalByDate[count] = numberOfLeases;

    }
    return totalByDate;
  }

  /**
   * Works out amount of leases per User per day for last 5 weeks.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 weeks
   */
  private static int[] getUserStatsWeek(List<Lease> leases) {
    //Sorts leases in order of Date Leased.
    leases.sort(Comparator.comparing(Lease::getDateLeased));

    //Makes the array that shall be returned
    int[] totalByWeek = new int[5];

    //Iterates for 5 weeks
    for (int i = 0; i < 5; i++) {
      //Sets the date range for that week
      LocalDateTime dateTo = LocalDateTime.now().minusDays(i * 7);
      LocalDateTime dateFrom = LocalDateTime.now().minusDays((i + 1) * 7);

      //Groups all Leases by Borrowing Customer, then by Date Leased.
      //It then filters out any leases that wasn't between start and end of each week.
      Map<LocalDateTime, Map<Customer, List<Lease>>> leasesMappedPerWeek = leases.stream()
          .filter(item -> (item.getDateLeased().isBefore(dateTo)) && (item.getDateLeased()
              .isAfter(dateFrom))).collect(Collectors.groupingBy((Lease::getDateLeased),
              Collectors.groupingBy(Lease::getBorrowingCustomer)));

      int totalNoOfLeases = 0;
      Object[] dates = leasesMappedPerWeek.keySet().toArray();
      int dateSize = dates.length;

      // Iterates through the leases for each Day
      for (int j = 0; j < dateSize; j++) {
        Map<Customer, List<Lease>> dateMap = leasesMappedPerWeek.get(dates[j]);
        Object[] customers = dateMap.keySet().toArray();
        int customerSize = customers.length;

        //Iterates through leases per customer and finds number of leases per customer per day
        //Adds it to holder variable
        for (int k = 0; k < customerSize; k++) {
          int temp = dateMap.get(customers[k]).size();
          totalNoOfLeases = totalNoOfLeases + temp;
        }

        //Averages number of Leases based upon no.of Customers
        totalNoOfLeases = totalNoOfLeases / customerSize;
      }

      totalByWeek[i] = totalNoOfLeases;
    }

    return totalByWeek;
  }

  /**
   * Works out amount of leases per User per day for last 5 months.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 months
   */
  private static int[] getUserStatsMonth(List<Lease> leases) {
    //Sorts leases in order of Date Leased.
    leases.sort(Comparator.comparing(Lease::getDateLeased));

    //Makes the array that shall be returned
    int[] totalByMonth = new int[5];

    //Iterates for 5 months
    for (int i = 0; i < 5; i++) {
      //Sets the date range for that month
      LocalDateTime dateTo = LocalDateTime.now().minusMonths(i);
      LocalDateTime dateFrom = LocalDateTime.now().minusMonths(i + 1);

      //Groups all Leases by Borrowing Customer, then by Date Leased.
      //It then filters out any leases that wasn't between the start and end of each month.
      Map<LocalDateTime, Map<Customer, List<Lease>>> leasesMappedPerMonth = leases.stream()
          .filter(item -> (item.getDateLeased().isBefore(dateTo)) && (item.getDateLeased()
              .isAfter(dateFrom))).collect(Collectors.groupingBy((Lease::getDateLeased),
              Collectors.groupingBy(Lease::getBorrowingCustomer)));

      int totalNoOfLeases = 0;
      Object[] dates = leasesMappedPerMonth.keySet().toArray();
      int dateSize = dates.length;

      //Iterates through each Date
      for (int j = 0; j < dateSize; j++) {
        Map<Customer, List<Lease>> dateMap = leasesMappedPerMonth.get(dates[j]);
        Object[] customers = dateMap.keySet().toArray();
        int customerSize = customers.length;

        //Iterates through leases per customer and finds number of leases per customer per day
        //Adds it to holder variable
        for (int k = 0; k < customerSize; k++) {
          int temp = dateMap.get(customers[k]).size();
          totalNoOfLeases = totalNoOfLeases + temp;
        }

        //Averages total number of Leased by number of customers.
        totalNoOfLeases = totalNoOfLeases / customerSize;
      }

      totalByMonth[i] = totalNoOfLeases;
    }

    return totalByMonth;
  }

  /**
   * Works out the top 5 most popular resources loaned within specified time period.
   *
   * @param library
   * @param timePeriod "Day", "Week", "Month"
   * @param resourceType the type of resource you want to find out
   * @return a list of most popular resources
   */

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

    HashMap<Resource, Integer> map = new HashMap<>();

    for (Lease lease : leases) {
      Resource key = lease.getBorrowedCopy().getResource(); //get a key

      if (map.containsKey(key)) { //check if resource been inserted into the map
        map.put(key, map.get(key)+1) //incrament the counter
      } else {
        map.put(key, 1); //add key to the map
      }
    }
    // at this point we have how many times, each reasource, within given time period, been leased
    ArrayList<Integer> freq = new ArrayList<>();
    Object[] vals = map.values().toArray();
    for(int i = 0; i < map.values().size(); i++){
      freq.add((Integer)vals[i]);
    }

    Collections.sort(freq);
    Collections.reverse(freq);

    ArrayList<Resource> popularResources = new ArrayList<>();

    Object[] keys = map.keySet().toArray();
    for (int i = 0; i < 5; i++){
      try {
        for (Object key : keys) {
          Resource rkey = (Resource) key;
          if (map.get(rkey) == freq.get(i)) {
            popularResources.add(rkey);
          }

        }
      }catch (IndexOutOfBoundsException e){
        continue;
      }
    }



    return popularResources;
  }


}
