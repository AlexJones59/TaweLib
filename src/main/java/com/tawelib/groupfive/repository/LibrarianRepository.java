package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

public class LibrarianRepository implements UserRepository<Librarian> {

  private ArrayList<Librarian> librarians;

  public LibrarianRepository() {
    librarians = new ArrayList<>();
  }

  /**
   * This method returns a User with a given username.
   *
   * @param username Username
   * @return User
   */
  @Override
  public Librarian authenticate(String username) {
    for (Librarian librarian : librarians) {
      if (true) { //TODO: check if the username is the one we are looking for.
        return librarian;
      }
    }

    throw new AuthenticationException();
  }

  /**
   * This method generates a username for a given user.
   *
   * @param librarian Customer or Librarian
   */
  @Override
  public void generateUsername(Librarian librarian) {
    //TODO: set the librarian's username to a generated one making sure it's unique.
  }

  /**
   * This method returns all entities held by the class.
   *
   * @return List of entities
   */
  @Override
  public List<Librarian> getAll() {
    return librarians;
  }

  /**
   * This method persists an entity in the repository.
   *
   * @param librarian Entity to be added
   */
  @Override
  public void add(Librarian librarian) {
    librarians.add(librarian);
  }
}
