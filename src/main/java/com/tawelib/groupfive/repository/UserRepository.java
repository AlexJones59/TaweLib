package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.User;

public abstract class UserRepository extends BaseRepository {

  /**
   * This method returns a User with a given username.
   *
   * @param username Username
   * @return User
   */
  public abstract User authenticate(String username);

  /**
   * This method generates a username for a given user.
   *
   * @param user User
   */
  protected abstract void generateUsername(User user);
}
