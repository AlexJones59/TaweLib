package com.tawelib.groupfive.fxmlcontroller;

import static javafx.scene.control.Alert.AlertType.INFORMATION;

import com.tawelib.groupfive.entity.Event;
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

  private final double EVENT_CELL_WIDTH = 380.0;
  private final double EVENT_CELL_HEIGHT = 100.0;

  @FXML
  private ListView<Button> eventsListField;

  @FXML
  public void initialize() {
    ArrayList<Event> allEvents = EventsController.getEvents();
    for (int i = 0; i < allEvents.size(); i++) {

      Event e = allEvents.get(i);
      Button description = new Button(e.getEventDate().toString().substring(0, 10) + "\n"
          + e.getEventDate().toString().substring(11, 16) + "\n" + e.getEventName() + "\n");

      description.setPrefSize(EVENT_CELL_WIDTH, EVENT_CELL_HEIGHT);
      eventsListField.getItems().addAll(description);

      description.setOnAction(event -> {
        String aboutEvent = e.getEventDate() + "\n" + e.getEventName() + "\n" + e.getDescription()
            + "\nFree slots: "
            /* + (e.getCapacity()-library.getParticipationRepository().getNumberOfParticipants(e))*/;//TODO: uncomment when library will work
        AlertHelper.alert(INFORMATION, aboutEvent);
      });

      /*Checks if there are free slots and person dontparticipate, if not - disables the button*/
    /*if((library.getParticipationRepository().getNumberOfParticipants(e) >= e.getCapacity())
        || (library.getParticipationRepository().doesParticipate(e,getSelectedUser()))){
      signUpForEvent.setDisable(true);//TODO: uncomment when library works
    }*/
    }
  }

  @Override
  public void back() {
    SceneHelper.setUpScene(this, "Events");
  }


}
