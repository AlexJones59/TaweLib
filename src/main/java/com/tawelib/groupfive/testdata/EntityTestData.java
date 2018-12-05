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
import com.tawelib.groupfive.repository.CustomerRepository;
import com.tawelib.groupfive.repository.LibrarianRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            "Phone #",
            "1",
            "The street",
            "Swansea",
            "SA28PJ",
            new Date()
        )
    );

    librarianRepository.add(
        new Librarian(
            "happy",
            "Admin",
            "Phone # :D",
            "2",
            "The better street",
            "Cardiff",
            "SA1 4LL",
            new Date()
        )
    );
    //[CUSTOMER]
    // ---------------------------------------------------------------------------------------------
    customerRepository.add(
        new Customer(
            "Nice",
            "Customer",
            "000000000",
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
            "pon nombrero",
            "uno",
            "Some street",
            "MEXICOOO",
            "MX03 2UT"
        )
    );

    // [BOOK]
    // ---------------------------------------------------------------------------------------------
    Book book = new Book(
        "Book1",
        2010,
        null,
        "Theeee Author",
        "Publisheeeer",
        "Genreeeeeeeeeee",
        "IZBNN",
        "C#"
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
        "DVD1",
        2018,
        null,
        "Director",
        60,
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
        "New Laptop",
        2016,
        null,
        "Manufaturer",
        "Model",
        "installedOperatingSystem"
    );

    library.getResourceRepository().add(laptop);

    Laptop laptop1 = new Laptop(
        "HP Laptop",
        2018,
        null,
        "HP",
        "HP 5000 ?",
        "Windows (Better than Linux)"
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


    Lease lease = new Lease(
        library.getCustomerRepository().getSpecific("nice.customer"),
        copy
    );
    lease.setDueDate(new Date());
    library.getLeaseRepository().add(lease);


    Lease lease2 = new Lease(
        library.getCustomerRepository().getAll().get(0),
        copy2
    );
    library.getLeaseRepository().add(lease2);

    // [REQUEST]
    // ---------------------------------------------------------------------------------------------

    Request newRequest = new Request(
        library.getCustomerRepository().getAll().get(0),
        book1
    );

    library.getRequestRepository().add(newRequest);


    Request newRequest2 = new Request(
        library.getCustomerRepository().getAll().get(0),
        dvd1
    );
    newRequest2.setStatus(RequestStatus.RESERVED);

    library.getRequestRepository().add(newRequest2);

    // [FINE]
    //----------------------------------------------------------------------------------------------

    Fine fine = new Fine(lease, 3);

    library.getFineRepository().add(fine);

    // [TRANSACTION]
    //----------------------------------------------------------------------------------------------

    Transaction transaction = new Transaction(5,
        library.getCustomerRepository().getSpecific("nice.customer"));

    library.getTransactionRepository().add(transaction);
  }
}
