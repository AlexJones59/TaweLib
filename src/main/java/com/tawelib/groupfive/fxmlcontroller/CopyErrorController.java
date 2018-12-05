package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;

//TODO link to screen

/**
 * Controls the copy error screen
 *
 * @author Dearbhla Jackson
 * @version 0.1
 */
public class CopyErrorController extends BaseFxmlController{

  @FXML
  public URL location;

  @FXML
  public Button btnOk;

  @FXML
  public Button btnRequestCopy;

  /**
   * Instantiates a new Copy error controller.
   */
  public CopyErrorController(){
  }

  /**
   * Clicking ok will close the pop up and return to previous screen
   */
  public void clickOK() {
    SceneHelper.setUpScene(this, "BrowseResource");
  }
}
