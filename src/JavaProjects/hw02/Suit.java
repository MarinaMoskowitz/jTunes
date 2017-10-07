package JavaProjects.hw02;

/**
 * Type for the four types of suits in a deck of 52 suit-value cards.
 * Spades: This suit represents the type spades in a deck of cards.
 * Spades can only be of the color black.
 * Clubs: This suit represents the type clubs in a deck of cards.
 * Clubs can only be of the color black.
 * hearts: This suit represents the type hearts in a deck of cards.
 * Hearts can only be of the color red.
 * Dimonds: This suit represents the type Dimonds in a deck of cards.
 * Dimonds can only be of the color red.
 */
public enum Suit {
  SPADES("BLACK"),
  CLUBS("BLACK"),
  HEARTS("RED"),
  DIAMONDS("RED");

  private final String color;

  /**
   * Constructs a suit in terms of its type in color.
   *
   * @param color the color type of the suit
   */
  Suit(String color) {
    this.color = color;
  }

  /**
   * Gets the color type of a suit.
   *
   * @return color the color type of a suit
   */
  public String getSuitColor() {
    return this.color;
  }
}
