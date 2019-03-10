package com.tawelib.groupfive.util;

import com.tawelib.groupfive.contentprovider.ContentProvider;
import com.tawelib.groupfive.contentprovider.FetchableTrailer;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.exception.ContentProviderException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;

/**
 * Manages displaying trailers for Resources.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class TrailerHelper {

  private TrailerHelper() {
    throw new UnsupportedOperationException();
  }

  /**
   * Displays a trailer for a resource.
   *
   * @param resource Resource
   */
  public static void showTrailer(Resource resource) {
    try {
      if (false) {
        //TODO: check if resource has static content?
      } else if (resource instanceof FetchableTrailer) {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("Trailer for: " + resource.getTitle());

        WebView webView = new WebView();
        webView.setPrefWidth(1280);
        webView.setPrefHeight(720);
        alert.getDialogPane().setContent(webView);

        FetchableTrailer fetchableTrailerResource = (FetchableTrailer) resource;
        webView.getEngine().load(ContentProvider.fetchContent(fetchableTrailerResource));

        alert.showAndWait();

        //unloading the page
        webView.getEngine().load(null);
      } else {
        throw new ContentProviderException("No trailers for this type of media.");
      }
    } catch (Exception e) {
      AlertHelper.alert(AlertType.ERROR, e.getMessage());
    }
  }
}
