package com.tawelib.groupfive.view;

import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.FileSystemHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * This class is the Library application. It loads a library from a file or
 * creates a new one. It shows the GUI and after closing the app windows it
 * saves the library in a file.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class Library extends Application {

  public static final String DEFAULT_LIBRARY_NAME = "Tawe-Lib";

  private static String announceText;

  private com.tawelib.groupfive.entity.Library library;

  /**
   * Launches the app normally.
   *
   * @param args Arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Launches the app to show an announcement.
   *
   * @param text Announcement.
   */
  public static void launchAnnounce(String text) {
    announceText = text;
    launch();
  }

  /**
   * Loads the library and shows the GUI. If an announcement is to be made, the
   * announcement is shown and the app terminates.
   */
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
      AlertHelper.alert(
          AlertType.ERROR,
          "Could not load nor create the library."
      );
      System.exit(1);
    }

    primaryStage.show();

    if (announceText != null) {
      AlertHelper.alert(AlertType.ERROR, announceText);
      System.exit(1);
    }
  }

  /**
   * Saves the library to a file and exits.
   */
  @Override
  public void stop() {
    try {
      FileSystemHelper.saveLibraryToFile(library);
    } catch (IOException e) {
      AlertHelper.alert(
          AlertType.ERROR,
          "Could not save the library. See error log for more details."
      );
      e.printStackTrace();
      System.exit(1);
    }
  }
}
