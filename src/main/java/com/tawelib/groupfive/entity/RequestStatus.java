package com.tawelib.groupfive.entity;

import java.io.Serializable;

/**
 * The enum Request status. This is used by the request class.
 *
 * @author Shree Desai
 * @version 0.1
 */
public enum RequestStatus implements Serializable {
  /**
   * Requested - When request is made.
   */
  REQUESTED,
  /**
   * Reserved - When a copy is available, so has been signed to customer.
   */
  RESERVED,
  /**
   * Closed - Once the customer received a copy of resource/ no long needs a
   * copy.
   */
  CLOSED
}
