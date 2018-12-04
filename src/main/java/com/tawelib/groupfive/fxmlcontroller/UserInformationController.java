package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.tablewrapper.CopyTableWrapper;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

  @FXML
  public TableView<CopyTableWrapper> resourceTableView;

  @FXML
  public TableColumn<CopyTableWrapper, String> resourceIdTableColumn;

  @FXML
  public TableColumn<CopyTableWrapper, String> copyIdTableColumn;

  @FXML
  public TableColumn<CopyTableWrapper, String> titleTableColumn;

  @FXML
  public TableColumn<CopyTableWrapper, Date> dueDateTableColumn;

  @FXML
  public TableColumn<CopyTableWrapper, CopyStatus> statusTableColumn;

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

    //TODO: Populate.

    devCommand();
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

  /**
   * TODO: Remove this.
   */
  private void devCommand() {
    if (selectedUser.getClass().equals(Customer.class)) {
      Book book = new Book(
          "The tiTTle",
          2010,
          null,
          "Theeee Author",
          "Publisheeeer",
          "Genreeeeeeeeeee",
          "IZBNN",
          "C#"
      );
      library.getResourceRepository().add(book);

      Copy copy = new Copy(book);
      library.getCopyRepository().add(copy);

      Lease lease = new Lease(
          (Customer) selectedUser,
          copy
      );
      lease.setDueDate(new Date());
      library.getLeaseRepository().add(lease);

      CopyTableWrapper wrapper = new CopyTableWrapper(
          lease
      );

      resourceTableView.getItems().add(wrapper);
    }
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserList");
  }
}
