package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.manager.EventManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The class controls CreateEvent.fxml file. It allow to create a new event and add it to the events
 * repository. This screen is available only for librarian.
 *
 * @author Oskars Dervinis
 * @version 1.0
 */
public class CreateEventController extends BaseFxmlController {

  @FXML
  private TextField nameTextField;

  @FXML
  private TextField minuteDateField;

  @FXML
  private TextField hourDateField;

  @FXML
  private TextField dayDateField;

  @FXML
  private TextField monthDateField;

  @FXML
  private TextField yearDateField;

  @FXML
  private TextField maxParticipientsField;

  @FXML
  private TextArea descriptionField;


  /**
   * The method creates the event through EventManager class, checks for input data correctness,
   * outputs alerts if necessary. Called by pressing the button on the bottom.
   */
  public void createEvent() {
    String name = nameTextField.getText();
    int day = 0;
    int month = 0;
    int year = 0;
    int hour = 0;
    int minute = 0;
    int maxPpl = 0;
    String description = descriptionField.getText();

    /*Check if the dates are numbers*/
    boolean passedCasting = true;
    try {
      day = Integer.parseInt(dayDateField.getText());
      month = Integer.parseInt(monthDateField.getText());
      year = Integer.parseInt(yearDateField.getText());
      hour = Integer.parseInt(hourDateField.getText());
      minute = Integer.parseInt(minuteDateField.getText());
      maxPpl = Integer.parseInt(maxParticipientsField.getText());
    } catch (Exception e) {
      passedCasting = false;
      AlertHelper.alert(AlertType.WARNING,
          "Please provide the information in right format");
    }
    /*Check if the date is in the right format*/
    if (!passedCasting || day <= 0 || month <= 0 || year < 2019 || maxPpl < 0 || hour < 0
        || minute < 0 || day > 31 || month > 12 || year > 2050 || hour > 24 || minute > 60) {
      AlertHelper.alert(AlertType.WARNING, "Please provide the correct date");
    } else {
      LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
      if (date.isAfter(LocalDateTime.now())) { //Checks if the date is after current time
        Event event = new Event("12", name, date, maxPpl, description);//TODO: now event id is 1
        EventManager.addEvent(library, event);
        back();
      } else {
        AlertHelper.alert(AlertType.ERROR, "Enter the date after the current time");
      }

    }
  }

  /**
   * Returns to the previous screen.
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "Events");
  }

}
