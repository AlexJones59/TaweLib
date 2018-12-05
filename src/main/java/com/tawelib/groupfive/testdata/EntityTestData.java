package com.tawelib.groupfive.testdata;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.repository.CustomerRepository;
import com.tawelib.groupfive.repository.LibrarianRepository;
import java.util.Date;

public class EntityTestData {

  /**
   * Populates the Library with test data.
   *
   * @param library Library.
   */
  public static void populateLibrary(Library library) {
    LibrarianRepository librarianRepository = library.getLibrarianRepository();
    CustomerRepository customerRepository = library.getCustomerRepository();

    librarianRepository.add(
        new Librarian(
            "System",
            "Admin",
            "Phone #",
            "110",
            "The street",
            "Swansea",
            "SA28PJ",
            new Date()
        )
    );
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

    Book book = new Book(
        "The tiTTle",
        2010,
        null,
        "Theeee Author",
        "Publisheeeer",
        "Genreeeeeeeeeee",
        "IZBNN",
        "C#"
    );
    library.getResourceRepository().add(book);

    Copy copy = new Copy(book);
    library.getCopyRepository().add(copy);

    Lease lease = new Lease(
        library.getCustomerRepository().getAll().get(0),
        copy
    );
    lease.setDueDate(new Date());
    library.getLeaseRepository().add(lease);
  }
}
