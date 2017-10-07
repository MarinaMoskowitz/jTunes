/*
package cs3500.hw04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Card;
import FreecellOperations;
import PileType;
import Suit;
import Value;
import FreecellModelCreator;

import static org.junit.Assert.assertEquals;

*/
/**
 * Test class for the MultiMoveModelClass.
 *//*

public class FreecellMultiMoveTest {

  Card[] deck = new Card[] {
    new Card(Value.K, Suit.HEARTS),
    new Card(Value.NINE, Suit.HEARTS),
    new Card(Value.FIVE, Suit.HEARTS),
    new Card(Value.K, Suit.DIAMONDS),
    new Card(Value.NINE, Suit.DIAMONDS),
    new Card(Value.FIVE, Suit.DIAMONDS),
    new Card(Value.K, Suit.CLUBS),
    new Card(Value.NINE, Suit.CLUBS),
    new Card(Value.FIVE, Suit.CLUBS),
    new Card(Value.K, Suit.SPADES),
    new Card(Value.NINE, Suit.SPADES),
    new Card(Value.FIVE, Suit.SPADES),
    new Card(Value.Q, Suit.CLUBS),
    new Card(Value.EIGHT, Suit.CLUBS),
    new Card(Value.FOUR, Suit.CLUBS),
    new Card(Value.Q, Suit.SPADES),
    new Card(Value.EIGHT, Suit.SPADES),
    new Card(Value.FOUR, Suit.SPADES),
    new Card(Value.Q, Suit.HEARTS),
    new Card(Value.EIGHT, Suit.HEARTS),
    new Card(Value.FOUR, Suit.HEARTS),
    new Card(Value.Q, Suit.DIAMONDS),
    new Card(Value.EIGHT, Suit.DIAMONDS),
    new Card(Value.FOUR, Suit.DIAMONDS),
    new Card(Value.J, Suit.HEARTS),
    new Card(Value.SEVEN, Suit.HEARTS),
    new Card(Value.THREE, Suit.HEARTS),
    new Card(Value.J, Suit.DIAMONDS),
    new Card(Value.SEVEN, Suit.DIAMONDS),
    new Card(Value.THREE, Suit.DIAMONDS),
    new Card(Value.J, Suit.CLUBS),
    new Card(Value.SEVEN, Suit.CLUBS),
    new Card(Value.THREE, Suit.CLUBS),
    new Card(Value.J, Suit.SPADES),
    new Card(Value.SEVEN, Suit.SPADES),
    new Card(Value.THREE, Suit.SPADES),
    new Card(Value.TEN, Suit.CLUBS),
    new Card(Value.SIX, Suit.CLUBS),
    new Card(Value.TWO, Suit.CLUBS),
    new Card(Value.TEN, Suit.SPADES),
    new Card(Value.SIX, Suit.SPADES),
    new Card(Value.TWO, Suit.SPADES),
    new  Card(Value.TEN, Suit.HEARTS),
    new Card(Value.SIX, Suit.HEARTS),
    new Card(Value.TWO, Suit.HEARTS),
    new Card(Value.TEN, Suit.DIAMONDS),
    new Card(Value.SIX, Suit.DIAMONDS),
    new Card(Value.TWO, Suit.DIAMONDS),
    new Card(Value.A, Suit.HEARTS),
    new Card(Value.A, Suit.SPADES),
    new Card(Value.A, Suit.DIAMONDS),
    new Card(Value.A, Suit.CLUBS)};
  ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(deck));

  @Test (expected = IllegalArgumentException.class)
  public void testEndGameState() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 20, false);
    test.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 10);
    List<Card> deck = test.getDeck();
    test.startGame(deck, 52, 4, false);
    for (int i = 0; i < 52; i++) {
      test.move(PileType.CASCADE, i, 0, PileType.FOUNDATION, (i / 13));
    }
    assertEquals(test.isGameOver(), true);
    test.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOutOfBoundsCardIndex() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(test.getDeck(), 5, 0, true);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSourceAndDestination() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 20, false);
    test.move(PileType.CASCADE, -10, 0, PileType.CASCADE, 0);
    test.startGame(newDeck, 52, 4, false);
    test.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSourceAndDestinationNull() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 20, false);
    test.move(null, 10, 1, null, 10);
    test.startGame(newDeck, 52, 4, false);
    test.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 10);
  }

  @Test
  public void testMoveToSamePile() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 20, false);
    test.move(PileType.CASCADE, 10, 1, PileType.CASCADE, 10);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "O5:\n" +
            "O6:\n" +
            "O7:\n" +
            "O8:\n" +
            "O9:\n" +
            "O10:\n" +
            "O11:\n" +
            "O12:\n" +
            "O13:\n" +
            "O14:\n" +
            "O15:\n" +
            "O16:\n" +
            "O17:\n" +
            "O18:\n" +
            "O19:\n" +
            "O20:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠, 2♦", test.getGameState());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveMultiToOpen() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 20, false);
    test.move(PileType.CASCADE, 11, 1, PileType.OPEN, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveMultiToFoundation() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 20, false);
    test.move(PileType.CASCADE, 11, 1, PileType.FOUNDATION, 10);
  }

  @Test
  public void testValidMove() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 10);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♦, 3♠, 2♦\n" +
            "C12:", test.getGameState());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInValidPileMove() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 10, 0, PileType.CASCADE, 11);
  }

  //testing that the pile you want to move to is not a valid move
  @Test (expected = IllegalArgumentException.class)
  public void testInValidPileMove2() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 10);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♦, 3♠, 2♦\n" +
            "C12:", test.getGameState());
    test.move(PileType.CASCADE, 10, 0, PileType.CASCADE, 5);
  }



  //testing that the pile you want to move to is not a valid move
  @Test
  public void testMovingInvaildPile() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 10);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♦, 3♠, 2♦\n" +
            "C12:", test.getGameState());
    test.move(PileType.CASCADE, 0, 0, PileType.CASCADE, 11);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♦, 3♠, 2♦\n" +
            "C12:", test.getGameState());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testToooooMany() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 10);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♦, 3♠, 2♦\n" +
            "C12:", test.getGameState());
    test.move(PileType.CASCADE, 10, 0, PileType.CASCADE, 9);
  }

  @Test
  public void testMoveToEmpty() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 10);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♦, 3♠, 2♦\n" +
            "C12:", test.getGameState());
    test.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 11);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9:\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♦, 3♠, 2♦\n" +
            "C12: 5♣, 4♥, 3♣, 2♥", test.getGameState());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveValidPileToInvalidPile() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 6, 0, PileType.CASCADE, 1);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testEmptyMove() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 10);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 10);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoveToFoundation() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 0);
    assertEquals("F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠, 2♦", test.getGameState());
    test.move(PileType.CASCADE, 0, 3, PileType.FOUNDATION, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoveFromFoundation() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 0);
    assertEquals("F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠, 2♦", test.getGameState());
    test.move(PileType.CASCADE, 8, 3, PileType.FOUNDATION, 0);
    assertEquals("F1: A♥, 2♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠, 2♦", test.getGameState());
    test.move(PileType.CASCADE, 8, 2, PileType.OPEN, 0);
    test.move(PileType.CASCADE, 8, 1, PileType.OPEN, 1);
    test.move(PileType.CASCADE, 8, 0, PileType.OPEN, 2);
    test.move(PileType.FOUNDATION, 0, 0, PileType.CASCADE, 8);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoveMultiToOpen() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 4, PileType.OPEN, 0);
    assertEquals("F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠, 2♦", test.getGameState());
    test.move(PileType.CASCADE, 0, 3, PileType.OPEN, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoveToOpen() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 4, PileType.OPEN, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoveSuit() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 4);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveFromInvalidCascadePile() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 15, 0, PileType.CASCADE, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testMoveToInvalidCascadePile() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 2, 0, PileType.CASCADE, 15);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidPileMove() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 4, PileType.OPEN, 0);
    assertEquals("F1: A♥\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1:\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠, 2♦", test.getGameState());
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCardIndexMultiMove() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 4, 3, PileType.OPEN, 3);
    test.move(PileType.CASCADE, 4, 0, PileType.CASCADE, 3);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidMoveColor() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 3, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 2♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠", test.getGameState());
    test.move(PileType.CASCADE, 5, 3, PileType.OPEN, 1);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 2♦\n" +
            "O2: 2♠\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠", test.getGameState());
    test.move(PileType.OPEN, 0, 0, PileType.CASCADE, 5);
  }

*/
/*  @Test
  public void testSameMove() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 3, PileType.OPEN, 0);
    test.move(PileType.CASCADE, 11, 3, PileType.OPEN, 0);
    assertEquals("F1:\n" +
            "F2:\n" +
            "F3:\n" +
            "F4:\n" +
            "O1: 2♦\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣, A♥\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥, 2♣, A♦\n" +
            "C4: K♦, Q♠, J♦, 10♠, A♣\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥, 3♣, 2♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♦, 3♠", test.getGameState());
  }*//*


 */
/* @Test
  public void testInvalidMoveToFoundation1() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 3, PileType.OPEN, 0);
    test.move(PileType.CASCADE, 11, 2, PileType.OPEN, 1);
    test.move(PileType.CASCADE, 11, 1, PileType.OPEN, 2);
    test.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 0);
    test.move(PileType.CASCADE, 8, 3, PileType.FOUNDATION, 0);
    test.move(PileType.CASCADE, 2, 4, PileType.FOUNDATION, 1);
    test.move(PileType.CASCADE, 3, 4, PileType.FOUNDATION, 2);
    test.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION, 2);
    test.move(PileType.CASCADE, 8, 2, PileType.FOUNDATION, 2);
    test.move(PileType.OPEN, 1, 1, PileType.FOUNDATION, 2);
    assertEquals("F1: A♥, 2♥\n" +
            "F2: A♦, 2♦\n" +
            "F3: A♣, 2♣, 3♣\n" +
            "F4:\n" +
            "O1: \n" +
            "O2: 3♠\n" +
            "O3: 4♦\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥\n" +
            "C4: K♦, Q♠, J♦, 10♠\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♥", test.getGameState());
  }*//*


  @Test
  public void testFail() {
    FreecellOperations<Card> test =
            FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
    test.startGame(newDeck, 12, 4, false);
    test.move(PileType.CASCADE, 11, 3, PileType.OPEN, 0);
    test.move(PileType.CASCADE, 11, 2, PileType.OPEN, 1);
    test.move(PileType.CASCADE, 11, 1, PileType.OPEN, 2);
    test.move(PileType.CASCADE, 0, 4, PileType.FOUNDATION, 0);
    test.move(PileType.CASCADE, 8, 3, PileType.FOUNDATION, 0);
    test.move(PileType.CASCADE, 2, 4, PileType.FOUNDATION, 1);
    test.move(PileType.CASCADE, 3, 4, PileType.FOUNDATION, 2);
    test.move(PileType.CASCADE, 2, 3, PileType.FOUNDATION, 2);
    test.move(PileType.CASCADE, 8, 2, PileType.FOUNDATION, 2);
    assertEquals("F1: A♥, 2♥\n" +
            "F2: A♦\n" +
            "F3: A♣, 2♣, 3♣\n" +
            "F4:\n" +
            "O1: 2♦\n" +
            "O2: 3♠\n" +
            "O3: 4♦\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥\n" +
            "C4: K♦, Q♠, J♦, 10♠\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣, 4♥\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠", test.getGameState());
    test.move(PileType.CASCADE, 8, 1, PileType.CASCADE, 11);
    assertEquals("F1: A♥, 2♥\n" +
            "F2: A♦\n" +
            "F3: A♣, 2♣, 3♣\n" +
            "F4:\n" +
            "O1: 2♦\n" +
            "O2: 3♠\n" +
            "O3: 4♦\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣, A♠\n" +
            "C3: 5♥, 4♣, 3♥\n" +
            "C4: K♦, Q♠, J♦, 10♠\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠, 3♦, 2♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9: 5♣\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦\n" +
            "C12: 5♠, 4♥", test.getGameState());
    test.move(PileType.OPEN, 0, 0, PileType.FOUNDATION, 1);
    test.move(PileType.CASCADE, 1, 4, PileType.FOUNDATION, 3);
    test.move(PileType.CASCADE, 5, 3, PileType.FOUNDATION, 3);
    test.move(PileType.OPEN, 1, 0, PileType.FOUNDATION, 3);
    test.move(PileType.CASCADE, 5, 2, PileType.FOUNDATION, 1);
    test.move(PileType.OPEN, 2, 0, PileType.FOUNDATION, 1);
    test.move(PileType.CASCADE, 8, 0, PileType.OPEN, 0);
    test.move(PileType.CASCADE, 10, 3, PileType.CASCADE, 8);
    test.move(PileType.CASCADE, 11, 0, PileType.CASCADE, 8);
    test.move(PileType.CASCADE, 8, 0, PileType.CASCADE, 10);
    assertEquals("F1: A♥, 2♥\n" +
            "F2: A♦, 2♦, 3♦, 4♦\n" +
            "F3: A♣, 2♣, 3♣\n" +
            "F4: A♠, 2♠, 3♠\n" +
            "O1: 5♣\n" +
            "O2:\n" +
            "O3:\n" +
            "O4:\n" +
            "C1: K♥, Q♣, J♥, 10♣\n" +
            "C2: 9♥, 8♣, 7♥, 6♣\n" +
            "C3: 5♥, 4♣, 3♥\n" +
            "C4: K♦, Q♠, J♦, 10♠\n" +
            "C5: 9♦, 8♠, 7♦, 6♠\n" +
            "C6: 5♦, 4♠\n" +
            "C7: K♣, Q♥, J♣, 10♥\n" +
            "C8: 9♣, 8♥, 7♣, 6♥\n" +
            "C9:\n" +
            "C10: K♠, Q♦, J♠, 10♦\n" +
            "C11: 9♠, 8♦, 7♠, 6♦, 5♠, 4♥\n" +
            "C12:", test.getGameState());
  }
}
*/
