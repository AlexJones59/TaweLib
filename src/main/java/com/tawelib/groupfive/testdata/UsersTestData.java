package com.tawelib.groupfive.testdata;

import com.github.javafaker.Faker;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.repository.CustomerRepository;
import com.tawelib.groupfive.repository.LibrarianRepository;
import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Generates users for testing and showcase purposes.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
class UsersTestData {

  private static final int NUMBER_OF_LIBRARIAN_ACCOUNTS = 4;
  private static final int NUMBER_OF_CUSTOMER_ACCOUNTS = 128;

  private UsersTestData() {
    throw new UnsupportedOperationException("Util class.");
  }

  static void generate(Library library) {
    LibrarianRepository librarianRepository = library.getLibrarianRepository();
    CustomerRepository customerRepository = library.getCustomerRepository();

    librarianRepository.add(
        new Librarian(
            "System",
            "Admin",
            "5751236485",
            "1",
            "The street",
            "Swansea",
            "SA28PJ",
            SimulatedLocalDateTime.now()
        )
    );

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

    Faker faker = new Faker(new Locale("en-GB"));

    for (int i = 0; i < NUMBER_OF_LIBRARIAN_ACCOUNTS; i++) {
      Date randomDate = faker.date().past(500, TimeUnit.DAYS);

      librarianRepository.add(
          new Librarian(
              faker.name().firstName(),
              faker.name().lastName(),
              faker.phoneNumber().cellPhone(),
              faker.address().buildingNumber(),
              faker.address().streetName(),
              faker.address().city(),
              faker.address().zipCode(),
              randomDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
          )
      );
    }

    for (int i = 0; i < NUMBER_OF_CUSTOMER_ACCOUNTS; i++) {
      customerRepository.add(
          new Customer(
              faker.name().firstName(),
              faker.name().lastName(),
              faker.phoneNumber().cellPhone(),
              faker.address().buildingNumber(),
              faker.address().streetName(),
              faker.address().city(),
              faker.address().zipCode()
          )
      );
    }
  }
}
