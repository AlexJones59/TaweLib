package com.tawelib.groupfive.entity;

import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 * File name: Resource.java Dvd class saves all info pertaining to a Dvd.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Dvd extends Resource {

  private String director;
  private int runtime;
  private ArrayList<String> languages;
  private ArrayList<String> subtitleLanguages;

  /**
   * Instantiates a new Dvd.
   *
   * @param director the director
   * @param runtime the runtime
   */
  public Dvd(String title, int year, Image thumbnailImage, ResourceType type,
      String director, int runtime) {
    super(title, year, thumbnailImage, type);
    this.director = director;
    this.runtime = runtime;
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
   * Gets languages.
   *
   * @return the languages
   */
  public ArrayList<String> getLanguages() {
    return languages;
  }

  /**
   * Adds languages.
   *
   * @param languages the languages
   */
  public void addLanguage(ArrayList<String> languages) {
    //TODO: Write logic for this with array checking.
    //this.languages.add(languages);
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
  public void addSubtitleLanguages(ArrayList<String> subtitleLanguages) {
    //TODO: Write logic for this with array checking.
    //this.subtitleLanguages.add(subtitleLanguages);
  }
}
