package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.controller.UserController;
import com.tawelib.groupfive.util.SceneHelper;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controls the Account Creation screen.
 *
 * @author Dearbhla Jackson, Shree Desai
 * @version 0.4
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
  private Label lblLastName;

  @FXML
  private Label lblFirstNameCheck;

  @FXML
  private Label lblLastNameCheck;

  @FXML
  private Label lblPhoneNoCheck;

  @FXML
  private Label lblHouseNoCheck;

  @FXML
  private Label lblStreetCheck;

  @FXML
  private Label lblCityCheck;

  @FXML
  private Label lblPostcodeCheck;

  @FXML
  private Label lblEmploymentDateCheck;

  @FXML
  private TextField txtCity;

  @FXML
  private DatePicker dateEmploymentDate;

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
  private TextField txtLastName;


  /**
   * Instantiates a new Account creation controller.
   */
  public AccountCreationController() {
  }

  /**
   * Changes visibilities of certain things depending on if you are creating a
   * librarian.
   */
  public void toggleCreate() {
    if (cbxLibrarian.isSelected()) {
      txtStaffNo.setVisible(true);
      txtStaffNo.setDisable(false);
      lblStaffNo.setVisible(true);
      dateEmploymentDate.setVisible(true);
      dateEmploymentDate.setOpacity(1);
      dateEmploymentDate.setDisable(false);
      lblEmployDate.setVisible(true);
    } else {
      txtStaffNo.setVisible(false);
      txtStaffNo.setDisable(true);
      lblStaffNo.setVisible(false);
      dateEmploymentDate.setVisible(false);
      dateEmploymentDate.setOpacity(0);
      dateEmploymentDate.setDisable(true);
      lblEmployDate.setVisible(false);
    }
  }

  /**
   * First name check.
   */
  public void firstNameCheck() {
    if (txtFirstName.getText().length() != 0) {
      if (!txtFirstName.getText().matches("[A-Z][a-zA-Z]*")) {
        lblFirstNameCheck.setText("This is not valid data for this field.");
      } else {
        lblFirstNameCheck.setText("First name is valid.");
      }
    }
  }

  /**
   * Last name check.
   */
  public void lastNameCheck() {
    if (txtLastName.getText().length() != 0) {
      if (!txtLastName.getText().matches("[a-zA-z]+([ '-][a-zA-Z]+)*")) {
        lblLastNameCheck.setText("This is not valid data for this field.");
      } else {
        lblLastNameCheck.setText("Last Name is valid.");
      }
    }
  }

  /**
   * Phone no check.
   */
  public void phoneNoCheck() {
    if (txtPhoneNo.getText().length() != 0) {
      if (!txtPhoneNo.getText().matches("^\\+?(?:\\d\\s?){10,12}$")) {
        lblPhoneNoCheck.setText("This is not valid data for this field.");
      } else {
        lblPhoneNoCheck.setText("Phone No. is valid.");
      }
    }
  }

  /**
   * House no check.
   */
  public void houseNoCheck() {
    if (txtHouseNo.getText().length() != 0) {
      if (txtHouseNo.getText().length() >= 10) {
        lblHouseNoCheck.setText("This is not valid data for this field.");
      } else {
        lblHouseNoCheck.setText("House No. is valid.");
      }
    }
  }


  /**
   * Street check.
   */
  public void streetCheck() {
    if (txtStreet.getText().length() != 0) {
      if (!txtStreet.getText().matches("^[#.0-9a-zA-Z\\s,-]+$")) {
        lblStreetCheck.setText("This is not valid data for this field.");
      } else {
        lblStreetCheck.setText("Street is valid.");
      }
    }
  }

  /**
   * City check.
   */
  public void cityCheck() {
    if (txtCity.getText().length() != 0) {
      if (!txtCity.getText().matches("^[#.0-9a-zA-Z\\s,-]+$")) {
        lblCityCheck.setText("This is not valid data for this field.");
      } else {
        lblCityCheck.setText("City is valid.");
      }
    }
  }


  /**
   * Postcode check.
   */
  public void postcodeCheck() {
    if (txtPostcode.getText().length() != 0) {
      if (!txtPostcode.getText()
          .matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")) {
        lblPostcodeCheck.setText("This is not valid data for this field.");
      } else {
        lblPostcodeCheck.setText("Post code is valid.");
      }
    }
  }

  /**
   * Postcode check.
   */
  public void employmentDateCheck() {
    Date currentDate = new Date();
    LocalDate pickedDate = dateEmploymentDate.getValue();
    Date picked = Date.from(pickedDate.atStartOfDay()
        .atZone(ZoneId.systemDefault()).toInstant());
    if (picked.after(currentDate)) {
      lblEmploymentDateCheck.setText("This is not valid data for this field.");
    } else {
      lblEmploymentDateCheck.setText("Employment Date is valid");
    }

  }

  /**
   * Creates an appropriate account.
   */
  public void createAccount() {
    if (cbxLibrarian.isSelected()) {
      UserController.createLibrarianAccount(
          library,
          txtFirstName.getText(),
          txtLastName.getText(),
          Date.from(dateEmploymentDate.getValue().atStartOfDay()
              .atZone(ZoneId.systemDefault()).toInstant()),
          txtPhoneNo.getText(),
          txtHouseNo.getText(),
          txtStreet.getText(),
          txtCity.getText(),
          txtPostcode.getText()
      );
    } else {
      UserController.createCustomerAccount(
          library,
          txtFirstName.getText(),
          txtLastName.getText(),
          txtPhoneNo.getText(),
          txtHouseNo.getText(),
          txtStreet.getText(),
          txtCity.getText(),
          txtPostcode.getText()
      );
    }

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setHeaderText("Success");
    alert.setContentText("User account created.");
    alert.showAndWait();

    back();
  }

  /**
   * Goes back to the user dashboard screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}
