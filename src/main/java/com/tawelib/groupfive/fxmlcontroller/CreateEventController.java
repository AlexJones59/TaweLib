package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.manager.EventManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
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

  private final static int NAME_MIN_LENGTH = 2;
  private final static int NAME_MAX_LENGTH = 50;
  private final static int MIN_PARTICIPANTS = 1;

  private final static String TIME_INPUT_ERROR = "Enter the time in right bounds";
  private final static String MIN_PARTICIPANTS_ERROR
      = "Amount of the participants must be more or equals to " + MIN_PARTICIPANTS;
  private final static String DATE_CHRONOLOGY_ERROR = "Enter the date after the current time";
  private final static String TYPE_INPUT_ERROR = "Please provide the information in right format";
  private final static String NAME_INPUT_ERROR = "Minimum amount fo characters in name is "
      + NAME_MIN_LENGTH + " and maximum is " + NAME_MAX_LENGTH;


  @FXML
  private TextField nameTextField;

  @FXML
  private TextField minuteDateField;

  @FXML
  private TextField hourDateField;

  @FXML
  private TextField maxParticipientsField;

  @FXML
  private TextArea descriptionField;

  @FXML
  private DatePicker datePicker;


  /**
   * The method creates the event through EventManager class, checks for input data correctness,
   * outputs alerts if necessary. Called by pressing the button on the bottom.
   */
  public void createEvent() {
    String name = nameTextField.getText();
    int hour = 0;
    int minute = 0;
    int maxPpl = 0;
    LocalDate eventDate = LocalDate.of(0, 1, 1);
    String description = descriptionField.getText();

    /*Check if the dates and amount of participants(maxPpl) are numbers*/
    boolean passedNumberReading = true;
    try {
      hour = Integer.parseInt(hourDateField.getText());
      minute = Integer.parseInt(minuteDateField.getText());
      maxPpl = Integer.parseInt(maxParticipientsField.getText());
      eventDate = datePicker.getValue();
    } catch (Exception e) {
      passedNumberReading = false;
      AlertHelper.alert(AlertType.WARNING, TYPE_INPUT_ERROR);
    }

    if (passedNumberReading) {//If user entered numbers where needed
      if (nameCheck(name) && timeCheck(hour, minute)) {//if name and time satisfies the conditions

        LocalTime eventTime = LocalTime.of(hour, minute);
        LocalDateTime date = LocalDateTime.of(eventDate, eventTime);

        if (date.isAfter(LocalDateTime.now())) { //Checks if the date is after current time

          if (maxPpl >= MIN_PARTICIPANTS) {//If satisfies the max > min amount of participants
            Event event = new Event("", name, date, maxPpl, description);
            EventManager.addEvent(library, event);
            back();
          } else {
            AlertHelper.alert(AlertType.WARNING, MIN_PARTICIPANTS_ERROR);
          }
        } else {
          AlertHelper.alert(AlertType.WARNING, DATE_CHRONOLOGY_ERROR);
        }
      }
    }
  }

  /**
   * Initializes when the scene is opened, makes the textfield for datePicker disabled.
   */
  @FXML
  public void initialize() {
    datePicker.getEditor().setDisable(true);
  }

  /**
   * Method checks whether the time is in the right bounds
   *
   * @param hour The value of hour
   * @param minute The value of minute
   * @return true if the input data satisfies the conditions, false otherwise.
   */
  private boolean timeCheck(int hour, int minute) {
    boolean toReturn;
    if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
      toReturn = true;
    } else {
      AlertHelper.alert(AlertType.WARNING, TIME_INPUT_ERROR);
      toReturn = false;
    }
    return toReturn;
  }

  /**
   * Method checks whether the name of the event satisfies the length conditions stated above.
   *
   * @param name The name of the event.
   * @return True if the conditions are satisfied, false otherwise.
   */
  private boolean nameCheck(String name) {
    boolean toReturn;
    if (name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH) {
      toReturn = true;
    } else {
      AlertHelper.alert(AlertType.WARNING, NAME_INPUT_ERROR);
      toReturn = false;
    }
    return toReturn;
  }

  /**
   * Returns to the previous screen.
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "Events");
  }

}
