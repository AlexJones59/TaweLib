package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Request;
import com.tawelib.groupfive.exception.OverResourceCapException;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.tablewrapper.LeaseTableWrapper;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.ResourceHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 * The type User information controller. Here a user can see the all the information linked to their
 * account. This screen will let users edit their account information, borrow resources, manage
 * balance, and other actions relating to their account.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class UserInformationController extends BaseFxmlController {

  @FXML
  private ImageView userProfileImageView;

  @FXML
  private TextField firstNameTextField;

  @FXML
  private TextField lastNameTextField;

  @FXML
  private TextField usernameTextField;

  @FXML
  private TextField addressTextField;

  @FXML
  private Label balanceLabel;

  @FXML
  private TextField balanceTextField;

  @FXML
  private TableView<LeaseTableWrapper> resourceTableView;

  @FXML
  private TableColumn<LeaseTableWrapper, String> resourceIdTableColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, String> copyIdTableColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, String> titleTableColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, LocalDateTime> dueDateTableColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, CopyStatus> statusTableColumn;

  @FXML
  private Button btnBorrow;

  @FXML
  private Button btnManageBalance;

  @FXML
  private Button btnEditUserInfo;

  @FXML
  private Button returnCopyButton;

  @FXML
  private Button btnPickUpReserved;

  @FXML
  private Button declareLostButton;

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
    btnEditUserInfo.setVisible(isLibrarianLoggedIn());
    btnEditUserInfo.setVisible(isLibrarianLoggedIn());
    btnBorrow.setVisible((isLibrarianLoggedIn()));
    btnManageBalance.setVisible(isLibrarianLoggedIn());
    returnCopyButton.setVisible(isLibrarianLoggedIn());
    btnPickUpReserved.setVisible(isLibrarianLoggedIn());
    declareLostButton.setVisible(isLibrarianLoggedIn());

    //sets the screen up for when a customer is logged in
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
      //sets the screen for when a librarian is logged in
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
      if (resourceTableView.getSelectionModel().getSelectedItem().getStatus()
          .equals("BORROWED")) {
        CopyManager.returnResourceCopy(library,
            resourceTableView.getSelectionModel().getSelectedItem()
                .getCopyId());

        AlertHelper.alert(AlertType.INFORMATION, "Returned.");

        refresh();
      } else {
        AlertHelper.alert(AlertType.ERROR,
            "Lease selected is not a " + "returnable object");
      }
    } else {
      AlertHelper.alert(AlertType.WARNING, "User is not a Customer.");
    }
  }

  /**
   * Allows a user to pick up a copy of a resource they have reserved.
   */
  public void pickUpReserved() {
    if (selectedUser.getClass().equals(Customer.class)) {
      Customer selectedCustomer = (Customer) selectedUser;
      if (resourceTableView.getSelectionModel().getSelectedItem().getStatus()
          .equals("RESERVED")) {
        try {
          CopyManager.pickUpReservedCopy(library,
              resourceTableView.getSelectionModel().getSelectedItem()
                  .getResourceId(), selectedCustomer.getUsername());
          AlertHelper.alert(AlertType.INFORMATION, "Picked up Reserved Copy");

        } catch (OverResourceCapException e) {
          AlertHelper.alert(Alert.AlertType.ERROR, "You have exceeded the resource cap. "
              + "An item must be returned before another can be borrowed.");
        }
        refresh();

      } else {
        AlertHelper.alert(AlertType.ERROR,
            "Lease selected is not " + "of a reserved copy.");
      }

    } else {
      AlertHelper.alert(AlertType.WARNING, "User is not a Customer.");
    }
  }

  /**
   * Declares a copy as lost.
   */
  public void declareLost() {
    String copyId = resourceTableView.getSelectionModel().getSelectedItem()
        .getCopyId();
    AlertHelper.alert(AlertType.INFORMATION, "Declaring as lost: " + copyId);
    CopyManager.lostCopy(library, copyId);
    refresh();
  }

  /**
   * Goes back to previous scene.
   */
  @Override
  public void back() {
    if (loggedInUser.getClass().equals(Librarian.class)) {
      SceneHelper.setUpScene(this, "UserList");
    } else {
      SceneHelper.setUpScene(this, "UserDashboard");
    }
  }

  /**
   * Populates the table.
   *
   * @param customerLeases resources the customer has leased
   * @param customerRequests resources the customer has requested to lease
   * @param customerReserved respurces that are reserved for the customer
   */
  private void setTableContents(List<Lease> customerLeases,
      List<Request> customerRequests, List<Request> customerReserved) {
    resourceTableView.getItems().clear();

    for (Lease lease : customerLeases) {
      resourceTableView.getItems().add(new LeaseTableWrapper(lease, (Customer) selectedUser));
    }

    for (Request request : customerRequests) {
      resourceTableView.getItems().add(new LeaseTableWrapper(request));
    }

    for (Request reserved : customerReserved) {
      resourceTableView.getItems().add(new LeaseTableWrapper(reserved));
    }
  }
}
