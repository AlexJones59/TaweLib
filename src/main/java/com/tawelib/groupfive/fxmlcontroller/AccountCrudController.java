package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.controller.UserController;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
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
public class AccountCrudController extends BaseFxmlController {

  private CrudAction crudAction;
  private static final String VALID_DATA_ERROR =
      "This is not valid data for " + "this field.";

  @FXML
  public ResourceBundle resources;

  @FXML
  public URL location;

  @FXML
  public Button btnBack;

  @FXML
  public Button btnCreate;

  @FXML
  public Button btnUpdate;

  @FXML
  public CheckBox cbxLibrarian;

  @FXML
  public Label lblAccountType;

  @FXML
  public Label lblCity;

  @FXML
  public Label lblEmployDate;

  @FXML
  public Label lblFirstName;

  @FXML
  public Label lblHouseNo;

  @FXML
  public Label lblPhoneNo;

  @FXML
  public Label lblPostcode;

  @FXML
  public Label lblScreenTitle;

  @FXML
  public Label lblStreet;

  @FXML
  public Label lblLastName;

  @FXML
  public Label lblFirstNameCheck;

  @FXML
  public Label lblLastNameCheck;

  @FXML
  public Label lblPhoneNoCheck;

  @FXML
  public Label lblHouseNoCheck;

  @FXML
  public Label lblStreetCheck;

  @FXML
  public Label lblCityCheck;

  @FXML
  public Label lblPostcodeCheck;

  @FXML
  public Label lblEmploymentDateCheck;

  @FXML
  public TextField txtCity;

  @FXML
  public DatePicker dateEmploymentDate;

  @FXML
  public TextField txtFirstName;

  @FXML
  public TextField txtHouseNo;

  @FXML
  public TextField txtPhoneNo;

  @FXML
  public TextField txtPostcode;

  @FXML
  public TextField txtStreet;

  @FXML
  public TextField txtLastName;


  /**
   * Instantiates a new Account creation controller.
   */
  public AccountCrudController() {
  }

  @Override
  public void refresh() {
    setFieldVisibilities();

    if (crudAction == CrudAction.UPDATE) {
      populateFields();
    }
  }

  private void setFieldVisibilities() {
    if (crudAction == CrudAction.CREATE) {
      btnCreate.setVisible(true);
      btnUpdate.setVisible(false);
    } else if (crudAction == CrudAction.UPDATE) {
      btnCreate.setVisible(false);
      btnUpdate.setVisible(true);
      cbxLibrarian.setDisable(true);
    }
  }

  private void populateFields() {
    cbxLibrarian.setSelected(selectedUser.getClass().equals(Librarian.class));
    txtFirstName.setText(selectedUser.getFirstName());
    txtLastName.setText(selectedUser.getLastName());
    txtPhoneNo.setText(selectedUser.getPhoneNumber());
    txtHouseNo.setText(selectedUser.getAddress().getHouseNumber());
    txtStreet.setText(selectedUser.getAddress().getStreet());
    txtCity.setText(selectedUser.getAddress().getCity());
    txtPostcode.setText(selectedUser.getAddress().getPostCode());
  }

  /**
   * Changes visibilities of certain things depending on if you are creating a
   * librarian.
   */
  public void toggleCreate() {
    if (cbxLibrarian.isSelected()) {
      dateEmploymentDate.setVisible(true);
      dateEmploymentDate.setOpacity(1);
      dateEmploymentDate.setDisable(false);
      lblEmployDate.setVisible(true);
    } else {
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
        lblFirstNameCheck.setText(VALID_DATA_ERROR);
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
        lblLastNameCheck.setText(VALID_DATA_ERROR);
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
        lblPhoneNoCheck.setText(VALID_DATA_ERROR);
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
        lblHouseNoCheck.setText(VALID_DATA_ERROR);
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
        lblStreetCheck.setText(VALID_DATA_ERROR);
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
        lblCityCheck.setText(VALID_DATA_ERROR);
      } else {
        lblCityCheck.setText("City is valid.");
      }
    }
  }


  /**
   * Postcode check.
   * //TODO: Check why alerts do not just are making it go to dashboard.
   */
  public void postcodeCheck() {
    if (txtPostcode.getText().length() != 0) {
      if (!txtPostcode.getText()
          .matches("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$")) {
        lblPostcodeCheck.setText(VALID_DATA_ERROR);
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
      lblEmploymentDateCheck.setText(VALID_DATA_ERROR);
    } else {
      lblEmploymentDateCheck.setText("Employment Date is valid");
    }

  }

  /**
   * Creates an appropriate account.
   */
  public void createAccount() {
    if (txtFirstName.getText().equals("")
        || txtLastName.getText().equals("")
        || txtPhoneNo.getText().equals("")
        || txtHouseNo.getText().equals("")
        || txtStreet.getText().equals("")
        || txtCity.getText().equals("")
        || txtPostcode.getText().equals("")) {
      AlertHelper.alert(AlertType.ERROR, "You have unfilled Text fields. \n"
          + "Please fill them in before trying to create user.");
    } else if (lblFirstNameCheck.getText().equals(VALID_DATA_ERROR)
        || lblLastNameCheck.getText().equals(VALID_DATA_ERROR)
        || lblPhoneNoCheck.getText().equals(VALID_DATA_ERROR)
        || lblHouseNoCheck.getText().equals(VALID_DATA_ERROR)
        || lblStreetCheck.getText().equals(VALID_DATA_ERROR)
        || lblCityCheck.getText().equals(VALID_DATA_ERROR)
        || lblPostcodeCheck.getText().equals(VALID_DATA_ERROR)) {
      AlertHelper.alert(AlertType.ERROR, "Input Data is not valid. \n"
          + "Please check your input and rectify to pass check.");
    } else if (cbxLibrarian.isSelected()) {
      if (dateEmploymentDate.getValue() == null) {
        AlertHelper.alert(AlertType.ERROR, "You have unfilled Text fields. \n"
            + "Please fill them in before trying to create user.");
      } else if (lblEmploymentDateCheck.getText().equals(VALID_DATA_ERROR)) {
        AlertHelper.alert(AlertType.ERROR, "Input Data is not valid. \n"
            + "Please check your input and rectify to pass check.");
      } else {
        UserController.createLibrarianAccount(library, txtFirstName.getText(),
            txtLastName.getText(), Date.from(
                dateEmploymentDate.getValue().atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant()),
            txtPhoneNo.getText(), txtHouseNo.getText(), txtStreet.getText(),
            txtCity.getText(), txtPostcode.getText());
        AlertHelper.alert(AlertType.CONFIRMATION, "User account created.");
        back();
      }
    } else {
      UserController.createCustomerAccount(library, txtFirstName.getText(),
          txtLastName.getText(), txtPhoneNo.getText(), txtHouseNo.getText(),
          txtStreet.getText(), txtCity.getText(), txtPostcode.getText());
      AlertHelper.alert(AlertType.CONFIRMATION, "User account created.");
      back();
    }


  }


  /**
   * Creates an appropriate account.
   */
  public void updateAccount() {
    UserController
        .updateUserAccount(library, selectedUser, txtFirstName.getText(),
            txtLastName.getText(), txtPhoneNo.getText(), txtHouseNo.getText(),
            txtStreet.getText(), txtCity.getText(), txtPostcode.getText());

    AlertHelper.alert(AlertType.INFORMATION, "User account updated.");

    back();
  }

  /**
   * Goes back to the user dashboard screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }

  public CrudAction getCrudAction() {
    return crudAction;
  }

  public void setCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }
}
