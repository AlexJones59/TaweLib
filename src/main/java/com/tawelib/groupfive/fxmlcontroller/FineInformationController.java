package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FineInformationController extends BaseFxmlController {

  @FXML
  private Button btnBack;

  @FXML
  private TextField txtFineAmount;

  @FXML
  private TextField txtField2;

  @FXML
  private TextField txtField3;

  @FXML
  private TextField txtField4;

  @FXML
  private TextField txtField5;

  @FXML
  private TextField txtDateLeased;

  @FXML
  private TextField txtResourceName;

  @FXML
  private TextField txtField1;

  @FXML
  private Label lblField5;

  @FXML
  private Label lblField4;

  @FXML
  private Label lblField1;

  @FXML
  private Label lblField3;

  @FXML
  private Label lblField2;

  @FXML
  private TextField txtTimeOverdue;

  @FXML
  private TextField txtResourceId;

  @FXML
  private TextField txtResourceType;

  public FineInformationController() {
  }

  public void back() {
    SceneHelper.setUpScene(this, "TransactionsAndFines");
  }

}
