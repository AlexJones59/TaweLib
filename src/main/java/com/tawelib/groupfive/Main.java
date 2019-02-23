package com.tawelib.groupfive;

import com.tawelib.groupfive.contentprovider.ContentProvider;
import com.tawelib.groupfive.util.ActivationHelper;
import com.tawelib.groupfive.view.Library;

/**
 * This class is the entry point to the application. It checks whether the
 * application is activated. In case it is it launches the Tawe-Lib application.
 * In case it is not it announces the fact and terminates.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class Main {

  public static final boolean DEV_MODE = true;

  /**
   * This method checks whether the application is activated. In case it is, it
   * launches the Tawe-Lib application. In case it is not, it announces the fact
   * and terminates.
   *
   * @param args Arguments
   */
  public static void main(String[] args) {
    //TODO: remove the dev code
    try {
      System.out.println(
          ContentProvider.fetchContent("avatar (2009)")
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.exit(0);

    if (ActivationHelper.isActivated()) {
      Library.main(args);
    } else {
      Library.launchAnnounce("Software is not activated.");
    }
  }
}
