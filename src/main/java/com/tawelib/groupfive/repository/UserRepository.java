package com.tawelib.groupfive.repository;

public interface UserRepository<UserType> extends BaseRepository<UserType> {

  /**
   * This method returns a User with a given username.
   *
   * @param username Username
   * @return User
   */
  UserType authenticate(String username);

  /**
   * This method generates a username for a given user.
   *
   * @param user Customer or Librarian
   */
  void generateUsername(UserType user);
}
