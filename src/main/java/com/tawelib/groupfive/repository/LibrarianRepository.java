package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.exception.AuthenticationException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * File Name - LibrarianRepository.java The Librarian repository class handles librarian details.
 *
 * @author Created by Themis
 * @version 0.2
 */
public class LibrarianRepository implements UserRepository<Librarian> {

  private static ArrayList<Librarian> librarians;

  private static long librarianNumber = 0;

  private static final String LIBRARIAN_PREFIX = "LB";

  private static Hashtable<String, Librarian> LibrarianTable = new Hashtable<String, Librarian>();

  /**
   * Instantiates a new Librarian repository.
   */
  public LibrarianRepository() {
    librarians = new ArrayList<>();
  }

  /**
   * Gets specific.
   *
   * @param librarianUsername the librarian username
   * @return the specific
   */
  public Librarian getSpecific(String librarianUsername) {
    return null;
  }

  /**
   * Checks if a librarian is in the list by its username.
   *
   * @param username Username.
   * @return the librarian
   */
  @Override
  public Librarian authenticate(String username) {
    for (Librarian librarian : librarians) {
      if (librarian.getUsername()
          == username) { //TODO: check if the username is the one we are looking for.
        return librarian;
      }
    }

    throw new AuthenticationException();
  }

  /**
   * Generates a unique username for librarian.
   */
  @Override
  public void generateUsername(Librarian librarian) {
    String librarianUsername = String.format(
        "%s%s",
        LIBRARIAN_PREFIX,
        librarianNumber
    );
    librarianNumber++;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Librarian> getAll() {
    return librarians;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Librarian librarian) {
    librarians.add(librarian);
  }


}
