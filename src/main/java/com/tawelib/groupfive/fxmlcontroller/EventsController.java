package com.tawelib.groupfive.fxmlcontroller;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

/**
 * The class controls Events.fxml file, allows to observe event, see the users past events,
 * see next events and joined events. Also for librarian is available creation of the new event.
 *
 * @author Oskars Dervinis
 * @version 1.0
 */
public class EventsController extends BaseFxmlController {

  private final double EVENT_CELL_WIDTH = 380.0;
  private final double EVENT_CELL_HEIGHT = 100.0;
  private final double JOIN_BUTTON_HEIGHT = 10.0;
  private final String JOINING_EVENT_CONFIRMATION = "Are you sure you want to join the event '";
  private final String JOINING_EVENT_SUCCESS = "Successfully joined the event!";

  @FXML
  private Button newEventBtn;
  @FXML
  private ListView<VBox> upcomingEventsField;

  @FXML
  private ListView<VBox> currentEventsField;

  /**
   * initializes the GUI, adds 2 list views of events.
   */
  @FXML
  public void initialize() {
    System.out.println(library);//TODO: library check
    if (isCustomerLoggedIn()) {
      newEventBtn.setVisible(false);//Creation event is not allowed
    }
    initUpcomingEvents();
    initJoinedEvents();
  }

  /** The method adds upcoming events to the scrolling pane
   */
  private void initUpcomingEvents() {
    ArrayList<Event> allEvents = getEvents();//library.getEventRepository().getUpcomingEvents();
    for (int i = 0; i < allEvents.size(); i++) {
      upcomingEventsField.getItems().addAll(constructEventCell(allEvents.get(i), false));
    }
  }

  /**
   * The method adds already joined events to the scrolling pane
   */
  private void initJoinedEvents() {
    ArrayList<Event> allEvents = getEvents();//TODO: get events for this user(maybe not implemented yet)
    for (int i = 0; i < allEvents.size(); i++) {
      currentEventsField.getItems().addAll(constructEventCell(allEvents.get(i), true));
    }
  }

  /**
   * The method opens the scene to view past events where this user participated.
   * Called by pressing pastEventsBtn.
   */
  public void showPastEvents() {
    SceneHelper.setUpScene(this, "PastEvents");
  }

  /**
   * The method opens the scene to create the new event. Is visible only for librarian. Called by
   * pressint createBtn button.
   */
  public void createNewEvent() {
    SceneHelper.setUpScene(this, "CreateEvent");
  }

  /**
   * The method creates the "cell" consisting of 2 buttons(for more info about the event and the
   * button for signing for event. It is reppresentated as VBox. Also checks if the user can join
   * the event or not.
   *
   * @param e the event will be displayed.
   * @return the VBox as representation of the event.
   */
  private VBox constructEventCell(Event e, boolean isJoined) {
    VBox box = new VBox();

    Button showDescrBtn = new Button(e.getEventDate().toString().substring(0, 10) + "\n"
        + e.getEventDate().toString().substring(11, 16) + "\n" + e.getEventName() + "\n");
    showDescrBtn.setPrefSize(EVENT_CELL_WIDTH, EVENT_CELL_HEIGHT);
    Button signUpForEvent = new Button("Join");//TODO: make look good
    signUpForEvent.setPrefSize(EVENT_CELL_WIDTH, JOIN_BUTTON_HEIGHT);
    box.getChildren().addAll(showDescrBtn, signUpForEvent);
    eventButtonActions(e, showDescrBtn, signUpForEvent);

    if (isJoined) {
      signUpForEvent.setVisible(false);
    }
    /*Checks if there are free slots and person dontparticipate, if not - disables the button*/
    /*if((library.getParticipationRepository().getNumberOfParticipants(e) >= e.getCapacity())
        || (library.getParticipationRepository().doesParticipate(e,getSelectedUser()))){
      signUpForEvent.setDisable(true);//TODO: uncomment when library works
    }*/

    return box;
  }

  /**
   * The method states the actions taken after presing the buttons which are part of VBox event in
   * the scrolling pane.
   *
   * @param e the event this button belongs
   * @param description A button which opens the full info about the event
   * @param join The button for joining the event
   */
  private void eventButtonActions(Event e, Button description, Button join) {

    description.setOnAction(event -> {
      String aboutEvent =
          e.getEventDate() + "\n" + e.getEventName() + "\n" + e.getDescription() + "\nFree slots: "
          /* + (e.getCapacity()-library.getParticipationRepository().getNumberOfParticipants(e))*/;//TODO: uncomment when library will work
      AlertHelper.alert(INFORMATION, aboutEvent);
    });

    join.setOnAction(event -> {
      Alert alert = new Alert(CONFIRMATION, JOINING_EVENT_CONFIRMATION + e.getEventName() + "'?");
      Optional<ButtonType> answer = alert.showAndWait();
      if (answer.get() == ButtonType.OK) {
        //EventManager.joinEvent(library, getSelectedUser(), e);TODO: uncomment when library will work
        AlertHelper.alert(INFORMATION, JOINING_EVENT_SUCCESS);
      }
    });

  }

  /**
   * Returns to the previous screen.
   */
  @Override
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
  //TODO: test data while library doesnt work, remove from here when the project is ready

  public static ArrayList<Event> getEvents() {
    Event event0 = new Event("4", "oneEvent", LocalDateTime.now().plusDays(1), 5, "Description1");
    Event event1 = new Event("45", "twoEvent", LocalDateTime.now().plusDays(5), 10, "Description2");
    Event event2 = new Event("456", "threeEvent", LocalDateTime.now().plusDays(30), 15,
        "Description3");
    ArrayList<Event> upcomingEventsList = new ArrayList<>();
    upcomingEventsList.add(event0);
    upcomingEventsList.add(event1);
    upcomingEventsList.add(event2);
    return upcomingEventsList;
  }
}
