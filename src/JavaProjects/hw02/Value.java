package JavaProjects.hw02;

/**
 * Values for the thirteen possible values in a 52 suit-value deck of cards represented low to high.
 */
public enum Value {
  A(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10),
  J(11),
  Q(12),
  K(13);

  private final int value;

  /**
   * Constructs a card in terms of its value.
   *
   * @param value the color of the card
   */
  Value(int value) {
    this.value = value;
  }

  /**
   * Gets the value of a card.
   *
   * @return value the value of the card
   */
  public int getValue() {
    return  this.value;
  }
}


