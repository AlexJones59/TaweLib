package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewTransactionController extends BaseFxmlController {


  @FXML
  private Button btnCancel;

  @FXML
  private TextField txtCustomerUsername;

  @FXML
  private TextField txtAmount;

  @FXML
  private Button btnTopUp;

  public NewTransactionController() {
  }

  public void back() {
    SceneHelper.setUpScene(this, "TransactionsAndFines");
  }
}
