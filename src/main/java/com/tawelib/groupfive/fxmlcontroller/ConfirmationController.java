package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

//TODO link to screen

/**
 * Controls the confirmation screen
 *
 * @author Dearbhla Jackson
 * @version 0.1
 */
public class ConfirmationController extends BaseFxmlController{

  @FXML
  public Button btnYes;

  @FXML
  public Button btnNo;

  /**
   * Instantiates a new Confirmation controller class
   */
  public ConfirmationController(){
  }

  /**
   * Clicking no will take you to the previous screen
   */
  public void clickNo() {
    SceneHelper.setUpScene(this, "BrowseResource");
  }

  /**
   * Clicking yes will take you to the previous screen but also add your
   * resource the list of copies a user has taken out
   */
  public void clickYes() {
    SceneHelper.setUpScene(this, "BrowseResource");
    //TODO add resource to copies borrowed -ask Shree controller method for this

  }
}
