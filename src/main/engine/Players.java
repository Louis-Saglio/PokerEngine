package main.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Players extends ArrayList<Player> {

  private Integer currentIndex = 0;

  Player getNext() {
    if (currentIndex == size()) {
      currentIndex = 0;
    }
    currentIndex++;
    return get(currentIndex - 1);
  }

  Player getNextPlaying() {
    // What if no player is playing ? Maybe it is impossible.
    Player player = getNext();
    if (!player.getIsPlaying()) {
      return getNextPlaying();
    }
    return player;
  }

  void setCurrentIndex(Integer currentIndex) {
    if (currentIndex >= this.size()) {
      throw new RuntimeException("CurrentIndex too big");
    }
    this.currentIndex = currentIndex;
  }

  HashMap<PlayerStatus, List<Player>> getPlayersByResult() {
    HashMap<PlayerStatus, List<Player>> players = new HashMap<>();
    players.put(PlayerStatus.WINNER, new ArrayList<>());
    players.put(PlayerStatus.LOOSER, new ArrayList<>());
    Player winner = stream().max(Player::comparesCards).orElse(null);
    for (Player player : this) {
      if (player.comparesCards(winner) == 0) players.get(PlayerStatus.WINNER).add(player);
      else if (player.comparesCards(winner) == -1) players.get(PlayerStatus.LOOSER).add(player);
    }
    return players;
  }
}
