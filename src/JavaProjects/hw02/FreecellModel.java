package JavaProjects.hw02;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Model for the Feecell Game.
 */
public class FreecellModel implements FreecellOperations<Card> {

  /**
   * HW4 CHANGES:
   * - changed all instance variables from private to protected
   * represents a list of cards in a foundation pile.
   * - Added a boolean variable to check if the game has started.
   */
  protected List<List<Card>> foundation;

  /**
   * represents a list of cards in an open pile.
   */
  protected List<List<Card>> open;

  /**
   * represents a list of cards in a cascade pile.
   */
  protected List<List<Card>> cascade;

  /**
   * represents whether the game has started yet or not.
   */
  protected boolean gameStart;

  /**
  * constructs a FreecellModel.
  */
  public FreecellModel() {
    this.foundation = new ArrayList<List<Card>>();
    this.open = new ArrayList<List<Card>>();
    this.cascade = new ArrayList<List<Card>>();
    this.gameStart = false;
  }

  /**
   * Makes a sorted deck of cards.
   *
   * @return a complete deck as an ArrayList of Cards
   */
  @Override
  public List<Card> getDeck() {
    List<Card> deck = new ArrayList<Card>();
    for (Suit s : Suit.values()) {
      for (Value v : Value.values()) {
        deck.add(new Card(v, s));
      }
    }
    return deck;
  }

  @Override
  public void startGame(List<Card> deck, int numCascadePiles, int numOpenPiles, boolean shuffle) {

    if (!isValidDeck(deck) || deck == null || numCascadePiles < 4 || numOpenPiles < 1) {
      throw new IllegalArgumentException("invalid deck");
    } else {

      this.foundation = new ArrayList<List<Card>>();
      this.open = new ArrayList<List<Card>>();
      this.cascade = new ArrayList<List<Card>>();

      for (int i = 0; i < 4; i++) {
        foundation.add(new ArrayList<>());
      }

      for (int i = 0; i < numOpenPiles; i++) {
        open.add(new ArrayList<>());
      }

      for (int i = 0; i < numCascadePiles; i++) {
        cascade.add(new ArrayList<>());
      }

      if (shuffle) {
        Collections.shuffle(deck);
      }
      for (int i = 0; i < deck.size(); i++) {
        cascade.get(i % numCascadePiles).add(deck.get(i));
      }
    }
    this.gameStart = true;
  }

  /**
  * Helper method to determine if a deck is valid.
  *
  * @param    the deck
  * @return   boolean value
  */
  private boolean isValidDeck(List<Card> deck) {
    if (deck.size() != 52) {
      return false;
    } else {
      List<Card> dup = new ArrayList<Card>();
      for (Card c : deck) {
        if (dup.contains(c)) {
          return false;
        }
        dup.add(c);
      }
    }
    return true;
  }

  /**
   * HW4 - changed from private to protected
   * Helper method to check if invalid move to source or destination piles.
   *
   * @param sourcePile the pile being checked
   * @param pileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the deck is invalid
   */
  protected void checkIllegalPile(List<List<Card>> sourcePile, int pileNumber) {
    if (sourcePile.size() - 1 < pileNumber || pileNumber < 0) {
      throw new IllegalArgumentException("invalid move");
    }
  }

  /**
   * Helper method for the move method to place the sourceCard onto the destinationPile.
   *
   * @param sourceCard     the card being moved
   * @param destination    the type of the destination pile
   * @param destPileNumber the pile number of the given type, starting at 0
   * @throws IllegalArgumentException if the deck is invalid
   */
  protected void placeCard(Card sourceCard, PileType destination,
                         int destPileNumber) {
    List<Card> destinationPile;
    Card destinationCard;

    switch (destination) {
      case FOUNDATION:
        checkIllegalPile(foundation, destPileNumber);
        destinationPile = this.foundation.get(destPileNumber);
        if (destinationPile.size() == 0 && sourceCard.getValue() == Value.A.getValue()) {
          destinationPile.add(sourceCard);
        } else if (destinationPile.size() > 0) {
          destinationCard = destinationPile.get(destinationPile.size() - 1);
          if ((sourceCard.getValue() == destinationCard.getValue() + 1)
                  && sourceCard.getSuitColor() == destinationCard.getSuitColor()) {
            destinationPile.add(sourceCard);
          } else {
            throw new IllegalArgumentException("invalid move");
          }
        } else {
          throw new IllegalArgumentException("invalid move");
        }
        break;
      case OPEN:
        checkIllegalPile(open, destPileNumber);
        destinationPile = this.open.get(destPileNumber);
        if (destinationPile.size() == 0) {
          destinationPile.add(sourceCard);
        } else {
          throw new IllegalArgumentException("invalid move");
        }
        break;
      case CASCADE:
        checkIllegalPile(cascade, destPileNumber);
        destinationPile = this.cascade.get(destPileNumber);
        if (destinationPile.size() > 0) {
          destinationCard = destinationPile.get(destinationPile.size() - 1);
          if (sourceCard.getValue() == destinationCard.getValue() - 1
                  &&  !sourceCard.getSuitColor().equals(destinationCard.getSuitColor())) {
            destinationPile.add(sourceCard);
          } else {
            throw new IllegalArgumentException("invalid move");
          }
        } else {
          destinationPile.add(sourceCard);
        }
        break;
      default:
        throw new IllegalArgumentException("invalid move");
    }
  }

  @Override
  public void move(PileType source, int pileNumber, int cardIndex, PileType destination,
                   int destPileNumber) {
    Card sourceCard;
    List<Card> sourcePile;

    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over, can't make moves");
    }
    if (pileNumber < 0 || destPileNumber < 0) {
      throw new IllegalArgumentException("source and/or destination pile numbers aren't valid.");
    }
    if (source == null || destination == null) {
      throw new IllegalArgumentException("source and/or destination piles can't be null");
    }
    if (source.equals(destination) && pileNumber == destPileNumber) {
      return;
    }

    switch (source) {
      case FOUNDATION:
        checkIllegalPile(foundation, pileNumber);
        sourcePile = this.foundation.get(pileNumber);
        if (cardIndex != sourcePile.size()  - 1 || sourcePile.size() == 0) {
          throw new IllegalArgumentException("can't pick up this card\n");
        }
        sourceCard = sourcePile.get(cardIndex);
        break;

      case OPEN:
        checkIllegalPile(open, pileNumber);
        sourcePile = this.open.get(pileNumber);
        if (cardIndex != sourcePile.size() - 1 || sourcePile.size() == 0) {
          throw new IllegalArgumentException("can't pick up this card\n");
        }
        sourceCard = sourcePile.get(cardIndex);
        break;

      case CASCADE:
        checkIllegalPile(cascade, pileNumber);
        sourcePile = this.cascade.get(pileNumber);
        if (cardIndex != sourcePile.size() - 1 || sourcePile.size() <= 0) {
          throw new IllegalArgumentException("can't pick up this card\n");
        }
        sourceCard = sourcePile.get(cardIndex);
        break;
      default:
        throw new IllegalArgumentException("can't pick it up\n");
    }
    placeCard(sourceCard, destination, destPileNumber);
    sourcePile.remove(sourceCard);
  }

  @Override
  public boolean isGameOver() {
    return foundation.get(0).size() == 13 && foundation.get(1).size() == 13
            && foundation.get(2).size() == 13 && foundation.get(3).size() == 13;
  }

  @Override
  public String getGameState() {
    if (cascade == null || foundation == null || open == null) {
      return "";
    }
    String format = "";

    format += this.getGameStateHelp(this.foundation, "F");
    format += this.getGameStateHelp(this.open, "O");
    format += this.getGameStateHelp(this.cascade, "C");
    return format;
  }

  /**
   * Helper for the getGameState method.
   *
   * @param pileType    the pileType to be formatted
   * @param letterType  the letter representing the PileType for formatting
   * @return return     the formatted String output for the given Pile
   */
  private String getGameStateHelp(List<List<Card>> pileType, String letterType) {
    String format = "";
    for (int i = 0; i < pileType.size(); i++) {
      format += letterType + (i + 1) + ":";
      List<Card> currPile = pileType.get(i);
      for (Card c : currPile) {
        if (currPile.get(currPile.size() - 1) == c) {
          format += " " + c.toString();
        } else {
          format += " " + c.toString() + ",";
        }
      }
      if (pileType != this.cascade || pileType.size() - 1 != i) {
        format += "\n";
      }
    }
    return format;
  }
}
