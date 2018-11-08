package main.java.engine;

import java.util.ArrayList;
import java.util.List;

class Game {

  private final Integer tableStake;
  private final List<Gamer> gamers = new ArrayList<>();

  Game(Integer startingChip, List<String> users) {
    this.tableStake = startingChip;
    for (String user : users) {
      this.addGamer(user, startingChip);
    }
  }

  private void addGamer(String user, Integer money) {
    this.gamers.add(new Gamer(user, money));
  }

  private Integer getTableStake() {
    return tableStake;
  }

  List<Gamer> getGamers() {
    return gamers;
  }

  Integer getBigBlind(){
    return getTableStake() / 100;
  }

  Integer getSmallBlind() {
    return getBigBlind() / 2;
  }

  // getBuyIn
}
