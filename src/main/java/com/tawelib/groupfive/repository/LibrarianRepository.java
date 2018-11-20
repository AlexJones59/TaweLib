package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.exception.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

public class LibrarianRepository implements UserRepository<Librarian> {

  private ArrayList<Librarian> librarians;

  private static long librarianNumber = 0;

  private static String LibrarianPrefix = "LB";

  public LibrarianRepository() {
    librarians = new ArrayList<>();
  }

  @Override
  public Librarian authenticate(String username) {
    for (Librarian librarian : librarians) {
      if (true) { //TODO: check if the username is the one we are looking for.
        librarians.contains(username);
        return librarian;
      }
    }

    throw new AuthenticationException();
  }

  @Override
  public void generateUsername(Librarian librarian) {
    //TODO: set the librarian's username to a generated one making sure it's unique.
    String librarianId = String.valueOf(LibrarianPrefix + librarianNumber++);
  }

  @Override
  public List<Librarian> getAll() {
    return librarians;
  }

  @Override
  public void add(Librarian librarian) {
    librarians.add(librarian);
  }
}