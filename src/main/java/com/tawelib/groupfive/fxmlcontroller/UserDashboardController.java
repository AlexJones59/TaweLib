package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
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

    if (loggedInUser.getClass().equals(Librarian.class)) {
      setGuiForLibrarians();
    } else {
      setGuiForCustomers();
    }
  }

  private void setGuiForUsers() {
    usernameTextField.setText(loggedInUser.getUsername());
    fullNameTextField.setText(loggedInUser.getFullName());
    //TODO: Format Address nicely.
    addressTextField.setText(loggedInUser.getAddress().toString());
  }

  private void setGuiForLibrarians() {
    staffNumberLabel.setVisible(true);
    staffNumberTextField.setVisible(true);
    staffNumberTextField.setText(
        String.format(
            "%d",
            ((Librarian) loggedInUser).getStaffNumber()
        )
    );
  }

  private void setGuiForCustomers() {
    staffNumberLabel.setVisible(false);
    staffNumberTextField.setVisible(false);
  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    loggedInUser = null;
    SceneHelper.setUpScene(this, "Login");
  }
}
