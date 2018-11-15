module com.tawelib.groupfive {
  requires javafx.base;
  requires javafx.graphics;
  requires javafx.web;
  requires javafx.fxml;
  requires javafx.controls;
  requires javafx.media;

  exports com.tawelib.groupfive;
  exports com.tawelib.groupfive.view to javafx.graphics;
}