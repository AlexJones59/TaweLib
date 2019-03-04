package com.tawelib.groupfive.manager;

import com.tawelib.groupfive.entity.Event;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Participation;
import com.tawelib.groupfive.entity.User;

import java.util.ArrayList;

/**
 * EventManager.java The Event Manager class handles data
 * flow between the Event Repository, Participation Repository and the GUI interfaces.
 *
 * @author Rimantas Kazlauskas
 * @version 1.0
 */
public class EventManager {

  /**
   * Add event.
   *
   * @param library the library
   * @param event the event
   */
  public static void addEvent(Library library, Event event) {
    library.getEventRepository().add(event);
  }

  /**
   * Join event.
   *
   * @param library the library
   * @param user the user
   * @param event the event
   */
  public static void joinEvent(Library library, User user, Event event) {
    Participation participation = new Participation(user, event);
    library.getParticipationRepository().add(participation);
  }

  /**
   * Checks whatever the user has already joined a given event.
   *
   * @param library the library
   * @param user the user
   * @param event the event
   * @return True if joined, false otherwise
   */
  public static boolean hasJoined(Library library, User user, Event event) {
    return library.getParticipationRepository().doesParticipate(event, user);
  }

  /**
   * Checks whatever a given event is full
   *
   * @param library the library
   * @param event the event
   * @return True if full, false otherwise
   */
  public boolean eventFull(Library library, Event event) {

    int participating = library.getParticipationRepository().getNumberOfParticipants(event);
    return participating == event.getCapacity();

  }

}
