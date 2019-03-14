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
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
  private ComboBox<ResourceType> cmbResourceType;

  private ResourceType[] resourceTypes = {
      null,
      ResourceType.BOOK,
      ResourceType.DVD,
      ResourceType.LAPTOP,
      ResourceType.GAME
  };

  //TABLE----------------------------------------------------------
  @FXML
  private TableView<ResourceTableWrapper> tblNewAdditionsResourcesTable;

  @FXML
  private TableColumn<ResourceTableWrapper, String> idColumn;

  @FXML
  private TableColumn<ResourceTableWrapper, String> titleColumn;

  @FXML
  private TableColumn<ResourceTableWrapper, Integer> yearColumn;

  @FXML
  private TableColumn<ResourceTableWrapper, ResourceType> typeColumn;

  /**
   * Initializes the new addition table gui.
   */
  @FXML
  public void initialize() {
    idColumn.setCellValueFactory(
            new PropertyValueFactory<ResourceTableWrapper, String>("id"));

    titleColumn.setCellValueFactory(
            new PropertyValueFactory<ResourceTableWrapper, String>("title"));

    yearColumn.setCellValueFactory(
            new PropertyValueFactory<ResourceTableWrapper, Integer>("year"));

    typeColumn.setCellValueFactory(
            new PropertyValueFactory<ResourceTableWrapper, ResourceType>("type"));
  }

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
    EventsController c = (EventsController)SceneHelper.setUpScene(this, "Events");
    c.setLibrary(library);
  }

  /**
   * Searches for newly added resources by type and displays the result in the table.
   */
  public void search() {
    List<Resource> result;

    if (cmbResourceType.getValue() == ResourceType.BOOK) {
      result = new ArrayList<>(
              library.getResourceRepository().searchBook(
                      txtSearch.getText(),
                      lastLogin
              )
      );
    } else if (cmbResourceType.getValue() == ResourceType.DVD) {
      result = new ArrayList<>(
              library.getResourceRepository().searchDvd(
                      txtSearch.getText(),
                      lastLogin
              )
      );
    } else if (cmbResourceType.getValue() == ResourceType.LAPTOP) {
      result = new ArrayList<>(
              library.getResourceRepository().searchLaptop(
                      txtSearch.getText(),
                      lastLogin
              )
      );
    } else if (cmbResourceType.getValue() == ResourceType.GAME) {
      result = new ArrayList<>(
              library.getResourceRepository().searchGame(
                      txtSearch.getText(),
                      lastLogin
              )
      );
    } else {
      result = library.getResourceRepository().searchResource(
              txtSearch.getText(),
              lastLogin
      );
    }

    setTableContents(
            result
    );
  }

  /**
   * Populates the table.
   *
   * @param resources the resources in the library
   */
  private void setTableContents(List<Resource> resources) {
    tblNewAdditionsResourcesTable.getItems().clear();

    for (Resource resource : resources) {
      tblNewAdditionsResourcesTable.getItems().add(
              new ResourceTableWrapper(resource)
      );
    }
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

    Image profileImage = ResourceHelper.getUserProfileImage(loggedInUser);
    if (profileImage != null) {
      profileImageImageView.setImage(profileImage);
    }

    cmbResourceType.getItems().addAll(
            Arrays.asList(resourceTypes)
    );

    setTableContents(
            library.getResourceRepository().getNewAddtions(lastLogin)
    );
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
}
