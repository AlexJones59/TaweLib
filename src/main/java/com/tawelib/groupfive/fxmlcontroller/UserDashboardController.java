package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.tablewrapper.ResourceTableWrapper;
import com.tawelib.groupfive.util.ResourceHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controls the user dashboard screen. This is the main screen which a user can access their account
 * details on. A librarian can see their own personal information and navigate to aspects of the
 * system they have permission for, likewise for a customer.
 *
 * @author Petr Hoffmann
 * @version 1.1
 */
public class UserDashboardController extends BaseFxmlController {

  private static final int LISTVIEW_ONE_ELEM_HEIGHT = 20;

  @FXML
  private Button logOutButton;

  @FXML
  private TextField usernameTextField;

  @FXML
  private TextField fullNameTextField;

  @FXML
  private TextField addressTextField;

  @FXML
  private TextField phoneNumberTextField;

  @FXML
  private Label staffNumberLabel;

  @FXML
  private TextField staffNumberTextField;

  @FXML
  private Button browseResourceButton;

  @FXML
  private Button browseNewAdditionsButton;

  @FXML
  private Button manageAccountButton;

  @FXML
  private Button transactionsAndFinesButton;

  @FXML
  private Button createNewAccountButton;

  @FXML
  private Button overdueCopiesButton;

  @FXML
  private Button manageUsersButton;

  @FXML
  private Button eventsButton;

  @FXML
  private TextField accountBalanceTextField;

  @FXML
  private Label accountBalanceLabel;

  @FXML
  private ImageView profileImageImageView;

  @FXML
  private TextField txtSearch;

  @FXML
  private ListView<Button> newAdditionsList;

  public UserDashboardController() {
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
   * Takes the user to the statistics screen.
   */
  public void statistics() {
    SceneHelper.setUpScene(this, "Statistics");
  }

  /**
   * Takes the user to the profile images selection screen.
   */
  public void changeProfileImage() {
    SceneHelper.setUpScene(this, "ProfileImagePopUpMenu");
  }

  /**
   * Takes the user to the events screen.
   */
  public void eventsWindow() {
    EventsController c = (EventsController) SceneHelper.setUpScene(this, "Events");
    c.setLibrary(library);
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
        manageUsersButton,
    };

    customerNodes = new Node[]{
        transactionsAndFinesButton,
        manageAccountButton,
        accountBalanceLabel,
        accountBalanceTextField,
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
    populateListNewAdditions();

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
    accountBalanceLabel.setManaged(false);
    accountBalanceTextField.setManaged(false);
    manageAccountButton.setManaged(false);
    transactionsAndFinesButton.setManaged(false);
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
    staffNumberLabel.setManaged(false);
    staffNumberTextField.setManaged(false);
    createNewAccountButton.setManaged(false);
    manageUsersButton.setManaged(false);
    overdueCopiesButton.setManaged(false);
  }

  /**
   * The method populates the listview with the resources using buttons. If the button is pressed,
   * the scene with resource information is opened. The buttons are sorted as last added will be on
   * the top.
   */
  private void populateListNewAdditions() {

    List<Resource> newResources =
        library.getResourceRepository().searchResource("", lastLogin);
    newResources.sort(Comparator.comparing(Resource::getDateAdded).reversed());

    for (Resource resource : newResources) {
      Button oneResource = new Button(resource.getTitle());
      oneResource.setPrefSize(newAdditionsList.getWidth() - 16, LISTVIEW_ONE_ELEM_HEIGHT);

      oneResource.setOnAction(event -> {//Shows the info about the resource
        ResourceCrudController newController =
            (ResourceCrudController) SceneHelper.setUpScene(this, "ResourceCrud");
        newController.setSelectedResource(resource);
        newController.setCrudAction(CrudAction.UPDATE);
        newController.refresh();
      });

      newAdditionsList.getItems().add(oneResource);
    }

  }
}
