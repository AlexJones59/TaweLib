package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Rating;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.util.SceneHelper;

import java.util.List;

/**
 * RatingController.java Controller for ratings user interface.
 *
 * @author Alex Jones
 * @version 1.0
 */
public class RatingController extends BaseFxmlController {

  private Resource selectedResource;
  private List<Rating> ratings;
  private CrudAction crudAction;

  @Override
  public void refresh() {
    this.ratings = getLibrary().getRatingRepository()
        .getResourcesRatings(selectedResource.getResourceId());

  }



  /**
   * Sets the target resource.
   *
   * @param resource the selected resource
   */
  public void setSelectedResource(Resource resource) {
    this.selectedResource = resource;
  }

  /**
   * Saves the crud action of the previous page.
   *
   * @param crudAction the crudAction of the ResourceCrud page
   */
  public void setCrudAction(CrudAction crudAction) {
    this.crudAction = crudAction;
  }

  /**
   * Returns to the previous screen.
   * TODO Edit so that 'back' returns to resource's crud page
   */
  @Override
  public void back() {
    ResourceCrudController newController =
        (ResourceCrudController) SceneHelper.setUpScene(
            this,
            "ResourceCrud");

    newController.setSelectedResource(selectedResource);
    newController.setCrudAction(crudAction);
    newController.refresh();
  }
}
