package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Fine;
import com.tawelib.groupfive.entity.Transaction;
import com.tawelib.groupfive.tablewrapper.LeaseTableWrapper;
import com.tawelib.groupfive.tablewrapper.TransactionsFinesTableWrapper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The type Transactions and fines controller. A user can view all their fines and information
 * about them like the amount and what resource it is for. They can also see their
 * balance on this screen.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class TransactionsAndFinesController extends BaseFxmlController {

  @FXML
  private Button btnBack;

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
  private TableColumn<TransactionsFinesTableWrapper, LocalDateTime> timeIssuedTableColumn;

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


  /**
   * Instantiates a new Transactions and fines controller.
   */
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
    txtBalance.setText(String.format("£ %.2f",
        customer.getAccountBalanceInPounds()));

    setTableContents(
        library.getTransactionRepository().getTransactions(customer),
        library.getFineRepository().getCustomerFines(customer));


  }

  /**
   * Populates the table.
   *
   * @param transactions list of transactions
   * @param fines list of fines
   */
  private void setTableContents(List<Transaction> transactions,
      List<Fine> fines) {
    tblTransactionsFines.getItems().clear();

    for (Transaction transaction : transactions) {
      tblTransactionsFines.getItems().add(new
          TransactionsFinesTableWrapper(transaction));
    }

    for (Fine fine : fines) {
      tblTransactionsFines.getItems().add(new
          TransactionsFinesTableWrapper(fine));
    }
  }


  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}
