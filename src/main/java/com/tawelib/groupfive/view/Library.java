package com.tawelib.groupfive.view;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.FileSystemHelper;
import java.io.IOException;
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

      devFunction();
    } catch (Exception e) {
      //TODO: Announce that the library could not be loaded nor created.
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
    }
  }

  /**
   * Contains code for development purposes.
   */
  private void devFunction() {
    for (int i = 0; i < 5; i++) {
      library.getLibrarianRepository().add(
          new Librarian(
              "Petr",
              "Hoffmann",
              "phone #",
              "66",
              "The Street",
              "Swansea",
              "SA20AT",
              new Date()
          )
      );
    }

    for (Librarian librarian : library.getLibrarianRepository().getAll()) {
      System.out.println(librarian.getUsername());
    }
  }
}
