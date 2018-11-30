package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.User;

public abstract class BaseFxmlController {

  protected Library library;

  protected User loggedInUser;

  public Library getLibrary() {
    return library;
  }

  public void setLibrary(Library library) {
    this.library = library;
  }

  public User getLoggedInUser() {
    return loggedInUser;
  }

  public void setLoggedInUser(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }
}
