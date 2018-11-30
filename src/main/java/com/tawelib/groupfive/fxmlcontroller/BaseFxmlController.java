package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.User;
import javafx.stage.Stage;

public abstract class BaseFxmlController {

  protected Library library;

  protected Stage primaryStage;

  protected User loggedInUser;

  public Library getLibrary() {
    return library;
  }

  public void setLibrary(Library library) {
    this.library = library;
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public User getLoggedInUser() {
    return loggedInUser;
  }

  public void setLoggedInUser(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }
}
