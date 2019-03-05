package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.manager.EventManager;
import com.tawelib.groupfive.repository.EventRepository;
import com.tawelib.groupfive.util.SceneHelper;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

public class EventsController extends BaseFxmlController{

  @FXML
  private Button btnBack;

  @FXML
  private ScrollPane upcomipgEvents;

  @FXML
  private ListView<HBox> takenEvents;

  //private ArrayList<Event> upcomingEvents = library.getEventRepository().getUpcomingEvents();
  /**
   * Returns to the user dashboard screen.
   */
  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }

  /**
   * Initializes the gui.
   */
  @FXML
  public void initialize(){

    //ArrayList<Event> upcomingEventsList = library.getEventRepository().getUpcomingEvents();
    ArrayList<HBox> manyEvents = new ArrayList<>();

    double oneEventHeight = 50.0;
    for(int amountOfEvents = 0; amountOfEvents < 5; amountOfEvents++){
      Event a = new Event("456","oneEvent", LocalDateTime.now(), 5, "Description");
      HBox oneEvent = new HBox();
      Button event = new Button(a.getEventName() + "\n" + a.getDescription());
      event.setPrefSize(175.0, 100.0);
      Button signUpForEvent = new Button("+");
      oneEvent.getChildren().addAll(event, signUpForEvent);
      signUpForEvent.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          System.out.println("Signed for an event");
          EventManager.joinEvent(library, getLoggedInUser(), a);
        }
      });
      takenEvents.getItems().addAll(oneEvent);
    }
  }
}
