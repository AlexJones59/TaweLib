package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controls the Account Creation screen.
 *
 * @author Dearbhla Jackson
 * @version 0.1
 */
public class AccountCreationController extends BaseFxmlController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button btnBack;

  @FXML
  private Button btnCreate;

  @FXML
  private CheckBox cbxLibrarian;

  @FXML
  private Label lblAccountType;

  @FXML
  private Label lblCity;

  @FXML
  private Label lblEmployDate;

  @FXML
  private Label lblFirstName;

  @FXML
  private Label lblHouseNo;

  @FXML
  private Label lblPhoneNo;

  @FXML
  private Label lblPostcode;

  @FXML
  private Label lblScreenTitle;

  @FXML
  private Label lblStaffNo;

  @FXML
  private Label lblStreet;

  @FXML
  private Label lblSurname;

  @FXML
  private TextField txtCity;

  @FXML
  private TextField txtEmployDate;

  @FXML
  private TextField txtFirstName;

  @FXML
  private TextField txtHouseNo;

  @FXML
  private TextField txtPhoneNo;

  @FXML
  private TextField txtPostcode;

  @FXML
  private TextField txtStaffNo;

  @FXML
  private TextField txtStreet;

  @FXML
  private TextField txtSurname;

  /**
   * Instantiates a new Account creation controller.
   */
  public AccountCreationController() {
  }

  /**
   * Changes visibilities of certain things depending on if you are
   * creating a librarian.
   */
  public void toggleCreate() {
    if (cbxLibrarian.isSelected()) {
      txtStaffNo.setVisible(true);
      txtStaffNo.setDisable(false);
      lblStaffNo.setVisible(true);
      txtEmployDate.setVisible(true);
      txtEmployDate.setDisable(false);
      lblEmployDate.setVisible(true);

    }
  }


  /**
   * Goes back to the user dashboard screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}

