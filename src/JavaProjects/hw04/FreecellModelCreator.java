package JavaProjects.hw04;

import JavaProjects.hw02.Card;
import JavaProjects.hw02.FreecellModel;
import JavaProjects.hw02.FreecellOperations;

/**
 * A factory class for FreecellModel and FreecellMultiMove.
 */
public class FreecellModelCreator {
  public enum GameType {
    SINGLEMOVE, MULTIMOVE;
  }

  /**
   * Create an new instance of a FreeCellModel or a FreeCellModelMulti.
   *
   * @param type    single-move or multi-move Freecell game
   * @return        A model for a single-move or a model for a multi-move
   */
  public static FreecellOperations<Card> create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new FreecellModel();
      case MULTIMOVE:
        return new FreecellMultiMove();
      default:
        throw new IllegalArgumentException("Invalid Model");
    }
  }
}
