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
 * Controls the user dashboard screen. This is the main screen which a user can access their account
 * details on. A librarian can see their own personal information and navigate to aspects of the
 * system they have permission for, likewise for a customer.
 *
 * @author Petr Hoffmann
 * @version 1.0
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
   * Sets nodes visible to only one type of users visible to the correct type of users only.
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

  /**
   * Loads the GUI with information for all users.
   */
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

  /**
   * Loads the GUI with information for a librarian.
   */
  private void setGuiForLibrarians() {
    staffNumberTextField.setText(
        String.format(
            "%d",
            ((Librarian) loggedInUser).getStaffNumber()
        )
    );
  }

  /**
   * Load the GUI with information for a customer.
   */
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

  /**
   * Takes the user to the browse resource screen.
   */
  public void browseResources() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  /**
   * Takes the user to the account management screen.
   */
  public void manageAccount() {
    SceneHelper.setUpScene(this, "UserInformation");
  }

  /**
   * Takes the user to the transaction and fine information screen.
   */
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

  /**
   * Takes the user to the overdue copies screen.
   */
  public void overdueCopies() {
    SceneHelper.setUpScene(this, "OverdueCopies");
  }

  /**
   * Takes the user to the list of users screen.
   */
  public void manageUsers() {
    SceneHelper.setUpScene(this, "UserList");
  }

  /**
   * Takes the user to the profile images selection screen.
   */
  public void changeProfileImage() {
    SceneHelper.setUpScene(this, "ProfileImagePopUpMenu");
  }
}
