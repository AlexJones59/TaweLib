package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;

/**
 * This class wraps information about Resources that is shown in an FXML table.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class ResourceTableWrapper {

  /**
   * Constructs the resource wrapper.
   *
   * @param resource The resource to wrap around.
   */
  public ResourceTableWrapper(Resource resource) {
    this.resource = resource;
  }

  private Resource resource;

  /**
   * Returns the resource ID.
   *
   * @return The resource ID.
   */
  public String getId() {
    return resource.getResourceId();
  }

  /**
   * Returns the resource title.
   *
   * @return The resource title.
   */
  public String getTitle() {
    return resource.getTitle();
  }

  /**
   * Returns resource the year.
   *
   * @return The resource year.
   */
  public int getYear() {
    return resource.getYear();
  }

  /**
   * The resource type.
   *
   * @return The resource type.
   */
  public ResourceType getType() {
    return resource.getType();
  }

  /**
   * Returns the resource.
   *
   * @return The resource.
   */
  public Resource getResource() {
    return resource;
  }
}
