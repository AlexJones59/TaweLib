package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserDashboardController extends BaseFxmlController {

  @FXML
  public Button logOutButton;

  @FXML
  public TextField usernameTextField;

  @FXML
  public TextField fullNameTextField;

  @FXML
  public TextField addressTextField;

  @FXML
  public Label staffNumberLabel;

  @FXML
  public TextField staffNumberTextField;

  @FXML
  public Button browseResourceButton;

  @FXML
  public Button manageAccountButton;

  @FXML
  public Button transactionsAndFinesButton;

  @FXML
  public Button createNewAccountButton;

  @FXML
  public Button overdueCopiesButton;

  @FXML
  public Button manageUsersButton;

  @FXML
  public Button manageResourcesButton;

  public UserDashboardController() {
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    setGuiForUsers();

    if (isLibrarianLoggedIn()) {
      setGuiForLibrarians();
    } else {
      setGuiForCustomers();
    }

    setNodeVisibilities();
  }

  private void setNodeVisibilities() {
    Node[] availableToLibrariansOnly = {
        staffNumberLabel,
        staffNumberTextField,
        manageResourcesButton,
        overdueCopiesButton,
        createNewAccountButton,
        manageUsersButton
    };

    Node[] availableToCustomersOnly = {
        transactionsAndFinesButton,
        manageAccountButton
    };

    boolean librarianLoggedIn = isLibrarianLoggedIn();

    for (Node node : availableToLibrariansOnly) {
      node.setVisible(librarianLoggedIn);
    }

    boolean customerLoggedIn = isCustomerLoggedIn();

    for (Node node : availableToCustomersOnly) {
      node.setVisible(customerLoggedIn);
    }
  }

  private void setGuiForUsers() {
    usernameTextField.setText(loggedInUser.getUsername());
    fullNameTextField.setText(loggedInUser.getFullName());
    //TODO: Format Address nicely.
    addressTextField.setText(loggedInUser.getAddress().toString());
  }

  private void setGuiForLibrarians() {
    staffNumberTextField.setText(
        String.format(
            "%d",
            ((Librarian) loggedInUser).getStaffNumber()
        )
    );
  }

  private void setGuiForCustomers() {

  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    loggedInUser = null;
    SceneHelper.setUpScene(this, "Login");
  }

  public void browseResources() {
    SceneHelper.setUpScene(this, "Resources");
  }

  public void manageAccount() {
    SceneHelper.setUpScene(this, "UpdateUserInformation");
  }

  public void transactionsAndFines() {
    SceneHelper.setUpScene(this, "TransactionsAndFines");
  }

  public void createNewAccount() {
    SceneHelper.setUpScene(this, "CreateAccount");
  }

  public void overdueCopies() {
    SceneHelper.setUpScene(this, "OverdueCopies");
  }

  public void manageUsers() {
    SceneHelper.setUpScene(this, "UserList");
  }

  public void manageResources() {
    SceneHelper.setUpScene(this, "ResourceInformation");
  }
}
