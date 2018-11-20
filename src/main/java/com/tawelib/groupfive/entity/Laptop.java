package com.tawelib.groupfive.entity;

/**
 * File name: Laptop.java Laptop class saves all  info pertaining to a Laptop resource.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Laptop {

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
  public Laptop(String manufacturer, String model, String installedOperatingSystem) {
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
