package main.java.engine;

import java.util.Arrays;

class Engine {

  public static void main(String[] args) {
    Game game = new Game(10000, Arrays.asList("Rémi", "Guillaume", "Arnaud", "Louis"));
    Hand hand = new Hand(game);
    hand.play();
  }
}
