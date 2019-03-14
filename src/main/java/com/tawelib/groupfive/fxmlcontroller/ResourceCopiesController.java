package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.CopyStatus;
import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.exception.CopyAvailableException;
import com.tawelib.groupfive.exception.OverResourceCapException;
import com.tawelib.groupfive.manager.CopyManager;
import com.tawelib.groupfive.manager.RequestManager;
import com.tawelib.groupfive.tablewrapper.CopiesTableWrapper;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The type Resource copies controller. This lets a user see the all the information of a specific
 * copy of a resource. They can see the total number of copies and the number of available copies
 * for the resource. From here a user can declare a copy as lost, request to check it out, create
 * another copy of the chosen resource, or view the history of a selected copy.
 *
 * @author Petr Hoffman
 * @version 1.0
 */
public class ResourceCopiesController extends BaseFxmlController {

  /**
   * The Selected resource.
   */
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

    //declares text and visibilities depending on the above conditions
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
    try {
      RequestManager.createRequest(
          library,
          (Customer) loggedInUser,
          selectedResource
      );
    } catch (OverResourceCapException e) {
      AlertHelper.alert(Alert.AlertType.ERROR, "You have exceeded the resource cap. "
          + "An item must be returned before another can be borrowed.");
    } catch (CopyAvailableException e) {
      AlertHelper.alert(AlertType.ERROR, "Copy Available to Borrow! Request not made.");
    } finally {
      AlertHelper.alert(AlertType.INFORMATION, "Resource requested.");
    }
    back();
  }

  /**
   * Creates a copy of currently selected resource.
   */
  public void createCopy() {
    CopyManager.createResourceCopy(library, selectedResource);
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

  /**
   * Returns to the browse resources screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  /**
   * Gets selected resource.
   *
   * @return the selected resource
   */
  public Resource getSelectedResource() {
    return selectedResource;
  }

  /**
   * Sets selected resource.
   *
   * @param selectedResource the selected resource
   */
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
