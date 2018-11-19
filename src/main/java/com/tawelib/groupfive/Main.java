package com.tawelib.groupfive;

import com.tawelib.groupfive.util.ActivationHelper;
import com.tawelib.groupfive.view.Library;

/**
 * This class is the entry point to the application. It checks whether the application is activated.
 * In case it is it launches the Tawe-Lib application. In case it is not it launches the activation
 * tool.
 *
 * @author Petr Hoffmann
 * @version 0.1
 */
public class Main {

  /**
   * This method checks whether the application is activated. In case it is, it launches the
   * Tawe-Lib application. In case it is not, it launches the activation tool.
   *
   * @param args Arguments
   */
  public static void main(String[] args) {
    if (ActivationHelper.isActivated()) {
      Library.main(args);
    } else {
      //TODO: launch the activation tool
      System.out.println("TODO: launch the activation tool");
    }
  }
}
