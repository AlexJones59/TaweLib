package com.tawelib.groupfive.repository;

public interface UserRepository<UserT> extends BaseRepository<UserT> {

  /**
   * This method returns a User with a given username.
   *
   * @param username Username
   * @return User
   */
  UserT authenticate(String username);

  /**
   * This method generates a username for a given user.
   *
   * @param user Customer or Librarian
   */
  void generateUsername(UserT user);
}
