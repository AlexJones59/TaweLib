package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.tablewrapper.LeaseTableWrapper;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.ResourceHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
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

/**
 * The type User information controller.
 */
public class UserInformationController extends BaseFxmlController {

  @FXML
  private ImageView userProfileImageView;

  /**
   * The First name text field.
   */
  @FXML
  public TextField firstNameTextField;

  /**
   * The Last name text field.
   */
  @FXML
  public TextField lastNameTextField;

  /**
   * The Username text field.
   */
  @FXML
  public TextField usernameTextField;

  /**
   * The Address text field.
   */
  @FXML
  public TextField addressTextField;

  /**
   * The Balance label.
   */
  @FXML
  public Label balanceLabel;

  /**
   * The Balance text field.
   */
  @FXML
  public TextField balanceTextField;

  /**
   * The Resource table view.
   */
  @FXML
  public TableView<LeaseTableWrapper> resourceTableView;

  /**
   * The Resource id table column.
   */
  @FXML
  public TableColumn<LeaseTableWrapper, String> resourceIdTableColumn;

  /**
   * The Copy id table column.
   */
  @FXML
  public TableColumn<LeaseTableWrapper, String> copyIdTableColumn;

  /**
   * The Title table column.
   */
  @FXML
  public TableColumn<LeaseTableWrapper, String> titleTableColumn;

  /**
   * The Due date table column.
   */
  @FXML
  public TableColumn<LeaseTableWrapper, LocalDateTime> dueDateTableColumn;

  /**
   * The Status table column.
   */
  @FXML
  public TableColumn<LeaseTableWrapper, CopyStatus> statusTableColumn;

  /**
   * Instantiates a new User information controller.
   */
  public UserInformationController() {
  }

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    resourceIdTableColumn
        .setCellValueFactory(new PropertyValueFactory<>("resourceId"));

    copyIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("copyId"));

    titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

    dueDateTableColumn
        .setCellValueFactory(new PropertyValueFactory<>("dueDate"));

    statusTableColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {

    if (isCustomerLoggedIn()) {
      Customer loggedInCustomer = (Customer) loggedInUser;

      userProfileImageView
          .setImage(ResourceHelper.getUserProfileImage(loggedInCustomer));
      firstNameTextField.setText(loggedInCustomer.getFirstName());
      lastNameTextField.setText(loggedInCustomer.getLastName());
      usernameTextField.setText(loggedInCustomer.getUsername());
      addressTextField.setText(loggedInCustomer.getAddress().toString());

      balanceTextField.setText(String
          .format("£ %.2f", loggedInCustomer.getAccountBalanceInPounds()));

      setNodeVisibilities(new Node[]{balanceLabel, balanceTextField}, true);


      setTableContents(library.getLeaseRepository()
              .getCustomerLeaseHistory(loggedInCustomer),
          library.getRequestRepository()
              .getOpenCustomerRequests(loggedInCustomer),
          library.getRequestRepository().getCustomerReserved(loggedInCustomer));
    } else {
      userProfileImageView
          .setImage(ResourceHelper.getUserProfileImage(selectedUser));
      firstNameTextField.setText(selectedUser.getFirstName());
      lastNameTextField.setText(selectedUser.getLastName());
      usernameTextField.setText(selectedUser.getUsername());
      addressTextField.setText(selectedUser.getAddress().toString());

      if (selectedUser.getClass().equals(Customer.class)) {
        Customer selectedCustomer = (Customer) selectedUser;
        balanceTextField.setText(String
            .format("£ %.2f", selectedCustomer.getAccountBalanceInPounds()));

        setNodeVisibilities(new Node[]{balanceLabel, balanceTextField}, true);

        setTableContents(library.getLeaseRepository()
                .getCustomerLeaseHistory(selectedCustomer),
            library.getRequestRepository()
                .getOpenCustomerRequests(selectedCustomer),
            library.getRequestRepository()
                .getCustomerReserved(selectedCustomer));

      } else {
        setNodeVisibilities(new Node[]{balanceLabel, balanceTextField}, false);
      }
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
      SceneHelper.setUpScene(this, "BorrowResource");
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

  /**
   * Goes back to previous scene.
   */
  public void back() {
    if (loggedInUser.getClass().equals(Librarian.class)) {
      SceneHelper.setUpScene(this, "UserList");
    } else {
      SceneHelper.setUpScene(this, "UserDashboard");
    }


  }
}
