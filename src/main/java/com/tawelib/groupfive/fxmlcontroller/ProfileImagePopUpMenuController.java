package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.draw.Drawing;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.application.Platform;

public class ProfileImagePopUpMenuController extends BaseFxmlController {


  public ProfileImagePopUpMenuController() {
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    //TODO: Populate the table, etc.
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }

  /**
   * Launches an app for drawing custom images.
   */
  public void drawYourOwn() {
    Platform.runLater(new Runnable() {
      public void run() {
        Drawing drawingApp = new Drawing();
        drawingApp.startWithUserReference(loggedInUser);
      }
    });

    //TODO: Set up locks for asynchronous refresh.
  }
}
