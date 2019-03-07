package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.Review;
import com.tawelib.groupfive.manager.RatingManager;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

/**
 * NewRatingController.java Controls the new window pop up that handles
 * creation of a new review or rating.
 *
 * @author Alex Jones
 * @version 1.0
 */
public class NewRatingController extends BaseFxmlController {

  private Resource ratedResource;
  private CrudAction crudAction;

  @FXML
  private Label titleLabel;

  @FXML
  private Label ratingValueLabel;

  @FXML
  private Slider ratingSlider;

  @FXML
  private TextArea reviewArea;

  /**
   * Initialises new windows with title of resource and correct
   * set up for rating label.
   */
  void update() {

    ratingSlider.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable,
                          Number oldValue, Number newValue) {
        ratingValueLabel.setText(String.valueOf(newValue));
      }
    });

    titleLabel.setText(ratedResource.getTitle());
  }

  /**
   * Submits the entered rating/review into the r and r repository and closes the window.
   */
  public void confirm() {
    Rating rating;
    if (reviewArea.getText() == null) {
      rating = new Rating((int)ratingSlider.getValue(),ratedResource, (Customer)loggedInUser);
    } else {
      rating = new Review((int)ratingSlider.getValue(),ratedResource, (Customer)loggedInUser,
          reviewArea.getText());
    }
    RatingManager.createRating(library, rating);

    RatingController newController = (RatingController) SceneHelper
        .setUpScene(this, "ResourceRatings");

    newController.setSelectedResource(ratedResource);
    newController.setCrudAction(crudAction);
    newController.update();
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

  void setRatedResource(Resource resource) {
    this.ratedResource = resource;
  }

  void saveCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }
}
