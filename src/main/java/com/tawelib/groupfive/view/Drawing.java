package com.tawelib.groupfive.view;

//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sample application that demonstrates the use of JavaFX Canvas
 * @author Liam O'Reilly
 *
 */
public class Drawing extends Application {

  private static final int WINDOW_HEIGHT = 600;
  private static final int WINDOW_WIDTH = 800;
  private int brushSize = 5;
  private Canvas canvas;
  private GraphicsContext gc;

  private enum Shape {
    POINT,
    LINE,
    RECTANGLE,
    OVAL
  }

  double size = 5;
  Shape currentShape = Shape.POINT;

  Button chooseLine = new Button("Line");
  Button chooseRect = new Button("Rect");
  Button chooseOval = new Button("Oval");
  TextField sizeInput = new TextField("5");
  Button chooseSize = new Button("Choose size");

  @Override
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    stage.setTitle("Draw own image");
    stage.show();

    Pane buttonPane = new VBox();

    buttonPane.getChildren().add(chooseLine);
    buttonPane.getChildren().add(chooseRect);
    buttonPane.getChildren().add(chooseOval);
    buttonPane.getChildren().add(sizeInput);
    buttonPane.getChildren().add(chooseSize);

    canvas = new Canvas(600, 600);
    gc = canvas.getGraphicsContext2D();
    root.getChildren().add(canvas);
    root.setRight(buttonPane);

    Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    stage.setScene(scene);

    //OPERATIONS WITH TOOLS
    //CHANGE BUTTON STATE, ENABLE LINE DRAWING
    chooseLine.setOnAction((evt) -> {
      chooseLine();
    });
    chooseRect.setOnAction((evt) -> {
      chooseRectangle();
    });
    chooseOval.setOnAction((evt) -> {
      chooseOval();
    });
    //Changing the brush size
    gc.setLineWidth(size);
    chooseSize.setOnAction((evt) -> {
      size = Double.parseDouble(sizeInput.getText());
      gc.setLineWidth(size);
    });

    //IF MOUSE PRESSED
    canvas.setOnMousePressed((evt) -> {
      double startPosX = evt.getX();
      double startPosY = evt.getY();

      //when clicked, draw point
      canvas.setOnMouseReleased((evt1) -> {
        gc.strokeLine(evt.getX(), evt.getY(), evt.getX(), evt.getY());
      });

      //If the mouse is moved when pressed
      canvas.setOnMouseDragged((evt1) -> {

        //DRAW LINE
        if (currentShape == Shape.LINE) {
          canvas.setOnMouseReleased((evt2) -> {

            gc.strokeLine(startPosX, startPosY, evt1.getX(), evt1.getY());
          });
          //DRAW RECTANGLE
        } else if (currentShape == Shape.RECTANGLE) {
          canvas.setOnMouseReleased((evt2) -> {

            double width = startPosX - evt1.getX();
            double heigth = startPosY - evt1.getY();

            if (width <= 0 && heigth <= 0) {
              gc.strokeRect(startPosX, startPosY, Math.abs(width), Math.abs(heigth));
            } else if (width <= 0) {
              gc.strokeRect(startPosX, startPosY - heigth, Math.abs(width), Math.abs(heigth));
            } else if (heigth <= 0) {
              gc.strokeRect(startPosX - width, startPosY, Math.abs(width), Math.abs(heigth));
            } else {
              gc.strokeRect(startPosX - width, startPosY - heigth, Math.abs(width),
                  Math.abs(heigth));
            }
          });
          //DRAW OVAL
        } else if (currentShape == Shape.OVAL) {
          canvas.setOnMouseReleased((evt2) -> {

            double width = startPosX - evt1.getX();
            double heigth = startPosY - evt1.getY();

            if (width <= 0 && heigth <= 0) {
              gc.strokeOval(startPosX, startPosY, Math.abs(width), Math.abs(heigth));
            } else if (width <= 0) {
              gc.strokeOval(startPosX, startPosY - heigth, Math.abs(width), Math.abs(heigth));
            } else if (heigth <= 0) {
              gc.strokeOval(startPosX - width, startPosY, Math.abs(width), Math.abs(heigth));
            } else {
              gc.strokeOval(startPosX - width, startPosY - heigth, Math.abs(width),
                  Math.abs(heigth));
            }

          });
        }
        //DRAWS CUSTOM LINE
        else {
          gc.strokeLine(evt1.getX(), evt1.getY(), evt1.getX(), evt1.getY());
        }
      });

    });


  }

  private void unpressButton() {
    switch (currentShape) {
      case LINE:
        chooseLine.setOpacity(1);
        break;
      case RECTANGLE:
        chooseRect.setOpacity(1);
        break;
      case OVAL:
        chooseOval.setOpacity(1);
        break;
      default:
        break;
    }

  }

  private void chooseLine() {
    unpressButton();
    if (currentShape != Shape.LINE) {
      chooseLine.setOpacity(0.5);
      currentShape = Shape.LINE;
    } else {
      currentShape = Shape.POINT;
    }
  }

  private void chooseRectangle() {
    unpressButton();
    if (currentShape != Shape.RECTANGLE) {
      chooseRect.setOpacity(0.5);
      currentShape = Shape.RECTANGLE;
    } else {
      currentShape = Shape.POINT;
    }
  }

  private void chooseOval() {
    unpressButton();
    if (currentShape != Shape.OVAL) {
      chooseOval.setOpacity(0.5);
      currentShape = Shape.OVAL;
    } else {
      currentShape = Shape.POINT;
    }
  }
}