package com.tawelib.groupfive.view;

import com.tawelib.groupfive.util.FileSystemHelper;
import java.io.IOException;
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
      //TODO: Announce that the library could not be saved.
    }
  }
}
