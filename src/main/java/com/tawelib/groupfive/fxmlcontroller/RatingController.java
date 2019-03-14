package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.Review;
import com.tawelib.groupfive.exception.InvalidRaterException;
import com.tawelib.groupfive.manager.RatingManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * RatingController.java Controller for ratings user interface.
 *
 * @author Alex Jones
 * @version 1.0
 */
public class RatingController extends BaseFxmlController {

  private Resource selectedResource;
  private CrudAction crudAction;
  private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy mm:HH");

  @FXML
  private Label resourceTitle;

  @FXML
  private Label averageRatingLabel;

  @FXML
  private Label averageRatingStarsLabel;

  @FXML
  private BarChart<String,Integer> ratingChart;

  @FXML
  private VBox reviewVerticalBox;

  /**
   * Sets dynamic fields and populates reviews panel.
   */
  public void update() {
    resourceTitle.setText(this.selectedResource.getTitle());

    List<Rating> ratings = getLibrary().getRatingRepository()
        .getResourcesRatings(this.selectedResource);

    int[] ratingsAmount = {0, 0, 0, 0, 0, 0};

    for (Rating rating : ratings) {
      ratingsAmount[rating.getRatingValue()]++;

      if (rating instanceof Review) {
        reviewVerticalBox.getChildren().add(createReviewBox((Review) rating));
      }
    }
    reviewVerticalBox.setPrefWidth(Region.USE_COMPUTED_SIZE);

    XYChart.Series<String,Integer> ratingsData = new XYChart.Series<>();
    for (int i = 1; i < 6; i++) {
      ratingsData.getData().add(new XYChart.Data<>(String
          .valueOf(i), ratingsAmount[i]));
    }

    ratingChart.getData().add(ratingsData);

    double averageRating = ((double)(ratingsAmount[1] + (2 * ratingsAmount[2])
        + (3 * ratingsAmount[3]) + (4 * ratingsAmount[4])
        + (5 * ratingsAmount[5]))) / (ratingsAmount[1] + ratingsAmount[2]
        + ratingsAmount[3] + ratingsAmount[4] + ratingsAmount[5]);

    if (ratings.isEmpty()) {
      averageRatingLabel.setText("N/A");
    } else {
      averageRatingLabel.setText(String.format("%.1f", averageRating));
    }

    StringBuilder averageStars = new StringBuilder();
    for (int i = 1; i <= (int)averageRating; i++) {
      averageStars.append("★");
    }

    averageRatingStarsLabel.setText(averageStars.toString());
  }

  /**
   * Creates a formatted BorderPane containing the contents of a review.
   *
   * @param review the review being displayed
   * @return formatted BorderPane displaying a review's content
   */
  private BorderPane createReviewBox(Review review) {
    BorderPane reviewPane = new BorderPane();
    reviewPane.setStyle("-fx-border-color: black");

    Customer reviewer = getLibrary().getCustomerRepository()
        .getSpecific(review.getRater().getUsername());

    Label reviewerAndDate = new Label(reviewer.getFullName()
        + " - " + review.getDateRated().format(format));
    reviewPane.setTop(reviewerAndDate);

    StringBuilder reviewRating = new StringBuilder();
    for (int i = 0; i < review.getRatingValue(); i++) {
      reviewRating.append("★");
    }

    Label reviewRatingLabel = new Label(reviewRating.toString());
    reviewPane.setLeft(reviewRatingLabel);

    TextArea reviewBodyArea = new TextArea(review.getReviewBody());
    reviewBodyArea.setEditable(false);
    reviewPane.setBottom(reviewBodyArea);
    reviewBodyArea.setWrapText(true);
    reviewBodyArea.setPrefSize(500,Region.USE_COMPUTED_SIZE);
    reviewBodyArea.setMaxHeight(100);

    BorderPane.setMargin(reviewerAndDate, new Insets(10));
    BorderPane.setMargin(reviewRatingLabel, new Insets(0,10,0,10));
    BorderPane.setMargin(reviewBodyArea, new Insets(10));

    reviewPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

    return reviewPane;
  }

  /**
   * Sets the target resource.
   *
   * @param resource the selected resource
   */
  void setSelectedResource(Resource resource) {
    this.selectedResource = resource;
  }

  /**
   * Saves the crud action of the previous page.
   *
   * @param crudAction the crudAction of the ResourceCrud page
   */
  void setCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }

  /**
   * Opens new window where user can enter a new rating or review.
   *
   * @throws InvalidRaterException User trying to leave a rating is invalid
   */
  public void newRatingWindow() {
    try {
      if (!RatingManager.validRater(getLibrary(),(Customer)getLoggedInUser(),
          selectedResource)) {
        throw new InvalidRaterException();
      }

      NewRatingController newController =
          (NewRatingController) SceneHelper.setUpScene(
              this,
              "ResourceNewRating");

      newController.setRatedResource(selectedResource);
      newController.saveCrudAction(crudAction);
      newController.update();

    } catch (InvalidRaterException e) {
      AlertHelper.alert(Alert.AlertType.WARNING, "You have not "
          + "leased this resource or have already left a review or rating.");
    }
  }

  /**
   * Returns to the previous screen.
   */
  @Override
  public void back() {
    ResourceCrudController newController =
        (ResourceCrudController) SceneHelper.setUpScene(
            this,
            lastSceneName);
    newController.setLastSceneName("BrowseResources");
    newController.setSelectedResource(this.selectedResource);
    newController.setCrudAction(this.crudAction);
    newController.refresh();
  }
}