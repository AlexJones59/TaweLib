package com.tawelib.groupfive.util;

import com.tawelib.groupfive.Main;
import com.tawelib.groupfive.draw.Drawing;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.Resource;
import com.tawelib.groupfive.entity.User;
import com.tawelib.groupfive.testdata.EntityTestData;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Alert.AlertType;

/**
 * Takes care of all file system related operations.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class FileSystemHelper {

  public static final String LIBRARY_SAVE_DIR = "data/";
  public static final String IMAGES_SAVE_DIR = "data/images/";

  private FileSystemHelper() {
    // An empty constructor to prevent instantiating this class.
  }

  /**
   * Saves a given library to the file system.
   *
   * @param library Library to be saved.
   * @throws IOException Unable to save the changes.
   */
  public static void saveLibraryToFile(Library library) throws IOException {
    createDirectoryIfNotExist(LIBRARY_SAVE_DIR);
    String path = getLibraryPath(library.getName());

    FileOutputStream outputStream = new FileOutputStream(path);
    ObjectOutputStream objectOutputStream
        = new ObjectOutputStream(outputStream);

    objectOutputStream.writeObject(library);

    objectOutputStream.close();
  }

  /**
   * Returns a library read from the filesystem or new one.
   *
   * @param name Name of the library to load.
   * @return Loaded library.
   * @throws IOException Unable to access the file.
   * @throws ClassNotFoundException Corrupted file.
   */
  public static Library getLibrary(String name)
      throws IOException, ClassNotFoundException {
    Library library;

    try {
      library = loadLibraryFromFile(name);
    } catch (ClassNotFoundException
        | InvalidClassException
        | FileNotFoundException e
    ) {
      if (Main.DEV_MODE) {
        File libraryFile = new File(
            getLibraryPath(
                com.tawelib.groupfive.view.Library.DEFAULT_LIBRARY_NAME)
        );

        if (libraryFile.exists()) {
          libraryFile.delete();
        }

        library = createNewLibrary(name);
      } else {
        AlertHelper.alert(AlertType.ERROR, "Failed to load library.");
        System.exit(1);
      }
    }

    return library;
  }

  /**
   * Returns a path to the user's profile image.
   *
   * @param user User.
   * @return Path to the user's profile image.
   */
  public static String getUserProfilePicturePath(User user) {
    if (user == null && Main.DEV_MODE) {
      //For development purposes.
      String directory = IMAGES_SAVE_DIR + "profile/default/";
      createDirectoryIfNotExist(directory);
      return directory + "temp." + Drawing.IMAGE_FORMAT;
    } else {
      String directory = IMAGES_SAVE_DIR + "profile/custom/";
      createDirectoryIfNotExist(directory);
      return directory + user.getUsername() + "."
          + Drawing.IMAGE_FORMAT;
    }
  }

  /**
   * Creates a directory recursively if it doesn't exist already.
   *
   * @param path Path.
   */
  public static void createDirectoryIfNotExist(String path) {
    File directoryNecessary = new File(path);
    if (!directoryNecessary.exists()) {
      directoryNecessary.mkdirs();
    }
  }

  /**
   * Returns a path to the resource's image.
   *
   * @param resource User.
   * @return Path to the resource's image.
   */
  public static String getResourcePicturePath(Resource resource) {
    if (resource == null && Main.DEV_MODE) {
      //For development purposes.
      String directory = IMAGES_SAVE_DIR + "resource/default/";
      createDirectoryIfNotExist(directory);
      return directory + "temp." + Drawing.IMAGE_FORMAT;
    } else {
      String directory = IMAGES_SAVE_DIR + "resource/custom/";
      createDirectoryIfNotExist(directory);
      return directory + resource.getResourceId() + "."
          + Drawing.IMAGE_FORMAT;
    }
  }

  /**
   * Reads a Library from the file system. If the file path supplied is null a
   * default path is used.
   *
   * @param name Name of the library.
   * @return Library loaded from the file system.
   */
  private static Library loadLibraryFromFile(String name)
      throws IOException, ClassNotFoundException {

    String path = getLibraryPath(name);
    FileInputStream inputStream = new FileInputStream(path);
    ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

    try {
      return (Library) objectInputStream.readObject();
    } finally {
      objectInputStream.close();
    }
  }

  /**
   * Creates a new library. Populates it if running in development mode.
   *
   * @param name Name of the library.
   * @return Library.
   */
  private static Library createNewLibrary(String name) {
    Library library = new Library(name);

    if (Main.DEV_MODE) {
      EntityTestData.populateLibrary(library);
    }

    return library;
  }

  /**
   * Returns the save path for a library with a given name.
   *
   * @param name Name of the library.
   * @return Path to the library.
   */
  private static String getLibraryPath(String name) {
    return LIBRARY_SAVE_DIR + name;
  }
}
