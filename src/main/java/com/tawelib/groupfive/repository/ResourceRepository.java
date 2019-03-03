package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Book;
import com.tawelib.groupfive.entity.Dvd;
import com.tawelib.groupfive.entity.Game;
import com.tawelib.groupfive.entity.Laptop;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.ResourceType;
import com.tawelib.groupfive.exception.ResourceNotFoundException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * File Name - ResourceRepository.java The Resource repository class handles all resources.
 *
 * @author Themis Mouyiasis, Shree Desai
 * @version 1.0
 */
public class ResourceRepository implements BaseRepository<Resource> {

  protected ArrayList<Resource> resources;

  private int lastBookNumber = 0;
  private int lastDvdNumber = 0;
  private int lastLaptopNumber = 0;
  private int lastGameNumber = 0;

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
   * Search through resources of type "Book".
   *
   * @param query the query
   * @param lastLogin the timestamp of the user's previous login.
   * @return the list of resources fulfilling search query
   */
  public List<Book> searchBook(String query, LocalDateTime lastLogin) {
    ArrayList<Book> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Book.class) && searchResource.getDateAdded().isAfter(lastLogin)) {
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
   * Search through resources of type "DVD".
   *
   * @param query the query
   * @param lastLogin the timestamp of the user's previous login.
   * @return the list of resources fulfilling search query
   */
  public List<Dvd> searchDvd(String query, LocalDateTime lastLogin) {
    ArrayList<Dvd> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Dvd.class) && searchResource.getDateAdded().isAfter(lastLogin)) {
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
   * Search through resources.
   *
   * @param query the query
   * @param lastLogin the timestamp of the user's previous login.
   * @return the list of resources fulfilling search query
   */
  public List<Resource> searchResource(String query, LocalDateTime lastLogin) {
    ArrayList<Resource> result = new ArrayList<>();

    for (Resource resource : resources) {
    	if (resource.getDateAdded().isAfter(lastLogin)) {
    		if (
    			resource.getResourceId().contains(query)
	              || resource.getTitle().contains(query)
	              || Integer.toString(resource.getYear()).contains(query)
    		) {
	        result.add(resource);
    		}
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
   * Search through resources of type "Laptop".
   *
   * @param query the query
   * @param lastLogin the timestamp of the user's previous login.
   * @return the list of resources fulfilling search query
   */
  public List<Laptop> searchLaptop(String query, LocalDateTime lastLogin) {
    ArrayList<Laptop> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Laptop.class) && searchResource.getDateAdded().isAfter(lastLogin)) {
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
   * Search through resources of type "Game".
   *
   * @param query the query
   * @return the list of resources fulfilling search query
   */
  public List<Game> searchGame(String query) {
    ArrayList<Game> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Game.class)) {
        Game searchGame = (Game) searchResource;
        if (
            searchGame.getResourceId().contains(query)
                || searchGame.getTitle().contains(query)
                || Integer.toString(searchGame.getYear()).contains(query)
                || searchGame.getPublisher().contains(query)
                || searchGame.getGenre().contains(query)
                || searchGame.getRating().contains(query)
        ) {
          result.add(searchGame);
        }
      }
    }

    return result;
  }
  
  /**
   * Search through resources of type "Game".
   *
   * @param query the query
   * @param lastLogin the timestamp of the user's previous login.
   * @return the list of resources fulfilling search query
   */
  public List<Game> searchGame(String query, LocalDateTime lastLogin) {
    ArrayList<Game> result = new ArrayList<>();

    for (Resource searchResource : resources) {
      if (searchResource.getClass().equals(Game.class)&& searchResource.getDateAdded().isAfter(lastLogin)) {
        Game searchGame = (Game) searchResource;
        if (
            searchGame.getResourceId().contains(query)
                || searchGame.getTitle().contains(query)
                || Integer.toString(searchGame.getYear()).contains(query)
                || searchGame.getPublisher().contains(query)
                || searchGame.getGenre().contains(query)
                || searchGame.getRating().contains(query)
        ) {
          result.add(searchGame);
        }
      }
    }

    return result;
  }
  
  /**
   * Search through resources for newly added resources since the user last logged on.
   *
   * @param lastLogin the timestamp of the user's previous login.
   * @return the list of resources stored in the repository that have been added to the system since the user last logged on.
   */
  public List<Resource> getAll(LocalDateTime lastLogin) {  
	  ArrayList<Resource> result = new ArrayList<>();
	  for (Resource resource : resources) {	  
		  if (resource.getDateAdded().isAfter(lastLogin)) {
	         result.add(resource);
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
   * Search for a specific Game.
   *
   * @param resourceId Resource ID.
   * @return the specific Game.
   * @throws ResourceNotFoundException When unable to find resource of a given type.
   */
  public Game getSpecificGame(String resourceId) throws ResourceNotFoundException {
    for (Resource resource : resources) {
      if (
          resource.getType() == ResourceType.GAME
              && resource.getResourceId().equals(resourceId)
      ) {
        return (Game) resource;
      }
    }

    throw new ResourceNotFoundException(
        String.format(
            "Unable to find a game with ID %s in the repository",
            resourceId
        )
    );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Resource> getAll() {
	  for (Resource resource : resources) {
	  }
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
      case GAME: {
        typePrefix = "G";
        newResourceId = typePrefix + Integer.toString(lastGameNumber);
        lastGameNumber++;
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
