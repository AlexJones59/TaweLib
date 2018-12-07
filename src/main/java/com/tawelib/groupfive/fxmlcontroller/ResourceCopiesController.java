package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.manager.RequestManager;
import com.tawelib.groupfive.tablewrapper.CopiesTableWrapper;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

  @FXML
  private Button declareLostButton;

  @FXML
  private Button createCopyButton;

  @FXML
  private Button historyButton;

  @FXML
  private Label totalCopiesLabel;

  @FXML
  private Label availableCopiesLabel;

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
    int availableCopies = 0;
    int totalCopies = 0;

    if (!copiesTableView.getItems().isEmpty()) {
      copiesTableView.getItems().clear();
    }

    for (Copy copy : library.getCopyRepository()
        .getResourceCopies(selectedResource)
    ) {
      copiesTableView.getItems().add(
          new CopiesTableWrapper(
              copy, library.getLeaseRepository()
          )
      );
      totalCopies++;
      if (copy.getStatus() == CopyStatus.AVAILABLE) {
        availableCopies++;
      }
    }

    totalCopiesLabel.setText(Integer.toString(totalCopies));
    availableCopiesLabel.setText(Integer.toString(availableCopies));

    declareLostButton.setVisible(isLibrarianLoggedIn());
    historyButton.setVisible(isLibrarianLoggedIn());
    requestCopyButton.setVisible(isCustomerLoggedIn());
    createCopyButton.setVisible(isLibrarianLoggedIn());
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

  /**
   * Creates a copy of currently selected resource.
   */
  public void createCopy() {
    CopyManager.createResourceCopy(library, selectedResource, 1);
    AlertHelper.alert(AlertType.INFORMATION, "Copy created.");
    refresh();
  }

  /**
   * Shows the copy history screen.
   */
  public void history() {
    CopyHistoryController newController = (CopyHistoryController) SceneHelper
        .setUpScene(
            this,
            "CopyHistory"
        );

    Copy copy = copiesTableView.getSelectionModel().getSelectedItem().getCopy();

    newController.setSelectedCopy(copy);

    newController.refresh();
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

  /**
   * Declares a copy as lost.
   */
  public void declareLost() {
    CopiesTableWrapper copiesWrapper = copiesTableView.getSelectionModel()
        .getSelectedItem();

    if (copiesWrapper != null) {
      String copyId = copiesWrapper.getCopy().getId();
      AlertHelper.alert(AlertType.INFORMATION, "Declaring as lost: " + copyId);
      CopyManager.lostCopy(library, copyId);
    }
  }
}
