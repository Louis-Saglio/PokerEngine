package main.java.card.combinations;

import main.java.card.Card;
import main.java.card.Rank;
import main.java.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DoublePaireTest {

  @Test
  void DoublePaire() {
    List<Rank> expected = Arrays.asList(Rank.Ten, Rank.Eight);
    List<Rank> actual = new DoublePaire(Rank.Eight, Rank.Ten).getRanksForTest();
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void DoublePaire1() {
    List<Rank> expected = Arrays.asList(Rank.Eight, Rank.Two);
    List<Rank> actual = new DoublePaire(Rank.Two, Rank.Eight).getRanksForTest();
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void DoublePaire2() {
    List<Rank> expected = Arrays.asList(Rank.Ace, Rank.Two);
    List<Rank> actual = new DoublePaire(Rank.Two, Rank.Ace).getRanksForTest();
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
    Set<Combination> actual = DoublePaire.buildFromCards(sourceCards);
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
    Set<DoublePaire> expected = new HashSet<>(Arrays.asList(
        new DoublePaire(Rank.Eight, Rank.Ace),
        new DoublePaire(Rank.Ace, Rank.Ace)
    ));

    Set<Combination> actual = DoublePaire.buildFromCards(sourceCards);
    assertEquals(expected, actual);
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
    Set<DoublePaire> expected = new HashSet<>();
    expected.add(new DoublePaire(Rank.Eight, Rank.Ace));
    Set<Combination> actual = DoublePaire.buildFromCards(sourceCards);
    assertEquals(expected, actual);
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

  @Test
  void comparesWithOtherInferior() {
    Paire paire = new Paire(Rank.Five);
    DoublePaire doublePaire = new DoublePaire(Rank.Three, Rank.Two);
    assertEquals((Integer) 1, doublePaire.compares(paire));
  }
}