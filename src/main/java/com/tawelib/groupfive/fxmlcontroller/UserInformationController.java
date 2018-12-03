package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
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

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    firstNameTextField.setText(selectedUser.getFirstName());
    lastNameTextField.setText(selectedUser.getLastName());
    usernameTextField.setText(selectedUser.getUsername());
    addressTextField.setText(selectedUser.getAddress().toString());

    if (selectedUser.getClass().equals(Customer.class)) {
      Customer selectedCustomer = (Customer) selectedUser;
      balanceTextField.setText(
          String.format(
              "£ %.2f",
              selectedCustomer.getAccountBalanceInPounds()
          )
      );

      setNodeVisibilities(
          new Node[]{
              balanceLabel,
              balanceTextField
          },
          true
      );
    } else {
      setNodeVisibilities(
          new Node[]{
              balanceLabel,
              balanceTextField
          },
          false
      );
    }
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserList");
  }
}
