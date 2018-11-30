package com.tawelib.groupfive.fxmlcontroller;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.util.SceneHelper;
import javafx.scene.Node;

public class AccountCreationController extends BaseFxmlController {

  public AccountCreationController() {
  }

  public void back() {
    SceneHelper.setUpScene(this, "UserDashboard");
  }
  /*
  @Override
  protected void refresh() {
    setGuiForUsers();

    if (isLibrarianLoggedIn()) {
      setGuiForLibrarians();
    } else {
      setGuiForCustomers();
    }
  }

  private void setGuiForUsers() {
    usernameTextField.setText(loggedInUser.getUsername());
    fullNameTextField.setText(loggedInUser.getFullName());
    //TODO: Format Address nicely.
    addressTextField.setText(loggedInUser.getAddress().toString());
  }

  private void setGuiForLibrarians() {
    staffNumberTextField.setText(
        String.format(
            "%d",
            ((Librarian) loggedInUser).getStaffNumber()
        )
    );
  }*/
}

