package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NewTransactionController extends BaseFxmlController {

  @FXML
  public Label usernameLabel;

  @FXML
  public TextField amountTextField;

  @Override
  public void refresh() {

  }

  public NewTransactionController() {
  }

  public void topUp() {

  }

  public void back() {
    SceneHelper.setUpScene(this, "UserInformation");
  }
}
