package com.tawelib.groupfive.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Event.java Event class that contains all information about a particular event
 *
 * @author Rimantas Kazlauskas
 * @version 1.0
 */
public class Event implements Serializable {

  private String eventId;
  private String eventName;
  private LocalDateTime eventDate;
  private int capacity;
  private String description;

  /**
   * Instantiates a new event.
   *
   * @param eventId the event ID
   * @param eventName the name of the event
   * @param eventDate the date on which event will happen
   * @param capacity the amount of people that can attend
   * @param description a short description of the upcoming event
   */
  public Event(String eventId, String eventName, LocalDateTime eventDate,
      int capacity, String description) {
    this.eventId = eventId;
    this.eventName = eventName;
    this.eventDate = eventDate;
    this.capacity = capacity;
    this.description = description;
  }

  /**
   * Gets event ID.
   *
   * @return the event ID
   */
  public String getEventId() {
    return eventId;
  }

  /**
   * Gets event name.
   *
   * @return the event name
   */
  public String getEventName() {
    return eventName;
  }

  /**
   * Gets event date.
   *
   * @return the event date
   */
  public LocalDateTime getEventDate() {
    return eventDate;
  }

  /**
   * Gets capacity.
   *
   * @return the capacity
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets event name.
   *
   * @param eventName the event name
   */
  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  /**
   * Sets event date.
   *
   * @param date the date
   */
  public void setEventDate(LocalDateTime date) {
    this.eventDate = date;
  }

  /**
   * Sets capacity.
   *
   * @param capacity the capacity
   */
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  /**
   * Sets description.
   *
   * @param description the description
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
