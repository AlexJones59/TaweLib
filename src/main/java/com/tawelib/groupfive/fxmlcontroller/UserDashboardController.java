package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  public TextField staffNumberTextField;

  public UserDashboardController() {
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    usernameTextField.setText(loggedInUser.getUsername());
    fullNameTextField.setText(loggedInUser.getFullName());
    //TODO: Format Address nicely.
    addressTextField.setText(loggedInUser.getAddress().toString());

    if (loggedInUser.getClass().equals(Librarian.class)) {
      staffNumberTextField.setText(
          String.format(
              "%d",
              ((Librarian) loggedInUser).getStaffNumber()
          )
      );
    }
  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    loggedInUser = null;
    SceneHelper.setUpScene(this, "Login");
  }
}
