module com.tawelib.groupfive {
  requires javafx.base;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.web;
  requires javafx.swing;
  requires javafx.media;

  opens com.tawelib.groupfive;
  opens com.tawelib.groupfive.view;
  opens com.tawelib.groupfive.draw;
  opens com.tawelib.groupfive.fxmlcontroller;
  opens com.tawelib.groupfive.entity;
  opens com.tawelib.groupfive.tablewrapper;
}