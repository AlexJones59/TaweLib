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

  private User user;
  private Event event;

  /**
   * Instantiates a new Participation.
   *
   * @param user the user
   * @param event the event
   */
  public Participation(User user, Event event) {
    this.user = user;
    this.event = event;
  }

  /**
   * Gets username.
   *
   * @return the username
   */
  public User getUser() {
    return user;
  }

  /**
   * Gets event ID.
   *
   * @return the event ID
   */
  public Event getEvent() {
    return event;
  }

}
