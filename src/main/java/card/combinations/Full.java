package main.java.card.combinations;

import main.java.card.Cards;
import main.java.card.Rank;

import java.util.Comparator;

public class Full extends Combination {

  private static Integer value = DoublePaire.getNextValue();
  private Rank doubleRank;
  private Rank tripletRank;

  Full(Rank tripletRank, Rank doubleRank) {
    super(value);
    this.tripletRank = tripletRank;
    this.doubleRank = doubleRank;
  }

  public static Combination buildBestFromCards(Cards cards) {
    Rank bestTriplet = cards.getRanksByMinimumNbr(3)
        .stream()
        .max(Comparator.comparingInt(Rank::getValue))
        .orElse(null);
    if (bestTriplet == null) {
      return null;
    }
    Rank bestDouble = cards.getRanksByMinimumNbr(2)
        .stream()
        .filter(rank -> rank != bestTriplet)
        .max(Comparator.comparingInt(Rank::getValue))
        .orElse(null);
    return bestDouble != null ? new Full(bestTriplet, bestDouble) : null;
  }

  @Override
  Integer comparesWithSame(Combination combination) {
    Full full = (Full) combination;
    if (tripletRank.getValue() > full.tripletRank.getValue()) {
      return 1;
    } else if (tripletRank.getValue() < full.tripletRank.getValue()) {
      return -1;
    } else if (doubleRank.getValue() > full.doubleRank.getValue()) {
      return 1;
    } else if (doubleRank.getValue() < full.doubleRank.getValue()) {
      return -1;
    }
    return 0;
  }
}
