package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.manager.RequestManager;
import com.tawelib.groupfive.manager.ResourceManager;
import com.tawelib.groupfive.manager.UserManager;
import com.tawelib.groupfive.tablewrapper.CopiesTableWrapper;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ResourceCopiesController extends BaseFxmlController {

  Resource selectedResource;

  @FXML
  private TableView<CopiesTableWrapper> copiesTableView;

  @FXML
  private TableColumn<CopiesTableWrapper, String> idTableColumn;

  @FXML
  private TableColumn<CopiesTableWrapper, String> titleTableColumn;

  @FXML
  private TableColumn<CopiesTableWrapper, CopyStatus> availabilityTableColumn;

  @FXML
  private TableColumn<CopiesTableWrapper, LocalDateTime> dueDateTableColumn;

  @FXML
  private Button requestCopyButton;

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    idTableColumn.setCellValueFactory(
        new PropertyValueFactory<CopiesTableWrapper, String>("id"));

    titleTableColumn.setCellValueFactory(
        new PropertyValueFactory<CopiesTableWrapper, String>("title"));

    availabilityTableColumn.setCellValueFactory(
        new PropertyValueFactory<CopiesTableWrapper, CopyStatus>(
            "status"));

    dueDateTableColumn.setCellValueFactory(
        new PropertyValueFactory<CopiesTableWrapper, LocalDateTime>("dueDate"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    for (Copy copy : library.getCopyRepository()
        .getResourceCopies(selectedResource)
    ) {
      copiesTableView.getItems().add(
          new CopiesTableWrapper(
              copy, library.getLeaseRepository()
          )
      );
    }

    requestCopyButton.setVisible(isCustomerLoggedIn());
  }

  /**
   * Requests a currently selected resource.
   */
  public void request() {
    RequestManager.createRequest(
        library,
        (Customer) loggedInUser,
        selectedResource
    );

    AlertHelper.alert(AlertType.INFORMATION, "Resource requested.");
    back();
  }

  public void back() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  public Resource getSelectedResource() {
    return selectedResource;
  }

  public void setSelectedResource(
      Resource selectedResource) {
    this.selectedResource = selectedResource;
  }
}
