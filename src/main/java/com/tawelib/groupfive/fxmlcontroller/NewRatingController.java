package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.Review;
import com.tawelib.groupfive.manager.RatingManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


/**
 * NewRatingController.java Controls the new window pop up that handles
 * creation of a new review or rating.
 *
 * @author Alex Jones
 * @version 1.0
 */
public class NewRatingController extends BaseFxmlController {

  private int ratingValue = 0;
  private Resource ratedResource;
  private CrudAction crudAction;

  @FXML
  private Label titleLabel;

  @FXML
  private Label ratingLabel;

  @FXML
  private ComboBox<String> ratingBox;

  @FXML
  private TextArea reviewArea;

  /**
   * Initialises new windows with title of resource and correct
   * set up for rating label.
   */
  void update() {
    titleLabel.setText(ratedResource.getTitle());

    ratingBox.getItems().addAll("★","★★","★★★",
        "★★★★","★★★★★");

    ratingBox.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<>() {
          @Override
          public void changed(ObservableValue<? extends Number> observable,
                              Number oldValue, Number newValue) {
            ratingValue = newValue.intValue() + 1;
            ratingLabel.setText("Rating*: " + ratingValue);
          }
        });
  }

  /**
   * Submits the entered rating/review into the r and r repository and closes the window.
   */
  public void confirm() {
    if (ratingValue == 0) {
      AlertHelper.alert(Alert.AlertType.WARNING, "You have not "
          + "selected a rating value.");
    } else {
      Rating rating;

      if (reviewArea.getText().isEmpty()) {
        rating = new Rating(ratingValue, ratedResource, (Customer) loggedInUser);
      } else {
        rating = new Review(ratingValue, ratedResource, (Customer) loggedInUser,
            reviewArea.getText());
      }
      RatingManager.createRating(library, rating);

      RatingController newController = (RatingController) SceneHelper
          .setUpScene(this, "ResourceRatings");

      newController.setSelectedResource(ratedResource);
      newController.setCrudAction(crudAction);
      newController.update();
    }
  }

  /**
   * Returns to previous page without submitting a review.
   */
  @Override
  public void back() {
    RatingController newController = (RatingController) SceneHelper
        .setUpScene(this, "ResourceRatings");

    newController.setSelectedResource(ratedResource);
    newController.setCrudAction(crudAction);
    newController.update();
  }

  /**
   * Sets the pane's selected resource.
   *
   * @param resource resource being reviewed
   */
  void setRatedResource(Resource resource) {
    this.ratedResource = resource;
  }

  /**
   * Saves the crud action from the previous window.
   *
   * @param crudAction crudAction being saved
   */
  void saveCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }
}
