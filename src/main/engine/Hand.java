package main.engine;

import main.card.Card;
import main.card.Deck;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Hand {
  final Game game;
  private final Deck deck = new Deck();
  private final Players players = new Players();
  private final Integer dealerIndex = 0; // Maybe get from game in constructor
  private final List<Card> board = new ArrayList<>();

  Hand(Game game) {
    this.game = game;
    for (Gamer gamer : this.game.getGamers()) {
      players.add(new Player(gamer, this));
    }
  }

  Players getPlayers() {
    return players;
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

  List<Card> getBoard() {
    // For test
    return board;
  }

  private void doBlind() {
    // Move to game ? No because it updates player.currentBet which is relevant only in a Hand
    // context
    players.setCurrentIndex(dealerIndex + 1);
    players.getNext().bet(game.getSmallBlind());
    players.getNext().bet(game.getBigBlind());
  }

  private void handOutCards() {
    Integer cardNumber = 2; // Evolution : could be field in the future
    for (Player player : players) {
      player.setDownCards(deck.getSomeCard(cardNumber));
    }
  }

  private void playPreFlop() {
    System.out.println("Hand.playPreFlop");
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
    System.out.println("Hand.playFlop");
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
    System.out.println("Hand.playTurn");
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
    System.out.println("Hand.playRiver");
    board.addAll(deck.getSomeCard(1));
    players.setCurrentIndex(dealerIndex + 1);
    for (int i = 0; i < players.stream().filter(Player::getIsPlaying).count(); i++) {
      players.getNextPlaying().playRiver();
    }
    while (!players.stream().allMatch(Player::hasEndedTurn)) {
      players.getNextPlaying().playRiver();
    }
  }

  Player getWinner() {
    return players.stream().max(Player::comparesCards).orElse(null);
  }

  private void showDown() {
    System.out.println("Hand.showDown");
    Player player = getWinner();//.win;
    System.out.println(player);
  }

  void play() {
    init();
    playPreFlop();
    playFlop();
    playTurn();
    playRiver();
    showDown();
    System.out.println(players);
  }
}
