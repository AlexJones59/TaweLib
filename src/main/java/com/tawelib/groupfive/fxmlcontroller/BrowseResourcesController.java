package com.tawelib.groupfive.fxmlcontroller;

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
import javafx.scene.control.TextField;
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


  public BrowseResourcesController() {
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    cmbResourceType.setItems(resourceType);

  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }


}
