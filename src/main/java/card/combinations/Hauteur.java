package main.java.card.combinations;

import main.java.card.Card;
import main.java.card.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class Hauteur extends Combination {
  private static Integer value = 0;
  private List<Rank> ranks;

  Hauteur(Collection<Rank> ranks) {
    super(value);
    this.ranks = ranks.stream().sorted(Comparator.comparingInt(Rank::getValue)).sorted(Collections.reverseOrder()).collect(Collectors.toList());
  }

  static Integer getNextValue() {
    return value + 1;
  }

  public static List<Combination> buildFromCards(List<Card> cards) {
    List<Rank> ranks = new ArrayList<>();
    for (Card card : cards) {
      ranks.add(card.getRank());
    }
    return Collections.singletonList(new Hauteur(ranks));
  }

  List<Rank> getRanksForTest() {
    return ranks;
  }

  @Override
  Integer comparesWithSame(Combination combination) {
    Hauteur hauteur = (Hauteur) combination;
    if (ranks.size() != hauteur.ranks.size()) throw new RuntimeException("Can't compare hauteurs of different sizes");
    for (int i = 0; i < ranks.size(); i++) {
      if (ranks.get(i).getValue() > hauteur.ranks.get(i).getValue()) {
        return 1;
      } else if (ranks.get(i).getValue() < hauteur.ranks.get(i).getValue()) {
        return -1;
      }
    }
    return 0;
  }
}
