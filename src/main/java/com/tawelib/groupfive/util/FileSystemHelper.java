package com.tawelib.groupfive.util;

import com.tawelib.groupfive.draw.Drawing;
import com.tawelib.groupfive.entity.Library;
import com.tawelib.groupfive.entity.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Takes care of all file system related operations.
 *
 * @author Petr Hoffmann
 */
public class FileSystemHelper {

  private static final String LIBRARY_SAVE_DIR = "data/";
  private static final String IMAGES_SAVE_DIR = "data/images/";

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
  public static Library getLibrary(String name) throws IOException,
      ClassNotFoundException {
    Library library;

    try {
      library = loadLibraryFromFile(name);
    } catch (FileNotFoundException e) {
      library = new Library(name);
    }

    return library;
  }

  public static String getUserProfilePicturePath(User user) {
    if (user == null) {
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

  public static void createDirectoryIfNotExist(String path) {
    File directoryNecessary = new File(path);
    if (!directoryNecessary.exists()) {
      directoryNecessary.mkdirs();
    }
  }

  /**
   * Reads a Library from the file system. If the file path supplied is null a
   * default path is used.
   *
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

  private static String getLibraryPath(String name) {
    return LIBRARY_SAVE_DIR + name;
  }
}
