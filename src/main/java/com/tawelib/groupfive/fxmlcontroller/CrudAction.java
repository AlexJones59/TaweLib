package com.tawelib.groupfive.fxmlcontroller;

import java.io.Serializable;

/**
 * The enum Crud action is used to determine what the crud screens are being
 * used for.
 *
 * @author Petr Hoffman
 * @version 1.0
 */
public enum CrudAction implements Serializable {
  /**
   * Create crud action.
   */
  CREATE,
  /**
   * Update crud action.
   */
  UPDATE
}
