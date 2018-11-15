package main.java.engine;

import main.java.card.Card;
import main.java.card.Cards;
import main.java.card.combinations.*;
import main.java.card.combinations.exceptions.CombinationCreationError;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

class Player {
  private Boolean isPlaying = true;
  private Integer currentBet = 0;
  private final Hand hand; // may cause spaghetti code
  private final Gamer gamer;
  private List<Card> downCards;

  Player(Gamer gamer, Hand hand) {
    System.out.println("Player.Player");
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
    int nextInt = -1;
    while (nextInt != 1 && nextInt != 2 && nextInt != 3) {
      System.out.println(gamer);
      System.out.println("Biggest bet : " + hand.getBiggestBet() + " own bet : " + currentBet);
      System.out.println("1 : Fold\n2 : Check/Call\n3 : Raise");
      nextInt = hand.game.readFrom();
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

  private Integer chooseRaiseAmount(Integer min) {
    // Check this in game ? In action system
    int raiseAmount = 0;
    while (raiseAmount < min || raiseAmount + currentBet < hand.getBiggestBet()) {  // put * 2 in a constant
      System.out.println("Choose a raise amount");
      try {
        raiseAmount = hand.game.readFrom();
      } catch (InputMismatchException e) {
        System.out.println("Bad input, try again");
      }
    }
    return raiseAmount;
  }

  private Integer getPreFlopRaiseAmount() {
    return chooseRaiseAmount(hand.game.getBigBlind() * 2);
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
    return chooseRaiseAmount(hand.game.getBigBlind());
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
    return chooseRaiseAmount(hand.game.getBigBlind() * 2);
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
    return chooseRaiseAmount(hand.game.getBigBlind());
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

  private Cards getAllCards() {
    Cards allCards = new Cards();
    allCards.addAll(downCards);
    allCards.addAll(hand.getBoard());
    return allCards;
  }

  Combination getBestCombination() {
    List<Combination> combinations = new ArrayList<Combination>() {
      @Override
      public boolean add(Combination o) {
        if (o == null) return false;
        return super.add(o);
      }
    };
    Cards allCards = getAllCards();
    try {
      combinations.add(new Hauteur(allCards));
    } catch (CombinationCreationError ignored) {
    }
    try {
      combinations.add(new Paire(allCards));
    } catch (CombinationCreationError ignored) {
    }
    try {
      combinations.add(new Full(allCards));
    } catch (CombinationCreationError ignored) {
    }
    try {
      combinations.add(new DoublePaire(allCards));
    } catch (CombinationCreationError ignored) {
    }
    Combination bestCombination = combinations.stream().max(Combination::compares).orElse(null);
    System.out.println(toString() + " : " + bestCombination);
    return bestCombination;
  }

  Integer comparesCards(Player player) {
    Combination bestCombination = getBestCombination();
    Combination playerBestCombination = player.getBestCombination();
    if (bestCombination == null && playerBestCombination == null) return 0;
    else if (bestCombination == null) return -1;
    else if (playerBestCombination == null) return 1;
    return bestCombination.compares(playerBestCombination);
  }

  void win(Integer money) {
    gamer.increaseMoney(money - currentBet);
  }

  void loose() {
    gamer.increaseMoney(-currentBet);
  }

  Integer getCurrentBet() {
    return currentBet;
  }

  Boolean getIsPlaying() {
    return isPlaying;
  }

  @Override
  public String toString() {
    return gamer.toString();
  }
}
