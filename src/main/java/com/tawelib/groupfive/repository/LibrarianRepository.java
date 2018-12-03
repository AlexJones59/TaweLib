package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Librarian;
import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The Librarian repository class holds the Librarians and performs manipulative
 * operations on them.
 *
 * @author Themis Mouyiasis, Modified by Petr Hoffmann
 * @version 0.3
 */
public class LibrarianRepository implements UserRepository<Librarian> {

  private ArrayList<Librarian> librarians;

  private int lastLibrarianNumber = 0;

  public LibrarianRepository() {
    librarians = new ArrayList<>();
  }

  /**
   * Returns a Librarian by their username. Throws an exception when unable to
   * authenticate.
   *
   * @param username Username.
   * @return the librarian
   */
  @Override
  public Librarian authenticate(String username) {
    Librarian librarian = getSpecific(username);

    if (librarian == null) {
      throw new AuthenticationException();
    } else {
      return librarian;
    }
  }

  /**
   * Searches for a Librarian with a given username.
   *
   * @param username Librarian's username.
   * @return Found Librarian or null.
   */
  public Librarian getSpecific(String username) {
    for (Librarian librarian : librarians) {
      if (librarian.getUsername().equals(username)) {
        return librarian;
      }
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<? extends User> search(String query) {
    ArrayList<User> foundUsers = new ArrayList<>();

    for (Librarian librarian : librarians) {
      if (
          librarian.getUsername().contains(query)
              || librarian.getFullName().contains(query)
              || Integer.toString(librarian.getStaffNumber()).contains(query)
      ) {
        foundUsers.add(librarian);
      }
    }

    return foundUsers;
  }

  /**
   * Generates a unique username for a librarian.
   *
   * @param librarian Librarian.
   */
  private void generateUsername(Librarian librarian) {
    String baseUsername = String.format(
        "%s.%s",
        librarian.getFirstName().toLowerCase(),
        librarian.getLastName().toLowerCase()
    );

    int suffixBase = 1;
    String usernameSuffix = "";
    String generatedUsername = baseUsername + usernameSuffix;

    // Checks if the username is already part of repositiory
    // If not, adds a number, and checks again.
    while (getSpecific(generatedUsername) != null) {
      usernameSuffix = String.format(".%d", suffixBase);
      generatedUsername = baseUsername + usernameSuffix;
      suffixBase++;
    }

    //Use
    assignGeneratedId(
        librarian,
        generatedUsername
    );
  }

  /**
   * Generates a unique staff number for a librarian.
   *
   * @param librarian Librarian.
   */
  private void generateStaffNumber(Librarian librarian) {
    try {
      Field usernameField = librarian.getClass()
          .getDeclaredField("staffNumber");
      usernameField.setAccessible(true);
      usernameField.set(librarian, lastLibrarianNumber);
      usernameField.setAccessible(false);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    } finally {
      lastLibrarianNumber++;
    }
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
    if (!librarians.contains(librarian)) {
      generateUsername(librarian);
      generateStaffNumber(librarian);
      librarians.add(librarian);
    }
  }
}
