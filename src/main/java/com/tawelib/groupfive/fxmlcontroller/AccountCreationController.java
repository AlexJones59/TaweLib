package com.tawelib.groupfive.fxmlcontroller;

import java.net.URL;
import java.util.ResourceBundle;


import com.tawelib.groupfive.util.SceneHelper;
//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
  private CheckBox cbxBibrarian;

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

  public AccountCreationController() {
  }


  /*public void checkedLibrarian(ActionEvent event) {
    lblEmployDate.setDisable(false);
    txtEmployDate.setDisable(false);
    lblStaffNo.setDisable(false);
    txtStaffNo.setDisable(false);
  }*/


  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
  /*
  @Override
  protected void refresh() {
    setGuiForUsers();

    if (isLibrarianLoggedIn()) {
      setGuiForLibrarians();
    } else {
      setGuiForCustomers();
    }
  }

  private void setGuiForUsers() {
    usernameTextField.setText(loggedInUser.getUsername());
    fullNameTextField.setText(loggedInUser.getFullName());
    //TODO: Format Address nicely.
    addressTextField.setText(loggedInUser.getAddress().toString());
  }

  private void setGuiForLibrarians() {
    staffNumberTextField.setText(
        String.format(
            "%d",
            ((Librarian) loggedInUser).getStaffNumber()
        )
    );
  }*/
}

