package com.tawelib.groupfive.view;

import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.FileSystemHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.io.IOException;
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
