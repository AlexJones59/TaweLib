package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - ResourceRepository.java The Resource repository class handles all
 * resources.
 *
 * @author Themis Mouyiasis, Shree Desai
 * @version 0.6
 */
public class ResourceRepository implements BaseRepository<Resource> {

  private ArrayList<Resource> resources;

  private int lastBookNumber = 0;
  private int lastDvdNumber = 0;
  private int lastLaptopNumber = 0;

  public ResourceRepository() {
    resources = new ArrayList<>();
  }

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
        lastDvdNumber++;
        break;
      }
      case BOOK: {
        typePrefix = "B";
        newResourceId = typePrefix + Integer.toString(lastBookNumber);
        lastBookNumber++;
        break;
      }
      case LAPTOP: {
        typePrefix = "L";
        newResourceId = typePrefix + Integer.toString(lastLaptopNumber);
        lastLaptopNumber++;
        break;
      }
      default:

    }

    try {
      Field idField = resource.getClass().getSuperclass()
          .getDeclaredField("resourceId");
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
   * TODO: Refactor: use searchResource(String query)
   *
   * @param query the query
   * @return the list of resources fulfilling search query
   */
  public List<Book> searchBook(String query) {
    ArrayList<Book> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Book.class)) {
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

    return result;

  }

  /**
   * Search through resources of type "DVD".
   *
   * TODO: Refactor: use searchResource(String query)
   *
   * @param query the query
   * @return the list of resources fulfilling search query
   */
  public List<Dvd> searchDvd(String query) {
    ArrayList<Dvd> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Dvd.class)) {
        Dvd searchDvd = (Dvd) searchResource;
        if (searchDvd.getTitle().equals(query)
            || searchDvd.getYear() == Integer.valueOf(query)
            || searchDvd.getDirector().equals(query)
            || searchDvd.getRuntime() == Integer.valueOf(query)) {
          result.add(searchDvd);
        }
      }
    }

    return result;

  }

  /**
   * Search through resources.
   *
   * @param query the query
   * @return the list of resources fulfilling search query
   */
  public List<Resource> searchResource(String query) {
    ArrayList<Resource> result = new ArrayList<>();

    for (Resource resource : resources) {
      if (
          resource.getResourceId().contains(query)
              || resource.getTitle().contains(query)
              || Integer.toString(resource.getYear()).contains(query)
      ) {
        result.add(resource);
      }
    }

    return result;
  }

  /**
   * Search through resources of type "Laptop".
   *
   * TODO: Refactor: use searchResource(String query)
   *
   * @param query the query
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Laptop> searchLaptop(String query, String searchAttribute) {
    ArrayList<Laptop> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Laptop.class)) {
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

    return result;
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
      generateId(resource);
      resources.add(resource);
    }
  }
}
