package com.tawelib.groupfive.util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Helps with conversion between Strings and String Collections.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class ExplosionHelper {

  private static final String DELIMITER = ",";

  private ExplosionHelper() {
    throw new IllegalStateException("Util class");
  }

  /**
   * Explodes a string into an ArrayList.
   *
   * @param string String to explode.
   * @return The exploded array.
   */
  public static ArrayList<String> explode(String string) {
    return new ArrayList<>(Arrays.asList(string.split(DELIMITER)));
  }

  /**
   * Implodes an ArrayList to a String.
   *
   * @param strings ArrayList to implode.
   * @return Imploded String.
   */
  public static String implode(ArrayList<String> strings) {
    return String.join(DELIMITER, strings);
  }
}
