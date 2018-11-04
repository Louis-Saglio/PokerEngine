package main.engine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {

  private Players players;

  @BeforeEach
  void setUp() {
    players = new Round(new Game(42, Arrays.asList("test1", "test2", "test3"))).getPlayers();
  }

  @Test
  void getNext() {
    final Player expectedPlayer = players.get(0);
    final Player actualPlayer = players.getNext();

    assertEquals(expectedPlayer, actualPlayer);
  }

  @Test
  void getNextPlaying() {
    players.get(0).playPreFlop(1);
    Player expected = players.get(1);
    Player nextPlaying = players.getNextPlaying();
    assertEquals(expected, nextPlaying);
  }

  @Test
  void setCurrentIndex() {
    assertThrows(RuntimeException.class, () -> players.setCurrentIndex(3));
  }
}