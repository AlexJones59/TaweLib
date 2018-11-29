package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.exception.AuthenticationException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * File Name - ResourceRepository.java The Resource repository class handles all resources.
 *
 * @author Created by Themis, Modified by Nayeem Mohammed & Shree Desai
 * @version 0.2
 */
public class ResourceRepository implements BaseRepository<Resource> {

  private static ArrayList<Resource> resources;


  private static Hashtable<String, Resource> LeaseTable = new Hashtable<String, Resource>();

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
    return null;
  }

  /**
   * Search through resources of type "DVD".
   *
   * @param query the query
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Dvd> searchDvd(String query, String searchAttribute) {
    return null;
  }

  /**
   * Search through resources of type "Laptop".
   *
   * @param query the query
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Laptop> searchLaptop(String query, String searchAttribute) {
    return null;
  }

  /**
   * Search the resources for a specific book.
   *
   * @return the specific book
   */
  public Book getSpecificBook(String resourceId) {
    for (Resource resource : resources) {
      if (resource.getId().equals(resourceId)) {
        return (Book) resource;
      }
    }
    throw new AuthenticationException();
  }

  /**
   * Search resources for a specific dvd.
   *
   * @return the specific dvd
   */
  public Dvd getSpecificDvd(String resourceId) {
    for (Resource resource : resources) {
      if (resource.getId().equals(resourceId)) {
        return (Dvd) resource;
      }
    }
    throw new AuthenticationException();

  }

  /**
   * Search for a specific laptop.
   * @param resourceId
   * @return the pecific laptop
   */
  public Laptop getSpecificLaptop(String resourceId) {
    for (Resource resource : resources) {
      if (resource.getId().equals(resourceId)) {
        return (Laptop) resource;
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
    if (!resources.contains((resource))) {
      resources.add(resource);
    }
  }


}
