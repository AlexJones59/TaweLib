package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.util.SceneHelper;

public class AccountCreationController extends BaseFxmlController {

  public AccountCreationController() {
  }


  /**
   * Sets the dynamic fields.
   */
  @Override
  public void refresh() {
    //TODO: Populate the table, etc.
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
}

