package main.java.engine;

import main.java.card.Card;
import main.java.card.Rank;
import main.java.card.Suit;
import main.java.card.combinations.Combination;
import main.java.card.combinations.DoublePaire;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

  @Test
  void getBestCombination() {
    Hand hand = new Hand(new Game(10000, Arrays.asList("p1")));
    hand.getBoard().clear();
    hand.getBoard().addAll(Arrays.asList(
        new Card(Suit.SPADE, Rank.Ace),
        new Card(Suit.HEART, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.Jack),
        new Card(Suit.DIAMOND, Rank.King),
        new Card(Suit.CLUB, Rank.Queen)
    ));
    hand.getPlayers().get(0).setDownCards(Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Eight)
    ));
    Combination expected = new DoublePaire(Rank.Ace, Rank.Eight);
    Combination actual = hand.getPlayers().get(0).getBestCombination();
    assertEquals(expected, actual);
  }
}