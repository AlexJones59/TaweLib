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
import javafx.scene.control.TextField;

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
  private TextField searchField;

  @FXML
  public void initialize() {
    searchField.setOnKeyTyped(event -> refresh());
  }

  @Override
  public void refresh() {
    eventsListField.getItems().remove(0, eventsListField.getItems().size());
    ArrayList<Event> allEvents = EventManager.getUserPastEvents(library, loggedInUser);
    for (Event e : allEvents) {
      if (e.getEventName().contains(searchField.getText())) {
        //The main info shown on the button
        int year = e.getEventDate().getYear();
        String month = e.getEventDate().getMonth().toString();
        int day = e.getEventDate().getDayOfMonth();

        Button description = new Button(year + " " + month + " " + day + "\n"
            + e.getEventDate().toLocalTime().toString() + "\n" + e.getEventName() + "\n");

        description.setPrefSize(EVENT_CELL_WIDTH, EVENT_CELL_HEIGHT);
        eventsListField.getItems().addAll(description);

        description.setOnAction(event -> {
          String aboutEvent = year + " " + month + " " + day + "\n"
              + e.getEventDate().toLocalTime().toString() + "\n" + e.getEventName() + "\n" + e
              .getDescription();

          AlertHelper.eventDescription(INFORMATION, aboutEvent);
        });
      }
    }
  }

  @Override
  public void back() {
    SceneHelper.setUpScene(this, "Events");
  }


}
