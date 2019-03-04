package com.tawelib.groupfive.entity;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Event implements Serializable {

  private String eventId;
  private String eventName;
  private LocalDateTime eventDate;
  private int capacity;
  private String description;

  public Event(String eventId, String eventName, LocalDateTime eventDate,
      int capacity, String description) {
    this.eventId = eventId;
    this.eventName = eventName;
    this.eventDate = eventDate;
    this.capacity = capacity;
    this.description = description;
  }

  public String getEventId() {
    return eventId;
  }

  public String getEventName() {
    return eventName;
  }

  public LocalDateTime getEventDate() {
    return eventDate;
  }

  public int getCapacity() {
    return capacity;
  }

  public String getDescription() {
    return description;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public void setEventDate(LocalDateTime date) {
    this.eventDate = date;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
