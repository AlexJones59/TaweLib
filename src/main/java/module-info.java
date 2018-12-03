module com.tawelib.groupfive {
  requires javafx.base;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.web;
  requires javafx.swing;
  requires javafx.media;

  exports com.tawelib.groupfive;
  exports com.tawelib.groupfive.view to javafx.graphics;
  exports com.tawelib.groupfive.draw to javafx.graphics;
  opens com.tawelib.groupfive.fxmlcontroller to javafx.fxml;
  opens com.tawelib.groupfive.entity;
}