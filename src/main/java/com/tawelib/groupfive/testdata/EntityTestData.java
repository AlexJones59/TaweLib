package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Fine;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.entity.RequestStatus;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.repository.CustomerRepository;
import com.tawelib.groupfive.repository.LibrarianRepository;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Entity Test Data Class is used to populate the library for demonstration
 * purposes.
 *
 * @author Nayeem Mohammed, Shree Desai
 *
 */
public class EntityTestData {

  /**
   * Populates the Library with test data.
   *
   * @param library Library.
   */
  public static void populateLibrary(Library library) {
    LibrarianRepository librarianRepository = library.getLibrarianRepository();
    CustomerRepository customerRepository = library.getCustomerRepository();
    //[LIBRARIAN]
    // ---------------------------------------------------------------------------------------------
    librarianRepository.add(
        new Librarian(
            "System",
            "Admin",
            "5751236485",
            "1",
            "The street",
            "Swansea",
            "SA28PJ",
            LocalDateTime.now()
        )
    );

    librarianRepository.add(
        new Librarian(
            "Default",
            "Librarian",
            "6124856473",
            "2",
            "The better street",
            "Cardiff",
            "SA1 4LL",
            LocalDateTime.now()
        )
    );
    //[CUSTOMER]
    // ---------------------------------------------------------------------------------------------
    customerRepository.add(
        new Customer(
            "Nice",
            "Customer",
            "6321546875",
            "56",
            "Swansea Uni",
            "Swansea",
            "SA20AT"
        )
    );

    customerRepository.add(
        new Customer(
            "Sombrero",
            "Customer",
            "3211624486",
            "55",
            "Some street",
            "Mexico",
            "MX03 2UT"
        )
    );

    // [BOOK]
    // ---------------------------------------------------------------------------------------------
    Book book = new Book(
        "The Hobbit",
        1973,
        null,
        "J. R. R. Tolkien",
        "Publisher",
        "Fantasy",
        "0-8020-9086-9",
        "English"
    );

    library.getResourceRepository().add(book);

    Book book1 = new Book(
        "The Dunwich Horror",
        1980,
        null,
        "H.P. Lovecraft",
        "No clue",
        "Cosmic Horror",
        "IZBN",
        "R'lyeh"
    );

    library.getResourceRepository().add(book1);

    // [DVD]
    // ---------------------------------------------------------------------------------------------

    ArrayList<String> l1 = new ArrayList<>();

    l1.add("English");

    Dvd dvd = new Dvd(
        "The Prestige",
        2006,
        null,
        "Christopher Nolan",
        195,
        l1,
        l1
    );

    library.getResourceRepository().add(dvd);

    ArrayList<String> l2 = new ArrayList<>();

    l2.add("Spanish");

    ArrayList<String> l3 = new ArrayList<>();

    l3.add("English");

    Dvd dvd1 = new Dvd(
        "Intersteller",
        2016,
        null,
        "Christopher Nolan",
        420,
        l2,
        l3
    );

    library.getResourceRepository().add(dvd1);

    // [LAPTOP]
    // ---------------------------------------------------------------------------------------------

    Laptop laptop = new Laptop(
        "The Beast",
        2016,
        null,
        "Lenoto",
        "X7",
        "Ubuntu"
    );

    library.getResourceRepository().add(laptop);

    Laptop laptop1 = new Laptop(
        "HP Laptop",
        2018,
        null,
        "HP",
        "HP 5000",
        "Windows"
    );

    library.getResourceRepository().add(laptop1);

    // [COPY]
    // ---------------------------------------------------------------------------------------------

    Copy copy = new Copy(book);
    library.getCopyRepository().add(copy);

    Copy copy2 = new Copy(dvd);
    library.getCopyRepository().add(copy2);

    Copy copy3 = new Copy(laptop);
    library.getCopyRepository().add(copy3);

    // [LEASE]
    // ---------------------------------------------------------------------------------------------

    //    Lease lease = new Lease(
    //        library.getCustomerRepository().getSpecific("nice.customer"),
    //        copy
    //    );
    //    lease.dev_setDateLeased(LocalDateTime.of(2018, 11, 5, 12, 0));
    //    lease.setDueDate(LocalDateTime.of(2018, 11, 15, 12, 0));
    //    lease.dev_setDateReturned(LocalDateTime.of(2018, 12, 5, 12, 0));
    //    library.getLeaseRepository().add(lease);

    CopyManager.borrowResourceCopy(
        library,
        copy.getId(),
        "nice.customer"
    );

    try {
      //Sets date Leased
      Field dateLeased =
          library.getLeaseRepository().getAll().get(0).getClass().getDeclaredField("dateLeased");
      dateLeased.setAccessible(true);
      dateLeased.set(library.getLeaseRepository().getAll().get(0),
          LocalDateTime.of(2018, 12, 5, 12, 0));
      dateLeased.setAccessible(false);

      //Sets due Date
      Field dueDate =
          library.getLeaseRepository().getAll().get(0).getClass().getDeclaredField("dueDate");
      dueDate.setAccessible(true);
      dueDate.set(library.getLeaseRepository().getAll().get(0),
          LocalDateTime.of(2018, 12, 5, 12, 0));
      dueDate.setAccessible(false);

    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }


    CopyManager.borrowResourceCopy(
        library,
        copy2.getId(),
        "nice.customer"
    );

    // [REQUEST]
    // ---------------------------------------------------------------------------------------------

    Request newRequest = new Request(
        library.getCustomerRepository().getAll().get(1),
        book
    );

    library.getRequestRepository().add(newRequest);

    //Returns copy so we can checkout reserved
    //CopyManager.returnResourceCopy(library, copy.getId());

    Request newRequest2 = new Request(
        library.getCustomerRepository().getAll().get(0),
        dvd1
    );
    library.getRequestRepository().add(newRequest2);

  }
}
