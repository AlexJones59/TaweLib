package com.tawelib.groupfive.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ResourceHelper {

  private ResourceHelper() {
    throw new IllegalStateException("Utility class");
  }

  public static final String FXML_VIEWS_DIR =
      "/src/main/java/com/tawelib/groupfive/view/";

  public static URL getViewUrl(String resourceName)
      throws MalformedURLException {
    File file = new File(
        System.getProperty("user.dir")
            + FXML_VIEWS_DIR
            + resourceName
            + ".fxml"
    );

    return file.toURI().toURL();
  }
}
