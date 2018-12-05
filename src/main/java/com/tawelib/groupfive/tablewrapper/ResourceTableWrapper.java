package com.tawelib.groupfive.tablewrapper;

import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;

/**
 * This class wraps information about Resources that is shown in an FXML table.
 */
public class ResourceTableWrapper {

  public ResourceTableWrapper(Resource resource) {
    this.resource = resource;
  }

  private Resource resource;

  public String getId() {
    return resource.getResourceId();
  }

  public String getTitle() {
    return resource.getTitle();
  }

  public int getYear() {
    return resource.getYear();
  }

  public ResourceType getType() {
    return resource.getType();
  }

  public Resource getResource() {
    return resource;
  }
}
