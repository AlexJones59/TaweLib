package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Resource;
import java.util.List;

public class ResourceRepository implements BaseRepository<Resource> {

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  @Override
  public List<Resource> getAll() {
    return null;
  }

  /**
   * This method persists an entity in the repository.
   *
   * @param entity Entity to be added
   */
  @Override
  public void add(Resource entity) {

  }
}
