package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.manager.UserManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//TODO author for this class
/**
 * The type New transaction controller.
 *
 * @author
 * @version 1.0
 */
public class NewTransactionController extends BaseFxmlController {

  @FXML
  public Label usernameLabel;

  @FXML
  public TextField amountTextField;

  /**
   * Instantiates a new New transaction controller.
   */
  public NewTransactionController() {
  }

  /**
   * Populates the username box correctly.
   */
  @Override
  public void refresh() {
    usernameLabel.setText(selectedUser.getUsername());
  }

  /**
   * Adds entered amount of funds to the user's balance.
   */
  public void topUp() {
    if (selectedUser.getClass().equals(Customer.class)) {
      Customer selectedCustomer = (Customer) selectedUser;

      try {
        double fundsBeingAdded = Double.parseDouble(amountTextField.getText());

        int fundsInPennies = (int) (fundsBeingAdded * 100);

        UserManager.topUpAccountBalance(library, selectedCustomer.getUsername(),
            fundsInPennies);

        AlertHelper.alert(AlertType.INFORMATION, "Funds added!");

        back();
      } catch (NumberFormatException e) {
        AlertHelper.alert(AlertType.WARNING, "You must enter a number.");
      }
    }
  }

  /**
   * Returns to the user information screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, "UserInformation");
  }
}
