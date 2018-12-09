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
 * @version 1.0
 */
public class ResourceRepository implements BaseRepository<Resource> {

  private ArrayList<Resource> resources;

  private int lastBookNumber = 0;
  private int lastDvdNumber = 0;
  private int lastLaptopNumber = 0;

  /**
   * Initiates a new ResourceRepository.
   */
  public ResourceRepository() {
    resources = new ArrayList<>();
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
      if (searchResource.getClass().equals(Book.class)) {
        Book searchBook = (Book) searchResource;
        if (
            searchBook.getResourceId().contains(query)
                || searchBook.getTitle().contains(query)
                || Integer.toString(searchBook.getYear()).contains(query)
                || searchBook.getAuthor().contains(query)
                || searchBook.getPublisher().contains(query)
                || searchBook.getGenre().contains(query)
        ) {
          result.add(searchBook);
        }
      }
    }

    return result;

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
      if (searchResource.getClass().equals(Dvd.class)) {
        Dvd searchDvd = (Dvd) searchResource;
        if (
            searchDvd.getResourceId().contains(query)
                || searchDvd.getTitle().contains(query)
                || Integer.toString(searchDvd.getYear()).contains(query)
                || searchDvd.getDirector().contains(query)
                || searchDvd.getRuntime() == Integer.valueOf(query)
        ) {
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
   * @param query the query
   * @return the list of resources fulfilling search query
   */
  public List<Laptop> searchLaptop(String query) {
    ArrayList<Laptop> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Laptop.class)) {
        Laptop searchLaptop = (Laptop) searchResource;
        if (
            searchLaptop.getResourceId().contains(query)
                || searchLaptop.getTitle().contains(query)
                || Integer.toString(searchLaptop.getYear()).contains(query)
                || searchLaptop.getManufacturer().contains(query)
                || searchLaptop.getModel().contains(query)
                || searchLaptop.getInstalledOperatingSystem().contains(query)
        ) {
          result.add(searchLaptop);
        }
      }
    }

    return result;
  }

  /**
   * Gets specific.
   *
   * @param resourceId the resource id
   * @return the specific
   */
  public Resource getSpecific(String resourceId) {
    for (Resource resource : resources) {
      if (resource.getResourceId().equals(resourceId)) {
        return resource;
      }
    }
    return null;
  }

  /**
   * Search the resources for a specific book.
   *
   * @param resourceId Resource ID.
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
   * @param resourceId Resource ID.
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
   * @param resourceId Resource ID.
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
}
