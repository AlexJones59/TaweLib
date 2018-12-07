package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.draw.Drawing;
import com.tawelib.groupfive.util.AlertHelper;
import com.tawelib.groupfive.util.FileSystemHelper;
import com.tawelib.groupfive.util.SceneHelper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class ProfileImagePopUpMenuController extends BaseFxmlController {

  @FXML
  public ImageView imgOption1;

  @FXML
  public ImageView imgOption2;

  @FXML
  public ImageView imgOption3;

  @FXML
  public ImageView imgOption4;

  @FXML
  public ImageView imgOption5;

  @FXML
  public ImageView imgOption6;

  public ProfileImagePopUpMenuController() {
  }

  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    ImageView[] standartImg = new ImageView[6];
    standartImg[0] = imgOption1;
    standartImg[1] = imgOption2;
    standartImg[2] = imgOption3;
    standartImg[3] = imgOption4;
    standartImg[4] = imgOption5;
    standartImg[5] = imgOption6;
    for (int i = 0; i < standartImg.length; i++) {
      Image img = new Image(
          "file:" + "data/images/profile/default/default_" + i + ".png");
      if (img != null) {
        standartImg[i].setImage(img);
        standartImg[i].setOnMouseClicked((evt) -> {
          try {
            File file = new File(
                FileSystemHelper.getUserProfilePicturePath(loggedInUser));
            BufferedImage bufferedImage = SwingFXUtils
                .fromFXImage(((ImageView) evt.getSource()).getImage(), null);
            ImageIO.write(bufferedImage, "png", file);
          } catch (IOException e) {
            System.out.println("File not found");
          }
          back();
        });
      }
    }
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

  /**
   * The method creates the window with selecting the image to set as a profile
   * Called from pressing a buttom chooseFileImg, accepts only png format.
   */
  public void choosePicture() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("PNG", "*.png"));
    File file = fileChooser.showOpenDialog(getPrimaryStage());
    if (file != null) {
      try {
        File currProfImg = new File(
            FileSystemHelper.getUserProfilePicturePath(loggedInUser));
        BufferedImage tempImg = ImageIO.read(file);
        ImageIO.write(tempImg, "png", currProfImg);
      } catch (IOException e) {
        AlertHelper.alert(AlertType.ERROR, "Unable to load image.");
      }

      AlertHelper.alert(
          AlertType.INFORMATION,
          "Profile image set successfully."
      );
    }
  }
}
