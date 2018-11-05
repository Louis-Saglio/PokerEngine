package main.engine;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private Integer tableStake;
  private List<Gamer> gamers = new ArrayList<>();

  Game(Integer startingChip, List<String> users) {
    this.tableStake = startingChip;
    for (String user : users) {
      this.addGamer(user, startingChip);
    }
  }

  private void addGamer(String user, Integer money) {
    this.gamers.add(new Gamer(user, money));
  }

  Integer getTableStake() {
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
