package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.tablewrapper.ResourceTableWrapper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * This controls the Browse Resource screen. This is used to search through the library's resources.
 * A user can search using different attributes of the resource using a text box, or search by the
 * type of a resource using a list provided.
 *
 * @author Petr Hoffman, Nayeem Mohammed
 * @version 1.0
 */
public class BrowseNewAdditionsController extends BaseFxmlController {

  @FXML
  private Button btnBack;

  @FXML
  private Text lblScreenTitle;

  @FXML
  private MenuButton mbtnSearchAttribute;

  @FXML
  private TextField txtSearch;

  @FXML
  private Label lblSearch;

  @FXML
  private Button btnInfo;

  @FXML
  private Button createNewButton;

  @FXML
  private ComboBox<ResourceType> cmbResourceType;

  private ResourceType[] resourceTypes =
      {null, ResourceType.BOOK, ResourceType.DVD, ResourceType.LAPTOP};

  // TABLE----------------------------------------------------------
  @FXML
  private TableView<ResourceTableWrapper> tblBrowseResourcesTable;

  @FXML
  private TableColumn<ResourceTableWrapper, String> idColumn;

  @FXML
  private TableColumn<ResourceTableWrapper, String> titleColumn;

  @FXML
  private TableColumn<ResourceTableWrapper, Integer> yearColumn;

  @FXML
  private TableColumn<ResourceTableWrapper, ResourceType> typeColumn;

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    idColumn.setCellValueFactory(new PropertyValueFactory<ResourceTableWrapper, String>("id"));

    titleColumn
        .setCellValueFactory(new PropertyValueFactory<ResourceTableWrapper, String>("title"));

    yearColumn.setCellValueFactory(new PropertyValueFactory<ResourceTableWrapper, Integer>("year"));

    typeColumn
        .setCellValueFactory(new PropertyValueFactory<ResourceTableWrapper, ResourceType>("type"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    cmbResourceType.getItems().addAll(Arrays.asList(resourceTypes));

    setTableContents(library.getResourceRepository().getNewAddtions(lastLogin));

    createNewButton.setVisible(isLibrarianLoggedIn());
  }

  /**
   * Searches for resources by type and displays the result in the table.
   */
  public void search() {
    List<Resource> result;



    if (cmbResourceType.getValue() == ResourceType.BOOK) {
      result = new ArrayList<>(
          library.getResourceRepository().searchBook(txtSearch.getText(), lastLogin));
    } else if (cmbResourceType.getValue() == ResourceType.DVD) {
      result = new ArrayList<>(
          library.getResourceRepository().searchDvd(txtSearch.getText(), lastLogin));
    } else if (cmbResourceType.getValue() == ResourceType.LAPTOP) {
      result = new ArrayList<>(
          library.getResourceRepository().searchLaptop(txtSearch.getText(), lastLogin));
    } else {
      result = library.getResourceRepository().searchResource(txtSearch.getText(), lastLogin);
    }

    setTableContents(result);
  }

  /**
   * Returns to the user dashboard screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }

  /**
   * Updates the resource CRUD screen if a resource is selected.
   */
  public void resourceInformation() {
    if (tblBrowseResourcesTable.getSelectionModel().getSelectedItem() != null) {
      setUpResourceCrud(CrudAction.UPDATE);
    }
  }

  /**
   * Create new resource.
   */
  public void createNew() {
    setUpResourceCrud(CrudAction.CREATE);
  }


  /**
   * Goes to resource crud screen for the selected resource.
   *
   * @param crudAction instance of crudAction
   */
  private void setUpResourceCrud(CrudAction crudAction) {
    ResourceCrudController newController =
        (ResourceCrudController) SceneHelper.setUpScene(this, "ResourceCrud");

    if (tblBrowseResourcesTable.getSelectionModel().getSelectedItem() != null) {
      newController.setSelectedResource(
          tblBrowseResourcesTable.getSelectionModel().getSelectedItem().getResource());
    }

    newController.setCrudAction(crudAction);
    newController.refresh();
  }

  /**
   * Populates the table.
   *
   * @param resources the resources in the library
   */
  private void setTableContents(List<Resource> resources) {
    tblBrowseResourcesTable.getItems().clear();

    for (Resource resource : resources) {
      tblBrowseResourcesTable.getItems().add(new ResourceTableWrapper(resource));
    }
  }
}
