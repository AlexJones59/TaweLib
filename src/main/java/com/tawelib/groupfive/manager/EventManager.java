package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Participation;
import com.tawelib.groupfive.entity.User;

import java.util.ArrayList;

public class EventManager {

  public static void addEvent(Library library, Event event) {
    library.getEventRepository().add(event);
  }

  public static void joinEvent(Library library, User user, Event event) {
    Participation participation = new Participation(user, event);
    library.getParticipationRepository().add(participation);
  }

  public static boolean hasJoined(Library library, User user, Event event) {
    return library.getParticipationRepository().doesParticipate(event, user);
  }

  public boolean eventFull(Library library, Event event) {

    int participating = library.getParticipationRepository().getNumberOfParticipants(event);
    return participating == event.getCapacity();

  }

}
