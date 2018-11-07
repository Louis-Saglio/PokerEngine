package main.card.combinations;

import main.card.Card;
import main.card.Rank;

import java.util.*;

public class Paire extends Combination {

  private static final Integer value = Hauteur.getNextValue();
  private final Rank rank;

  public Paire(Rank rank) {
    super(value);
    this.rank = rank;
  }

  public static Set<Combination> buildFromCards(List<Card> cards) {
    Set<Combination> paires = new HashSet<>();
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
