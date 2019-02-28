package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.manager.ResourceCapManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This controls the Borrow Resource screen. This allows Librarians to loan resources to customers
 * that have above minimum account balance. They loan a resource by entering its relevant copy ID.
 *
 * @author Shree Desai, Nayeem Mohammed
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
    List<Lease> overdueLeases = library.getLeaseRepository()
        .getCustomerOverdueLeases(selectedCustomer);
    Copy requestedCopy = library.getCopyRepository()
        .getSpecific(txtResourceCopyId.getText());

    if (ResourceCapManager
        .isUnderResourceCap(library, selectedCustomer, requestedCopy.getResource())) {

      if (requestedCopy == null) {
        AlertHelper.alert(AlertType.ERROR, "Copy ID is not valid.");
      } else {
        if (selectedCustomer.getAccountBalance() < 0) {
          AlertHelper.alert(
              AlertType.ERROR,
              "Your balance is below the minimum."
          );
          back();
        } else {
          if (!overdueLeases.isEmpty()) {
            AlertHelper.alert(
                AlertType.ERROR,
                "You have overdue copies."
            );
          } else {
            if (!requestedCopy.getStatus().equals(CopyStatus.AVAILABLE)) {
              AlertHelper.alert(
                  AlertType.ERROR,
                  "This copy is not available to borrow."
              );
              back();
            } else {
              CopyManager.borrowResourceCopy(
                  library,
                  txtResourceCopyId.getText(),
                  selectedCustomer.getUsername()
              );
              AlertHelper.alert(
                  AlertType.INFORMATION,
                  "Borrowed."
              );
              back();
            }
          }
        }
      }
    } else {
      AlertHelper.alert(Alert.AlertType.ERROR, "You have exceeded the resource cap. "
          + "An item must be returned before another can be borrowed.");
    }
  }

  /**
   * Returns to the user information screen.
   */
  public void cancel() {
    back();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "UserInformation");
  }
}
