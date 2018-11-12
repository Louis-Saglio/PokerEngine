package main.java.card;

public enum Rank {

  Ace(13),
  Two(0),
  Three(1),
  Four(2),
  Five(3),
  Six(4),
  Seven(5),
  Eight(7),
  Nine(8),
  Ten(9),
  Jack(10),
  Queen(11),
  King(12);

  private final Integer value;

  Rank(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return this.value;
  }

}