module com.tawelib.groupfive {
  requires javafx.base;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires javafx.web;
  requires javafx.swing;
  requires javafx.media;
 // requires kotlin.stdlib;
  opens com.tawelib.groupfive;
  opens com.tawelib.groupfive.draw;
  opens com.tawelib.groupfive.entity;
  opens com.tawelib.groupfive.exception;
  opens com.tawelib.groupfive.fxmlcontroller;
  opens com.tawelib.groupfive.manager;
  opens com.tawelib.groupfive.repository;
  opens com.tawelib.groupfive.tablewrapper;
  opens com.tawelib.groupfive.testdata;
  opens com.tawelib.groupfive.util;
  opens com.tawelib.groupfive.view;
}