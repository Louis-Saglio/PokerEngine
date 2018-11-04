package main.engine;

import main.card.Deck;

import java.util.Comparator;
import java.util.Optional;

class Round {
  Game game;
  private final Deck deck = new Deck();
  private Players players = new Players();
  private final Integer dealerIndex = 0; // Maybe get from game in constructor

  Round(Game game) {
    this.game = game;
    for (Gamer gamer : this.game.getGamers()) {
      players.add(new Player(gamer, this));
    }
  }

  private void init() {
    doBlind();
    handOutCards();
  }

  Integer getBiggestBet() {
    Optional<Player> player = players.stream().max(Comparator.comparingInt(Player::getCurrentBet));
    if (player.isPresent()) {
      return player.get().getCurrentBet();
    } else {
      return 0; // Const default biggest bet
    }
  }

  private void doBlind() {
    // Move to game ? No because it updates player.currentBet which is relevant only in a Round context
    players.setCurrentIndex(dealerIndex);
    players.getNext();
    players.getNext().bet(game.getSmallBlind());
    players.getNext().bet(game.getBigBlind());
  }

  private void handOutCards() {
    Integer cardNumber = 2; // Evolution : could be field in the future
    for (Player player : players) {
      player.setCards(deck.getSomeCard(cardNumber));
    }
  }

  private void playPreFlop() {
    players.setCurrentIndex(dealerIndex + 3);
    // Everybody play at least once
    for (int i = 0; i < players.size(); i++) {
      players.getNext().playPreFlop();
    }
    while (!players.stream().allMatch(Player::hasEndedPreFlop)) {
      players.getNextPlaying().playPreFlop();
    }
  }


  void play() {
    init();
    playPreFlop();
    System.out.println(players);
    // Bug : Fold; Raise 500; Fold; Raise 450 ? Check
  }
}
