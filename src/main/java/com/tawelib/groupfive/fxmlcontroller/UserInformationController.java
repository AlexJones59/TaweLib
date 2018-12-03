package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserInformationController extends BaseFxmlController {

  @FXML
  public TextField firstNameTextField;

  @FXML
  public TextField lastNameTextField;

  @FXML
  public TextField usernameTextField;

  @FXML
  public TextField addressTextField;

  @FXML
  public Label balanceLabel;

  @FXML
  public TextField balanceTextField;

  public UserInformationController() {
  }

  @Override
  protected void configureVisibilities() {
    customerNodes = new Node[]{
        balanceLabel,
        balanceTextField
    };
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    firstNameTextField.setText(loggedInUser.getFirstName());
    lastNameTextField.setText(loggedInUser.getLastName());
    usernameTextField.setText(loggedInUser.getUsername());
    addressTextField.setText(loggedInUser.getAddress().toString());

    //    if (false) {
    //      Customer loggedInCustomer = (Customer) loggedInUser;
    //      balanceTextField.setText(
    //          String.format(
    //              "£ %f",
    //              loggedInCustomer.getAccountBalanceInPounds()
    //          )
    //      );
    //    }
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserList");
  }
}
