package com.tawelib.groupfive.entity;

import javafx.scene.image.Image;

/**
 * Laptop.java Laptop class saves all information pertaining to a Laptop.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Laptop extends Resource {

  private String manufacturer;
  private String model;
  private String installedOperatingSystem;

  /**
   * Instantiates a new Laptop.
   *
   * @param manufacturer the manufacturer
   * @param model the model
   * @param installedOperatingSystem the installed operating system
   */
  public Laptop(String title, int year, Image thumbnailImage,
      String manufacturer, String model,
      String installedOperatingSystem) {
    super(title, year, thumbnailImage, ResourceType.LAPTOP);
    this.manufacturer = manufacturer;
    this.model = model;
    this.installedOperatingSystem = installedOperatingSystem;
  }

  /**
   * Gets manufacturer.
   *
   * @return the manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Sets manufacturer.
   *
   * @param manufacturer the manufacturer
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Gets model.
   *
   * @return the model
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets model.
   *
   * @param model the model
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Gets installed operating system.
   *
   * @return the installed operating system
   */
  public String getInstalledOperatingSystem() {
    return installedOperatingSystem;
  }

  /**
   * Sets installed operating system.
   *
   * @param installedOperatingSystem the installed operating system
   */
  public void setInstalledOperatingSystem(String installedOperatingSystem) {
    this.installedOperatingSystem = installedOperatingSystem;
  }
}
