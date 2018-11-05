package main.card.combinations;

import main.card.Card;

import java.util.List;

public abstract class Combination {

  private final Integer value;
  protected List<Card> cards;

  Combination(Integer value) {
    this.value = value;
  }

//  abstract List<Combination> buildFromCards(Card ...cards);

  abstract Integer comparesWithSame(Combination combination);

  public Integer compares(Combination combination) {
    if (value > combination.value) {
      return 1;
    } else if (value < combination.value) {
      return -1;
    } else {
      return comparesWithSame(combination);
    }
  }

}
