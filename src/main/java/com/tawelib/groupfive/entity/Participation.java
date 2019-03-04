package com.tawelib.groupfive.entity;

import java.io.Serializable;

/**
 * Participation.java Participation class is a class that ties up the user attending the event to an
 * event
 *
 * @author Rimantas Kazlauskas
 * @version 1.0
 */
public class Participation implements Serializable {

  private String username;
  private String eventId;

  /**
   * Instantiates a new Participation.
   *
   * @param username the username
   * @param eventId the event id
   */
  public Participation(User user, Event event) {
    this.username = user.getUsername();
    this.eventId = event.getEventId();
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets event ID.
   *
   * @return the event ID
   */
  public String getEventId() {
    return eventId;
  }

}
