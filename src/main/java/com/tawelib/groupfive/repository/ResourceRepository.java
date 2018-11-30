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
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Book> searchBook(String query, String searchAttribute) {
    ArrayList<Book> result = new ArrayList<>();
    for (Resource searchResource : resources) {
      if (searchResource.getType() == ResourceType.BOOK) {
        Book searchBook = (Book) searchResource; switch (searchAttribute) {
          case "Title": {
            if (searchBook.getTitle().equals(query)) {
              result.add(searchBook);
            }
            break;
          } case "Year": {
            if (searchBook.getYear() == Integer.valueOf(query)) {
              result.add(searchBook);
            }
            break;
          } case "Author": {
            if (searchBook.getAuthor().equals(query)) {
              result.add(searchBook);
            }
            break;
          } case "Publisher": {
            if (searchBook.getPublisher().equals(query)) {
              result.add(searchBook);
            }
            break;
          } case "Genre": {
            if (searchBook.getGenre().equals(query)) {
              result.add(searchBook);
            }
            break;
          } default :
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
   * @param searchAttribute the search attribute
   * @return the list of resources fulfilling search query
   */
  public List<Dvd> searchDvd(String query, String searchAttribute) {
    ArrayList<Dvd> result = new ArrayList<>();
    for (Resource searchResource : resources) {
      if (searchResource.getType() == ResourceType.DVD) {
        Dvd searchDvd = (Dvd) searchResource; switch (searchAttribute) {
          case "Title": {
            if (searchDvd.getTitle().equals(query)) {
              result.add(searchDvd);
            }
            break;
          } case "Year": {
            if (searchDvd.getYear() == Integer.valueOf(query)) {
              result.add(searchDvd);
            }
            break;
          } case "Director": {
            if (searchDvd.getDirector().equals(query)) {
              result.add(searchDvd);
            }
            break;
          } case "Runtime": {
            if (searchDvd.getRuntime() == Integer.valueOf(query)) {
              result.add(searchDvd);
            }
            break;
          } default :
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
        switch (searchAttribute) {
          case "title": {
            if (searchLaptop.getTitle().equals(query)) {
              result.add(searchLaptop);
            }
            break;
          } case "Year": {
            if (searchLaptop.getYear() == Integer.valueOf(query)) {
              result.add(searchLaptop);
            }
            break;
          } case "Manufacturer": {
            if (searchLaptop.getManufacturer().equals(query)) {
              result.add(searchLaptop);
            }
            break;
          } case "Model": {
            if (searchLaptop.getModel().equals(query)) {
              result.add(searchLaptop);
            }
            break;
          } case "Installed Operating System": {
            if (searchLaptop.getInstalledOperatingSystem().equals(query)) {
              result.add(searchLaptop);
            }
            break;
          } default:
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
