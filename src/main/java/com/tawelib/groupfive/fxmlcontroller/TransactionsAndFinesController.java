package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Fine;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.tablewrapper.LeaseTableWrapper;
import com.tawelib.groupfive.tablewrapper.TransactionsFinesTableWrapper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionsAndFinesController extends BaseFxmlController {

  @FXML
  private Button btnBack;

  @FXML
  private Button btnInfo;

  @FXML
  private Label lblScreenTitle;

  @FXML
  private Label lblUsername;

  @FXML
  private TextField txtUsername;

  @FXML
  private Label lblBalance;

  @FXML
  private TextField txtBalance;

  @FXML
  private TableView<TransactionsFinesTableWrapper> tblTransactionsFines;

  @FXML
  private TableColumn<TransactionsFinesTableWrapper, String> timeIssuedTableColumn;

  @FXML
  private TableColumn<TransactionsFinesTableWrapper, String> resourceIdTableColumn;

  @FXML
  private TableColumn<TransactionsFinesTableWrapper, String> resourceNameTableColumn;

  @FXML
  private TableColumn<TransactionsFinesTableWrapper, String> resourceTypeTableColumn;

  @FXML
  private TableColumn<TransactionsFinesTableWrapper, String> daysOverdueTableColumn;

  @FXML
  private TableColumn<TransactionsFinesTableWrapper, String> amountTableColumn;


  public TransactionsAndFinesController() {
  }

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize() {
    timeIssuedTableColumn.setCellValueFactory(new PropertyValueFactory<>(
        "timeIssued"));
    resourceIdTableColumn.setCellValueFactory(new PropertyValueFactory<>(
        "resourceId"));
    resourceNameTableColumn.setCellValueFactory(new PropertyValueFactory<>(
        "resourceName"));
    resourceTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>(
        "resourceType"));
    daysOverdueTableColumn.setCellValueFactory(new PropertyValueFactory<>(
        "daysOverdue"));
    amountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    Customer customer = (Customer) loggedInUser;
    txtUsername.setText(customer.getUsername());
    txtBalance.setText(String.format( "£ %.2f",
            customer.getAccountBalanceInPounds()));

    setTableContents(
        library.getTransactionRepository().getTransactions(customer),
        library.getFineRepository().getCustomerFines(customer));


  }

  private void setTableContents(List<Transaction> transactions,
      List<Fine> fines) {
    tblTransactionsFines.getItems().clear();

    for (Transaction transaction : transactions) {
      tblTransactionsFines.getItems().add(new
          TransactionsFinesTableWrapper(transaction));
    }

    for (Fine fine: fines) {
      tblTransactionsFines.getItems().add(new
          TransactionsFinesTableWrapper(fine));
    }
  }



  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}
