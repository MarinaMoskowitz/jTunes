/*
package cs3500.hw03;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import FreecellOperations;
import Card;
import FreecellModel;
import FreecellController;
import IFreecellController;

public class FreecellControllerTest {

  String test = "F1:\n"
          + "F2:\n"
          + "F3:\n"
          + "F4:\n"
          + "O1:\n"
          + "O2:\n"
          + "O3:\n"
          + "O4:\n"
          + "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦,\n"
          + "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦,\n"
          + "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦,\n"
          + "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦";

  //tests if readable is null
  @Test(expected = IllegalStateException.class)
  public void testNullReadable() {
    Reader in = new StringReader("C1");
    IFreecellController controller = new FreecellController(in, null);
  }

  //tests if appendable is null
  @Test(expected = IllegalStateException.class)
  public void testNullAppendable() {
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(null, out);
  }

  //tests if readable and appendable are null
  @Test(expected = IllegalStateException.class)
  public void testNullReadbaleAndAppendable() {
    IFreecellController controller = new FreecellController(null, null);
  }

  //tests if deck is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullDeck() throws IOException {
    Reader in = new StringReader("C1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    controller.playGame(null, model, 4, 4, true);
  }

  //tests if model is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() throws IOException {
    Reader in = new StringReader("C1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, null, 4, 4, true);
  }

  //tests if deck and model are null
  @Test(expected = IllegalArgumentException.class)
  public void testNullDeckAndModel() throws IOException {
    Reader in = new StringReader("C1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    controller.playGame(null, null, 4, 4, true);
  }

  @Test
  public void testOutOfBoundsCardIndex() {
    Reader in = new StringReader("C1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, -4, 0, true);
    String error = "Could not start game.";
    assertEquals(error, out.toString());
  }

  //tests initial game state
  @Test
  public void testInitialGame() throws IOException {
    Reader in = new StringReader("C1 13 O1 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1:\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
                    "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
                    "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
                    "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
                    "F1:\n" +
                    "F2:\n" +
                    "F3:\n" +
                    "F4:\n" +
                    "O1: 10♦\n" +
                    "O2:\n" +
                    "O3:\n" +
                    "O4:\n" +
                    "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦\n" +
                    "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
                    "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
                    "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
                    "Game quit prematurely.",
            out.toString());
  }

  //tests quit as the first input with q
  @Test
  public void testQuit0() {
    Reader in = new StringReader("q 12 O1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertEquals(model.getGameState() + "\nGame quit prematurely.", out.toString());
  }

  //tests quit as the first input with Q
  @Test
  public void testQuit1() {
    Reader in = new StringReader("Q 12 O1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertEquals(model.getGameState() + "\nGame quit prematurely.", out.toString());
  }

  //tests quit as the second input with Q
  @Test
  public void testQuit2() {
    Reader in = new StringReader("C3 q O1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertEquals(model.getGameState() + "\nGame quit prematurely.", out.toString());
  }

  //tests quit as the second input
  @Test
  public void testQuit3() {
    Reader in = new StringReader("C3 Q O1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //tests multiple Qs as the second input
  @Test
  public void testInvalidInput() {
    Reader in = new StringReader("C3 QQQQ O1");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n", out.toString());
  }

  //tests quit as the third input
  @Test
  public void testQuit4() {
    Reader in = new StringReader("C3 12 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertEquals(model.getGameState() + "\nGame quit prematurely.", out.toString());
  }

  //tests quit for any input after 3
  @Test
  public void testQuit5() {
    Reader in = new StringReader("C3 12 O1 Q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //tests quit for invalid source pile
  @Test
  public void testQuit6() {
    Reader in = new StringReader("MM 12 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertEquals(model.getGameState() + "\nGame quit prematurely.", out.toString());
  }

  //tests quit for invalid source pile re-do
  @Test
  public void testQuit7() {
    Reader in = new StringReader("MM 12 F1 Q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid source pile\n" +
            "Game quit prematurely.\n", out.toString());
  }

  //tests quit for invalid card index
  @Test
  public void testQuit8() {
    Reader in = new StringReader("C1 -13 Q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertEquals(model.getGameState() + "\nGame quit prematurely.", out.toString());
  }

  //tests quit for invalid card index re-do
  @Test
  public void testQuit9() {
    Reader in = new StringReader("C1 -13 F1 Q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //tests quit for destination pile re-do
  @Test
  public void testQuit10() {
    Reader in = new StringReader("C1 13 F9 C1 12 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. invalid move\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //tests quit for destination
  @Test
  public void testQuit11() {
    Reader in = new StringReader("C1 12 F1q O2 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //tests quit for wrong input numbers
  @Test
  public void testQuit12() {
    Reader in = new StringReader("Ms Q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertEquals(model.getGameState() + "\nGame quit prematurely.", out.toString());
  }

  //Tests invalid inputs
  @Test
  public void testValidInputs() {
    Reader in = new StringReader("C1 13 O1 C2 13 O2 C3 13 O3 C4 13 O4 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: J♦\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: J♦\n" +
            "O3: Q♦\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 10♦\n" +
            "O2: J♦\n" +
            "O3: Q♦\n" +
            "O4: K♦\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //Tests invalid inputs
  @Test
  public void testInvalidInputs() {
    Reader in = new StringReader("C1 1 F1 C2 1 F1 C3 1 F1 C4 1 F1 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
              "F2:\n" +
              "F3:\n" +
              "F4:\n" +
              "O1:\n" +
              "O2:\n" +
              "O3:\n" +
              "O4:\n" +
              "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
              "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
              "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
              "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
              "Invalid move. Try again. can't pick up this card\n" +
              "\n" +
              "F1:\n" +
              "F2:\n" +
              "F3:\n" +
              "F4:\n" +
              "O1:\n" +
              "O2:\n" +
              "O3:\n" +
              "O4:\n" +
              "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
              "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
              "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
              "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
              "Invalid move. Try again. can't pick up this card\n" +
              "\n" +
              "F1:\n" +
              "F2:\n" +
              "F3:\n" +
              "F4:\n" +
              "O1:\n" +
              "O2:\n" +
              "O3:\n" +
              "O4:\n" +
              "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
              "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
              "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
              "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
              "Invalid move. Try again. can't pick up this card\n" +
              "\n" +
              "F1:\n" +
              "F2:\n" +
              "F3:\n" +
              "F4:\n" +
              "O1:\n" +
              "O2:\n" +
              "O3:\n" +
              "O4:\n" +
              "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
              "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
              "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
              "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
              "Invalid move. Try again. can't pick up this card\n" +
              "\n" +
              "F1:\n" +
              "F2:\n" +
              "F3:\n" +
              "F4:\n" +
              "O1:\n" +
              "O2:\n" +
              "O3:\n" +
              "O4:\n" +
              "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
              "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
              "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
              "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
              "Game quit prematurely.", out.toString());
  }

  //Tests invalid inputs for source pile type
  @Test
  public void testInvalidSourcePileType() {
    Reader in = new StringReader("G1 1 F1 " +
            "meow1 " +
            "C1 " +
            "C2 1 F1 " +
            "F3 1 F1 " +
            "M3 " +
            "C3 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid source pile\n" +
            "Invalid move. Try again. Please enter a valid source pile\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //Tests invalid inputs for source pile number
  @Test
  public void testInvalidSourcePileNumber() {
    Reader in = new StringReader("C20 1 F1 " +
            "C-10 " +
            "C1 C2 1 F1 C13 1 F1 C13 C3");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. invalid move\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. source and/or destination pile numbers aren't valid.\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n", out.toString());
  }

  //Tests invalid inputs for card index
  @Test
  public void testInvalidCardIndex() {
    Reader in = new StringReader("C1 -1 F1 r 1 C2 1 F1 C3 * F1 29 1 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid source pile\n" +
            "Invalid move. Try again. Please enter a valid source pile\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Game quit prematurely.", out.toString());
  }

  //Tests invalid inputs for destination pile type
  @Test
  public void testInvalidDestinationPileType() {
    Reader in = new StringReader("C1 1 G1 meow1 F1 C2 1 F1 C3 1 M1 rrrr3 F1 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  //Tests invalid inputs for destination pile number
  @Test
  public void testInvalidDestinationPileNumber() {
    Reader in = new StringReader("C1 1 F30 F-1 F1 C2 1 F1 C3 1 Frrrr3 f% F1 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, false);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. source and/or destination pile numbers aren't valid.\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Invalid move. Try again. Please enter a valid destination pile\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Game quit prematurely.", out.toString());
  }

  @Test
  public void testShuffleFromController() {
    Reader in = new StringReader("C1 1 F30 F-1 F1 C2 1 F1 C3 1 Frrrr3 f% F1 q");
    StringBuffer out = new StringBuffer();
    IFreecellController controller = new FreecellController(in, out);
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    controller.playGame(deck, model, 4, 4, true);
    assertNotEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. can't pick up this card\n" +
            "\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. invalid move\n" +
            "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Invalid move. Try again. Please enter a valid card index\n" +
            "Game quit prematurely.\n", out.toString());
  }
}
*/
