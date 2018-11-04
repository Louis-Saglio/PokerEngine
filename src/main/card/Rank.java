package main.card;

public enum Rank {

  Ace(12),
  Two(0),
  Three(1),
  Four(2),
  Five(3),
  Six(4),
  Seven(5),
  Eight(7),
  Ten(8),
  Jack(9),
  Queen(10),
  King(11);

  private Integer rank;

  Rank(Integer rank){
    this.rank = rank;
  }

  private Integer getRank(){
    return this.rank;
  }

}