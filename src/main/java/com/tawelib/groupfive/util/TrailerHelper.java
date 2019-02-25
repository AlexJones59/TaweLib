package com.tawelib.groupfive.util;

import com.tawelib.groupfive.contentprovider.ContentProvider;
import com.tawelib.groupfive.contentprovider.FetchableTrailer;
import com.tawelib.groupfive.entity.Resource;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;

public class TrailerHelper {

  /**
   * Displays a trailer for a resource. TODO: also display static content
   *
   * @param resource Resource
   */
  public static void showTrailer(Resource resource) {
    if (false) {
      //TODO: check if resource has static content
    } else if (resource instanceof FetchableTrailer) {
      try {
        FetchableTrailer fetchableTrailerResource = (FetchableTrailer) resource;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Trailer for: " + resource.getTitle());
        WebView webView = new WebView();
        webView.setPrefWidth(1280);
        webView.setPrefHeight(720);
        alert.getDialogPane().setContent(webView);

        webView.getEngine().load(ContentProvider.fetchContent(fetchableTrailerResource));

        alert.showAndWait();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      AlertHelper.alert(AlertType.ERROR, "No trailer available.");
    }
  }
}
