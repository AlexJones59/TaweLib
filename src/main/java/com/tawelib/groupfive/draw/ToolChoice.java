package com.tawelib.groupfive.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * File Name - Tool Choice. Description - The class is an implementation of a
 * set of buttons that are used to choose the tool for drawing, filling option
 * and the size of the brush.
 *
 * @author Oskars Dervinis
 * @version 1.0
 */
public class ToolChoice {

  private static final String BORDER_COLOR_CSS_PRESSED =
      "-fx-border-color: #483d8b; -fx-border-width: 3px";
  private static final String BORDER_COLOR_CSS_UNPRESSED =
      "-fx-border-color: #00bfff; -fx-border-width: 3px";
  private static final double SQUARE_BUTTON_WIDTH = 80;
  private static final double THIN_BUTTON_HEIGHT = 40;

  private GraphicsContext gc;
  private VBox pane = new VBox(5);

  private boolean[] shapes;//Shapes as boolean(point,line,rect,oval,fill)
  private Button chooseLine = new Button("Line");
  private Button chooseRect = new Button("Rect");
  private Button chooseOval = new Button("Oval");
  private Button choosePoint = new Button("Point");
  private TextField sizeInput = new TextField("5");
  private Button chooseSize = new Button("Confirm");
  private Button isFilled = new Button("Fill");
  double size = 5;

  private Button[] shapeButtons = new Button[]{chooseLine, chooseRect,
      chooseOval, choosePoint, isFilled};

  private String currentTool = "Point";
  private Label toolTypeText = new Label(
      "The tool is: " + currentTool + "\nFill enabled: False");

  /**
   * Constructor of the pane of buttons.
   *
   * @param gc The graphic context used for drawing
   * @param shapes list of boolean representing shapes & filling option
   */
  ToolChoice(GraphicsContext gc, boolean[] shapes) {
    this.gc = gc;
    this.shapes = shapes;
    brushChanges();
    pane.getChildren()
        .addAll(chooseLine, chooseRect, chooseOval, choosePoint, sizeInput,
            chooseSize, isFilled, toolTypeText);
    styleButtons();
  }

  /**
   * Returns a pane of buttons.
   *
   * @return pane of buttons
   */
  public Pane getPane() {
    return pane;
  }

  /**
   * A method that sets all shapes in list of boolean stored in this class to
   * false before choosing another shape.
   */
  private void setAllShapesFalse() {
    //"-2" is because the last is filling option, not shape
    for (int i = 0; i <= shapes.length - 2; i++) {
      shapes[i] = false;
    }
  }

  /**
   * A method that decides what shape will be selected, size and filling option
   * according to the buttons.
   */
  private void brushChanges() {
    gc.setLineWidth(size);

    choosePoint.setOnAction((evt) -> {
      prepareButtons((Button) evt.getSource());
      shapes[0] = true;
      currentTool = "Point";
    });

    chooseLine.setOnAction((evt) -> {
      prepareButtons((Button) evt.getSource());
      shapes[1] = true;
      currentTool = "Line";
    });

    chooseRect.setOnAction((evt) -> {
      prepareButtons((Button) evt.getSource());
      shapes[2] = true;
      currentTool = "Rect";
    });

    chooseOval.setOnAction((evt) -> {
      prepareButtons((Button) evt.getSource());
      shapes[3] = true;
      currentTool = "Oval";
    });

    //Changing the brush size
    chooseSize.setOnAction((evt) -> {
      size = Double.parseDouble(sizeInput.getText());
      gc.setLineWidth(size);
    });

    isFilled.setOnAction((evt) -> {
      shapes[shapes.length - 1] = !shapes[shapes.length - 1];
      if (shapes[shapes.length - 1]) {
        isFilled.setStyle(BORDER_COLOR_CSS_PRESSED);
      } else {
        isFilled.setStyle(BORDER_COLOR_CSS_UNPRESSED);
      }
      toolTypeText.setText(
          "The tool is: " + currentTool + "\nFill enabled: " + shapes[
              shapes.length - 1]);
    });

  }

  /**
   * The method unpressed all the buttons for shapes before next shape will be
   * selected.
   */
  private void unpressShapeButtons() {
    for (int i = 0; i <= shapeButtons.length - 2; i++) {
      shapeButtons[i].setStyle(BORDER_COLOR_CSS_UNPRESSED);
    }
  }

  /**
   * The method gives the size and style to the burrons.
   */
  private void styleButtons() {

    sizeInput.setPrefSize(SQUARE_BUTTON_WIDTH, THIN_BUTTON_HEIGHT);
    sizeInput.setMaxSize(SQUARE_BUTTON_WIDTH, THIN_BUTTON_HEIGHT);
    chooseSize.setPrefSize(SQUARE_BUTTON_WIDTH, THIN_BUTTON_HEIGHT);
    chooseSize.setStyle(BORDER_COLOR_CSS_UNPRESSED);

    for (int i = 0; i <= shapeButtons.length - 1; i++) {
      shapeButtons[i].setPrefSize(SQUARE_BUTTON_WIDTH, SQUARE_BUTTON_WIDTH);
      shapeButtons[i].setStyle(BORDER_COLOR_CSS_UNPRESSED);
    }
  }

  /**
   * The method sets all the shapes to false and unpresses all the buttons,
   * changes the text displayed and gives the correct border to the button
   * pressed.
   *
   * @param b Is a button that is pressed as next shape.
   */
  private void prepareButtons(Button b) {
    setAllShapesFalse();
    unpressShapeButtons();
    toolTypeText.setText(
        "The tool is: " + currentTool + "\nFill enabled: " + shapes[
            shapes.length - 1]);
    b.setStyle(BORDER_COLOR_CSS_PRESSED);
  }
}
