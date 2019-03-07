package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Customer;
import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.Review;
import com.tawelib.groupfive.manager.RatingManager;
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

  @FXML
  private Label titleLabel;

  @FXML
  private Label ratingValueLabel;

  @FXML
  private Button submitButton;

  @FXML
  private Slider ratingSlider;

  @FXML
  private TextArea reviewArea;

  /**
   * Initialises new windows with title of resource and correct
   * set up for rating label.
   */
  void initialise() {

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
   * Submits the entered rating/review into the r&r repository and closes the window.
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
    submitButton.getScene().getWindow().hide();
  }

  void setRatedResource(Resource resource) {
    this.ratedResource = resource;
  }
}
