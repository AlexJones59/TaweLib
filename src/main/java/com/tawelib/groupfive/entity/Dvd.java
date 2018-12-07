package com.tawelib.groupfive.entity;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 * Dvd.java Dvd class saves all information pertaining to a Dvd.
 *
 * @author Shree Desai
 * @version 1.0
 */
public class Dvd extends Resource {

  private String director;
  private int runtime;
  private ArrayList<String> languages;
  private ArrayList<String> subtitleLanguages;

  /**
   * Instantiates a new Dvd.
   *
   * @param title the title
   * @param year the year
   * @param thumbnailImage the thumbnail image
   * @param director the director
   * @param runtime the runtime
   * @param languages languages
   * @param subtitleLanguages subtitle languages
   */
  public Dvd(String title, int year, Image thumbnailImage,
      String director, int runtime, ArrayList<String> languages,
      ArrayList<String> subtitleLanguages) {
    super(title, year, thumbnailImage, ResourceType.DVD);
    this.director = director;
    this.runtime = runtime;
    this.setLanguages(languages);
    this.setSubtitleLanguages(subtitleLanguages);
  }

  /**
   * Gets director.
   *
   * @return the director
   */
  public String getDirector() {
    return director;
  }

  /**
   * Sets director.
   *
   * @param director the director
   */
  public void setDirector(String director) {
    this.director = director;
  }

  /**
   * Gets runtime.
   *
   * @return the runtime
   */
  public int getRuntime() {
    return runtime;
  }

  /**
   * Sets runtime.
   *
   * @param runtime the runtime
   */
  public void setRuntime(int runtime) {
    this.runtime = runtime;
  }

  /**
   * Gets languages the dvd is available in.
   *
   * @return the languages
   */
  public ArrayList<String> getLanguages() {
    return languages;
  }

  /**
   * Adds languages that the dvd is available in.
   *
   * @param languages the languages
   */
  public void setLanguages(ArrayList<String> languages) {
    this.languages = languages;
  }

  /**
   * Gets subtitle languages.
   *
   * @return the subtitle languages
   */
  public ArrayList<String> getSubtitleLanguages() {
    return subtitleLanguages;
  }

  /**
   * Adds subtitle languages.
   *
   * @param subtitleLanguages the languages
   */
  public void setSubtitleLanguages(ArrayList<String> subtitleLanguages) {
    this.subtitleLanguages = subtitleLanguages;
  }
}
