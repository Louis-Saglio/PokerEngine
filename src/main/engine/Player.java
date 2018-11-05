package main.engine;

import main.card.Card;
import main.card.combinations.Combination;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Player {
  private Boolean isPlaying = true;
  private Integer currentBet = 0;
  private final Hand hand; // may cause spaghetti code
  private final Gamer gamer;
  private List<Card> downCards;

  Player(Gamer gamer, Hand hand) {
    this.gamer = gamer;
    this.hand = hand;
  }

  void bet(Integer money) {
    currentBet += money;
  }

  void setDownCards(List<Card> downCards) {
    this.downCards = downCards;
  }

  private Integer chooseAction() {
    Scanner scanner = new Scanner(System.in);
    int nextInt = -1;
    while (nextInt != 1 && nextInt != 2 && nextInt != 3) {
      System.out.println(gamer);
      System.out.println("Biggest bet : " + hand.getBiggestBet() + " own bet : " + currentBet);
      System.out.println("1 : Fold\n2 : Check/Call\n3 : Raise");
      nextInt = scanner.nextInt();
    }
    return nextInt;
  }

  private void fold() {
    this.isPlaying = false;
  }

  private void call() {
    bet(hand.getBiggestBet() - currentBet);
  }

  private void raise(Integer raiseAmount) {
    bet(raiseAmount);
  }

  private Integer getRaiseAmount(Integer min) {
    // Check this in game ? In action system
    int raiseAmount = 0;
    while (raiseAmount < min || raiseAmount + currentBet < hand.getBiggestBet()) {  // put * 2 in a constant
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
    return getRaiseAmount(hand.game.getBigBlind() * 2);
  }

  Boolean hasEndedTurn() {
    return !isPlaying || currentBet >= hand.getBiggestBet();
  }

  void playPreFlop(Integer choice) {
    switch (choice) {
      case 1:
        fold();
        break;
      case 2:
        call();
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
    return getRaiseAmount(hand.game.getBigBlind());
  }

  void playFlop(Integer choice) {
    switch (choice) {
      case 1:
        fold();
        break;
      case 2:
        call();
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

  private Integer getTurnRaiseAmount() {
    return getRaiseAmount(hand.game.getBigBlind() * 2);
  }

  void playTurn(Integer choice) {
    switch (choice) {
      case 1:
        fold();
        break;
      case 2:
        call();
        break;
      case 3:
        raise(getTurnRaiseAmount());
        break;
      default:
        throw new RuntimeException("Invalid turn action");
    }
  }

  void playTurn() {
    playTurn(chooseAction());
  }

  Integer getRiverRaiseAmount() {
    return hand.game.getBigBlind();
  }

  void playRiver(Integer choice) {
    switch (choice) {
      case 1:
        fold();
        break;
      case 2:
        call();
        break;
      case 3:
        raise(getRiverRaiseAmount());
        break;
      default:
        throw new RuntimeException("Invalid river action");
    }
  }

  void playRiver() {
    playRiver(chooseAction());
  }

  Combination getBestCombination() {
    return null;
  }

  Integer getCurrentBet() {
    return currentBet;
  }

  Boolean getIsPlaying() {
    return isPlaying;
  }
}
