package main.engine;

import main.card.Card;
import main.card.Deck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Round {
  Game game;
  private final Deck deck = new Deck();
  private Players players = new Players();
  private final Integer dealerIndex = 0; // Maybe get from game in constructor
  private List<Card> board = new ArrayList<>();

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
    // Move to game ? No because it updates player.currentBet which is relevant only in a Round
    // context
    players.setCurrentIndex(dealerIndex + 1);
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
    while (!players.stream().allMatch(Player::hasEndedTurn)) {
      players.getNextPlaying().playPreFlop();
    }
  }

  private void playFlop() {
    board.addAll(deck.getSomeCard(3));
    players.setCurrentIndex(dealerIndex + 1);
    for (int i = 0; i < players.stream().filter(Player::getIsPlaying).count(); i++) {
      players.getNextPlaying().playFlop();
    }
    while (!players.stream().allMatch(Player::hasEndedTurn)) {
      players.getNextPlaying().playFlop();
    }
  }

  private void playTurn() {
    board.addAll(deck.getSomeCard(1));
    players.setCurrentIndex(dealerIndex + 1);
    for (int i = 0; i < players.stream().filter(Player::getIsPlaying).count(); i++) {
      players.getNextPlaying().playTurn();
    }
    while (!players.stream().allMatch(Player::hasEndedTurn)) {
      players.getNextPlaying().playTurn();
    }
  }

  private void playRiver() {
    board.addAll(deck.getSomeCard(1));
    players.setCurrentIndex(dealerIndex + 1);
    for (int i = 0; i < players.stream().filter(Player::getIsPlaying).count(); i++) {
      players.getNextPlaying().playRiver();
    }
    while (!players.stream().allMatch(Player::hasEndedTurn)) {
      players.getNextPlaying().playRiver();
    }
  }

  Players getPlayers() {
    return players;
  }

  void play() {
    init();
    playPreFlop();
    playFlop();
    playTurn();
    playRiver();
    System.out.println(players);
  }
}
