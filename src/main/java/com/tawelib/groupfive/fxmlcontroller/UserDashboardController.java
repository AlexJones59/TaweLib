package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class UserDashboardController extends BaseFxmlController {

  @FXML
  public Button logOutButton;

  @FXML
  public CheckBox librarianCheckBox;

  public UserDashboardController() {
  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    loggedInUser = null;
    SceneHelper.setUpScene(this, "Login");
  }
}
