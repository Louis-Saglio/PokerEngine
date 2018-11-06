package main.card.combinations;

import main.card.Card;
import main.card.Rank;
import main.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DoublePaireTest {

  @Test
  void DoublePaire() {
    List<Rank> expected = Arrays.asList(Rank.Eight, Rank.Ten);
    List<Rank> actual = new DoublePaire(Rank.Ten, Rank.Eight).getRanksForTest();
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void buildFromCards1() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Eight),
        new Card(Suit.SPADE, Rank.Ace),
        new Card(Suit.HEART, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.Jack),
        new Card(Suit.DIAMOND, Rank.King),
        new Card(Suit.CLUB, Rank.Queen)
    );
    List<DoublePaire> expected = Collections.singletonList(
        new DoublePaire(Rank.Eight, Rank.Ace)
    );
    List<DoublePaire> actual = DoublePaire.buildFromCards(sourceCards);
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void buildFromCards2() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Eight),
        new Card(Suit.SPADE, Rank.Ace),
        new Card(Suit.HEART, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.Jack),
        new Card(Suit.DIAMOND, Rank.King),
        new Card(Suit.DIAMOND, Rank.Ace),
        new Card(Suit.CLUB, Rank.Ace)
    );
    List<DoublePaire> expected = Arrays.asList(
        new DoublePaire(Rank.Eight, Rank.Ace),
        new DoublePaire(Rank.Ace, Rank.Ace)
    );
    List<DoublePaire> actual = DoublePaire.buildFromCards(sourceCards);
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void buildFromCards3() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Eight),
        new Card(Suit.SPADE, Rank.Ace),
        new Card(Suit.HEART, Rank.Eight),
        new Card(Suit.DIAMOND, Rank.Jack),
        new Card(Suit.DIAMOND, Rank.King),
        new Card(Suit.DIAMOND, Rank.Ace)
    );
    List<DoublePaire> expected = Collections.singletonList(
        new DoublePaire(Rank.Eight, Rank.Ace)
    );
    List<DoublePaire> actual = DoublePaire.buildFromCards(sourceCards);
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void comparesWithSameEquals() {
    DoublePaire first = new DoublePaire(Rank.Ace, Rank.Jack);
    DoublePaire second = new DoublePaire(Rank.Jack, Rank.Ace);
    assertEquals((Integer) 0, first.compares(second));
  }

  @Test
  void comparesWithSameSuperior() {
    DoublePaire first = new DoublePaire(Rank.Ace, Rank.Jack);
    DoublePaire second = new DoublePaire(Rank.Ace, Rank.Two);
    assertEquals((Integer) 1, first.compares(second));
  }

  @Test
  void comparesWithSameInferior() {
    DoublePaire first = new DoublePaire(Rank.King, Rank.Jack);
    DoublePaire second = new DoublePaire(Rank.Ace, Rank.Two);
    assertEquals((Integer) (-1), first.compares(second));
  }
}