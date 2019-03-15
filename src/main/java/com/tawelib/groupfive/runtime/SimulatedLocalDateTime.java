package com.tawelib.groupfive.runtime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.TimeZone;

/**
 * Provides simulated current LocalDateTime.
 *
 * @author Petr Hoffmann
 * @version 1.0
 */
public class SimulatedLocalDateTime {

  private static final Random random = new Random();

  private SimulatedLocalDateTime() {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the current simulated time.
   *
   * @return Current simulated time.
   */
  public static LocalDateTime now() {
    return LocalDateTime.ofInstant(
        Instant.ofEpochMilli(
            SimulatedClock.getTimestamp()
        ),
        TimeZone.getDefault().toZoneId()
    );
  }

  /**
   * Returns a random local date time up to x numbers ago.
   *
   * @param x Number of days ago.
   * @return DateTime randomly up to x days ago.
   */
  public static LocalDateTime upToXDaysAgo(int x) {
    return now().minusDays(
        random.nextInt(x) + 1L
    );
  }
}
