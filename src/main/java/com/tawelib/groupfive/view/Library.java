package com.tawelib.groupfive.view;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.FileSystemHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import javafx.application.Application;
import javafx.stage.Stage;

public class Library extends Application {

  public static final String DEFAULT_LIBRARY_NAME = "Tawe-Lib";

  private com.tawelib.groupfive.entity.Library library;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      library = FileSystemHelper.getLibrary(DEFAULT_LIBRARY_NAME);

      SceneHelper.setUpScene(
          primaryStage,
          library,
          null,
          "Login"
      );

      setUpDefaultUsers();
    } catch (Exception e) {
      //TODO: Announce that the library could not be loaded nor created.
      e.printStackTrace();
      System.exit(1);
    }

    primaryStage.show();
  }

  @Override
  public void stop() {
    try {
      FileSystemHelper.saveLibraryToFile(library);
    } catch (IOException e) {
      //TODO: deal with this situation in a better way?
      e.printStackTrace();
      System.exit(1);
    }
  }

  /**
   * Contains code for development purposes.
   */
  private void setUpDefaultUsers() {
    library.getLibrarianRepository().add(
        new Librarian(
            "System",
            "Admin",
            "Phone #",
            "110",
            "The street",
            "Swansea",
            "SA28PJ",
            LocalDate.now()
        )
    );
    library.getCustomerRepository().add(
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
  }
}
