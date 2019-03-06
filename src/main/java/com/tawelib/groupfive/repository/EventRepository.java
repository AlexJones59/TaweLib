package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Event;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * EventRepository.java  the Event repository that handles Event data
 *
 * @author Rimantas Kazlauskas
 * @version 1.0
 */
public class EventRepository implements BaseRepository<Event> {

  private ArrayList<Event> events;

  private long lastEventId = 0;

  /**
   * Instantiates a new Event repository.
   */
  public EventRepository() {
    events = new ArrayList<>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Event> getAll() {
    return events;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Event event) {
    if (!events.contains(event)) {
      generateId(event);
      events.add(event);
    }
  }

  /**
   * Gets event.
   *
   * @param eventId the event ID
   * @return the event
   */
  public Event getEvent(String eventId) {
    for (Event i : events) {
      if (eventId.equals(i.getEventId())) {
        return i;
      }
    }
    throw new NoSuchElementException("event doesn't exist");
  }

  /**
   * Update event.
   *
   * @param event the event
   */
  public void updateEvent(Event event) {
    String eventId = event.getEventId();
    for (Event i : events) {
      if (eventId.equals(i.getEventId())) {
        events.set(events.indexOf(i), event);
        return;
      }
    }
  }

  /**
   * Gets upcoming events.
   *
   * @return the upcoming events
   */
  public ArrayList<Event> getUpcomingEvents() {
    ArrayList<Event> upcomingEvents = new ArrayList<>();
    for (Event i : events) {
      if (i.getEventDate().isAfter(LocalDateTime.now())) {
        upcomingEvents.add(i);
      }
    }
    return upcomingEvents;
  }

  /**
   * Gets past all past events.
   *
   * @return ArrayList of all past events
   */
  public ArrayList<Event> getPastEvents() {
    ArrayList<Event> pastEvents = new ArrayList<>();
    for (Event i : events) {
      if (i.getEventDate().isBefore(LocalDateTime.now())) {
        pastEvents.add(i);
      }
    }
    return pastEvents;
  }

  /**
   * Generates a unique id for events.
   *
   * @param Event event.
   */
  private void generateId(Event event) {
    String generatedEventId = String.format("C%d", lastEventId);

    try {
      Field idField = event.getClass().getDeclaredField("eventId");
      idField.setAccessible(true);
      idField.set(event, generatedEventId);
      idField.setAccessible(false);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    } finally {
      lastEventId++;
    }
  }


}


