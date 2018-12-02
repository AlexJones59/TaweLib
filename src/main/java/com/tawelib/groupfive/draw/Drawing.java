package com.tawelib.groupfive.draw;

import static javafx.scene.paint.Color.WHITE;

import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.util.FileSystemHelper;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


/**
 * The Drawing class creates a new window and allow to draw on its canvas and
 * save the result as a file. For drawing can be used point, line, rectangle and
 * oval shapes, also the closed chapes can be filled. The size of brush and
 * color can be changed. Native window resolution is 1920 x 1080p
 */
public class Drawing extends Application {

  public static final String IMAGE_FORMAT = "png";
  private static final int WINDOW_HEIGHT = 1080;
  private static final int WINDOW_WIDTH = 1920;
  private static final int CANVAS_SIDE = 800;
  private Canvas canvas;
  private GraphicsContext gc;
  private User user;

  /*An array of boolean which determines the shape selected and if
  filling option is enabled(point,line,rect,oval,fill)*/
  private boolean[] shapes = new boolean[]{true, false, false, false, false};

  private Button saveButton = new Button("SAVE");
  private Image imgOfCanvas;

  public static void main(String[] args) {
    launch(args);
  }

  public void startWithUserReference(User user) {
    this.user = user;
    start(new Stage());
  }

  @Override
  public void start(Stage stage) {
    stage.setTitle("Draw own image");
    stage.show();
    BorderPane root = new BorderPane();

    canvas = new Canvas(CANVAS_SIDE, CANVAS_SIDE);
    root.setCenter(canvas);

    gc = canvas.getGraphicsContext2D();
    gc.setFill(WHITE);
    gc.fillRect(0, 0, CANVAS_SIDE, CANVAS_SIDE);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setScene(scene);

    ColorChoice colors = new ColorChoice(gc);//Color choosing palette
    root.setRight(colors.getPane());

    ToolChoice tools = new ToolChoice(gc, shapes);//Tool/brush choosing buttons
    root.setLeft(tools.getPane());

    mouseDraw();

    tools.getPane().getChildren().add(saveButton);

    saveAsFile();
  }

  /**
   * The method uses MouseEvents to catch the users mouse pressed and draw on
   * the canvas shapes according to the selected ones.
   */
  private void mouseDraw() {
    canvas.setOnMousePressed((evt) -> {
      double startPosX = evt.getX();
      double startPosY = evt.getY();

      /*img Is an image of canvas before the mouse was pressed, needs
      for inserting on canvas is case of not final position of the
      shape.(To make effect of moving shape when mouse pressed) */

      SnapshotParameters param = new SnapshotParameters();
      Image img = canvas.snapshot(param, null);

      //If the mouse is moved when pressed
      canvas.setOnMouseDragged((evt1) -> {

        if (shapes[0]) { //If the shape is Point
          gc.fillOval(evt1.getX(), evt1.getY(), gc.getLineWidth(),
              gc.getLineWidth());
        } else {
          gc.drawImage(img, 0, 0);//refreshed the canvas in it previous state.
          double width = startPosX - evt1.getX();
          double height = startPosY - evt1.getY();
          double[] posXAndY = getCorrectCoords(startPosX, startPosY, width,
              height);

          if (shapes[1]) { //If the shape is Line
            gc.strokeLine(startPosX, startPosY, evt1.getX(), evt1.getY());
            //If the shape is unfilled Rectangle
          } else if (shapes[2] && !shapes[shapes.length - 1]) {
            gc.strokeRect(posXAndY[0], posXAndY[1], Math.abs(width),
                Math.abs(height));
            //If the shape is unfilled Oval
          } else if (shapes[3] && !shapes[shapes.length - 1]) {
            gc.strokeOval(posXAndY[0], posXAndY[1], Math.abs(width),
                Math.abs(height));
            //If the shape is filled Rectangle
          } else if (shapes[2] && shapes[shapes.length - 1]) {
            gc.fillRect(posXAndY[0], posXAndY[1], Math.abs(width),
                Math.abs(height));
            //If the shape is filled Oval
          } else if (shapes[3] && shapes[shapes.length - 1]) {
            gc.fillOval(posXAndY[0], posXAndY[1], Math.abs(width),
                Math.abs(height));
          }
        }
      });
    });
  }

  /**
   * The method takes the position of the mouse pressed and the dimensions of
   * the shape and return the adjusted x and y, as the shape can be drawn to the
   * any side.
   *
   * @param x x position when mouse pressed
   * @param y y position when mouse pressed
   * @return double[] where double[0] is x and double[1] is y
   */
  private double[] getCorrectCoords(double x, double y, double width,
      double height) {

    double[] posXAndY = new double[2];
    if (width <= 0 && height <= 0) {
      posXAndY[0] = x;
      posXAndY[1] = y;
    } else if (width <= 0) {
      posXAndY[0] = x;
      posXAndY[1] = y - height;
    } else if (height <= 0) {
      posXAndY[0] = x - width;
      posXAndY[1] = y;
    } else {
      posXAndY[0] = x - width;
      posXAndY[1] = y - height;
    }
    return posXAndY;
  }


  private void saveAsFile() {

    saveButton.setOnAction((evt) -> {

      File file = new File(FileSystemHelper.getUserProfilePicturePath(user));
      WritableImage imgOfCanvas = new WritableImage(CANVAS_SIDE, CANVAS_SIDE);
      canvas.snapshot(null, imgOfCanvas);
      BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imgOfCanvas, null);
      try {
        ImageIO.write(bufferedImage, IMAGE_FORMAT, file);
      } catch (IOException e) {
        System.out.println("File not found");
      }
      user.setProfileImage(bufferedImage);
    });
  }
}
