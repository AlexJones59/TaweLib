package com.tawelib.groupfive.repository;

import java.util.List;

/**
 * This interface defines all attributes and operations every repository needs to implement.
 */
public interface BaseRepository<EntityT> {

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  List<EntityT> getAll();

  /**
   * This method persists an entity in the repository.
   *
   * @param entity Entity to be added
   */
  void add(EntityT entity);
}
