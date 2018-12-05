package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TransactionsAndFinesController extends BaseFxmlController {

  @FXML
  private Button btnBack;

  @FXML
  private Button btnInfo;

  @FXML
  private Label lblScreenTitle;

  @FXML
  private Label lblUserId;

  @FXML
  private TextField txtUserId;

  @FXML
  private Label lblBalance;

  @FXML
  private TextField txtBalance;

  @FXML
  private TableView<?> tblTransactionsFines;

  @FXML
  private TableColumn<?, ?> timeIssuedColumn;

  @FXML
  private TableColumn<?, ?> resourceIdColumn;

  @FXML
  private TableColumn<?, ?> resourceNameColumn;

  @FXML
  private TableColumn<?, ?> resourceTypeColumn;

  @FXML
  private TableColumn<?, ?> daysOverdueColumn;

  @FXML
  private TableColumn<?, ?> amountColumn;


  public TransactionsAndFinesController() {
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
