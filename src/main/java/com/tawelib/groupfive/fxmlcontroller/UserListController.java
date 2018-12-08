package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//TODO get author

/**
 * The type User list controller.
 *
 * @author
 * @version 1.0
 */
public class UserListController extends BaseFxmlController {

  @FXML
  public TextField searchTextField;

  @FXML
  public TableView<User> userListTableView;


  @FXML
  public TableColumn<User, String> usernameTableColumn;

  @FXML
  public TableColumn<User, String> fullNameTableColumn;

  /**
   * Instantiates a new User list controller.
   */
  public UserListController() {
  }

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    usernameTableColumn.setCellValueFactory(
        new PropertyValueFactory<>("username"));

    fullNameTableColumn.setCellValueFactory(
        new PropertyValueFactory<>("fullName"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    userListTableView.getItems().addAll(
        library.getCustomerRepository().getAll()
    );
    userListTableView.getItems().addAll(
        library.getLibrarianRepository().getAll()
    );
  }

  /**
   * Searches for Users by the search term and repopulates the table.
   */
  public void searchAction() {
    userListTableView.getItems().clear();

    userListTableView.getItems().addAll(
        library.getLibrarianRepository().search(
            searchTextField.getText()
        )
    );

    userListTableView.getItems().addAll(
        library.getCustomerRepository().search(
            searchTextField.getText()
        )
    );
  }

  /**
   * Takes the user to the user information screen based on what was selected.
   */
  public void manageUser() {
    selectedUser = userListTableView.getSelectionModel().getSelectedItem();
    SceneHelper.setUpScene(this, "UserInformation");
  }

  /**
   * Returns to the user dashboard.
   */
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}
