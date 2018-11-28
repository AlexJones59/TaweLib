package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - ResourceRepository.java The Resource repository class handles all resources.
 *
 * @author Created by Themis, Modified by Nayeem Mohammed & Shree Desai
 * @version 0.2
 */
public class ResourceRepository implements BaseRepository<Resource> {

  private static ArrayList<Resource> resources;

  /**
   * Generates unique id as resource is persisted to repository.
   *
   * @param resource the new resource being persisted to repository
   */
  private void generateId(Resource resource) {
  }

  /**
   * Search through resources of type "Book".
   *
   * @param query the query
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Book> searchBook(String query, String searchAttribute) {
    for (Resource searchB : resources) {
      if (searchB.getTitle() == searchAttribute) {
        return (List<Book>) searchB;
      }
    }
    throw new AuthenticationException();
  }

  /**
   * Search through resources of type "DVD".
   *
   * @param query the query
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Dvd> searchDvd(String query, String searchAttribute) {
    for (Resource searchD : resources) {
      if (searchD.getTitle() == searchAttribute) {
        return (List<Dvd>) searchD;
      }
    }
    throw new AuthenticationException();
  }

  /**
   * Search through resources of type "Laptop".
   *
   * @param query the query
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Laptop> searchLaptop(String query, String searchAttribute) {
    for (Resource searchL : resources) {
      if (searchL.getTitle() == searchAttribute) {
        return (List<Laptop>) searchL;
      }
    }
    throw new AuthenticationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Resource> getAll() {
    return resources;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(Resource resource) {
    resources.add(resource);
  }
}
