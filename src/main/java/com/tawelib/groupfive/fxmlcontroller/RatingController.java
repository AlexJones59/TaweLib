package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.Review;
import com.tawelib.groupfive.exception.InvalidRaterException;
import com.tawelib.groupfive.manager.RatingManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * RatingController.java Controller for ratings user interface.
 *
 * @author Alex Jones
 * @version 1.0
 */
public class RatingController extends BaseFxmlController {

  private Resource selectedResource;
  private CrudAction crudAction;

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

  @FXML
  private Button backButton;

  @FXML
  private Button newRatingButton;

  /**
   * Sets dynamic fields & populates reviews panel.
   */
  @Override
  public void refresh() {
    resourceTitle.setText(selectedResource.getTitle());

    List<Rating> ratings = getLibrary().getRatingRepository()
        .getResourcesRatings(selectedResource.getResourceId());

    int[] ratingsAmount = new int[6];

    for (Rating rating : ratings) {
      ratingsAmount[rating.getRatingValue()]++;

      if (rating instanceof Review) {
        reviewVerticalBox.getChildren().add(createReviewBox((Review) rating));
      }
    }

    XYChart.Series<String,Integer> ratingsData = new XYChart.Series<>();
    for (int i = 1; i < 6; i++) {
      ratingsData.getData().add(new XYChart.Data<>(String
          .valueOf(i), ratingsAmount[i]));
    }

    ratingChart.getData().add(ratingsData);

    double averageRating = ((double)(ratingsAmount[1] + (2 * ratingsAmount[2])
        + (3 * ratingsAmount[3]) + (4 * ratingsAmount[4])
        + (5 * ratingsAmount[5]))) / 3.0;

    averageRatingLabel.setText("Average Rating: "
        + String.format("%.1f",averageRating));

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
        + " - " + review.getDateRated().toString());
    reviewPane.setTop(reviewerAndDate);

    StringBuilder reviewRating = new StringBuilder();
    for (int i = 0; i < review.getRatingValue(); i++) {
      reviewRating.append("★");
    }

    Label reviewRatingLabel = new Label(reviewRating.toString());
    reviewPane.setCenter(reviewRatingLabel);

    TextField reviewBodyField = new TextField(review.getReviewBody());
    reviewBodyField.setEditable(false);
    reviewPane.setBottom(reviewBodyField);

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
   * @throws IOException File not found
   * @throws InvalidRaterException User trying to leave a rating is invalid
   */
  public void newRatingWindow() throws IOException,
      InvalidRaterException {
    try {
      if (!RatingManager.validRater(getLibrary(),(Customer)getLoggedInUser(),
          selectedResource)) {
        throw new InvalidRaterException();
      }
      try {

        Parent root;
        FXMLLoader loader = new FXMLLoader();
        root = loader.load(getClass().getClassLoader()
            .getResource("com.tawelib.groupfive"
                + ".view.ResourceNewRating.fxml"));

        NewRatingController controller = loader.getController();
        controller.setRatedResource(selectedResource);
        controller.initialise();

        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setTitle("Leave New Rating or Review");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
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
            "ResourceCrud");

    newController.setSelectedResource(selectedResource);
    newController.setCrudAction(crudAction);
    newController.refresh();
  }
}
