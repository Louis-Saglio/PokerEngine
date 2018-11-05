package main.engine;

import main.card.Card;
import main.card.Rank;
import main.card.Suit;
import main.card.combinations.Combination;
import main.card.combinations.Paire;
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
    Combination expected = new Paire(Rank.Ace);
    Combination actual = hand.getPlayers().get(0).getBestCombination();
    assertEquals(expected, actual);
  }
}