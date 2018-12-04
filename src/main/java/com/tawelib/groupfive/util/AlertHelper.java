package com.tawelib.groupfive.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHelper {

  /**
   * Shows an alert message.
   *
   * @param alertType Alert type.
   * @param message Message.
   */
  public static void alert(AlertType alertType, String message) {
    Alert alert = new Alert(alertType);
    alert.setHeaderText(alertType.toString());
    alert.setContentText(message);
    alert.showAndWait();
  }
}
