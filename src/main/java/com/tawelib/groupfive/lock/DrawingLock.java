package com.tawelib.groupfive.lock;

public class DrawingLock {

  private boolean locked = true;

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }
}
