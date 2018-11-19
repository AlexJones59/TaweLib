package com.tawelib.groupfive.repository;

import java.util.List;

/**
 * This class is the superclass of all repository classes and acts as a template for storing
 * entities and performing basic operations on them.
 */
public abstract class BaseRepository {

  /**
   * This attribute holds all entities of the appropriate type.
   */
  protected List entities;

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  public abstract List getAll();

  /**
   * This method persists an object in the repository.
   *
   * @param o Object to be added
   */
  public abstract void add(Object o);
}
