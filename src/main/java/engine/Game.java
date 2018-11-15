package main.java.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Game extends Thread {

  private static Integer lastId = -1;

  private final Integer tableStake;
  private final List<Gamer> gamers = new ArrayList<>();
  private final Integer id;
  private final BlockingQueue<Integer> actionsBlockingQueue = new LinkedBlockingQueue<>();

  Game(Integer startingChip, List<String> users) {
    incrementLastId();
    id = lastId;
    tableStake = startingChip;
    for (String user : users) {
      addGamer(user, startingChip);
    }
  }

  synchronized private static void incrementLastId() {
    lastId++;
  }

  void sendTo(Integer action) {
    try {
      actionsBlockingQueue.put(action);
    } catch (InterruptedException ignored) {
    }
  }

  Integer readFrom() {
    try {
      return actionsBlockingQueue.take();
    } catch (InterruptedException e) {
      throw new RuntimeException("Interrupted while reading queue of game " + id);
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

  @Override
  public void run() {
    while (gamers.stream().filter(Gamer::hasMoney).count() > 1) {
      System.out.println("New hand for game " + id);
      new Hand(this).play();
    }
  }
}
