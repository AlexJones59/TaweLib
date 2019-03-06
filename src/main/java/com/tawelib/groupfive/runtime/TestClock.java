package com.tawelib.groupfive.runtime;

import com.tawelib.groupfive.Main;
import java.util.Date;

public class TestClock {

  private static long timestamp = 0;

  /**
   * Returns the current simulated timestamp.
   *
   * @return Current simulated timestamp.
   */
  public static long getTimestamp() {
    if (Main.DEV_MODE) {
      return timestamp;
    } else {
      return (new Date()).getTime();
    }
  }

  /**
   * Advances the simulation time forward.
   *
   * @param milliseconds Amount of milliseconds.
   */
  public static void addTime(long milliseconds) {
    TestClock.timestamp = milliseconds;
  }
}
