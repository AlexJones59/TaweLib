package com.tawelib.groupfive.controller;

import com.tawelib.groupfive.entity.Copy;
import com.tawelib.groupfive.entity.Resource;

/**
 * File Name - CopyController.java
 * The CopyController class controls data flow between the Copy Repository and the GUI interfaces.
 *
 * @author Nayeem Mohammed, Shree Desai
 * @version 0.2
 */
//TODO: Finish implementation of this class.
public class CopyController {

  /**
   * Create resource copy.
   *
   * @param resource the resource
   * @param amount the amount
   */
  public void createResourceCopy(Resource resource, int amount) {}

  /**
   * Borrow resource copy.
   *
   * @param copyId the copy id
   * @param customerUsername the customer username
   */
  public void borrowResourceCopy(String copyId, String customerUsername) {}

  /**
   * Return resource copy.
   *
   * @param copyId the copy id
   */
  public void returnResourceCopy(String copyId){}


}