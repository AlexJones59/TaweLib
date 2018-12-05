package com.tawelib.groupfive.util;

import java.util.ArrayList;
import java.util.Arrays;

public class ExplosionHelper {

  private static final String DELIMITER = ",";

  public static ArrayList<String> explode(String string) {
    return new ArrayList<>(Arrays.asList(string.split(DELIMITER)));
  }

  public static String implode(ArrayList<String> strings) {
    return String.join(DELIMITER, strings);
  }
}
