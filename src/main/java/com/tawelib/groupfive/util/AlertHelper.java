package com.tawelib.groupfive.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHelper {
  public static void alert(AlertType alertType, String message) {
    Alert alert = new Alert(alertType);
    alert.setHeaderText(alertType.toString());
    alert.setContentText(message);
    alert.showAndWait();
  }
}
