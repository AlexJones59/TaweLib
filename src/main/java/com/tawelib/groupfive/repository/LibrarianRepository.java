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
