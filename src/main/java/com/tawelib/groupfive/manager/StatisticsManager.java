package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Fine;
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

    if (resourceType == null) {
      customerLeases = library.getLeaseRepository().getCustomerLeaseHistory(customer);
    } else {
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
    }
    Collections.reverse(customerLeases);

    switch (timePeriod) {
      case "Day":
        result = getSpecificUserStatsDay(customerLeases);
        break;
      case "Week":
        result = getSpecificUserStatsWeek(customerLeases);
        break;
      case "Month":
        result = getSpecificUserStatsMonth(customerLeases);
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
    if (resourceType == null) {
      leases = library.getLeaseRepository().getAll();
    } else {
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
    }
    Collections.reverse(leases);

    switch (timePeriod) {
      case "Day":

        result = getAverageUserStatsDay(leases);
        break;
      case "Week":
        result = getAverageUserStatsWeek(leases);
        break;
      case "Month":
        result = getAverageUserStatsMonth(leases);
        break;
      default:
    }

    return result;
  }

  /**
   * Gets fine statistics.
   *
   * @param library the library
   * @param resourceType the resource type
   * @param timePeriod the time period
   * @return the average fine statistics
   */
  public static int[][] getFineStatistics(Library library, ResourceType resourceType,
      String timePeriod) {

    List<Fine> fines;
    int[][] result = new int[2][5];

    if (resourceType == null) {
      fines = library.getFineRepository().getAll();
    } else {
      switch (resourceType) {
        case BOOK:
        case DVD:
        case LAPTOP:
        case GAME:
          fines = library.getFineRepository().getResourceTypeFines(resourceType);
          break;
        default:
          fines = library.getFineRepository().getAll();
      }
    }
    Collections.reverse(fines);

    switch (timePeriod) {
      case "Day":
        result = getFineStatsDay(fines);
        break;
      case "Week":
        result = getFineStatsWeek(fines);
        break;
      case "Month":
        result = getFineStatsMonth(fines);
        break;
      default:
    }

    return result;
  }

  /**
   * Works out the top 5 most popular resources loaned within specified time period.
   *
   * @param library library
   * @param timePeriod "Day", "Week", "Month"
   * @param resourceType the type of resource you want to find out
   * @return a list of most popular resources
   */

  public static List<?> getPopularResources(Library library, String timePeriod,
      ResourceType resourceType) {
    List<Lease> leases = library.getLeaseRepository().getResourceTypeLeases(resourceType);
    Predicate<Lease> streamsPredicate = item -> item.getDateLeased().isAfter(LocalDateTime.now()
        .minusDays(1));
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

    // Gets list of leases of same type that were borrowed within given time period
    leases.stream().filter(streamsPredicate).collect(Collectors.toList());
    HashMap<Resource, Integer> map = new HashMap<>();

    //Fills HashMap with every Resource loaned in time period and No. of times leased.
    for (Lease lease : leases) {
      //Makes the Resource into a Key to use for HashMap
      Resource key = lease.getBorrowedCopy().getResource();

      //Checks if resource has been previously inserted into the map
      if (map.containsKey(key)) {
        //Increments counter for Number of Lease of that Resource
        map.put(key, (map.get(key)) + 1);
      } else {
        //Adds Resource to Map if not already added
        map.put(key, 1);
      }
    }

    //Changes the HashMap to an ArrayList
    Object[] keys = map.keySet().toArray();
    ArrayList<Integer> freq = new ArrayList<>();
    for (int i = 0; i < keys.length; i++) {
      freq.add(map.get(keys[i]));
    }

    //Sorts Arraylist in descending order
    Collections.sort(freq);
    Collections.reverse(freq);

    ArrayList<Resource> popularResources = new ArrayList<>();
    //Gets the 5 most popular resources
    for (int i = 0; i < 5; i++) {
      try {
        for (Object key : keys) {
          Resource resource = (Resource) key;
          //Checks if it is already in PopularResources
          if ((map.get(resource) == freq.get(i)) && (!popularResources.contains(resource))) {
            popularResources.add(resource);
          }
        }
      } catch (IndexOutOfBoundsException e) {
        continue;
      }
    }

    return popularResources;
  }

  /**
   * Works out amount of leases per day for last 5 days.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 days
   */
  private static int[] getSpecificUserStatsDay(List<Lease> leases) {

    //Groups all Leases by Date Leased.
    //It then filters out any leases that was before 4 days ago.
    Map<LocalDateTime, List<Lease>> leasesMappedPerDay = leases.stream()
        .filter(item -> item.getDateLeased().isAfter(LocalDateTime.now().minusDays(4)))
        .collect(Collectors.groupingBy(Lease::getDateLeased));

    //Makes the array that shall be returned
    int[] totalByDate = new int[5];
    Object[] dates = leasesMappedPerDay.keySet().toArray();

    //Iterates for 5 days
    for (int i = 0; i < dates.length; i++) {
      totalByDate[i] = leasesMappedPerDay.get(dates[i]).size();
    }

    return totalByDate;
  }

  /**
   * Works out amount of leases per week for last 5 weeks.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 weeks
   */
  private static int[] getSpecificUserStatsWeek(List<Lease> leases) {
    //Sorts leases in order of Date Leased.
    leases.sort(Comparator.comparing(Lease::getDateLeased));

    //Makes the array that shall be returned
    int[] totalByWeek = new int[5];

    //Iterates for 5 weeks
    for (int i = 0; i < 5; i++) {
      //Sets the date range for that week
      LocalDateTime dateTo = LocalDateTime.now().minusDays(i * 7);
      LocalDateTime dateFrom = LocalDateTime.now().minusDays((i + 1) * 7);

      //Groups all Leases by Date Leased.
      //It then filters out any leases that wasn't between start and end of each week.
      Map<LocalDateTime, List<Lease>> leasesMappedPerWeek = leases.stream()
          .filter(item -> (item.getDateLeased().isBefore(dateTo)) && (item.getDateLeased()
              .isAfter(dateFrom))).collect(Collectors.groupingBy(Lease::getDateLeased));

      int totalNoOfLeases = 0;
      Object[] dates = leasesMappedPerWeek.keySet().toArray();
      int dateSize = dates.length;

      // Iterates through the leases for each Day
      for (int j = 0; j < dateSize; j++) {
        totalNoOfLeases = totalNoOfLeases + leasesMappedPerWeek.get(dates[j]).size();
      }

      totalByWeek[i] = totalNoOfLeases;
    }

    return totalByWeek;
  }

  /**
   * Works out amount of leases per month for last 5 months.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 months
   */
  private static int[] getSpecificUserStatsMonth(List<Lease> leases) {
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
      Map<LocalDateTime, List<Lease>> leasesMappedPerMonth = leases.stream()
          .filter(item -> (item.getDateLeased().isBefore(dateTo)) && (item.getDateLeased()
              .isAfter(dateFrom))).collect(Collectors.groupingBy((Lease::getDateLeased)));

      int totalNoOfLeases = 0;
      Object[] dates = leasesMappedPerMonth.keySet().toArray();
      int dateSize = dates.length;

      //Iterates through each Date
      for (int j = 0; j < dateSize; j++) {
        totalNoOfLeases = totalNoOfLeases + leasesMappedPerMonth.get(dates[j]).size();
      }

      totalByMonth[i] = totalNoOfLeases;
    }

    return totalByMonth;
  }


  /**
   * Works out amount of leases per User per day for last 5 days.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 days
   */
  private static int[] getAverageUserStatsDay(List<Lease> leases) {

    //Groups all Leases by Borrowing Customer, then by Date Leased.
    //It then filters out any leases that was before 4 days ago.
    Map<LocalDateTime, Map<Customer, List<Lease>>> leasesMappedPerDay = leases.stream()
        .filter(item -> item.getDateLeased().isAfter(LocalDateTime.now().minusDays(4)))
        .collect(Collectors.groupingBy(Lease::getDateLeased,
            Collectors.groupingBy(Lease::getBorrowingCustomer)));

    //Makes the array that shall be returned
    int[] totalByDate = new int[5];
    Object[] dates = leasesMappedPerDay.keySet().toArray();

    //Iterates for 5 days
    for (int i = 0; i < dates.length; i++) {
      int totalNoOfLeases = 0;

      Map<Customer, List<Lease>> dateMap = leasesMappedPerDay.get(dates[i]);
      Object[] customers = dateMap.keySet().toArray();
      int customerSize = customers.length;

      //Iterates through leases per customer and finds number of leases per customer per day
      //Adds it to holder variable
      for (int k = 0; k < customerSize; k++) {
        int temp = dateMap.get(customers[k]).size();
        //Averages number of Leases based upon no.of Customers
        totalNoOfLeases = (totalNoOfLeases + temp) / customerSize;
      }
      totalByDate[i] = totalNoOfLeases;

    }

    return totalByDate;
  }

  /**
   * Works out amount of leases per User per day for last 5 weeks.
   *
   * @param leases Records of what users borrowed
   * @return Array of amount of leases for user for last 5 weeks
   */
  private static int[] getAverageUserStatsWeek(List<Lease> leases) {
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
  private static int[] getAverageUserStatsMonth(List<Lease> leases) {
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
   * Works out amount of fines per User per day for last 5 days.
   *
   * @param fines Records of User Fines
   * @return amount of fines per User per day for last 5 days.
   */
  private static int[][] getFineStatsDay(List<Fine> fines) {
    //Groups all Leases by Fined Customer, then by Date Accrued.
    //It then filters out any leases that was before 4 days ago.
    Map<LocalDateTime, Map<Customer, List<Fine>>> finesMappedPerDay = fines.stream()
        .filter(item -> item.getDateAccrued().isAfter(LocalDateTime.now().minusDays(4)))
        .collect(Collectors.groupingBy(Fine::getDateAccrued,
            Collectors.groupingBy(Fine::getFinedCustomer)));

    //Makes the array that shall be returned
    int[][] totalByDate = new int[2][5];
    Object[] dates = finesMappedPerDay.keySet().toArray();

    //Iterates for 5 days
    for (int i = 0; i < dates.length; i++) {
      int totalNoOfFines = 0;

      Map<Customer, List<Fine>> dateMap = finesMappedPerDay.get(dates[i]);
      Object[] customers = dateMap.keySet().toArray();
      int customerSize = customers.length;

      //Iterates through fines per customer and finds number of fines per customer per day
      //Adds it to holder variable
      for (int k = 0; k < customerSize; k++) {
        int temp = dateMap.get(customers[k]).size();
        //Averages number of Leases based upon no.of Customers
        totalNoOfFines = totalNoOfFines + temp;
      }
      totalByDate[0][i] = totalNoOfFines;
      totalByDate[1][i] = totalNoOfFines / customerSize;
    }
    return totalByDate;
  }

  /**
   * Works out amount of fines per User per week for last 5 weeks.
   *
   * @param fines Records of User Fines
   * @return amount of fines per User per day for last 5 weeks.
   */
  private static int[][] getFineStatsWeek(List<Fine> fines) {
    //Sorts fines in order of Date Accrued.
    fines.sort(Comparator.comparing(Fine::getDateAccrued));

    //Makes the array that shall be returned
    int[][] totalByWeek = new int[2][5];

    //Iterates for 5 weeks
    for (int i = 0; i < 5; i++) {
      //Sets the date range for that week
      LocalDateTime dateTo = LocalDateTime.now().minusDays(i * 7);
      LocalDateTime dateFrom = LocalDateTime.now().minusDays((i + 1) * 7);

      //Groups all Fines by Fined Customer, then by Date Accrued.
      //It then filters out any fines that wasn't between start and end of each week.
      Map<LocalDateTime, Map<Customer, List<Fine>>> finesMappedPerWeek = fines.stream()
          .filter(item -> (item.getDateAccrued().isBefore(dateTo)) && (item.getDateAccrued()
              .isAfter(dateFrom))).collect(Collectors.groupingBy((Fine::getDateAccrued),
              Collectors.groupingBy(Fine::getFinedCustomer)));

      int[] totalNoOfFines = new int[2];
      Object[] dates = finesMappedPerWeek.keySet().toArray();
      int dateSize = dates.length;

      // Iterates through the fines for each Day
      for (int j = 0; j < dateSize; j++) {
        Map<Customer, List<Fine>> dateMap = finesMappedPerWeek.get(dates[j]);
        Object[] customers = dateMap.keySet().toArray();
        int customerSize = customers.length;

        //Iterates through fines per customer and finds number of fines per customer per day
        //Adds it to holder variable
        for (int k = 0; k < customerSize; k++) {
          int temp = dateMap.get(customers[k]).size();
          totalNoOfFines[0] = totalNoOfFines[0] + temp;
        }
        totalNoOfFines[1] = totalNoOfFines[i] + (totalNoOfFines[0] / customerSize);
      }

      totalByWeek[0][i] = totalNoOfFines[0];
      totalByWeek[1][i] = totalNoOfFines[1] / 7;
    }

    return totalByWeek;

  }

  /**
   * Works out amount of fines per User per week for last 5 months.
   *
   * @param fines Records of User Fines
   * @return amount of fines per User per day for last 5 months.
   */
  private static int[][] getFineStatsMonth(List<Fine> fines) {
    //Sorts fines in order of Date Accrued.
    fines.sort(Comparator.comparing(Fine::getDateAccrued));

    //Makes the array that shall be returned
    int[][] totalByMonth = new int[2][5];

    //Iterates for 5 months
    for (int i = 0; i < 5; i++) {
      //Sets the date range for that month
      LocalDateTime dateTo = LocalDateTime.now().minusMonths(i);
      LocalDateTime dateFrom = LocalDateTime.now().minusMonths(i + 1);

      //Groups all Fines by Fined Customer, then by Date Accrued.
      //It then filters out any fines that wasn't between start and end of each month.
      Map<LocalDateTime, Map<Customer, List<Fine>>> finesMappedPerMonth = fines.stream()
          .filter(item -> (item.getDateAccrued().isBefore(dateTo)) && (item.getDateAccrued()
              .isAfter(dateFrom))).collect(Collectors.groupingBy((Fine::getDateAccrued),
              Collectors.groupingBy(Fine::getFinedCustomer)));

      int[] totalNoOfFines = new int[2];
      Object[] dates = finesMappedPerMonth.keySet().toArray();
      int dateSize = dates.length;

      //Iterates through each Date
      for (int j = 0; j < dateSize; j++) {
        Map<Customer, List<Fine>> dateMap = finesMappedPerMonth.get(dates[j]);
        Object[] customers = dateMap.keySet().toArray();
        int customerSize = customers.length;

        //Iterates through fines per customer and finds number of fines per customer per day
        //Adds it to holder variable
        for (int k = 0; k < customerSize; k++) {
          int temp = dateMap.get(customers[k]).size();
          totalNoOfFines[0] = totalNoOfFines[0] + temp;
        }
        totalNoOfFines[1] = totalNoOfFines[i] + (totalNoOfFines[0] / customerSize);
      }

      totalByMonth[0][i] = totalNoOfFines[0];
      totalByMonth[1][i] = totalNoOfFines[1] / 30;
    }

    return totalByMonth;
  }


}
