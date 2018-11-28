package com.tawelib.groupfive.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Defines all attributes and operations every repository needs to implement.
 *
 * @param <T> Entity class.
 * @author Petr Hoffmann
 * @version 0.1
 */
public interface BaseRepository<T> extends Serializable {

  /**
   * Returns all entities stored in the repository.
   *
   * @return List of entities.
   */
  List<T> getAll();

  /**
   * Persists an entity in the repository.
   *
   * @param entity Entity to be added
   */
  void add(T entity);

  /**
   * Gets specific instance of Entity.
   *
   * @return the specific
   */
  T getSpecific(String entityId);
}
