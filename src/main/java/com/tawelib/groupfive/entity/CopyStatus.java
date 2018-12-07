package com.tawelib.groupfive.entity;

import java.io.Serializable;

/**
 * CopyStatus.java The enum Copy status which is used by the Copy Class.
 *
 * @author Shree Desai
 * @version 1.0
 */
public enum CopyStatus implements Serializable {
  /**
   * When Copy is Available to be leased.
   */
  AVAILABLE,
  /**
   * When Copy is Borrowed.
   */
  BORROWED,
  /**
   * When Copy is Reserved.
   */
  RESERVED,
  /**
   * When User has lost a copy.
   */
  LOST
}
