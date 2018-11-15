package main.java.engine;

import java.util.Arrays;

class Engine {

  public static void main(String[] args) {
    Game game = new Game(10000, Arrays.asList("Rémi", "Guillaume", "Arnaud", "Louis"));
    game.start();
    Game game1 = new Game(10000, Arrays.asList("Jean", "Paul", "Pierre", "Thomas"));
    game1.start();
    game.sendTo(1);
    game1.sendTo(2);
    game.sendTo(2);
    game1.sendTo(1);
  }
}
