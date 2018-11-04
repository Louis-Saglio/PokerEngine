package main.card;

public class Card {

  private Rank rank;
  private Suit suit;

  public Card(Suit suit, Rank rank) {
    this.rank = rank;
    this.suit = suit;
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  @Override
  public String toString() {
    return rank + " of " + suit;
  }
}
