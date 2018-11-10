package main.java.card.combinations;

import main.java.card.Card;
import main.java.card.Cards;
import main.java.card.Rank;
import main.java.card.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FullTest {

  @Test
  void buildBestFromCards() {
    Cards sourceCards = new Cards(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.SPADE, Rank.Ace),
        new Card(Suit.HEART, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.King),
        new Card(Suit.CLUB, Rank.Queen)
    );
    Full expected = new Full(Rank.Ace, Rank.Eight);
    Combination actual = Full.buildBestFromCards(sourceCards);
    assertEquals(expected, actual);
  }

  @Test
  void comparesWithSame() {
    assertEquals((Integer) (-1), new Full(Rank.Three, Rank.Queen).compares(new Full(Rank.Three, Rank.King)));
  }
}