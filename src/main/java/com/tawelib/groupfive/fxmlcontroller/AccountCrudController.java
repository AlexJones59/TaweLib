package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.manager.UserManager;
import com.tawelib.groupfive.runtime.SimulatedLocalDateTime;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
 * Account Crud controller controls the Account Crud Screen. This is used to to create and update
 * user account info, while having checks for all the fields, to make sure all input are valid, in
 * terms of style needed for the attribute.
 *
 * @author Shree Desai, Petr Hoffmann
 * @version 1.0
 */
public class AccountCrudController extends BaseFxmlController {

  /**
   * Constant that holds standard message if invalid dta is entered.
   */
  private static final String VALID_DATA_ERROR =
      "This is not valid data for " + "this field.";

  private CrudAction crudAction;

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button btnBack;

  @FXML
  private Button btnCreate;

  @FXML
  private Button btnUpdate;

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
  private TextField txtStreet;

  @FXML
  private TextField txtLastName;


  /**
   * Instantiates a new Account creation controller.
   */
  public AccountCrudController() {
  }

  /**
   * This method resets the scene, and populates the text fields, if a user has been selected and
   * screen is being use dto update.
   */
  @Override
  public void refresh() {
    setFieldVisibilities();

    if (crudAction == CrudAction.UPDATE) {
      populateFields();
    }
  }

  /**
   * Changes visibilities of certain things depending on if you are creating a librarian.
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
   * First name check done while you are typing.
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
   * Last name check done while you are typing.
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
   * Phone number check done while you are typing.
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
   * House number check done while you are typing.
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
   * Street check done while you are typing.
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
   * City check done while you are typing.
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
   * Postcode check done while you are typing.
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
   * Employment Date check by making sure it was before today.
   */
  public void employmentDateCheck() {
    LocalDateTime currentDate = SimulatedLocalDateTime.now();
    LocalDate pickedDate = dateEmploymentDate.getValue();
    LocalDateTime picked = LocalDateTime.of(pickedDate, LocalTime.MIN);

    if (picked.isAfter(currentDate)) {
      lblEmploymentDateCheck.setText(VALID_DATA_ERROR);
    } else {
      lblEmploymentDateCheck.setText("Employment Date is valid");
    }

  }

  /**
   * Creates an appropriate account.
   */
  public void createAccount() {
    //Checks if text fields are empty, or displays alert.
    if (txtFirstName.getText().equals("") || txtLastName.getText().equals("")
        || txtPhoneNo.getText().equals("") || txtHouseNo.getText().equals("")
        || txtStreet.getText().equals("") || txtCity.getText().equals("")
        || txtPostcode.getText().equals("")) {
      AlertHelper.alert(AlertType.ERROR, "You have unfilled Text fields. \n"
          + "Please fill them in before trying to create user.");
    } else if (lblFirstNameCheck.getText().equals(VALID_DATA_ERROR)
        || lblLastNameCheck.getText().equals(VALID_DATA_ERROR)
        || lblPhoneNoCheck.getText().equals(VALID_DATA_ERROR) || lblHouseNoCheck
        .getText().equals(VALID_DATA_ERROR) || lblStreetCheck.getText()
        .equals(VALID_DATA_ERROR) || lblCityCheck.getText()
        .equals(VALID_DATA_ERROR) || lblPostcodeCheck.getText()
        .equals(VALID_DATA_ERROR)) {
      AlertHelper.alert(AlertType.ERROR, "Input Data is not valid. \n"
          + "Please check your input and rectify to pass check.");
      // Checks if Librarian account is being created.
    } else if (cbxLibrarian.isSelected()) {
      //Checks if employmentDate is selected, else displays alert.
      if (dateEmploymentDate.getValue() == null) {
        AlertHelper.alert(AlertType.ERROR, "You have unfilled Text fields. \n"
            + "Please fill them in before trying to create user.");
        //Checks if employmentDate is valid.
      } else if (lblEmploymentDateCheck.getText().equals(VALID_DATA_ERROR)) {
        AlertHelper.alert(AlertType.ERROR, "Input Data is not valid. \n"
            + "Please check your input and rectify to pass check.");
      } else {
        //Creates Librarian account.
        UserManager.createLibrarianAccount(library, txtFirstName.getText(),
            txtLastName.getText(),
            LocalDateTime.of(dateEmploymentDate.getValue(), LocalTime.MIN),
            txtPhoneNo.getText(), txtHouseNo.getText(), txtStreet.getText(),
            txtCity.getText(), txtPostcode.getText());
        AlertHelper.alert(AlertType.INFORMATION, "User account created.");
        back();
      }
    } else {
      //Creates Customer Account.
      UserManager.createCustomerAccount(library, txtFirstName.getText(),
          txtLastName.getText(), txtPhoneNo.getText(), txtHouseNo.getText(),
          txtStreet.getText(), txtCity.getText(), txtPostcode.getText());
      AlertHelper.alert(AlertType.INFORMATION, "User account created.");
      back();
    }
  }


  /**
   * Updates user account.
   */
  public void updateAccount() {
    UserManager.updateUserAccount(library, selectedUser, txtFirstName.getText(),
        txtLastName.getText(), txtPhoneNo.getText(), txtHouseNo.getText(),
        txtStreet.getText(), txtCity.getText(), txtPostcode.getText());

    AlertHelper.alert(AlertType.INFORMATION, "User account updated.");

    back();
  }

  /**
   * Goes back to the user dashboard screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, lastSceneName);
  }

  /**
   * Gets what the screen is being used.
   *
   * @return crudAction
   */
  public CrudAction getCrudAction() {
    return crudAction;
  }

  /**
   * Sets crud Action.
   *
   * @param crudAction Crud Action
   */
  public void setCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }

  /**
   * Sets all the visibilities of the buttons, based upon what the screen is being used for.
   */
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

  /**
   * Gets data related the user passed in and displays it in the text fields.
   */
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
}
