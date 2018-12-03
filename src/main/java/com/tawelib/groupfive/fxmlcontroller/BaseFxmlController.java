package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.User;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Holds important runtime references and controls the GUI actions.
 *
 * @author Petr Hoffmann
 * @version 0.2
 */
public abstract class BaseFxmlController {

  protected Library library;

  protected Stage primaryStage;

  protected User loggedInUser;

  protected Node[] librarianNodes;

  protected Node[] customerNodes;

  /**
   * Configures the visible nodes and calls the refresh function.
   */
  public void setVisibilitiesAndRefresh() {
    configureVisibilities();

    if (librarianNodes != null && customerNodes != null) {
      boolean librarianLoggedIn = isLibrarianLoggedIn();

      for (Node node : librarianNodes) {
        node.setVisible(librarianLoggedIn);
      }

      boolean customerLoggedIn = isCustomerLoggedIn();

      for (Node node : customerNodes) {
        node.setVisible(customerLoggedIn);
      }
    }

    refresh();
  }

  protected void configureVisibilities() {
  }

  /**
   * Performs actions once the new scene is shown and runtime variables are
   * set.
   */
  protected void refresh() {
  }

  /**
   * Returns the library.
   *
   * @return Library.
   */
  public Library getLibrary() {
    return library;
  }

  /**
   * Sets the library.
   *
   * @param library Library.
   */
  public void setLibrary(Library library) {
    this.library = library;
  }

  /**
   * Returns the primary stage reference.
   *
   * @return Primary stage.
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }

  /**
   * Sets the primary stage reference.
   *
   * @param primaryStage Primary stage.
   */
  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  /**
   * Returns the logged-in user.
   *
   * @return User who is logged in.
   */
  public User getLoggedInUser() {
    return loggedInUser;
  }

  /**
   * Sets the logged-in user.
   *
   * @param loggedInUser User who is logged in.
   */
  public void setLoggedInUser(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  /**
   * Returns true or false depending on whether a librarian is logged in.
   *
   * @return True if a librarian is logged in, false otherwise.
   */
  protected boolean isLibrarianLoggedIn() {
    if (loggedInUser == null) {
      return false;
    } else {
      return loggedInUser.getClass().equals(Librarian.class);
    }
  }

  /**
   * Returns true or false depending on whether a customer is logged in.
   *
   * @return True if a customer is logged in, false otherwise.
   */
  protected boolean isCustomerLoggedIn() {
    if (loggedInUser == null) {
      return false;
    } else {
      return loggedInUser.getClass().equals(Customer.class);
    }
  }
}