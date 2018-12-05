package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.tablewrapper.LeaseTableWrapper;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.ResourceHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class UserInformationController extends BaseFxmlController {

  @FXML
  private ImageView userProfileImageView;

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

  @FXML
  public TableView<LeaseTableWrapper> resourceTableView;

  @FXML
  public TableColumn<LeaseTableWrapper, String> resourceIdTableColumn;

  @FXML
  public TableColumn<LeaseTableWrapper, String> copyIdTableColumn;

  @FXML
  public TableColumn<LeaseTableWrapper, String> titleTableColumn;

  @FXML
  public TableColumn<LeaseTableWrapper, Date> dueDateTableColumn;

  @FXML
  public TableColumn<LeaseTableWrapper, CopyStatus> statusTableColumn;

  public UserInformationController() {
  }

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    resourceIdTableColumn.setCellValueFactory(
        new PropertyValueFactory<>("resourceId"));

    copyIdTableColumn.setCellValueFactory(
        new PropertyValueFactory<>("copyId"));

    titleTableColumn.setCellValueFactory(
        new PropertyValueFactory<>("title"));

    dueDateTableColumn.setCellValueFactory(
        new PropertyValueFactory<>("dueDate"));

    statusTableColumn.setCellValueFactory(
        new PropertyValueFactory<>("status"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {

    userProfileImageView.setImage(ResourceHelper
        .getUserProfileImage(selectedUser));
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

      setTableContents(
          library.getLeaseRepository().getCustomerLeaseHistory(selectedCustomer),
          library.getRequestRepository().getOpenCustomerRequests(selectedCustomer),
          library.getRequestRepository().getCustomerReserved(selectedCustomer));

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

  private void setTableContents(List<Lease> customerLeases,
      List<Request> customerRequests, List<Request> customerReserved) {
    resourceTableView.getItems().clear();

    for (Lease lease : customerLeases) {
      resourceTableView.getItems().add(new LeaseTableWrapper(lease));
    }

    for (Request request : customerRequests) {
      resourceTableView.getItems().add(new LeaseTableWrapper(request));
    }

    for (Request reserved : customerReserved) {
      resourceTableView.getItems().add(new LeaseTableWrapper(reserved));
    }


  }

  /**
   * Launches ManageBalance screen.
   */
  public void manageBalance() {
    if (selectedUser.getClass().equals(Customer.class)) {
      SceneHelper.setUpScene(this, "NewTransaction");
    } else {
      AlertHelper.alert(AlertType.WARNING, "User is not a Customer.");
    }
  }

  /**
   * Launches a window for borrowing new resources.
   */
  public void borrowNewResource() {
    if (selectedUser.getClass().equals(Customer.class)) {
      //TODO: decide from which site to approach this (user first or COPY first)
      //      SceneHelper.setUpScene(this, "BorrowResource");
      AlertHelper.alert(AlertType.ERROR, "To be implemented.");
    } else {
      AlertHelper.alert(AlertType.WARNING, "User is not a Customer.");
    }
  }

  /**
   * Launches a screen for editing user info.
   */
  public void editUserInfo() {
    AccountCrudController controller = (AccountCrudController) SceneHelper
        .setUpScene(this, "AccountCrud");

    controller.setCrudAction(CrudAction.UPDATE);
    controller.refresh();
  }

  /**
   * Returns a selected copy.
   */
  public void returnCopy() {
    if (selectedUser.getClass().equals(Customer.class)) {
      AlertHelper.alert(AlertType.ERROR, "To be implemented.");
    } else {
      AlertHelper.alert(AlertType.WARNING, "User is not a Customer.");
    }
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserList");
  }
}
