package main.engine;

import main.card.Card;
import main.card.Rank;
import main.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

  @Test
  void getWinner() {
    Hand hand = new Hand(new Game(10000, Arrays.asList("p1", "p2", "p3")));
    hand.getPlayers().get(0).setDownCards(Arrays.asList(new Card(Suit.CLUB, Rank.Eight), new Card(Suit.HEART, Rank.Seven)));
    hand.getPlayers().get(1).setDownCards(Arrays.asList(new Card(Suit.CLUB, Rank.Ace), new Card(Suit.HEART, Rank.Ace)));
    hand.getPlayers().get(2).setDownCards(Arrays.asList(new Card(Suit.DIAMOND, Rank.Eight), new Card(Suit.SPADE, Rank.Seven)));
    Player actual = hand.getWinner();
    Player expected = hand.getPlayers().get(1);
    assertEquals(expected, actual);
  }
}