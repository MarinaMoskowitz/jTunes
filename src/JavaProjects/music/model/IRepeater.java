package JavaProjects.music.model;

/**
 * Objects that implement a 'repeat' function.
 */
public interface IRepeater {
  /**
   * Get the starting beat of the repeat.
   * @return the beat to go back to
   */
  int getStartbeat();

  /**
   * Get the ending beat of the repeat.
   * @return the beat
   */
  int getEndbeat();

  /**
   * Mark this repeater as played.
   */
  void setPlayed();

  /**
   * Mark this repeater as unplayed.
   */
  void setUnplayed();

  /**
   * Whether this repeater has been played.
   * @return whether the repeat has been played.
   */
  boolean getPlayed();
}
