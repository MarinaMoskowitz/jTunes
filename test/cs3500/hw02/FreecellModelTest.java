/*
package cs3500.hw02;

import org.junit.Test;

import java.util.List;

import java.util.ArrayList;

import Card;
import FreecellModel;
import FreecellOperations;
import PileType;
import Suit;
import Value;

import static org.junit.Assert.assertEquals;

import static junit.framework.TestCase.fail;


*/
/**
 * Created by marinamoskowitz on 1/30/17.
 *//*

public class FreecellModelTest {
  FreecellOperations<Card> test = new FreecellModel();


  @Test
  public void testGetDeck() {
    test = new FreecellModel();
    List<Card> deck = test.getDeck();
    assertEquals(deck.toString(),
            "[A♠, 2♠, 3♠, 4♠, 5♠, 6♠, 7♠, 8♠, 9♠, 10♠, J♠, Q♠, K♠, A♣, 2♣, 3♣, 4♣, 5♣, "
                   + "6♣, 7♣, 8♣, 9♣, 10♣, J♣, Q♣, K♣, A♥, 2♥, 3♥, 4♥, 5♥, 6♥, 7♥, 8♥, 9♥,"
                    + " 10♥, J♥, Q♥, K♥, A♦, 2♦, 3♦, 4♦, 5♦, 6♦, 7♦, 8♦, 9♦, 10♦, J♦, Q♦, K♦]");
  }

  @Test (expected = NullPointerException.class)
  public void testGetDeckNull() throws Exception {
    test = new FreecellModel();
    test.startGame(null, 6, 4, false);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testStartGameInvalidDeck1() {
    test = new FreecellModel();
    List<Card> deck = test.getDeck();
    deck.add(new Card(Value.A, Suit.SPADES));
    deck.add(new Card(Value.A, Suit.SPADES));
    deck.add(new Card(Value.A, Suit.SPADES));
    test.startGame(deck, 6, 4, false);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testStartGameInvalidDeck2() {
    test = new FreecellModel();
    List<Card> deck = test.getDeck();
    deck.remove(4);
    test.startGame(deck, 6, 4, false);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testStartGameInvalidCascadeTest1() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 3, 4, true);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testStartGameInvalidOpen1() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 5, 0, true);
  }

  @Test
  public void testGetDeckEmpty() {
    test = new FreecellModel();
    try {
      test.getDeck();
    } catch (IllegalArgumentException e) {
      assertEquals(test.getDeck(), "");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDuplicateDeckShuffle2() {
    test = new FreecellModel();
    List<Card> deck = new ArrayList<Card>(53);
    Card sameCard = new Card(Value.A, Suit.SPADES);
    deck.add(sameCard);
    test.startGame(deck, 4, 4, true);
    fail("No Exception was thrown.");
  }

  @Test
  public void testGameStateAfterInvalidDeckShuffle1() {
    test = new FreecellModel();
    try {
      test.startGame(test.getDeck(), 0, 4, false);
      assertEquals(test.getGameState(), "");
    } catch (IllegalArgumentException e) {
      //throw exception is deck is invalid
      assertEquals(test.getGameState(), "");
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidStartGame() throws Exception {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 0, 4, false);
  }

  @Test
  public void testDealCorrectlyNoShuffle() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    assertEquals(test.getGameState(), "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n"
            + "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n"
            + "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n"
            + "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦");
  }

  @Test
  public void testDealCorrectlyShuffle() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    assertEquals(test.getGameState(), "F1:\n"
            + "F2:\n"
            + "F3:\n"
            + "F4:\n"
            + "O1:\n"
            + "O2:\n"
            + "O3:\n"
            + "O4:\n"
            + "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n"
            + "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n"
            + "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n"
            + "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSourcePileNum() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    test.move(PileType.OPEN, 4, 11, PileType.FOUNDATION, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDestinationPileNum() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    test.move(PileType.CASCADE, -4, 11, PileType.FOUNDATION, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToOpenInvalid1() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 0, true);
    test.move(PileType.CASCADE, 5, 13, PileType.OPEN, 3);
    test.move(PileType.CASCADE, 4, 12, PileType.OPEN, 3);
  }

  @Test
  public void testValidMoveFoundation() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 10, false);
    test.move(PileType.CASCADE, 3, 12, PileType.OPEN, 1);
    test.move(PileType.CASCADE, 3, 11, PileType.OPEN, 0);
    test.move(PileType.CASCADE, 3, 10, PileType.OPEN, 2);
    test.move(PileType.CASCADE, 3, 9, PileType.FOUNDATION, 1);
    test.move(PileType.CASCADE, 0, 12, PileType.OPEN, 3);
    test.move(PileType.CASCADE, 0, 11, PileType.OPEN, 4);
    test.move(PileType.CASCADE, 0, 10, PileType.FOUNDATION, 1);
    assertEquals(test.getGameState(), "F1:\n" +
            "F2: A♦, 2♦\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 9♦\n" +
            "O2: K♦\n" +
            "O3: 5♦\n" +
            "O4: 10♦\n" +
            "O5: 6♦\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "O9:\n" +
            "O10:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToFoundationInvalid1() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 5, 4, true);
    test.move(PileType.CASCADE, 25, 1, PileType.FOUNDATION, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToFoundationInvalid2() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 5, 4, true);
    test.move(PileType.CASCADE, 5, 1, PileType.FOUNDATION, 25);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveInvalidColorFoundation() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    test.move(PileType.CASCADE, 5, 5, PileType.OPEN, 1);
    test.move(PileType.CASCADE, 5, 6, PileType.OPEN, 0);
    test.move(PileType.CASCADE, 5, 9, PileType.OPEN, 2);
    test.move(PileType.CASCADE, 5, 12, PileType.FOUNDATION, 1);
    test.move(PileType.CASCADE, 5, 4, PileType.FOUNDATION, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToCascadeInvalid() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 5, 4, true);
    test.move(PileType.CASCADE, 0, 8, PileType.CASCADE, 0);
  }

  @Test
  public void testIsGameOver1() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 5, 4, false);
    assertEquals(test.isGameOver(), false);
  }

  @Test
  public void testIsGameOver2() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 52, 4, false);
    assertEquals(test.isGameOver(), false);
  }

  @Test
  public void testGameState() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    assertEquals(test.getGameState(), "F1:\n" +
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
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, K♦");
  }

  @Test
  public void testGameState2() {
    test = new FreecellModel();
    assertEquals("", test.getGameState());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    test.move(PileType.CASCADE, 4, 10, PileType.OPEN, 2);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testStartGame() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 2, 4, false);
    test.move(PileType.CASCADE, 4, 10, PileType.OPEN, 2);
  }

  @Test
  public void testRestartGameInProgress() {
    test = new FreecellModel();
    test.startGame(test.getDeck(), 4, 4, false);
    test.move(PileType.CASCADE, 3, 12, PileType.OPEN, 2);
    test.startGame(test.getDeck(), 4, 4, false);
    assertEquals(test.getGameState(), "F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "F5:\n" +
            "F6:\n" +
            "F7:\n" +
            "F8:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3: K♦\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "C1: A♠, 5♠, 9♠, K♠, 4♣, 8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦, A♠, 5♠, 9♠, K♠, 4♣, " +
            "8♣, Q♣, 3♥, 7♥, J♥, 2♦, 6♦, 10♦\n" +
            "C2: 2♠, 6♠, 10♠, A♣, 5♣, 9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦, 2♠, 6♠, 10♠, A♣, 5♣, " +
            "9♣, K♣, 4♥, 8♥, Q♥, 3♦, 7♦, J♦\n" +
            "C3: 3♠, 7♠, J♠, 2♣, 6♣, 10♣, A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦, 3♠, 7♠, J♠, 2♣, 6♣, 10♣, " +
            "A♥, 5♥, 9♥, K♥, 4♦, 8♦, Q♦\n" +
            "C4: 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, 6♥, 10♥, A♦, 5♦, 9♦, 4♠, 8♠, Q♠, 3♣, 7♣, J♣, 2♥, " +
            "6♥, 10♥, A♦, 5♦, 9♦, K♦\n" +
            "C5:\n" +
            "C6:\n" +
            "C7:\n" +
            "C8:");
  }

  //test the game has finished
  @Test
  public void testEndGame() {
    FreecellOperations model = new FreecellModel();
    List<Card> deck = model.getDeck();
    model.startGame(deck, 52, 4, false);
    for (int i = 0; i < 52; i++) {
      model.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, (i / 13));
    }
    assertEquals(model.isGameOver(), true);
  }
}
*/
