package com.tawelib.groupfive.util;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.fxmlcontroller.BaseFxmlController;
import com.tawelib.groupfive.fxmlcontroller.LoginController;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SceneHelper {

  private SceneHelper() {
    throw new IllegalStateException("Util class");
  }

  public static void setUpScene(Stage primaryStage, Library library,
      String sceneName)
      throws IOException {
    URL resourceLocation = ResourceHelper.getViewUrl(sceneName);

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(resourceLocation);
    loader.load();
    LoginController controller = loader.getController();

    /*
    These two references need to be passed around the application so that all
    controllers can access the necessary data and operations.
     */
    if (controller != null) {
      controller.setLibrary(library);
      controller.setPrimaryStage(primaryStage);
    }

    BorderPane root = loader.getRoot();
    Scene scene = new Scene(root);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void setUpScene(BaseFxmlController controller,
      String sceneName) {
    try {
      setUpScene(
          controller.getPrimaryStage(),
          controller.getLibrary(),
          sceneName
      );
    } catch (IOException e) {
      throw new Error("Could not set up scene.");
    }
  }
}
