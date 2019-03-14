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

  private ListView<String> hoursList;
  private ListView<String> minutesList;

  /**
   * The constructor builds the HBox consisting of 2 listviews for choosing the time.
   */
  public TimePicker() {
    minutesList = new ListView<>();
    hoursList = new ListView<>();

    for (int i = 0; i < 24; i++) {
      String hour;
      if (i < 10) {
        hour = "0" + i;
      } else {
        hour = "" + i;
      }
      hoursList.getItems().add(hour);
    }

    for (int i = 0; i < 60; i = i + 5) {
      String minute;
      if (i < 10) {
        minute = "0" + i;
      } else {
        minute = "" + i;
      }
      minutesList.getItems().add(minute);
    }

    this.getChildren().addAll(hoursList, minutesList);
  }

  /**
   * The method returns the time in LocalTime type by taking the info from focused cells.
   *
   * @return LocalTime time.
   */
  public LocalTime getTime() {

    int hour = Integer.parseInt(hoursList.getFocusModel().getFocusedItem());
    int minute = Integer.parseInt(minutesList.getFocusModel().getFocusedItem());

    return LocalTime.of(hour, minute);
  }

  public ListView getHoursListView() {
    return hoursList;
  }

  public ListView getMinutesListView() {
    return minutesList;
  }

  public void setMinutesList(ListView<String> minutesList) {
    this.minutesList = minutesList;
  }

  public void setHoursList(ListView<String> hoursList) {
    this.hoursList = hoursList;
  }
}
