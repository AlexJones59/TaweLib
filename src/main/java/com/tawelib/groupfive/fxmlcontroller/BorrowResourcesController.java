package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
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
 * @author Nayeem
 * @version 0.1
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

  private Customer selectedUser = (Customer) BaseFxmlController.selectedUser;

  /**
   * Checks whether user can borrow a resource and then lets them borrow specified resource.
   */
  public void borrow() {
    if (selectedUser.getAccountBalance() < 0) {
      AlertHelper.alert(AlertType.WARNING, "Your balance is below the minimum.");
    } /*else {
      //if (CopyManager.borrowResourceCopy.txtResourceCopyId.getText()) <<

      CopyManager.borrowResourceCopy(library, txtResourceCopyId.getText(), selectedUser);*/
  }


  public void cancel() {
    SceneHelper.setUpScene(this, "UserInformation");
  }

}
