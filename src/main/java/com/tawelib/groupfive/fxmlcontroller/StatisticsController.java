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
import java.util.HashMap;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


/**
 * The Statistics controller: A user can view info about borrowing statistics for themselves and an
 * average user. They can also view the most popular resources over different time periods, with
 * specifics given for each resource type. A Librarian will also be able to view statistics
 * pertaining to fines issued.
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

  private ObservableList<String> userStatTimePeriods = FXCollections.observableArrayList(
      "Day", "Week", "Month");

  private ObservableList<String> resourceTimePeriods = FXCollections.observableArrayList(
      "Day", "Week", "All Time");

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
  private SplitPane resourceSplitPane;

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
  private TableColumn<PopularResourcesTableWrapper, String> popResAvgRatingColumn;

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
  private TableColumn<PopularBookTableWrapper, String> popBookAvgRatingColumn;

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
  private TableColumn<PopularDvdTableWrapper, String> popDvdAvgRatingColumn;

  @FXML
  private TableView<PopularDvdDirectorTableWrapper> popDvdDirectorTableView;

  @FXML
  private TableColumn<PopularDvdDirectorTableWrapper, Integer> popDirectorRankColumn;

  @FXML
  private TableColumn<PopularDvdDirectorTableWrapper, String> popDirectorNameColumn;

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
  private TableColumn<PopularLaptopTableWrapper, String> popLaptopAvgRatingColumn;

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
  private TableColumn<PopularVideoGameTableWrapper, String> popVideogameAvgRatingColumn;

  @FXML
  private PieChart popVideoGameGenrePieChart;

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
   * Sets the dynamic fields. Adds the observable Lists to the comboBoxes and select default
   */
  @Override
  public void refresh() {

    userStatResTypeComboBox.getItems().addAll(resourceTypes);
    userStatTimeComboBox.getItems().addAll(userStatTimePeriods);
    setExpandedUserStatTitledPane();

    resourceStatResTypeComboBox.getItems().addAll(resourceTypes);
    resourceStatTimeComboBox.getItems().addAll(resourceTimePeriods);
    resourceStatTimeComboBox.getSelectionModel().selectFirst();

    bookStatTimeComboBox.getItems().addAll(resourceTimePeriods);
    bookStatTimeComboBox.getSelectionModel().selectFirst();

    dvdStatTimeComboBox.getItems().addAll(resourceTimePeriods);
    dvdStatTimeComboBox.getSelectionModel().selectFirst();

    laptopStatTimeComboBox.getItems().addAll(resourceTimePeriods);
    laptopStatTimeComboBox.getSelectionModel().selectFirst();

    videoStatTimeComboBox.getItems().addAll(resourceTimePeriods);
    videoStatTimeComboBox.getSelectionModel().selectFirst();
    setResourceStatTableViews();

    fineStatResTypeComboBox.getItems().addAll(resourceTypes);
    fineStatTimeComboBox.getItems().addAll(userStatTimePeriods);

  }

  private void setResourceStatTableViews() {
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
    popAuthorRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("authorRank"));
    popAuthorNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("authorName"));

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
    popDirectorRankColumn.setCellValueFactory(
        new PropertyValueFactory<>("directorRank"));
    popDirectorNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("directorName"));

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
    //Gets selected values of resource type and time period from combo boxes
    ResourceType resourceType = userStatResTypeComboBox.getSelectionModel().getSelectedItem();
    String timePeriod = userStatTimeComboBox.getSelectionModel().getSelectedItem();

    //Makes the array fto get dates for x axis
    String[] dates = new String[5];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Sets up the formatter and adds the dates to display on x axis of the bar chart
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

    //Gets the data for the specific user and add it to the series for bar chart and in textfield
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

    //Gets the data for the average user and add it to the series for bar chart and in textfield
    double[] averageUserStats = StatisticsManager.getAverageUserStatistics(library, resourceType,
        timePeriod);
    noAverageBorrowedTextField.setText(String.format("%.2f", averageUserStats[0]));
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
    resourceStatResTypeComboBox.getSelectionModel().selectFirst();
    resourceStatResTypeComboBoxHandler();
  }

  /**
   * Switches between Panes in Stack Pane based on chosen ResourceType.
   */
  public void resourceStatResTypeComboBoxHandler() {
    ResourceType resourceType = resourceStatResTypeComboBox.getSelectionModel().getSelectedItem();
    popResourcePane.setVisible(resourceType == null);
    popBookPane.setVisible(resourceType == ResourceType.BOOK);
    popDvdPane.setVisible(resourceType == ResourceType.DVD);
    popLaptopPane.setVisible(resourceType == ResourceType.LAPTOP);
    popVideoGamePane.setVisible(resourceType == ResourceType.GAME);

    if (resourceType == null) {
      setPopResourcePane();
    } else {
      switch (resourceType) {
        case BOOK:
          setPopBookPane();
          break;
        case DVD:
          setPopDvdPane();
          break;
        case LAPTOP:
          setPopLaptopPane();
          break;
        case GAME:
          setPopVideoGamePane();
          break;
        default:
      }
    }
  }

  /**
   * Initializes nodes in the Popular Resources Pane. Populates table based upon selected resource
   * type and time period
   */
  public void setPopResourcePane() {
    if (!popularResTableView.getItems().isEmpty()) {
      popularResTableView.getItems().clear();
    }

    List<Resource> popularResources = StatisticsManager.getPopularResources(
        library, resourceStatTimeComboBox.getSelectionModel().getSelectedItem(),
        null);

    for (int rank = 1; rank <= popularResources.size(); rank++) {
      popularResTableView.getItems().add(
          new PopularResourcesTableWrapper(rank,
              RatingManager.getResourceAverageRating(library, popularResources.get(rank - 1)))
      );
    }
  }

  /**
   * Initializes nodes in the Popular Books Pane. Populates tables based upon selected resource type
   * and time period
   */
  public void setPopBookPane() {
    if (!popularBookTableView.getItems().isEmpty()) {
      popularBookTableView.getItems().clear();
    }

    List<Resource> popularResources = StatisticsManager.getPopularResources(
        library, bookStatTimeComboBox.getSelectionModel().getSelectedItem(),
        ResourceType.BOOK);

    for (int rank = 1; rank <= popularResources.size(); rank++) {
      popularBookTableView.getItems().add(
          new PopularBookTableWrapper(rank,
              RatingManager.getResourceAverageRating(library, popularResources.get(rank - 1)))
      );
    }

    if (!popBookAuthorTableView.getItems().isEmpty()) {
      popBookAuthorTableView.getItems().clear();
    }

    List<String> popularAuthors = StatisticsManager
        .getPopularAuthors(library, bookStatTimeComboBox.getSelectionModel().getSelectedItem());

    for (int rank = 1; rank <= popularAuthors.size(); rank++) {
      popBookAuthorTableView.getItems().add(
          new PopularBookAuthorTableWrapper(rank, popularAuthors.get(rank - 1))
      );
    }

    setPopBookGenrePieChart();
  }

  /**
   * Populates Pie Chart with most popular genres from specified time period.
   */
  private void setPopBookGenrePieChart() {
    HashMap<String, Integer> dataMap = StatisticsManager
        .getPopularBookGenre(library, bookStatTimeComboBox.getSelectionModel().getSelectedItem());

    //Clear data
    popBookGenrePieChart.getData().clear();
    //Changes the HashMap to an ArrayList
    Object[] keys = dataMap.keySet().toArray();

    for (int i = 0; i < keys.length; i++) {
      popBookGenrePieChart.getData()
          .add(new PieChart.Data((String) keys[i], dataMap.get(keys[i])));
    }
  }

  /**
   * Initializes nodes in the Popular DVDs Pane. Populates tables based upon selected resource type
   * and time period
   */
  public void setPopDvdPane() {
    if (!popularDvdTableView.getItems().isEmpty()) {
      popularDvdTableView.getItems().clear();
    }

    List<Resource> popularResources = StatisticsManager.getPopularResources(
        library, dvdStatTimeComboBox.getSelectionModel().getSelectedItem(),
        ResourceType.DVD);

    for (int rank = 1; rank <= popularResources.size(); rank++) {
      popularDvdTableView.getItems().add(
          new PopularDvdTableWrapper(rank,
              RatingManager.getResourceAverageRating(library, popularResources.get(rank - 1)))
      );
    }

    if (!popDvdDirectorTableView.getItems().isEmpty()) {
      popDvdDirectorTableView.getItems().clear();
    }
    List<String> popularDirectors = StatisticsManager
        .getPopularDirectors(library, dvdStatTimeComboBox.getSelectionModel().getSelectedItem());

    for (int rank = 1; rank <= popularDirectors.size(); rank++) {
      popDvdDirectorTableView.getItems().add(
          new PopularDvdDirectorTableWrapper(rank, popularDirectors.get(rank - 1))
      );
    }
  }

  /**
   * Initializes nodes in the Popular Laptops Pane. Populates table based upon selected resource
   * type and time period
   */
  public void setPopLaptopPane() {
    if (!popularLaptopTableView.getItems().isEmpty()) {
      popularLaptopTableView.getItems().clear();
    }

    List<Resource> popularResources = StatisticsManager.getPopularResources(
        library, laptopStatTimeComboBox.getSelectionModel().getSelectedItem(),
        ResourceType.LAPTOP);

    for (int rank = 1; rank <= popularResources.size(); rank++) {
      popularLaptopTableView.getItems().add(
          new PopularLaptopTableWrapper(rank,
              RatingManager.getResourceAverageRating(library, popularResources.get(rank - 1)))
      );
    }
    setPopularOsPieChart();
  }

  /**
   * Populates Pie charts with most popular OSs to borrow in time period.
   */
  private void setPopularOsPieChart() {
    HashMap<String, Integer> dataMap = StatisticsManager
        .getPopularLaptopOs(library, laptopStatTimeComboBox.getSelectionModel().getSelectedItem());
    //Clear data
    popularOsPieChart.getData().clear();
    //Changes the HashMap to an ArrayList
    Object[] keys = dataMap.keySet().toArray();
    for (int i = 0; i < keys.length; i++) {
      popularOsPieChart.getData()
          .add(new PieChart.Data((String) keys[i], dataMap.get(keys[i])));
    }

  }

  /**
   * Initializes nodes in the Popular Video Games Pane. Populates table based upon selected resource
   * type and time period
   */
  public void setPopVideoGamePane() {
    if (!popularVideoGameTableView.getItems().isEmpty()) {
      popularVideoGameTableView.getItems().clear();
    }

    List<Resource> popularResources = StatisticsManager.getPopularResources(
        library, videoStatTimeComboBox.getSelectionModel().getSelectedItem(),
        ResourceType.GAME);

    for (int rank = 1; rank <= popularResources.size(); rank++) {
      popularVideoGameTableView.getItems().add(
          new PopularVideoGameTableWrapper(rank,
              RatingManager.getResourceAverageRating(library, popularResources.get(rank - 1)))
      );
    }
    setPopVideoGameGenrePieChart();
  }

  /**
   * Populates Pie Chart with most popular genres in specified time period.
   */
  private void setPopVideoGameGenrePieChart() {
    HashMap<String, Integer> dataMap = StatisticsManager
        .getPopularVideogameGenre(library,
            videoStatTimeComboBox.getSelectionModel().getSelectedItem());

    //Clear data
    popVideoGameGenrePieChart.getData().clear();

    //Changes the HashMap to an ArrayList
    Object[] keys = dataMap.keySet().toArray();
    for (int i = 0; i < keys.length; i++) {
      popVideoGameGenrePieChart.getData()
          .add(new PieChart.Data((String) keys[i], dataMap.get(keys[i])));
    }
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
    //Gets the selected resource type and time period
    ResourceType resourceType = fineStatResTypeComboBox.getSelectionModel().getSelectedItem();
    String timePeriod = fineStatTimeComboBox.getSelectionModel().getSelectedItem();

    //Makes the dates array to get dates for x axis
    String[] dates = new String[5];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Formats and gets dates to display in x axis of bar chart.
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

    //Gets the Average and Total Fine amt paid in specified time period for specified resource type
    double[][] fineStats = StatisticsManager.getFineStatistics(library, resourceType, timePeriod);
    totalFineAmountTextField.setText("£" + String.format("%.2f", fineStats[0][0]));
    aveFineAmountTextField.setText("£" + String.format("%.2f", fineStats[1][0]));

    totalFineStatSeries.getData().clear();
    averageFineStatSeries.getData().clear();

    //Populates bar chart with data.
    for (int count = dates.length; count > 0; count--) {
      totalFineStatSeries.getData()
          .add(new XYChart.Data<>(dates[count - 1], fineStats[0][count - 1]));
      averageFineStatSeries.getData()
          .add(new XYChart.Data<>(dates[count - 1], fineStats[1][count - 1]));
    }
    totalFineStatSeries.setName("Total Fine Amount");
    averageFineStatSeries.setName("Average Fine Amount");
    fineStatBarChart.getData().addAll(totalFineStatSeries, averageFineStatSeries);
  }


}
