package com.tawelib.groupfive.repository;

import com.tawelib.groupfive.entity.Game;
import com.tawelib.groupfive.entity.GameTest;
import com.tawelib.groupfive.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Test;

public class ResourceRepositoryTest {

  @Test
  public void test1() {
    ResourceRepository resourceRepository = new ResourceRepository();

    resourceRepository.add(
        GameTest.createGame()
    );

    try {
      Game retrievedGame = resourceRepository.getSpecificGame("G0");
    } catch (Exception e) {
      Assert.fail();
    }

    boolean thrown = false;
    try {
      Game retrievedGame = resourceRepository.getSpecificGame("SOMETHING RANDOM");
    } catch (Exception e) {
      if (e instanceof ResourceNotFoundException) {
        thrown = true;
      }
    }

    Assert.assertTrue(thrown);
  }
}
