package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.exception.AuthenticationException;
import com.tawelib.groupfive.repository.UserRepository;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controls the login screen.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class LoginController extends BaseFxmlController {

  @FXML
  public TextField usernameTextField;

  @FXML
  public Label usernameLabel;

  @FXML
  public CheckBox librarianCheckBox;

  public LoginController() {
  }

  /**
   * Changes the login info label depending on who is trying to log in.
   */
  public void toggleLibrarian() {
    if (librarianCheckBox.isSelected()) {
      usernameLabel.setText("Username or staff ID");
    } else {
      usernameLabel.setText("Username");
    }
  }

  /**
   * Authenticates a User (Customer or Librarian) in or shows an error dialog.
   */
  public void signIn() {
    UserRepository<? extends User> userRepository;

    if (librarianCheckBox.isSelected()) {
      userRepository = library.getLibrarianRepository();
    } else {
      userRepository = library.getCustomerRepository();
    }

    try {
      loggedInUser = userRepository.authenticate(usernameTextField.getText());

      SceneHelper.setUpScene(this, "UserDashboard");
    } catch (AuthenticationException e) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setHeaderText("User not found.");
      alert.setContentText("Try different credentials.");
      alert.showAndWait();
    }
  }
}
