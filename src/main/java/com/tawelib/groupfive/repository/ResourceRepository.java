package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - ResourceRepository.java The Resource repository class handles all
 * resources.
 *
 * @author Themis Mouyiasis, Shree Desai
 * @version 0.2
 */
public class ResourceRepository implements BaseRepository<Resource> {

  private static ArrayList<Resource> resources;

  private int lastBookNumber = 0;
  private int lastDvdNumber = 0;
  private int lastLaptopNumber = 0;

  /**
   * Generates unique id as resource is persisted to repository.
   *
   * @param resource the new resource being persisted to repository
   */
  private void generateId(Resource resource) {
    String typePrefix;
    String newResourceId = "";

    //Checks type of Resource and creates Id based upon that...
    switch (resource.getType()) {
      case DVD: {
        typePrefix = "D";
        newResourceId = typePrefix + Integer.toString(lastDvdNumber);
        break;
      }
      case BOOK: {
        typePrefix = "B";
        newResourceId = typePrefix + Integer.toString(lastBookNumber);
        break;
      }
      case LAPTOP: {
        typePrefix = "L";
        newResourceId = typePrefix + Integer.toString(lastLaptopNumber);
        break;
      } default:

    }

    try {
      Field idField = resource.getClass().getDeclaredField("resourceId");
      idField.setAccessible(true);
      idField.set(resource, newResourceId);
      idField.setAccessible(false);
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }


  }

  /**
   * Search through resources of type "Book".
   *
   * @param query the query
   * @return the list of resources fulfilling search query
   */
  public List<Book> searchBook(String query) {
    ArrayList<Book> result = new ArrayList<>();
    for (Resource searchResource : resources) {
      if (searchResource.getType() == ResourceType.BOOK) {
        Book searchBook = (Book) searchResource;
        if (searchBook.getTitle().equals(query)
            || searchBook.getYear() == Integer.valueOf(query)
            || searchBook.getAuthor().equals(query)
            || searchBook.getPublisher().equals(query)
            || searchBook.getGenre().equals(query)) {
          result.add(searchBook);
        }
      }
    }

    if (!result.isEmpty()) {
      return result;
    }
    return null;

  }

  /**
   * Search through resources of type "DVD".
   *
   * @param query the query
   * @return the list of resources fulfilling search query
   */
  public List<Dvd> searchDvd(String query) {
    ArrayList<Dvd> result = new ArrayList<>();
    for (Resource searchResource : resources) {
      if (searchResource.getType() == ResourceType.DVD) {
        Dvd searchDvd = (Dvd) searchResource;
        if (searchDvd.getTitle().equals(query)
            || searchDvd.getYear() == Integer.valueOf(query)
            || searchDvd.getDirector().equals(query)
            || searchDvd.getRuntime() == Integer.valueOf(query)) {
          result.add(searchDvd);
        }
      }
    }

    if (!result.isEmpty()) {
      return result;
    }
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
    ArrayList<Laptop> result = new ArrayList<>();
    for (Resource searchResource : resources) {
      if (searchResource.getType() == ResourceType.LAPTOP) {
        Laptop searchLaptop = (Laptop) searchResource;
        if (searchLaptop.getTitle().equals(query)
            || searchLaptop.getYear() == Integer.valueOf(query)
            || searchLaptop.getManufacturer().equals(query)
            || searchLaptop.getModel().equals(query)
            || searchLaptop.getInstalledOperatingSystem().equals(query)) {
          result.add(searchLaptop);

        }
      }
    }
    if (!result.isEmpty()) {
      return result;
    }
    return null;
  }

  /**
   * Search the resources for a specific book.
   *
   * @return the specific book
   */
  public Book getSpecificBook(String resourceId) {
    for (Resource resource : resources) {
      if (resource.getResourceId().equals(resourceId)) {
        return (Book) resource;
      }
    }
    return null;
  }

  /**
   * Search resources for a specific dvd.
   *
   * @return the specific dvd
   */
  public Dvd getSpecificDvd(String resourceId) {
    for (Resource resource : resources) {
      if (resource.getResourceId().equals(resourceId)) {
        return (Dvd) resource;
      }
    }
    return null;
  }

  /**
   * Search for a specific laptop.
   *
   * @return the specific laptop
   */
  public Laptop getSpecificLaptop(String resourceId) {
    for (Resource resource : resources) {
      if (resource.getResourceId().equals(resourceId)) {
        return (Laptop) resource;
      }
    }
    return null;
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
