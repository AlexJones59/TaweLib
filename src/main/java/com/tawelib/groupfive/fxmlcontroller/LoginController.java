package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.exception.AuthenticationException;
import com.tawelib.groupfive.repository.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController extends BaseFxmlController {

  @FXML
  public TextField usernameTextField;

  @FXML
  public Label usernameLabel;

  @FXML
  public CheckBox librarianCheckBox;

  public LoginController() {
  }

  public void toggleLibrarian() {
    if (librarianCheckBox.isSelected()) {
      usernameLabel.setText("Username or staff ID");
    } else {
      usernameLabel.setText("Username");
    }
  }

  public void signIn() {
    UserRepository<? extends User> userRepository;

    if (librarianCheckBox.isSelected()) {
      userRepository = library.getLibrarianRepository();
    } else {
      userRepository = library.getCustomerRepository();
    }

    try {
      loggedInUser = userRepository.authenticate(usernameTextField.getText());
    } catch (AuthenticationException e) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setHeaderText("User not found.");
      alert.setContentText("Try different credentials.");
      alert.showAndWait();
    }
  }
}
