package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.tablewrapper.OverdueCopiesTableWrapper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OverdueCopiesController extends BaseFxmlController {

  @FXML
  private Button btnBack;

  @FXML
  private Label lblScreenTitle;

  @FXML
  private TextField txtSearchQuery;

  @FXML
  private Label lblSearch;

  @FXML
  private TableView<OverdueCopiesTableWrapper> tblOverdueCopies;

  @FXML
  private TableColumn<OverdueCopiesTableWrapper, String> usernameColumn;

  @FXML
  private TableColumn<OverdueCopiesTableWrapper, String> titleColumn;

  @FXML
  private TableColumn<OverdueCopiesTableWrapper, String> idColumn;

  @FXML
  private TableColumn<OverdueCopiesTableWrapper, ResourceType> typeColumn;

  @FXML
  private TableColumn<OverdueCopiesTableWrapper, Date> dateColumn;

  @FXML
  private TableColumn<OverdueCopiesTableWrapper, String> fineColumn;


  public OverdueCopiesController() {
  }



  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    //TODO: Populate the table, etc.
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}
