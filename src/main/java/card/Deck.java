package main.java.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {

  private final Stack<Card> cards;
  // Maybe should extends Stack

  public Deck() {
    this.cards = new Stack<>();
    generateDeck();
    shuffleDeck();
  }

  private void generateDeck() {
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        this.cards.push(new Card(suit, rank));
      }
    }
  }

  private void shuffleDeck() {
    Collections.shuffle(this.cards);
    Collections.shuffle(this.cards);// todo why ?
  }

  public void printDeck() {
    this.cards.forEach(System.out::println);
  }

  public List<Card> getSomeCard(Integer nbr) {
    List<Card> cards = new ArrayList<>();
    for (int i = 0; i < nbr; i++) {
      cards.add(this.cards.pop());
    }
    return cards;
  }
}
