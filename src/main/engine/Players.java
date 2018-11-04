package main.engine;

import java.util.ArrayList;

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
}
