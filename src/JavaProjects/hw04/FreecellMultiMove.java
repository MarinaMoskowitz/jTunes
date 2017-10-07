package JavaProjects.hw04;

import JavaProjects.hw02.Card;
import JavaProjects.hw02.FreecellModel;
import JavaProjects.hw02.PileType;

import java.util.ArrayList;
import java.util.List;


/**
 * A multi-move Freecell Model.
 */
public class FreecellMultiMove extends FreecellModel {

  public FreecellMultiMove() {
    super();
  }

  /**
   * Helper method for placeCards that sets the maximum amount of cards allowed to move at one time.
   *
   * @return double    the maximum amount of cards allowed to move at once
   */
  protected double placeCardHelper() {
    int freeOpenPiles = 0;
    for (List<Card> o : open) {
      if (o.isEmpty()) {
        freeOpenPiles += 1;
      }
    }
    int freeCascadePiles = 0;
    for (List<Card> c : cascade) {
      if (c.isEmpty()) {
        freeOpenPiles += 1;
      }
    }
    return (freeOpenPiles + 1) * Math.pow(2, freeCascadePiles);
  }


  /**
   * A method to place multiple cards in a pile.
   *
   * @param multicards        the cards being moved to the destination pile
   * @param destination       the PileType of destination pile that the cards are being moved to
   * @param destPileNumber    the pileNumber of destination that the cards are being moved to
   */
  void placeCards(List<Card> multicards, PileType destination, int destPileNumber) {
    List<Card> destinationPile;
    Card destinationCard;
    if (destination == PileType.CASCADE) {
      checkIllegalPile(cascade, destPileNumber);
      destinationPile = cascade.get(destPileNumber);
      if (destinationPile.size() == 0 && (multicards.size() < placeCardHelper())) {
        destinationPile.addAll(multicards);
      }
      destinationCard = destinationPile.get(destinationPile.size() - 1);
      if (destinationCard.getValue() - 1 == multicards.get(0).getValue()
              && !multicards.get(0).getSuitColor().equals(destinationCard.getSuitColor())
              && multicards.size() < placeCardHelper()) {
        destinationPile.addAll(multicards);
      } else {
        throw new IllegalArgumentException("invalid move");
      }
    } else {
      throw new IllegalArgumentException("you suck");
    }
  }

  /**
   * Checks to see if pile being moved is a valid pile.
   *
   * @param multicards   the pile of cards being moved
   * @return boolean     true if the pile is valid, false if not
   */
  protected boolean moveHelper(List<Card> multicards) {
    List<Card> helperList = new ArrayList<Card>();
    helperList.addAll(multicards);
    Card sourceCard = helperList.remove(0);
    for (Card c : helperList) {
      if (c.getValue() == sourceCard.getValue() - 1
              && !sourceCard.getSuitColor().equals(c.getSuitColor())) {
        sourceCard = c;
      } else {
        return false;
      }
    }
    return true;
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
        if (cardIndex != sourcePile.size() - 1 || sourcePile.size() == 0) {
          throw new IllegalArgumentException("can't pick up this card\n");
        }
        sourceCard = sourcePile.get(cardIndex);
        placeCard(sourceCard, destination, destPileNumber);
        sourcePile.remove(sourceCard);
        break;
      case OPEN:
        checkIllegalPile(open, pileNumber);
        sourcePile = this.open.get(pileNumber);
        if (cardIndex != sourcePile.size() - 1 || sourcePile.size() == 0) {
          throw new IllegalArgumentException("can't pick up this card\n");
        }
        sourceCard = sourcePile.get(cardIndex);
        placeCard(sourceCard, destination, destPileNumber);
        sourcePile.remove(sourceCard);
        break;
      case CASCADE:
        checkIllegalPile(cascade, pileNumber);
        sourcePile = this.cascade.get(pileNumber);
        if (cardIndex == sourcePile.size() - 1 && sourcePile.size() > 0) {
          sourceCard = sourcePile.get(cardIndex);
          placeCard(sourceCard, destination, destPileNumber);
          sourcePile.remove(sourceCard);
        } else if (cardIndex < sourcePile.size() - 1 && sourcePile.size() > 0) {
          List<Card> multiCards = sourcePile.subList(cardIndex, sourcePile.size());
          if (moveHelper(multiCards)) {
            placeCards(multiCards, destination, destPileNumber);
            sourcePile.removeAll(multiCards);
          }
        } else {
          throw new IllegalArgumentException("can't pick up this card\n");
        }
        break;
      default:
        throw new IllegalArgumentException("invalid move");
    }
  }
}
