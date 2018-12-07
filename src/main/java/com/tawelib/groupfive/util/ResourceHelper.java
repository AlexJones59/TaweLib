package com.tawelib.groupfive.util;

import com.tawelib.groupfive.draw.Drawing;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.User;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.image.Image;

/**
 * Locates the project's resources (assets).
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class ResourceHelper {

  private ResourceHelper() {
    throw new IllegalStateException("Utility class");
  }

  public static final String FXML_VIEWS_DIR =
      "/src/main/java/com/tawelib/groupfive/view/";
  private static final String DEFAULT_IMAGE = "default_0";

  /**
   * Returns the URL of the requested view.
   *
   * @param viewName The name of the resource.
   * @return URL of the view.
   * @throws MalformedURLException When unable to parse the URL.
   */
  public static URL getViewUrl(String viewName)
      throws MalformedURLException {
    /*
    This approach was chosen because compatibility with Gradle and IntelliJ was
    necessary across all devices and operating systems.
     */
    File file = new File(
        System.getProperty("user.dir")
            + FXML_VIEWS_DIR
            + viewName
            + ".fxml"
    );

    return file.toURI().toURL();
  }

  /**
   * Returns a profile image for a given user.
   *
   * @param user User.
   * @return Profile image of the given user.
   */
  public static Image getUserProfileImage(User user) {
    String profilePicturePath = FileSystemHelper
        .getUserProfilePicturePath(user);
    File imageFile = new File(profilePicturePath);
    if (imageFile.isFile()) {
      return new Image("file:" + profilePicturePath);
    } else {
      String dir = "file:" + FileSystemHelper.IMAGES_SAVE_DIR
          + "profile/default/" + DEFAULT_IMAGE + "." + Drawing.IMAGE_FORMAT;
      return new Image(dir);
    }
  }

  /**
   * Returns an image for a given resource.
   *
   * @param resource Resource.
   * @return Resource image of a given resource.
   */
  public static Image getResourceImage(Resource resource) {
    String profilePicturePath = FileSystemHelper
        .getResourcePicturePath(resource);
    File imageFile = new File(profilePicturePath);

    if (imageFile.isFile()) {
      return new Image("file:" + profilePicturePath);
    } else {
      String resourceFilename = resource.getType().toString().toLowerCase();
      String dir = "file:" + FileSystemHelper.IMAGES_SAVE_DIR
          + "resource/default/" + resourceFilename + "." + Drawing.IMAGE_FORMAT;
      return new Image(dir);
    }
  }
}
