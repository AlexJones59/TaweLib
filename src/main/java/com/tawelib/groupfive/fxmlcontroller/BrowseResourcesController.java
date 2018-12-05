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

public class BrowseResourcesController extends BaseFxmlController {

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
  private ComboBox<ResourceType> cmbResourceType;

  private ResourceType[] resourceTypes = {
      null,
      ResourceType.BOOK,
      ResourceType.DVD,
      ResourceType.LAPTOP
  };

  //TABLE----------------------------------------------------------
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
    idColumn.setCellValueFactory(
        new PropertyValueFactory<ResourceTableWrapper, String>("id"));

    titleColumn.setCellValueFactory(
        new PropertyValueFactory<ResourceTableWrapper, String>("title"));

    yearColumn.setCellValueFactory(
        new PropertyValueFactory<ResourceTableWrapper, Integer>("year"));

    typeColumn.setCellValueFactory(
        new PropertyValueFactory<ResourceTableWrapper, ResourceType>("type"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    cmbResourceType.getItems().addAll(
        Arrays.asList(resourceTypes)
    );

    setTableContents(
        library.getResourceRepository().getAll()
    );
  }

  /**
   * Searches for resources and displays the result.
   */
  public void search() {
    List<Resource> result;

    if (cmbResourceType.getValue() == ResourceType.BOOK) {
      result = new ArrayList<>(
          library.getResourceRepository().searchBook(
              txtSearch.getText()
          )
      );
    } else if (cmbResourceType.getValue() == ResourceType.DVD) {
      result = new ArrayList<>(
          library.getResourceRepository().searchDvd(
              txtSearch.getText()
          )
      );
    } else if (cmbResourceType.getValue() == ResourceType.LAPTOP) {
      result = new ArrayList<>(
          library.getResourceRepository().searchLaptop(
              txtSearch.getText()
          )
      );
    } else {
      result = library.getResourceRepository().searchResource(
          txtSearch.getText()
      );
    }

    setTableContents(
        result
    );
  }

  private void setTableContents(List<Resource> resources) {
    tblBrowseResourcesTable.getItems().clear();

    for (Resource resource : resources) {
      tblBrowseResourcesTable.getItems().add(
          new ResourceTableWrapper(resource)
      );
    }
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }

  /**
   * Opens the resource CRUD screen if a resource is selected.
   */
  public void resourceInformation() {
    if (tblBrowseResourcesTable.getSelectionModel().getSelectedItem() != null) {
      ((ResourceCrudController) SceneHelper.setUpScene(
          this,
          "ResourceCrud")
      ).setSelectedResource(
          tblBrowseResourcesTable.getSelectionModel().getSelectedItem()
              .getResource()
      );
    }
  }
}
