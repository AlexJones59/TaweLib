package com.tawelib.groupfive.entity;

import java.util.ArrayList;

/**
 * File name: Resource.java Dvd class saves all info pertaining to a Dvd.
 *
 * @author Shree Desai
 * @version 0.2
 */
public class Dvd {

  private String director;
  private int runtime;
  private ArrayList <String> languages;
  private ArrayList <String> subtitleLanguages;

  /**
   * Instantiates a new Dvd.
   *
   * @param director the director
   * @param runtime the runtime
   */
  public Dvd(String director, int runtime) {
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
   * Sets languages.
   *
   * @param languages the languages
   */
  public void setLanguages(ArrayList<String> languages) {
    this.languages = languages;
  }

  /**
   * Adds a language.
   *
   * @param language the language
   */
  public void addLanguage(String language) {
    this.languages.add(language);
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
   * Sets subtitle languages.
   *
   * @param subtitleLanguages the subtitle languages
   */
  public void setSubtitleLanguages(ArrayList<String> subtitleLanguages) {
    this.subtitleLanguages = subtitleLanguages;
  }

  /**
   * Add a subtitle language.
   *
   * @param subtitleLanguage the subtitle language
   */
  public void addSubtitleLanguage(String subtitleLanguage) {
    this.languages.add(subtitleLanguage);
  }
}
