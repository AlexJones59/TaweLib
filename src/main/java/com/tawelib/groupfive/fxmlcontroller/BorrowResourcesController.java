package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Allows Librarians to let loan resources to customers that have above minimum account balance.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 1.0
 */
public class BorrowResourcesController extends BaseFxmlController {

  @FXML
  private Button btnCancel;

  @FXML
  private Label lblScreenTitle;

  @FXML
  private Button btnBorrow;

  @FXML
  private TextField txtResourceCopyId;

  @FXML
  private Label lblResourceId;

  private Customer selectedCustomer = (Customer) BaseFxmlController.selectedUser;

  /**
   * Checks whether user can borrow a resource based on availability or their account balance and
   * then lets them borrow specified resource if allowed.
   */
  public void borrow() {
    // Checks if Copy exists, or throws Error Alert.
    if (library.getCopyRepository().getSpecific(txtResourceCopyId.getText())
        != null) {
      // Checks if AccountBalance is in positive, else throws Error Alert.
      if (selectedCustomer.getAccountBalance() >= 0) {
        //Checks if copy is available to be borrowed.
        if (library.getCopyRepository().getSpecific(txtResourceCopyId.getText())
            .getStatus().equals(CopyStatus.AVAILABLE)) {
          //Borrows copy.
          CopyManager.borrowResourceCopy(library, txtResourceCopyId.getText(),
              selectedCustomer.getUsername());
          AlertHelper.alert(AlertType.INFORMATION,
              "Borrowed.");
          SceneHelper.setUpScene(this, "UserInformation");
        } else {
          AlertHelper.alert(AlertType.ERROR,
              "This copy is not available to " + "borrow.");
          SceneHelper.setUpScene(this, "UserInformation");
        }
      } else {
        AlertHelper
            .alert(AlertType.ERROR, "Your balance is below the minimum.");
        SceneHelper.setUpScene(this, "UserInformation");
      }

    } else {
      AlertHelper.alert(AlertType.ERROR, "Copy ID is no valid.");
    }
  }


  /**
   * Returns to the user information screen.
   */
  public void cancel() {
    SceneHelper.setUpScene(this, "UserInformation");
  }

}
