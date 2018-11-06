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
    List<Paire> expected = Arrays.asList(
        new Paire(Rank.Ace),
        new Paire(Rank.Eight)
    );
    List<Paire> actual = Paire.buildFromCards(sourceCards);
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void buildFromCards2() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Eight)
    );
    List<Paire> actual = Paire.buildFromCards(sourceCards);
    assertArrayEquals(new Paire[]{}, actual.toArray());
  }

  @Test
  void buildFromCards3() {
    List<Card> sourceCards = Arrays.asList(
        new Card(Suit.CLUB, Rank.Ace),
        new Card(Suit.CLUB, Rank.Ace)
    );
    List<Paire> expected = Collections.singletonList(new Paire(Rank.Ace));
    List<Paire> actual = Paire.buildFromCards(sourceCards);
    assertArrayEquals(expected.toArray(), actual.toArray());
  }

  @Test
  void comparesWithSame() {
    assertEquals((Integer) 0, new Paire(Rank.Ace).comparesWithSame(new Paire(Rank.Ace)));
  }
}