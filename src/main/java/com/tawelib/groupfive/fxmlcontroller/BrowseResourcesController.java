package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
  private MenuButton mbtnResourceType;


  public BrowseResourcesController() {
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    MenuItem menuItemBook  = new MenuItem("Book");
    MenuItem menuItemDvd  = new MenuItem("DVD");
    MenuItem menuItemLaptop  = new MenuItem("Laptop");

    mbtnResourceType.getItems().addAll(menuItemBook, menuItemDvd,
        menuItemLaptop);
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }


}
