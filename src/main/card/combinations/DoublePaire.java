package main.card.combinations;

import main.card.Card;
import main.card.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DoublePaire extends Combination {

  private static final Integer value = Paire.getNextValue();
  private final List<Rank> ranks;

  public DoublePaire(Rank rank1, Rank rank2) {
    super(value);
    ranks = Stream.of(rank1, rank2).sorted(Comparator.comparingInt(Rank::getValue)).collect(Collectors.toList());
  }

  public static List<DoublePaire> buildFromCards(List<Card> cards) {
    List<Paire> paires = Paire.buildFromCards(cards).stream().sorted(Combination::compares).collect(Collectors.toList());
    if (paires.size() % 2 == 1) {
      paires.remove(paires.size() - 1);
    }
    List<DoublePaire> doublePaires = new ArrayList<>();
    for (int i = 0; i < paires.size(); i += 2) {
      doublePaires.add(new DoublePaire(paires.get(i).getRank(), paires.get(i + 1).getRank()));
    }
    return doublePaires;
  }

  @Override
  Integer comparesWithSame(Combination combination) {
    DoublePaire doublePaire = (DoublePaire) combination;
    if (ranks.get(0).getValue() > doublePaire.ranks.get(0).getValue()) {
      return 1;
    } else if (ranks.get(0).getValue() < doublePaire.ranks.get(0).getValue()) {
      return -1;
    } else if (ranks.get(1).getValue() > doublePaire.ranks.get(1).getValue()) {
      return 1;
    } else if (ranks.get(1).getValue() < doublePaire.ranks.get(1).getValue()) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + " of " + ranks.get(0) + " and " + ranks.get(1);
  }
}
