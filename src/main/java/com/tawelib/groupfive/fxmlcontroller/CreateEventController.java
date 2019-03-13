package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.manager.EventManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import com.tawelib.groupfive.util.TimePicker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

  private static final int NAME_MIN_LENGTH = 2;
  private static final int NAME_MAX_LENGTH = 50;
  private static final int MIN_PARTICIPANTS = 1;
  private static final int MAX_DESCRIPTION_LENGTH = 200;

  private static final String MIN_PARTICIPANTS_ERROR
      = "Amount of the participants must be more or equals to " + MIN_PARTICIPANTS;
  private static final String DATE_CHRONOLOGY_ERROR = "Enter the date after the current time";
  private static final String TYPE_INPUT_ERROR = "Please provide the information in right format";
  private static final String NAME_INPUT_ERROR = "Minimum amount fo characters in name is "
      + NAME_MIN_LENGTH + " and maximum is " + NAME_MAX_LENGTH;
  private static final String DESCRIPTION_LENGTH_ERROR = "The maximum amount of characters in "
      + "descriptions is " + MAX_DESCRIPTION_LENGTH;


  @FXML
  private TextField nameTextField;

  @FXML
  private TextField maxParticipantsField;

  @FXML
  private TextArea descriptionField;

  @FXML
  private DatePicker datePicker;

  @FXML
  private Label maxCharsDescriptionsHint;

  @FXML
  private TimePicker timePicker;


  /**
   * The method creates the event through EventManager class, checks for input data correctness,
   * outputs alerts if necessary. Called by pressing the button on the bottom.
   */
  public void createEvent() {
    String name = nameTextField.getText();
    int maxPpl = 0;
    LocalDate eventDate = LocalDate.of(0, 1, 1);
    LocalTime eventTime = LocalTime.of(0, 0);
    String description = descriptionField.getText();

    /*Check if the dates and amount of participants(maxPpl) are numbers*/
    boolean passedNumberReading = true;
    try {
      maxPpl = Integer.parseInt(maxParticipantsField.getText());
      eventDate = datePicker.getValue();
      eventTime = timePicker.getTime();
    } catch (Exception e) {
      passedNumberReading = false;
      AlertHelper.alert(AlertType.WARNING, TYPE_INPUT_ERROR);
    }

    if (passedNumberReading) { //If user entered numbers where needed
      //if name, time and description satisfies the conditions
      if (nameCheck(name) && descriptionCheck(description)) {
        LocalDateTime date = LocalDateTime.of(eventDate, eventTime);

        if (date.isAfter(LocalDateTime.now())) { //Checks if the date is after current time

          if (maxPpl >= MIN_PARTICIPANTS) { //If satisfies the max > min amount of participants
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
    datePicker.setValue(LocalDate.now());
    datePicker.getEditor().setDisable(true);
    maxCharsDescriptionsHint.setText(maxCharsDescriptionsHint.getText() + MAX_DESCRIPTION_LENGTH);
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
   * Method checks whether the description fits in the maximum length bounds
   *
   * @param description The description string
   * @return True if the conditions are satisfied, false otherwise.
   */
  private boolean descriptionCheck(String description) {
    boolean toReturn;
    if (description.length() <= MAX_DESCRIPTION_LENGTH) {
      toReturn = true;
    } else {
      AlertHelper.alert(AlertType.WARNING, DESCRIPTION_LENGTH_ERROR);
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
