package JavaProjects.hw03;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import JavaProjects.hw02.Card;
import JavaProjects.hw02.FreecellOperations;
import JavaProjects.hw02.Suit;
import JavaProjects.hw02.Value;
import JavaProjects.hw04.FreecellModelCreator;

/**
 * Created by marinamoskowitz on 2/10/17.
 */
public class main {
  public static void main(String args[]){
    IFreecellController controller = new FreecellController(new InputStreamReader(System.in),
            System.out);
    FreecellOperations model = FreecellModelCreator.create(FreecellModelCreator.GameType.MULTIMOVE);
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
            new Card(Value.TEN, Suit.HEARTS),
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
    controller.playGame(newDeck, model, 12, 4, false);
    controller.playGame(newDeck, model,12,4,false);
  }
}
