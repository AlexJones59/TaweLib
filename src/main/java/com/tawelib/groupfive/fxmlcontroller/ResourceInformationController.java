package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ResourceInformationController extends BaseFxmlController{

  @FXML
  private Button btnBack;

  @FXML
  private TextField txtField2;

  @FXML
  private Label lblYear;

  @FXML
  private TextField txtField3;

  @FXML
  private Button btnCopyList;

  @FXML
  private TextField txtField4;

  @FXML
  private TextField txtField5;

  @FXML
  private TextField txtField1;

  @FXML
  private Label lblNoOfCopies;

  @FXML
  private Label lblField5;

  @FXML
  private Label lblField4;

  @FXML
  private Button btnModify;

  @FXML
  private Label lblField1;

  @FXML
  private Label lblId;

  @FXML
  private Label lblField3;

  @FXML
  private Label lblField2;

  @FXML
  private Label lblScreenTitle;

  @FXML
  private Label lblTitle;

  public void back() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }


}
