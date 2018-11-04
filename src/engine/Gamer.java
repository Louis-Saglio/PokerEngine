package engine;

public class Gamer {
  private Integer money;
  private final String name;

  @Override
  public String toString() {
    return name;
  }

  public Gamer(String name, Integer money) {
    this.money = money;
    this.name = name;
  }

  void decreaseMoney(Integer toRemove) {
    this.money -= toRemove;
  }
}
