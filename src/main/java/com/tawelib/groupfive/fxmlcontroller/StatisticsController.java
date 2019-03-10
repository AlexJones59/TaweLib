package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.manager.RatingManager;
import com.tawelib.groupfive.manager.StatisticsManager;
import com.tawelib.groupfive.tablewrapper.statisticstablewrappers.PopularBookAuthorTableWrapper;
import com.tawelib.groupfive.tablewrapper.statisticstablewrappers.PopularBookTableWrapper;
import com.tawelib.groupfive.tablewrapper.statisticstablewrappers.PopularDvdDirectorTableWrapper;
import com.tawelib.groupfive.tablewrapper.statisticstablewrappers.PopularDvdTableWrapper;
import com.tawelib.groupfive.tablewrapper.statisticstablewrappers.PopularLaptopTableWrapper;
import com.tawelib.groupfive.tablewrapper.statisticstablewrappers.PopularResourcesTableWrapper;
import com.tawelib.groupfive.tablewrapper.statisticstablewrappers.PopularVideoGameTableWrapper;
import com.tawelib.groupfive.util.SceneHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


/**
 * The Statistics controller: A user can view info about borrowing statistics for themselves and an
 * average user. They can also view the most popular resources over different time periods, with
 * specifics given for each resource type. A Librarian will also be able to statistics pertaining to
 * fines issued.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class StatisticsController extends BaseFxmlController {

  private ResourceType[] resourceTypes = {
      null,
      ResourceType.BOOK,
      ResourceType.DVD,
      ResourceType.LAPTOP,
      ResourceType.GAME

  };

  private ObservableList<String> timePeriods = FXCollections.observableArrayList(
      "Day", "Week", "Month");

  @FXML
  private Button backButton;

  @FXML
  private Accordion statsContainer;

  @FXML
  private TitledPane userStatPane;

  @FXML
  private ComboBox<String> userStatTimeComboBox;

  @FXML
  private ComboBox<ResourceType> userStatResTypeComboBox;

  @FXML
  private Label userLabel;

  @FXML
  private TextField noUserBorrowedTextField;

  @FXML
  private TextField noAverageBorrowedTextField;

  @FXML
  private BarChart<String, Number> userStatBarChart;

  private XYChart.Series<String, Number> specificUserStatSeries = new XYChart.Series<>();

  private XYChart.Series<String, Number> averageUserStatSeries = new XYChart.Series<>();

  @FXML
  private CategoryAxis userStatXAxis;

  @FXML
  private NumberAxis userStatYAxis;

  @FXML
  private TitledPane resourceStatPane;

  @FXML
  private ComboBox<ResourceType> resourceStatResTypeComboBox;

  @FXML
  private Pane popResourcePane;

  @FXML
  private TableView<PopularResourcesTableWrapper> popularResTableView;

  @FXML
  private TableColumn<PopularResourcesTableWrapper, Integer> popResRankColumn;

  @FXML
  private TableColumn<PopularResourcesTableWrapper, String> popResResourceIdColumn;

  @FXML
  private TableColumn<PopularResourcesTableWrapper, String> popResTitleColumn;

  @FXML
  private TableColumn<PopularResourcesTableWrapper, ResourceType> popResTypeColumn;

  @FXML
  private TableColumn<PopularResourcesTableWrapper, Integer> popResAvgRatingColumn;

  @FXML
  private ComboBox<String> resourceStatTimeComboBox;

  @FXML
  private Pane popBookPane;

  @FXML
  private ComboBox<String> bookStatTimeComboBox;

  @FXML
  private TableView<PopularBookTableWrapper> popularBookTableView;

  @FXML
  private TableColumn<PopularBookTableWrapper, Integer> popBookRankColumn;

  @FXML
  private TableColumn<PopularBookTableWrapper, String> popBookResourceIdColumn;

  @FXML
  private TableColumn<PopularBookTableWrapper, String> popBookTitleColumn;

  @FXML
  private TableColumn<PopularBookTableWrapper, String> popBookAuthorColumn;

  @FXML
  private TableColumn<PopularBookTableWrapper, String> popBookGenreColumn;

  @FXML
  private TableColumn<PopularBookTableWrapper, Integer> popBookAvgRatingColumn;

  @FXML
  private PieChart popBookGenrePieChart;

  @FXML
  private TableView<PopularBookAuthorTableWrapper> popBookAuthorTableView;

  @FXML
  private TableColumn<PopularBookAuthorTableWrapper, Integer> popAuthorRankColumn;

  @FXML
  private TableColumn<PopularBookAuthorTableWrapper, String> popAuthorNameColumn;

  @FXML
  private Pane popDvdPane;

  @FXML
  private ComboBox<String> dvdStatTimeComboBox;

  @FXML
  private TableView<PopularDvdTableWrapper> popularDvdTableView;

  @FXML
  private TableColumn<PopularDvdTableWrapper, Integer> popDvdRankColumn;

  @FXML
  private TableColumn<PopularDvdTableWrapper, String> popDvdResourceIdColumn;

  @FXML
  private TableColumn<PopularDvdTableWrapper, String> popDvdTitleColumn;

  @FXML
  private TableColumn<PopularDvdTableWrapper, String> popDvdDirectorColumn;

  @FXML
  private TableColumn<PopularDvdTableWrapper, Integer> popDvdAvgRatingColumn;

  @FXML
  private TableView<PopularDvdDirectorTableWrapper> popDvdDirectorTableView;

  @FXML
  private TableColumn<PopularDvdDirectorTableWrapper, Integer> popDirectorsRankColumn;

  @FXML
  private TableColumn<PopularDvdDirectorTableWrapper, String> popDirectorsNameColumn;

  @FXML
  private Pane popLaptopPane;

  @FXML
  private ComboBox<String> laptopStatTimeComboBox;

  @FXML
  private TableView<PopularLaptopTableWrapper> popularLaptopTableView;

  @FXML
  private TableColumn<PopularLaptopTableWrapper, Integer> popLaptopRankColumn;

  @FXML
  private TableColumn<PopularLaptopTableWrapper, String> popLaptopResourceIdColumn;

  @FXML
  private TableColumn<PopularLaptopTableWrapper, String> popLaptopTitleColumn;

  @FXML
  private TableColumn<PopularLaptopTableWrapper, String> popLaptopOsColumn;

  @FXML
  private TableColumn<PopularLaptopTableWrapper, Integer> popLaptopAvgRatingColumn;

  @FXML
  private PieChart popularOsPieChart;

  @FXML
  private Pane popVideoGamePane;

  @FXML
  private TableView<PopularVideoGameTableWrapper> popularVideoGameTableView;

  @FXML
  private TableColumn<PopularVideoGameTableWrapper, Integer> popVideogameRankColumn;

  @FXML
  private TableColumn<PopularVideoGameTableWrapper, String> popVideogameResourceIdColumn;

  @FXML
  private TableColumn<PopularVideoGameTableWrapper, String> popVideogameTitleColumn;

  @FXML
  private TableColumn<PopularVideoGameTableWrapper, String> popVideogameGenreColumn;

  @FXML
  private TableColumn<PopularVideoGameTableWrapper, String> popVideogameCertRatingColumn;

  @FXML
  private TableColumn<PopularVideoGameTableWrapper, Boolean> popVideogameMultiplayerColumn;

  @FXML
  private TableColumn<PopularVideoGameTableWrapper, Integer> popVideogameAvgRatingColumn;

  @FXML
  private ComboBox<String> videoStatTimeComboBox;

  @FXML
  private TitledPane fineStatPane;

  @FXML
  private TextField aveFineAmountTextField;

  @FXML
  private ComboBox<String> fineStatTimeComboBox;

  @FXML
  private TextField totalFineAmountTextField;

  @FXML
  private BarChart<String, Number> fineStatBarChart;

  private XYChart.Series<String, Number> averageFineStatSeries = new XYChart.Series<>();

  private XYChart.Series<String, Number> totalFineStatSeries = new XYChart.Series<>();

  @FXML
  private ComboBox<ResourceType> fineStatResTypeComboBox;


  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    userStatResTypeComboBox.getItems().addAll(resourceTypes);
    userStatTimeComboBox.getItems().addAll(timePeriods);
    setExpandedUserStatTitledPane();
    resourceStatResTypeComboBox.getItems().addAll(resourceTypes);
    resourceStatTimeComboBox.getItems().addAll(timePeriods);
    bookStatTimeComboBox.getItems().addAll(timePeriods);
    dvdStatTimeComboBox.getItems().addAll(timePeriods);
    laptopStatTimeComboBox.getItems().addAll(timePeriods);
    videoStatTimeComboBox.getItems().addAll(timePeriods);
    fineStatResTypeComboBox.getItems().addAll(resourceTypes);
    fineStatTimeComboBox.getItems().addAll(timePeriods);
    setResourceStatTableViews();
  }

  private void setResourceStatTableViews(){
    //Popular Resources Table
    popResRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("resRank"));
    popResResourceIdColumn.setCellValueFactory(
        new PropertyValueFactory<>("resResourceId"));
    popResTitleColumn.setCellValueFactory(
        new PropertyValueFactory<>("resTitle"));
    popResTypeColumn.setCellValueFactory(
        new PropertyValueFactory<>("resType"));
    popResAvgRatingColumn.setCellValueFactory(
        new PropertyValueFactory<>("resAvgRating"));

    //Popular Book Table
    popBookRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("bookRank"));
    popBookResourceIdColumn.setCellValueFactory(
        new PropertyValueFactory<>("bookResourceId"));
    popBookTitleColumn.setCellValueFactory(
        new PropertyValueFactory<>("bookTitle"));
    popBookAuthorColumn.setCellValueFactory(
        new PropertyValueFactory<>("bookAuthor"));
    popBookGenreColumn.setCellValueFactory(
        new PropertyValueFactory<>("bookGenre"));
    popBookAvgRatingColumn.setCellValueFactory(
        new PropertyValueFactory<>("bookAvgRating"));

    //Popular Author Table
    /*
    popAuthorRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("authorRank"));
    popAuthorNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("authorName"));*/

    //Popular Dvd Table
    popDvdRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("dvdRank"));
    popDvdResourceIdColumn.setCellValueFactory(
        new PropertyValueFactory<>("dvdResourceId"));
    popDvdTitleColumn.setCellValueFactory(
        new PropertyValueFactory<>("dvdTitle"));
    popDvdDirectorColumn.setCellValueFactory(
        new PropertyValueFactory<>("dvdDirector"));
    popDvdAvgRatingColumn.setCellValueFactory(
        new PropertyValueFactory<>("dvdAvgRating"));

    //Popular Director Table
    /*
    popDirectorsRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("directorRank"));
    popDirectorsNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("directorName"));*/

    //Popular Laptop Table
    popLaptopRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("laptopRank"));
    popLaptopResourceIdColumn.setCellValueFactory(
        new PropertyValueFactory<>("laptopResourceId"));
    popLaptopTitleColumn.setCellValueFactory(
        new PropertyValueFactory<>("laptopTitle"));
    popLaptopOsColumn.setCellValueFactory(
        new PropertyValueFactory<>("laptopOs"));
    popLaptopAvgRatingColumn.setCellValueFactory(
        new PropertyValueFactory<>("laptopAvgRating"));

    //Popular Video Game Table
    popVideogameRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("videoGameRank"));
    popVideogameResourceIdColumn.setCellValueFactory(
        new PropertyValueFactory<>("videoGameResourceId"));
    popVideogameTitleColumn.setCellValueFactory(
        new PropertyValueFactory<>("videoGameTitle"));
    popVideogameGenreColumn.setCellValueFactory(
        new PropertyValueFactory<>("videoGameGenre"));
    popVideogameCertRatingColumn.setCellValueFactory(
        new PropertyValueFactory<>("videoGameCertRating"));
    popVideogameMultiplayerColumn.setCellValueFactory(
        new PropertyValueFactory<>("videoGameMultiPlayer"));
    popVideogameAvgRatingColumn.setCellValueFactory(
        new PropertyValueFactory<>("videoGameAvgRating"));
  }

  /**
   * Returns to the user dashboard screen.
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }

  /**
   * Sets nodes visible to only one type of users visible to the correct type of users only.
   */
  @Override
  protected void configureVisibilities() {
    customerNodes = new Node[]{
        userLabel,
        noUserBorrowedTextField
    };
    librarianNodes = new Node[]{
        fineStatPane
    };


  }

  /**
   * Initializes nodes in the User Statistics Pane.
   */
  public void setExpandedUserStatTitledPane() {
    statsContainer.setExpandedPane(userStatPane);
    //Sets default value to first value in time period list.
    userStatTimeComboBox.getSelectionModel().selectFirst();
    //Sets default value to first value in resource types list.
    userStatResTypeComboBox.getSelectionModel().selectFirst();
    userStatComboBoxHandler();

  }

  /**
   * Handles the event of the Combo Boxes' value being changed in User Statistics Pane. Populates
   * the Bar Chart.
   */
  public void userStatComboBoxHandler() {
    ResourceType resourceType = userStatResTypeComboBox.getSelectionModel().getSelectedItem();
    String timePeriod = userStatTimeComboBox.getSelectionModel().getSelectedItem();
    String[] dates = new String[5];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (int i = 0; i < dates.length; i++) {
      switch (timePeriod) {
        case "Day":
          dates[i] = LocalDateTime.now().minusDays(i).format(formatter);
          break;
        case "Week":
          dates[i] = LocalDateTime.now().minusDays((long) i * 7).format(formatter);
          break;
        case "Month":
          dates[i] = LocalDateTime.now().minusMonths(i).getMonth().toString();
          break;
        default:
      }
    }

    userStatBarChart.getData().clear();

    if (isCustomerLoggedIn()) {
      int[] specificUserStats = StatisticsManager
          .getSpecificUserStatistics(library, (Customer) getLoggedInUser(),
              resourceType, timePeriod);
      noUserBorrowedTextField.setText(String.valueOf(specificUserStats[0]));
      specificUserStatSeries.getData().clear();
      for (int count = dates.length; count > 0; count--) {

        specificUserStatSeries.getData()
            .add(new XYChart.Data<>(dates[count - 1], specificUserStats[count - 1]));
      }
      specificUserStatSeries.setName("You");
      userStatBarChart.getData().add(specificUserStatSeries);

    }
    int[] averageUserStats = StatisticsManager.getAverageUserStatistics(library, resourceType,
        timePeriod);
    noAverageBorrowedTextField.setText(String.valueOf(averageUserStats[0]));
    averageUserStatSeries.getData().clear();
    for (int count = dates.length; count > 0; count--) {
      averageUserStatSeries.getData()
          .add(new XYChart.Data<>(dates[count - 1], averageUserStats[count - 1]));
    }
    averageUserStatSeries.setName("Average User");
    userStatBarChart.getData().add(averageUserStatSeries);

  }

  /**
   * Initializes nodes in the Resource Statistics Pane.
   */
  public void setExpandedResourceStatTitledPane() {
    statsContainer.setExpandedPane(resourceStatPane);
    //Sets default value to first value in resource types list.
    fineStatResTypeComboBox.getSelectionModel().selectFirst();
    resourceStatResTypeComboBoxHandler();

  }

  /**
   * Switches between Panes in Stack Pane based on chosen ResourceType.
   */
  public void resourceStatResTypeComboBoxHandler() {
    ResourceType resourceType = resourceStatResTypeComboBox.getSelectionModel().getSelectedItem();
    if (resourceType == null) {
      popResourcePane.setVisible(true);
      popBookPane.setVisible(false);
      popDvdPane.setVisible(false);
      popLaptopPane.setVisible(false);
      popVideoGamePane.setVisible(false);
    } else {
      switch (resourceType) {
        case BOOK:
          popResourcePane.setVisible(false);
          popBookPane.setVisible(true);
          popDvdPane.setVisible(false);
          popLaptopPane.setVisible(false);
          popVideoGamePane.setVisible(false);
          break;
        case DVD:
          popResourcePane.setVisible(false);
          popBookPane.setVisible(false);
          popDvdPane.setVisible(true);
          popLaptopPane.setVisible(false);
          popVideoGamePane.setVisible(false);
          break;
        case LAPTOP:
          popResourcePane.setVisible(false);
          popBookPane.setVisible(false);
          popDvdPane.setVisible(false);
          popLaptopPane.setVisible(true);
          popVideoGamePane.setVisible(false);
          break;
        case GAME:
          popResourcePane.setVisible(false);
          popBookPane.setVisible(false);
          popDvdPane.setVisible(false);
          popLaptopPane.setVisible(false);
          popVideoGamePane.setVisible(true);
          break;
        default:
      }
    }
  }

  /**
   * Initializes nodes in the Popular Resources Pane.
   */
  public void setPopResourcePane() {
  }

  /**
   * Initializes nodes in the Popular Books Pane.
   */
  public void setPopBookPane() {
  }

  /**
   * Initializes nodes in the Popular DVDs Pane.
   */
  public void setPopDvdPane() {
  }

  /**
   * Initializes nodes in the Popular Laptops Pane.
   */
  public void setPopLaptopPane() {
  }

  /**
   * Initializes nodes in the Popular Video Games Pane.
   */
  public void setPopVideoGamePane() {
  }

  /**
   * Initializes nodes in the Fine Statistics Pane.
   */
  public void setExpandedFineStatTitledPane() {
    statsContainer.setExpandedPane(fineStatPane);
    //Sets default value to first value in resource types list.
    fineStatResTypeComboBox.getSelectionModel().selectFirst();
    //Sets default value to first value in time period list.
    fineStatTimeComboBox.getSelectionModel().selectFirst();
    fineStatComboBoxHandler();

  }

  /**
   * Handles the event of the Combo Boxes' value being changed in Fine Statistics Pane. Populates
   * the Bar Chart.
   */
  public void fineStatComboBoxHandler() {
    ResourceType resourceType = fineStatResTypeComboBox.getSelectionModel().getSelectedItem();
    String timePeriod = fineStatTimeComboBox.getSelectionModel().getSelectedItem();
    String[] dates = new String[5];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    for (int i = 0; i < dates.length; i++) {
      switch (timePeriod) {
        case "Day":
          dates[i] = LocalDateTime.now().minusDays(i).format(formatter);
          break;
        case "Week":
          dates[i] = LocalDateTime.now().minusDays((long) i * 7).format(formatter);
          break;
        case "Month":
          dates[i] = LocalDateTime.now().minusMonths(i).getMonth().toString();
          break;
        default:
      }
    }

    fineStatBarChart.getData().clear();

    int[][] fineStats = StatisticsManager.getFineStatistics(library, resourceType, timePeriod);
    totalFineAmountTextField.setText(String.valueOf(fineStats[0][0]));
    aveFineAmountTextField.setText(String.valueOf(fineStats[1][0]));
    totalFineStatSeries.getData().clear();
    averageFineStatSeries.getData().clear();
    for (int count = dates.length; count > 0; count--) {
      totalFineStatSeries.getData()
          .add(new XYChart.Data<>(dates[count - 1], fineStats[0][count - 1]));
      averageFineStatSeries.getData()
          .add(new XYChart.Data<>(dates[count - 1], fineStats[1][count - 1]));
    }
    totalFineStatSeries.setName("Total Fines");
    averageFineStatSeries.setName("Average Fine");
    fineStatBarChart.getData().addAll(totalFineStatSeries, averageFineStatSeries);
  }


}
