package JavaProjects.music.model;

/**
 * Represents one of multiple endings in a song.
 */
public class MultEnd implements IRepeater{
  private boolean played = false;
  private int startbeat;
  private int endingbegin;
  private int lastbeat;

  /**
   * Standard constructor.
   * @param start the starting beat of the ending
   * @param endbegin the beginning of the ending portion
   * @param last the last beat of this ending.
   */
  public MultEnd(int start, int endbegin, int last) {
    this.startbeat = start;
    this.endingbegin = endbegin;
    this.lastbeat = last;
  }

  /**
   * get the starting beat of this ending.
   * @return the beat
   */
  public int getStartbeat() {
    return this.startbeat;
  }

  /**
   * Get the beginning of this ending.
   * @return the beat
   */
  public int getBeginEndbeat() {
    return this.endingbegin;
  }

  /**
   * Get the ending beat.
   * @return the beat
   */
  public int getEndbeat() {
    return this.lastbeat;
  }

  /**
   * Set this ending as played.
   */
  public void setPlayed() {
    this.played = true;
  }

  /**
   * Set this ending as unplayed.
   */
  public void setUnplayed() {
    this.played = false;
  }

  /**
   * Get whether this has been played.
   * @return the boolean
   */
  public boolean getPlayed() {
    return this.played;
  }
}
