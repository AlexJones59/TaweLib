package com.tawelib.groupfive.util;

import java.time.LocalTime;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

/**
 * The class creates the user-friendly interface for picking the time, used in event creation.
 *
 * @author Oskars Dervinis
 * @version 1.0
 */

public class TimePicker extends HBox {

  private ListView<Integer> hoursList;
  private ListView<Integer> minutesList;

  /**
   * The constructor builds the HBox consisting of 2 listviews for choosing the time.
   */
  public TimePicker() {
    minutesList = new ListView<>();
    hoursList = new ListView<>();

    for (int i = 0; i < 24; i++) {
      hoursList.getItems().add(i);
    }
    for (int i = 0; i < 60; i++) {
      minutesList.getItems().add(i);
    }

    this.getChildren().addAll(hoursList, minutesList);
  }

  /**
   * The method returns the time in LocalTime type by taking the info from focused cells;
   *
   * @return LocalTime time.
   */
  public LocalTime getTime() {

    int hour = hoursList.getFocusModel().getFocusedItem();
    int minute = minutesList.getFocusModel().getFocusedItem();

    return LocalTime.of(hour, minute);
  }

  public ListView getHoursListView() {
    return hoursList;
  }

  public ListView getMinutesListView() {
    return minutesList;
  }

  public void setMinutesList(ListView<Integer> minutesList) {
    this.minutesList = minutesList;
  }

  public void setHoursList(ListView<Integer> hoursList) {
    this.hoursList = hoursList;
  }
}
