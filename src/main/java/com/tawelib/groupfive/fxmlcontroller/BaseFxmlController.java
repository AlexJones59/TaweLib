package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.User;
import java.time.LocalDateTime;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Holds important runtime references and controls the GUI actions. This is the superclass for all
 * of the screens in this system.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public abstract class BaseFxmlController {

  protected static User loggedInUser;

  protected static User selectedUser;

  protected static LocalDateTime lastLogin;

  protected Library library;

  protected Stage primaryStage;

  protected Node[] librarianNodes;

  protected Node[] customerNodes;

  protected String lastSceneName;

  protected String currentSceneName;

  /**
   * Configures the visible nodes and calls the refresh function.
   */
  public void setVisibilitiesAndRefresh() {
    configureVisibilities();

    if (librarianNodes != null) {
      setNodeVisibilities(librarianNodes, isLibrarianLoggedIn());
    }

    if (customerNodes != null) {
      setNodeVisibilities(customerNodes, isCustomerLoggedIn());
    }

    refresh();

    if (isLibrarianLoggedIn()) {
      refreshForLibrarians();
    }

    if (isCustomerLoggedIn()) {
      refreshForCustomers();
    }
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
   * Returns the currently selected user.
   *
   * @return Selected User.
   */
  public User getSelectedUser() {
    return selectedUser;
  }

  /**
   * Sets the currently selected User.
   *
   * @param selectedUser Selected User.
   */
  public void setSelectedUser(User selectedUser) {
    this.selectedUser = selectedUser;
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

  /**
   * Returns to the previous screen.
   */
  public void back() {
  }

  /**
   * Sets node visibilities.
   *
   * @param nodes the nodes
   * @param visible whether node is visible or not
   */
  protected void setNodeVisibilities(Node[] nodes, boolean visible) {
    for (Node node : nodes) {
      node.setVisible(visible);
    }
  }

  /**
   * Empty method.
   */
  protected void configureVisibilities() {
  }

  /**
   * Performs actions once the new scene is shown and runtime variables are set.
   */
  protected void refresh() {
  }

  /**
   * Performs actions once the new scene is shown and runtime variables are set in case that a
   * Librarian is logged in.
   */
  protected void refreshForLibrarians() {
  }

  /**
   * Performs actions once the new scene is shown and runtime variables are set in case that a
   * Customer is logged in.
   */
  protected void refreshForCustomers() {
  }

  /**
   * Sets the name of the previous scene for later access.
   *
   * @param sceneName Name of the previous scene displayed
   */
  public void setLastSceneName(String sceneName) {
    this.lastSceneName = sceneName;
  }

  /**
   * Returns the name of the previous scene.
   *
   * @return Name of the previous scene
   */
  public String getLastSceneName() {
    return lastSceneName;
  }

  /**
   * Returns the name of the current scene displayed.
   *
   * @return Name of the current scene displayed
   */
  public String getCurrentSceneName() {
    return currentSceneName;
  }

  /**
   * Sets the name of the current scene.
   *
   * @param sceneName Name of the current scene
   */
  public void setCurrentSceneName(String sceneName) {
    this.currentSceneName = sceneName;
  }
}
