package com.tawelib.groupfive.view;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.FileSystemHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Library extends Application {

  public static final String DEFAULT_LIBRARY_NAME = "Tawe-Lib";

  private com.tawelib.groupfive.entity.Library library;

  private static String announceText;

  public static void main(String[] args) {
    launch(args);
  }

  public static void launchAnnounce(String text) {
    announceText = text;
    launch();
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      library = FileSystemHelper.getLibrary(DEFAULT_LIBRARY_NAME);

      SceneHelper.setUpScene(
          primaryStage,
          library,
          "Login"
      );
    } catch (Exception e) {
      //TODO: Announce that the library could not be loaded nor created.
      e.printStackTrace();
      System.exit(1);
    }

    primaryStage.show();

    if (announceText != null) {
      AlertHelper.alert(AlertType.ERROR, announceText);
      System.exit(1);
    }
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
}
