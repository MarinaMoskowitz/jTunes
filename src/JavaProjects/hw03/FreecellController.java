package JavaProjects.hw03;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import JavaProjects.hw02.Card;
import JavaProjects.hw02.PileType;
import JavaProjects.hw02.FreecellOperations;

/**
 * Controller for Freecell Model
 */
public class FreecellController implements IFreecellController<Card> {
  final Readable in;
  final Appendable out;

  /**
   * Constructs a new FreecellController.
   *
   * @param in   readable for system input
   * @parm  out  appendable for system input
   **/
  public FreecellController(Readable in, Appendable out) {
    try {
      Objects.requireNonNull(in);
      Objects.requireNonNull(out);
    } catch (NullPointerException e) {
      throw new IllegalStateException("readable and/or appendable must be initialized.");
    }
    this.in = in;
    this.out = out;
  }

  @Override
  public void playGame(List<Card> deck, FreecellOperations<Card> model, int numCascadePiles,
                       int numOpenPiles, boolean shuffle) {

    Scanner scan = new Scanner(this.in);

    try {
      Objects.requireNonNull(deck);
      Objects.requireNonNull(model);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("deck and/or model must be initialized");
    }

    try {
      model.startGame(deck, numCascadePiles, numOpenPiles, shuffle);
    } catch (IllegalArgumentException e) {
      helpAppendOut("Could not start game.");
      return;
    }

    while (!model.isGameOver()) {
      helpAppendOut(model.getGameState() + "\n");

      String quit = "Game quit prematurely.";
      int pileNumber = 0;
      PileType source = null;
      boolean validSourceMove = false;
      String sourceInput = scan.next();

      while (!validSourceMove) {
        if (playerEndGame(sourceInput)) {
          helpAppendOut(quit);
          return;
        }
        try {
          source = checkValidInputChar(sourceInput);
          pileNumber = Integer.parseInt(sourceInput.substring(1)) - 1;
          validSourceMove = true;
          break;
        } catch (IllegalArgumentException e) {
          helpAppendOut("Invalid move. Try again. Please enter a valid source pile\n");
          if (!scan.hasNext()) {
            return;
          }
          sourceInput = scan.next();
        }
      }


      int cardIndex = 0;
      boolean validCard = false;
      String cardInput = scan.next();

      while (!validCard) {
        if (playerEndGame(cardInput)) {
          helpAppendOut(quit);
          return;
        }
        try {
          cardIndex = Integer.parseInt(cardInput);
          validCard = true;
        } catch (NumberFormatException e) {
          helpAppendOut("Invalid move. Try again. Please enter a valid card index\n");
          if (!scan.hasNext()) {
            return;
          }
          cardInput = scan.next();
        }
      }

      PileType destination = null;
      int destPileNumber = 0;
      boolean validDestinationMove = false;
      String destinationInput = scan.next();

      while (!validDestinationMove) {
        try {
          if (playerEndGame(destinationInput)) {
            helpAppendOut(quit);
            return;
          } else {
            destination = checkValidInputChar(destinationInput);
            destPileNumber = Integer.parseInt(destinationInput.substring(1)) - 1;
            validDestinationMove = true;
          }
        } catch (IllegalArgumentException e) {
          helpAppendOut("Invalid move. Try again. Please enter a valid destination pile\n");
          if (!scan.hasNext()) {
            return;
          }
          destinationInput = scan.next();
        }
      }

      try {
        model.move(source, pileNumber, cardIndex, destination, destPileNumber);
      } catch (IllegalArgumentException e) {
        helpAppendOut("Invalid move. Try again. " + e.getMessage() + "\n");
      }
    }
    helpAppendOut("Game over.");
  }


  /**
   * HW4 Change.
   * Helper method to append the given string.
   *
   * @param    ap given string to be appended
   */
  private void helpAppendOut(String ap) {
    try {
      this.out.append(ap);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  /*
  * Helper method that determines if the user entered a q or Q to quit the game.
  *
  * @param str    the string the user enters
  * @return       a boolean value based on if the user entered q or Q
  */
  private boolean playerEndGame(String str) {
    return str.equals("q") || str.equals("Q");
  }

  /*
  * Helper method that checks if the user entered a valid pile charater.
  *
  * @param str    the string the user enters
  * @return       The pile type based on if the user entered a C, O, or F
  * @throws       IllegalArgumentException if the character entered is invalid
  */
  private PileType checkValidInputChar(String str) throws IllegalArgumentException {
    PileType pile;

    switch (str.charAt(0)) {
      case 'C':
        pile = PileType.CASCADE;
        return pile;
      case 'O':
        pile = PileType.OPEN;
        return pile;
      case 'F':
        pile = PileType.FOUNDATION;
        return pile;
      default: throw new IllegalArgumentException();
    }
  }
}