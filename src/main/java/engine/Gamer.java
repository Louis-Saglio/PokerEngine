package main.java.engine;

class Gamer {
  private Integer allIn;
  private final String name;

  @Override
  public String toString() {
    return name;
  }

  Gamer(String name, Integer allIn) {
    this.allIn = allIn;
    this.name = name;
  }

  void increaseMoney(Integer money) {
    this.allIn += money;
  }
}
