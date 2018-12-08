package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.tablewrapper.CopiesTableWrapper;
import com.tawelib.groupfive.tablewrapper.LeaseTableWrapper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//TODO get author for this class

/**
 * The type Copy history controller.
 *
 * @author unknown
 * @version 1.0
 */
public class CopyHistoryController extends BaseFxmlController {

  /**
   * The Selected copy.
   */
  Copy selectedCopy;

  @FXML
  private TableView<LeaseTableWrapper> copyHistoryTableView;

  @FXML
  private TableColumn<LeaseTableWrapper, String> usernameTableColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, LocalDateTime> dateBorrowedTableColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, LocalDateTime> dateReturnedTableColumn;

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    usernameTableColumn.setCellValueFactory(
        new PropertyValueFactory<LeaseTableWrapper, String>("username"));

    dateBorrowedTableColumn.setCellValueFactory(
        new PropertyValueFactory<LeaseTableWrapper, LocalDateTime>("leaseDate"));

    dateReturnedTableColumn.setCellValueFactory(
        new PropertyValueFactory<LeaseTableWrapper, LocalDateTime>("returnDate"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    if (!copyHistoryTableView.getItems().isEmpty()) {
      copyHistoryTableView.getItems().clear();
    }

    for (Lease lease : library.getLeaseRepository().getCopyLeaseHistory(selectedCopy)) {
      copyHistoryTableView.getItems().add(
          new LeaseTableWrapper(lease)
      );
    }
  }

  /**
   * Returns to the browse resource screen.
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "BrowseResources");
  }

  /**
   * Gets selected copy.
   *
   * @return the selected copy
   */
  public Copy getSelectedCopy() {
    return selectedCopy;
  }

  /**
   * Sets selected copy.
   *
   * @param selectedCopy the selected copy
   */
  public void setSelectedCopy(Copy selectedCopy) {
    this.selectedCopy = selectedCopy;
  }
}
