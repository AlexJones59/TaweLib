package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.User;
import java.util.Collection;

/**
 * Defines all operations user repositories needs to implement.
 *
 * @param <T> Entity class.
 * @author Petr Hoffmann
 * @version 0.2
 */
public interface UserRepository<T> extends BaseRepository<T> {

  /**
   * Authenticates a user and returns the User subclass. Throws an exception
   * when the user isn't found.
   *
   * @param username Username.
   * @return T User subclass.
   */
  T authenticate(String username);

  Collection<? extends User> search(String query);
}
