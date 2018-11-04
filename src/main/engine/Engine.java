package main.engine;

import java.util.Arrays;

public class Engine {

  public static void main(String[] args) {
    Game game = new Game(10000, Arrays.asList("Rémi", "Guillaume", "Arnaud", "Louis"));
    Round round = new Round(game);
    round.play();
  }
}
