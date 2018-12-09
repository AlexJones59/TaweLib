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
 * File Name - Drawing.java
 * Description - The Drawing class creates a new window
 * and allow to draw on its canvas and save the result as a file. For drawing
 * can be used point, line, rectangle and oval shapes, also the closed chapes
 * can be filled. The size of brush and color can be changed. Native window
 * resolution is 1920 x 1080p.
 *
 * @author - Oskars Dervinis, Petr Hoffman
 * @version - 1.0
 */
public class Drawing extends Application {

  /**
   * The constant IMAGE_FORMAT.
   */
  public static final String IMAGE_FORMAT = "png";
  private static final int WINDOW_HEIGHT = 1080;
  private static final int WINDOW_WIDTH = 1920;
  private static final int CANVAS_SIDE = 800;
  private Canvas canvas;
  private GraphicsContext graphicsContext;
  private User user;

  /*An array of boolean which determines the shape selected and if
  filling option is enabled(point,line,rect,oval,fill).*/
  private boolean[] shapes = new boolean[]{true, false, false, false, false};

  private Button saveButton = new Button("SAVE");

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * This method is used to launch this program.
   *
   * @param user the User whose Profile Image is being drawn.
   */
  public void startWithUserReference(User user) {
    this.user = user;
    start(new Stage());
  }

  /**
   * Opens a window, and sets all tool choices and colour choices to default
   * values.
   */
  public void start(Stage stage) {
    stage.setTitle("Draw own image");
    stage.show();
    BorderPane root = new BorderPane();

    // Sets Canvas of window to right position,
    canvas = new Canvas(CANVAS_SIDE, CANVAS_SIDE);
    root.setCenter(canvas);

    graphicsContext = canvas.getGraphicsContext2D();
    graphicsContext.setFill(WHITE);
    graphicsContext.fillRect(0, 0, CANVAS_SIDE, CANVAS_SIDE);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setScene(scene);

    //Colour choosing palette is shown on right
    ColorChoice colors = new ColorChoice(graphicsContext);
    root.setRight(colors.getPane());

    //Tool/brush choosing buttons are shown on left.
    ToolChoice tools = new ToolChoice(graphicsContext, shapes);
    root.setLeft(tools.getPane());

    mouseDraw();

    tools.getPane().getChildren().add(saveButton);

    saveAsFile();
  }

  /**
   * The method catches User's mouse actions and displays the resulting shapes
   * on the canvas based on selected options.
   */
  private void mouseDraw() {
    canvas.setOnMousePressed(evt -> {
      double startPosX = evt.getX();
      double startPosY = evt.getY();

      /*Image of canvas is taken on the mouse click event to show the start
      position before mouse is dragged. */
      SnapshotParameters param = new SnapshotParameters();
      Image img = canvas.snapshot(param, null);

      //If the mouse is moved during click.
      canvas.setOnMouseDragged(evt1 -> {

        /*  If the User has chosen "Point", it uses dots to show the particle
            trace allowing you to draw on the canvas. */
        if (shapes[0]) {
          graphicsContext.fillOval(evt1.getX(), evt1.getY(),
              graphicsContext.getLineWidth(), graphicsContext.getLineWidth());
        } else {
          /*  If 'Point'is not chosen, it doesn't automatically keep the first
              position displayed but instead refreshes the canvas in it's
              previous state.*/
          graphicsContext.drawImage(img, 0, 0);
          double width = startPosX - evt1.getX();
          double height = startPosY - evt1.getY();
          double[] posXAndY = getCorrectCoords(startPosX, startPosY, width,
              height);

          if (shapes[1]) {
            // 'Line' is chosen.
            graphicsContext
                .strokeLine(startPosX, startPosY, evt1.getX(), evt1.getY());

          } else if (shapes[2] && !shapes[shapes.length - 1]) {
            //Shape is unfilled Rectangle.
            graphicsContext
                .strokeRect(posXAndY[0], posXAndY[1], Math.abs(width),
                    Math.abs(height));
          } else if (shapes[3] && !shapes[shapes.length - 1]) {
            //Shape is unfilled Oval.
            graphicsContext
                .strokeOval(posXAndY[0], posXAndY[1], Math.abs(width),
                    Math.abs(height));

          } else if (shapes[2] && shapes[shapes.length - 1]) {
            //Shape is filled Rectangle.
            graphicsContext.fillRect(posXAndY[0], posXAndY[1], Math.abs(width),
                Math.abs(height));

          } else if (shapes[3] && shapes[shapes.length - 1]) {
            //Shape is filled Oval.
            graphicsContext.fillOval(posXAndY[0], posXAndY[1], Math.abs(width),
                Math.abs(height));
          }
        }
      });
    });
  }

  /**
   * The method takes the position of the mouse pressed and the dimensions of
   * the shape and returns the adjusted x and y, as the shape can be drawn to
   * any side.
   *
   * @param x x position when mouse pressed
   * @param y y position when mouse pressed
   * @param width Width.
   * @param height Height.
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


  /**
   * Saves new drawing to file.
   */
  private void saveAsFile() {

    saveButton.setOnAction(evt -> {

      File file = new File(FileSystemHelper.getUserProfilePicturePath(user));
      WritableImage imgOfCanvas = new WritableImage(CANVAS_SIDE, CANVAS_SIDE);
      canvas.snapshot(null, imgOfCanvas);
      BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imgOfCanvas, null);
      try {
        ImageIO.write(bufferedImage, IMAGE_FORMAT, file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }
}
