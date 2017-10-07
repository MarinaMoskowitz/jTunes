package JavaProjects.hw03;

import java.util.List;
import JavaProjects.hw02.FreecellOperations;

/**
 * Created by marinamoskowitz on 2/1/17.
 */
public interface IFreecellController<K> {
  /**
   * Starts a new game of freecell using the given model, number of cascade and open piles,
   * and the provided deck, with or without shuffling it first.
   *
   * @param deck                      the deck to be dealt
   * @param model                     the model
   * @param numCascadePiles           number of cascade piles
   * @param numOpenPiles              number of open piles
   * @param shuffle                   if true, shuffle the deck else deal the deck as-is
   * @throws IllegalStateException    if the controller has not been initialized properly to
   *                                  receive input and transmit output.
   */
  void playGame(List<K> deck, FreecellOperations<K> model, int numCascadePiles,
                int numOpenPiles, boolean shuffle);
}
