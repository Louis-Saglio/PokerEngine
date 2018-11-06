package main.card.combinations;

import main.card.Card;
import main.card.Rank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoublePaire extends Combination {

  private static final Integer value = Paire.getNextValue();

  public DoublePaire(Rank rank1, Rank rank2) {
    super(value);
    ranks = Stream.of(rank1, rank2).sorted(Comparator.comparingInt(Rank::getValue)).collect(Collectors.toList()); // Weaker first
  }

  private final List<Rank> ranks;

  public static Set<Combination> buildFromCards(List<Card> cards) {
    LinkedHashMap<Rank, Integer> nbrByRank = new LinkedHashMap<>();
    for (Card card : cards) {
      if (!nbrByRank.containsKey(card.getRank())) {
        nbrByRank.put(card.getRank(), 0);
      }
      nbrByRank.put(card.getRank(), nbrByRank.get(card.getRank()) + 1);
    }
    List<Rank> paires = new ArrayList<>();
    for (Map.Entry<Rank, Integer> key_value : nbrByRank.entrySet()) {
      int paireNbr = key_value.getValue() / 2;
      for (int i = 0; i < paireNbr; i++) {
        paires.add(key_value.getKey());
      }
    }
    Set<Combination> doublePaires = new HashSet<>();
    for (int i = 0; i < paires.size(); i++) {
      for (int j = 0; j < paires.size(); j++) {
        DoublePaire doublePaire = new DoublePaire(paires.get(i), paires.get(j));
        if (i != j) {
          doublePaires.add(doublePaire);
        }
      }
    }
    return doublePaires;
  }

  public List<Rank> getRanksForTest() {
    return ranks;
  }

  @Override
  Integer comparesWithSame(Combination combination) {
    DoublePaire doublePaire = (DoublePaire) combination;
    if (ranks.get(1).getValue() > doublePaire.ranks.get(1).getValue()) {
      return 1;
    } else if (ranks.get(1).getValue() < doublePaire.ranks.get(1).getValue()) {
      return -1;
    } else if (ranks.get(0).getValue() > doublePaire.ranks.get(0).getValue()) {
      return 1;
    } else if (ranks.get(0).getValue() < doublePaire.ranks.get(0).getValue()) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + " of " + ranks.get(1) + " and " + ranks.get(0);
  }
}
