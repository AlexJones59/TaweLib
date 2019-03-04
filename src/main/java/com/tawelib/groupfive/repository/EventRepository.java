package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EventRepository implements BaseRepository<Event> {

  private ArrayList<Event> events;

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
      events.add(event);
    }
  }

  public Event getEvent(String eventID) {
    for (Event i : events) {
      if (eventID == i.getEventId()) {
        return i;
      }
    }
    throw new NoSuchElementException("event doesn't exist");
  }

  public void updateEvent(Event event) {
    String eventID = event.getEventId();
    for (Event i : events) {
      if (eventID == i.getEventId()) {
        events.set(events.indexOf(i), event);
        return;
      }
    }
  }

  public ArrayList<Event> getUpcomingEvents() {
    ArrayList<Event> upcomingEvents = new ArrayList<Event>();

    for (Event i : events) {
      if (i.getEventDate().isAfter(LocalDateTime.now())) {
        upcomingEvents.add(i);
      }
    }
    return upcomingEvents;
  }


}


