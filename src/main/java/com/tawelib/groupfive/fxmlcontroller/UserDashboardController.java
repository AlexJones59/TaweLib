package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.ResourceHelper;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controls the user dashboard screen.
 *
 * @author Petr Hoffmann
 * @version 0.1
 */
public class UserDashboardController extends BaseFxmlController {

  @FXML
  public Button logOutButton;

  @FXML
  public TextField usernameTextField;

  @FXML
  public TextField fullNameTextField;

  @FXML
  public TextField addressTextField;

  @FXML
  public TextField phoneNumberTextField;

  @FXML
  public Label staffNumberLabel;

  @FXML
  public TextField staffNumberTextField;

  @FXML
  public Button browseResourceButton;

  @FXML
  public Button manageAccountButton;

  @FXML
  public Button transactionsAndFinesButton;

  @FXML
  public Button createNewAccountButton;

  @FXML
  public Button overdueCopiesButton;

  @FXML
  public Button manageUsersButton;

  @FXML
  private TextField accountBalanceTextField;

  @FXML
  private Label accountBalanceLabel;

  @FXML
  public ImageView profileImageImageView;

  public UserDashboardController() {
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  protected void refresh() {
    setGuiForUsers();

    if (isLibrarianLoggedIn()) {
      setGuiForLibrarians();
    } else {
      setGuiForCustomers();
    }
  }

  /**
   * Sets nodes visible to only one type of users visible to the correct type of
   * users only.
   */
  @Override
  protected void configureVisibilities() {
    librarianNodes = new Node[]{
        staffNumberLabel,
        staffNumberTextField,
        overdueCopiesButton,
        createNewAccountButton,
        manageUsersButton
    };

    customerNodes = new Node[]{
        transactionsAndFinesButton,
        manageAccountButton,
        accountBalanceLabel,
        accountBalanceTextField
    };
  }

  private void setGuiForUsers() {
    usernameTextField.setText(loggedInUser.getUsername());
    fullNameTextField.setText(loggedInUser.getFullName());
    //TODO: Format Address nicely.
    addressTextField.setText(loggedInUser.getAddress().toString());
    phoneNumberTextField.setText(loggedInUser.getPhoneNumber());

    Image profileImage = ResourceHelper.getUserProfileImage(loggedInUser);
    if (profileImage != null) {
      profileImageImageView.setImage(profileImage);
    }
  }

  private void setGuiForLibrarians() {
    staffNumberTextField.setText(
        String.format(
            "%d",
            ((Librarian) loggedInUser).getStaffNumber()
        )
    );
  }

  private void setGuiForCustomers() {
    accountBalanceTextField.setText(
        String.format(
            "£ %.2f",
            ((Customer) loggedInUser).getAccountBalanceInPounds()
        )
    );
  }

  /**
   * Logs the user out.
   */
  public void logOut() {
    loggedInUser = null;
    SceneHelper.setUpScene(this, "Login");
  }

  public void browseResources() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  public void manageAccount() {
    SceneHelper.setUpScene(this, "UserInformation");
  }

  public void transactionsAndFines() {
    SceneHelper.setUpScene(this, "TransactionsAndFines");
  }

  /**
   * Creates a new account.
   */
  public void createNewAccount() {
    AccountCrudController controller = (AccountCrudController) SceneHelper
        .setUpScene(this, "AccountCrud");

    controller.setCrudAction(CrudAction.CREATE);
    controller.refresh();
  }

  public void overdueCopies() {
    SceneHelper.setUpScene(this, "OverdueCopies");
  }

  public void manageUsers() {
    SceneHelper.setUpScene(this, "UserList");
  }

  public void changeProfileImage() {
    SceneHelper.setUpScene(this, "ProfileImagePopUpMenu");
  }
}
