package main.java.card.combinations;

import main.java.card.Card;
import main.java.card.Cards;
import main.java.card.Rank;
import main.java.card.Suit;
import main.java.card.combinations.exceptions.CombinationCreationError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    Combination actual = new Full(sourceCards);
    assertEquals(expected, actual);
  }

  @Test
  void buildBestFromCardsFail() {
    Cards sourceCards = new Cards(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.SPADE, Rank.Ace),
        new Card(Suit.HEART, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.King),
        new Card(Suit.CLUB, Rank.Queen)
    );
    assertThrows(CombinationCreationError.class, () -> new Full(sourceCards));
  }

  @Test
  void comparesWithSame() {
    assertEquals((Integer) (-1), new Full(Rank.Three, Rank.Queen).compares(new Full(Rank.Three, Rank.King)));
  }

  @Test
  void comparesWithInferior() {
    assertEquals((Integer) 1, new Full(Rank.Six, Rank.Nine).compares(new Full(Rank.Five, Rank.Queen)));
  }
}