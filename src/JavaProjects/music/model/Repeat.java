package JavaProjects.music.model;

public class Repeat implements IRepeater{
  // start should default to 0
  private boolean played = false;
  private int startbeat = 0;
  private int endbeat;

  /**
   * A constructor that takes a starting and ending beat.
   * @param start the start of the repeat.
   * @param end the end of the repeat.
   */
  public Repeat(int start, int end) {
    this.startbeat = start;
    this.endbeat = end;
  }

  /**
   * A constructor that only takes the ending and defaults the start to 0.
   * @param end the end of the repeat
   */
  public Repeat(int end) {
    this.endbeat = end;
  }

  /**
   * Get the starting beat of this repeat.
   * @return the beat
   */
  public int getStartbeat() {
    return this.startbeat;
  }

  /**
   * Get the ending beat of this repeat.
   * @return the beat
   */
  public int getEndbeat() {
    return this.endbeat;
  }

  /**
   * Set this Repeat as played.
   */
  public void setPlayed() {
    this.played = true;
  }

  /**
   * Set this repeat as unplayed.
   */
  public void setUnplayed() {
    this.played = false;
  }

  /**
   * Get whether this repeat has been played.
   * @return the boolean
   */
  public boolean getPlayed() {
    return this.played;
  }
}
