package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Lease;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.tablewrapper.LeaseTableWrapper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//TODO get author of this class

/**
 * The type Overdue copies controller.
 *
 * @author
 * @version 1.0
 */
public class OverdueCopiesController extends BaseFxmlController {

  @FXML
  private TextField txtSearchQuery;

  @FXML
  private TableView<LeaseTableWrapper> tblOverdueCopies;

  @FXML
  private TableColumn<LeaseTableWrapper, String> usernameColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, String> titleColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, String> copyIdColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, ResourceType> typeColumn;

  @FXML
  private TableColumn<LeaseTableWrapper, Date> dateColumn;


  /**
   * Instantiates a new Overdue copies controller.
   */
  public OverdueCopiesController() {
  }

  /**
   * Initializes the gui.
   */
  public void initialize() {
    usernameColumn.setCellValueFactory(
        new PropertyValueFactory<>("username"));

    titleColumn.setCellValueFactory(
        new PropertyValueFactory<>("title"));

    copyIdColumn.setCellValueFactory(
        new PropertyValueFactory<>("copyId"));

    typeColumn.setCellValueFactory(
        new PropertyValueFactory<>("type"));

    dateColumn.setCellValueFactory(
        new PropertyValueFactory<>("dueDate"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    if (!tblOverdueCopies.getItems().isEmpty()) {
      tblOverdueCopies.getItems().clear();
    }

    for (Lease overdueLease
        : library.getLeaseRepository()
        .searchOverdueLeases(txtSearchQuery.getText())
    ) {
      tblOverdueCopies.getItems().add(
          new LeaseTableWrapper(overdueLease)
      );
    }
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }

  /**
   * Search.
   */
  public void search() {
    refresh();
  }
}
