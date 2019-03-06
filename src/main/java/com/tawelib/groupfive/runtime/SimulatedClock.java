package com.tawelib.groupfive.runtime;

import com.tawelib.groupfive.Main;
import java.util.Date;

public class SimulatedClock {

  // in milliseconds
  private static long timestamp = 0;

  private SimulatedClock() {
    throw new UnsupportedOperationException();
  }

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
   * @param seconds Amount of seconds.
   */
  public static void addSeconds(long seconds) {
    SimulatedClock.timestamp += seconds * 1000;
  }

  /**
   * Advances the simulated time by a given number of minutes.
   *
   * @param minutes Minutes.
   */
  public static void addMinutes(long minutes) {
    addSeconds(minutes * 60);
  }

  /**
   * Advances the simulated time by a given number of hours.
   *
   * @param hours Hours.
   */
  public static void addHours(long hours) {
    addMinutes(hours * 60);
  }

  /**
   * Advances the simulated time by a given number of days.
   *
   * @param days Days.
   */
  public static void addDays(long days) {
    addHours(days * 24);
  }
}
