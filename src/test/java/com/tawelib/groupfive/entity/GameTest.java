package com.tawelib.groupfive.entity;

import org.junit.Test;

public class GameTest {

  @Test
  public void test1() {
    Game game = createGame();
  }

  public static Game createGame() {
    return new Game(
        "Supreme Commander: Forged Alliance",
        2007,
        null,
        "THQ",
        "Strategy",
        "PEGI 12",
        true
    );
  }
}
