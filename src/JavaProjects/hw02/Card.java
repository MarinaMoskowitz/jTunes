package JavaProjects.hw02;

/**
 * A card represented by its value and suit.
 */
public class Card {

  private Value value;
  private Suit suit;

  /**
   * Constructs a card in terms of its value and suit.
   * @param value the number value of a card
   * @param suit the suit type of a card
   */
  public Card(Value value, Suit suit) {
    this.value = value;
    this.suit = suit;
  }

  /**
   * Prints cards in terms of its value and suit.
   *
   * @return Card type as a string
   */
  @Override
  public String toString() {
    String val;
    String sui;
    switch (this.value) {
      case A:
        val = "A";
        break;
      case TWO:
        val = "2";
        break;
      case THREE:
        val = "3";
        break;
      case FOUR:
        val = "4";
        break;
      case FIVE:
        val = "5";
        break;
      case SIX:
        val = "6";
        break;
      case SEVEN:
        val = "7";
        break;
      case EIGHT:
        val = "8";
        break;
      case NINE:
        val = "9";
        break;
      case TEN:
        val = "10";
        break;
      case J:
        val = "J";
        break;
      case Q:
        val = "Q";
        break;
      case K:
        val = "K";
        break;
      default: throw new IllegalArgumentException("invalid value");
    }
    switch (this.suit) {
      case SPADES:
        sui = "♠";
        break;
      case CLUBS:
        sui = "♣";
        break;
      case HEARTS:
        sui = "♥";
        break;
      case DIAMONDS:
        sui = "♦";
        break;
      default: throw new IllegalArgumentException("invalid suit");
    }

    return val + sui;
  }

  /**
   * HW-4 Addition: since getValue in Value Enum is private, made this method to access it.
   * Gets the value of a card.
   *
   * @return value the value of the card
   */

  public int getValue() {
    return  this.value.getValue();
  }

  /**
   * HW-4 Addition: since getSuitColor in Suit Enum is private, made this method to access it.
   * Gets the color type of a suit.
   *
   * @return color the color type of a suit
   */
  public String getSuitColor() {
    return this.suit.getSuitColor();
  }
}
