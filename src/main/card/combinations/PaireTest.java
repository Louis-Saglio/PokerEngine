package main.card.combinations;

import main.card.Card;
import main.card.Rank;
import main.card.Suit;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaireTest {

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
    Set<Paire> expected = new HashSet<>(Arrays.asList(
        new Paire(Rank.Ace),
        new Paire(Rank.Eight)
    ));
    Set<Combination> actual = Paire.buildFromCards(sourceCards);
    assertEquals(expected, actual);
  }

  @Test
  void buildFromCards2() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Eight)
    );
    Set<Combination> actual = Paire.buildFromCards(sourceCards);
    assertEquals(new HashSet<Paire>(), actual);
  }

  @Test
  void buildFromCards3() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Ace)
    );
    Set<Paire> expected = new HashSet<>(Collections.singletonList(new Paire(Rank.Ace)));
    Set<Combination> actual = Paire.buildFromCards(sourceCards);
    assertEquals(expected, actual);
  }

  @Test
  void buildFromCards4() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.HEART, Rank.Ace),
        new Card(Suit.SPADE, Rank.Ace),
        new Card(Suit.DIAMOND, Rank.Ace)
    );
    Set<Paire> expected = new HashSet<>(Collections.singletonList(new Paire(Rank.Ace)));
    Set<Combination> actual = Paire.buildFromCards(sourceCards);
    assertEquals(expected, actual);
  }

  @Test
  void buildFromCards5() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.HEART, Rank.Ace),
        new Card(Suit.SPADE, Rank.Ace)
    );
    Set<Paire> expected = new HashSet<>(Collections.singletonList(new Paire(Rank.Ace)));
    Set<Combination> actual = Paire.buildFromCards(sourceCards);
    assertEquals(expected, actual);
  }

  @Test
  void comparesWithSame() {
    assertEquals((Integer) 0, new Paire(Rank.Ace).comparesWithSame(new Paire(Rank.Ace)));
  }
}