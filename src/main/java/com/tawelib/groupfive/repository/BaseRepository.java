package com.tawelib.groupfive.repository;

import java.util.List;

/**
 * This interface defines all attributes and operations every repository needs to implement.
 */
public interface BaseRepository<EntityT> {

  /**
   * Returns all entities stored in the repository.
   *
   * @return List of entities
   */
  List<EntityT> getAll();

  /**
   * Persists an entity in the repository.
   *
   * @param entity Entity to be added
   */
  void add(EntityT entity);
}
