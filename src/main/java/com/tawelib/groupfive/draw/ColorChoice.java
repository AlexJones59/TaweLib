package com.tawelib.groupfive.draw;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * The class constructs a VBox of colors that can be selected for drawing.
 */
public class ColorChoice {

  private Button white = new Button();
  private Button black = new Button();
  private Button red = new Button();
  private Button green = new Button();
  private Button blue = new Button();
  private Button yellow = new Button();
  private Button brown = new Button();
  private Button pink = new Button();

  private Button[] buttons = {white, black, red, green, blue, yellow, brown,
      pink};
  private Pane colorPane;
  private GraphicsContext gc;
  private static final String BORDER_CSS_PRESSED = "-fx-border-color: #483d8b";
  private static final String BORDER_CSS_UNPRESSED = "-fx-border-color: #00bfff";

  private Button pressedColor;

  /**
   * The constructor of the Pane of colors.
   *
   * @param gc The graphic context used for drawing on canvas.
   */
  ColorChoice(GraphicsContext gc) {

    colorPane = new VBox(8);
    for (int i = 0; i <= buttons.length - 1; i++) {
      colorPane.getChildren().add(buttons[i]);
    }
    this.gc = gc;
    chooseColor();
    paintButtons();

  }

  /**
   * The pane of colors.
   *
   * @return pane of colors
   */
  public Pane getPane() {
    return colorPane;
  }

  /**
   * The method paints all the buttons in their color and defines a style for them.
   */
  private void paintButtons() {

    String borderDescription =
        "; " + BORDER_CSS_UNPRESSED + "; -fx-border-width: 3px;";
    white.setStyle("-fx-background-color: #ffffff");
    black.setStyle("-fx-background-color: #000000");
    red.setStyle("-fx-background-color: #ff0000");
    green.setStyle("-fx-background-color: #00ff00");
    blue.setStyle("-fx-background-color: #0000ff");
    yellow.setStyle("-fx-background-color: #ffff00");
    brown.setStyle("-fx-background-color: #a52a2a");
    pink.setStyle("-fx-background-color: #ffc0cb");

    for (int i = 0; i <= buttons.length - 1; i++) {
      buttons[i].setPrefSize(60, 60);
      buttons[i].setStyle(buttons[i].getStyle() + borderDescription);
    }
  }

  /**
   * The method is called then one of the colored buttons is clicked and in the constructor. It
   * gives a color to Graphic context according to the button pressed  and changes the style of this
   * button.
   */
  private void chooseColor() {

    gc.setFill(Color.BLACK);
    for (int i = 0; i <= buttons.length - 1; i++) {
      buttons[i].setOnAction((evt) -> {
        paintButtons();
        pressedColor = (Button) evt.getSource();
        gc.setStroke(Color.web(pressedColor.getStyle().subSequence(23, 29)
            .toString()));
        gc.setFill(Color.web(pressedColor.getStyle().subSequence(23, 29)
            .toString()));
        pressedColor.setStyle(pressedColor.getStyle() + BORDER_CSS_PRESSED);
      });
    }

  }
}
