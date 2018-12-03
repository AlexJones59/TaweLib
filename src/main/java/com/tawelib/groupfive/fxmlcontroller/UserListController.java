package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserListController extends BaseFxmlController {

  @FXML
  public TableView<User> userListTableView;

  @FXML
  public TableColumn<User, String> usernameTableColumn;

  @FXML
  public TableColumn<User, String> fullNameTableColumn;

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
        library.getLibrarianRepository().getAll()
    );
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}
