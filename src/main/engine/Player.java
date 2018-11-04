package main.engine;

import main.card.Card;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Player {
  private Boolean isPlaying = true;
  private Integer currentBet = 0;
  private List<Card> cards;
  private final Gamer gamer;
  private final Round round; // may cause spaghetti code

  Player(Gamer gamer, Round round) {
    this.gamer = gamer;
    this.round = round;
  }

  void bet(Integer money) {
    currentBet += money;
  }

  void setCards(List<Card> cards) {
    this.cards = cards;
  }

  private Integer chooseAction() {
    System.out.print(gamer);
    System.out.println("1 : Fold\n2 : Check/Call\n3 : Raise");
    return new Scanner(System.in).nextInt();
  }

  private void fold() {
    this.isPlaying = false;
  }

  private void call() {
    bet(round.getBiggestBet() - currentBet);
  }

  private void raise(Integer raiseAmount) {
    bet(raiseAmount);
  }

  private Integer getRaiseAmount() {
    int raiseAmount = 0;
    while (raiseAmount < round.game.getBigBlind() * 2 || raiseAmount + currentBet < round.getBiggestBet()) {  // put * 2 in a constant
      System.out.println("Choose a raise amount");
      try {
        raiseAmount = new Scanner(System.in).nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Bad input, try again");
      }
    }
    return raiseAmount;
  }

  Boolean hasEndedPreFlop() {
    return !isPlaying || currentBet >= round.getBiggestBet();
  }

  void playPreFlop() {
    switch (chooseAction()) {
      case 1:
        fold();
        break;
      case 2:
        this.call();
        break;
      case 3:
        raise(getRaiseAmount());
        break;
      default:
        playPreFlop();
    }
  }

  Integer getCurrentBet() {
    return currentBet;
  }

  Boolean getIsPlaying() {
    return isPlaying;
  }
}
