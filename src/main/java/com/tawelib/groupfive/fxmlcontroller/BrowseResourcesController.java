package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.util.SceneHelper;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class BrowseResourcesController extends BaseFxmlController {

  @FXML
  private Button btnBack;

  @FXML
  private Text lblScreenTitle;

  @FXML
  private MenuButton mbtnSearchAttribute;

  @FXML
  private TextField txtSearch;

  @FXML
  private Label lblSearch;

  @FXML
  private Button btnInfo;

  @FXML
  private ComboBox<String> cmbResourceType;

  private ObservableList<String> resourceType =
      FXCollections.observableArrayList(
      "Book", "DVD","Laptop");

  //TABLE----------------------------------------------------------
  @FXML
  private TableView<Resource> tblBrowseResourcesTable;

  @FXML
  private TableColumn<Resource, String> publisherColumn;

  @FXML
  private TableColumn<Resource, String> genreColumn;

  @FXML
  private TableColumn<Resource, String> idColumn;

  @FXML
  private TableColumn<Resource, String> authorColumn;

  @FXML
  private TableColumn<Resource, String> titleColumn;

  @FXML
  private TableColumn<Resource, String> typeColumn;

  @FXML
  private TableColumn<Resource, String> isbnColumn;

  @FXML
  private TableColumn<Resource, String> languageColumn;

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    idColumn.setCellValueFactory(
        new PropertyValueFactory<Resource, String>("resourceId"));

    titleColumn.setCellValueFactory(
        new PropertyValueFactory<Resource, String>("title"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    cmbResourceType.setItems(resourceType);

    mbtnResourceType.getItems().addAll(menuItemBook, menuItemDvd,
        menuItemLaptop);



    tblBrowseResourcesTable.getItems().addAll(
        library.getResourceRepository().getAll());
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }


}
