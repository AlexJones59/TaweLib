package com.tawelib.groupfive.runtime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class SimulatedLocalDateTime {

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
            TestClock.getTimestamp()
        ),
        TimeZone.getDefault().toZoneId()
    );
  }
}
