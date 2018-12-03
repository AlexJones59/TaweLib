package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.User;
import java.lang.reflect.Field;
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

  default void assignGeneratedId(User user, String username) {
    try {
      Field usernameField = user.getClass().getSuperclass()
          .getDeclaredField("username");
      usernameField.setAccessible(true);
      usernameField.set(user, username);
      usernameField.setAccessible(false);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }
  }
}
