package com.tawelib.groupfive.entity;

import java.io.Serializable;

/**
 * Enum RequestStatus. The enum Request status which is used by the Request Class.
 *
 * @author Shree Desai
 * @version 1.0
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
   * Closed - Once the customer received a copy of resource/ no long needs a copy.
   */
  CLOSED
}
