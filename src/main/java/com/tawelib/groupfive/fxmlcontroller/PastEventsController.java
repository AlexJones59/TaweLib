package com.tawelib.groupfive.fxmlcontroller;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.manager.EventManager;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * The class controls PastEvents.fxml file. Allows to see the users past attended events.
 *
 * @author Oskars Dervinis
 * @version 1.0
 */
public class PastEventsController extends BaseFxmlController {

  private static final double EVENT_CELL_WIDTH = 312.0;
  private static final double EVENT_CELL_HEIGHT = 100.0;

  @FXML
  private ListView<Button> eventsListField;

  @FXML
  public void initialize() {

  }

  @Override
  public void refresh() {
    ArrayList<Event> allEvents = EventManager.getUserPastEvents(library, loggedInUser);
    for (Event e : allEvents) {
      //The main info shown on the button
      int year = e.getEventDate().getYear();
      String month = e.getEventDate().getMonth().toString();
      int day = e.getEventDate().getDayOfMonth();
      int hour = e.getEventDate().getHour();
      int min = e.getEventDate().getMinute();
      Button description = new Button(year + " " + month + " " + day + "\n"
          + hour + ":" + min + "\n" + e.getEventName() + "\n");

      description.setPrefSize(EVENT_CELL_WIDTH, EVENT_CELL_HEIGHT);
      eventsListField.getItems().addAll(description);

      description.setOnAction(event -> {
        String aboutEvent = year + " " + month + " " + day + "\n"
            + hour + ":" + min + "\n" + e.getEventName() + "\n" + e
            .getDescription();

        AlertHelper.eventDescription(INFORMATION, aboutEvent);
      });
    }
  }

  @Override
  public void back() {
    SceneHelper.setUpScene(this, "Events");
  }


}
