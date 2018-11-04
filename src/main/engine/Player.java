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
    Scanner scanner = new Scanner(System.in);
    int nextInt = -1;
    while (nextInt != 1 && nextInt != 2 && nextInt != 3) {
      System.out.println(gamer);
      System.out.println("Biggest bet : " + round.getBiggestBet() + " own bet : " + currentBet);
      System.out.println("1 : Fold\n2 : Check/Call\n3 : Raise");
      nextInt = scanner.nextInt();
    }
    return nextInt;
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

  private Integer getRaiseAmount(Integer min) {
    int raiseAmount = 0;
    while (raiseAmount < min || raiseAmount + currentBet < round.getBiggestBet()) {  // put * 2 in a constant
      System.out.println("Choose a raise amount");
      try {
        raiseAmount = new Scanner(System.in).nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Bad input, try again");
      }
    }
    return raiseAmount;
  }

  private Integer getPreFlopRaiseAmount() {
    return getRaiseAmount(round.game.getBigBlind() * 2);
  }

  Boolean hasEndedPreFlop() {
    return !isPlaying || currentBet >= round.getBiggestBet();
  }

  void playPreFlop(Integer choice) {
    switch (choice) {
      case 1:
        fold();
        break;
      case 2:
        this.call();
        break;
      case 3:
        raise(getPreFlopRaiseAmount());
        break;
      default:
        throw new RuntimeException("Invalid pre flop action");
    }
  }

  void playPreFlop() {
    playPreFlop(chooseAction());
  }

  private Integer getFlopRaiseAmount() {
    return getRaiseAmount(round.game.getBigBlind());
  }

  void playFlop(Integer choice) {
    switch (choice) {
      case 1:
        fold();
        break;
      case 2:
        this.call();
        break;
      case 3:
        raise(getFlopRaiseAmount());
        break;
      default:
        throw new RuntimeException("Invalid flop action");
    }
  }

  void playFlop() {
    playFlop(chooseAction());
  }

  Integer getCurrentBet() {
    return currentBet;
  }

  Boolean getIsPlaying() {
    return isPlaying;
  }
}
