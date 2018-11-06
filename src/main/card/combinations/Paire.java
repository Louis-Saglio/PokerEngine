package main.card.combinations;

import main.card.Card;
import main.card.Rank;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Paire extends Combination {

  private static final Integer value = 0;
  private final Rank rank;

  public Paire(Rank rank) {
    super(value);
    this.rank = rank;
  }

  public static List<Paire> buildFromCards(List<Card> cards) {
    List<Paire> paires = new ArrayList<>();
    LinkedList<Rank> doneRanks = new LinkedList<>();
    for (Card card : cards) {
      for (Card card1 : cards) {
        if (card.getRank() == card1.getRank() && card != card1 && !doneRanks.contains(card.getRank())) {
          paires.add(new Paire(card.getRank()));
          doneRanks.add(card.getRank());
        }
      }
    }
    return paires;
  }

  static Integer getNextValue() {
    return value + 1;
  }

  Rank getRank() {
    return rank;
  }

  @Override
  Integer comparesWithSame(Combination combination) {
    Paire paire = (Paire) combination;
    if (rank.getValue() > paire.rank.getValue()) {
      return 1;
    } else if (rank.getValue() < paire.rank.getValue()) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return "Paire of " + rank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank);
  }
}
