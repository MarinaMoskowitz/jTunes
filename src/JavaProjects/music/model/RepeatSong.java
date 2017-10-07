package JavaProjects.music.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A song with repeats.
 */
public class RepeatSong extends Song{
  List<Repeat> repeats;
  List<MultEnd> endings;

  /**
   * A constructor for an empty song.
   */
  public RepeatSong() {
    super();
    this.repeats = new ArrayList<Repeat>();
    this.endings = new ArrayList<MultEnd>();
  }

  /**
   * A constructor that takes in a song.
   * @param s the song
   */
  public RepeatSong(ISong s) {
    this.song = s.getSong();
    this.tempo = s.getTempo();
    this.repeats = new ArrayList<Repeat>();
    this.endings = new ArrayList<MultEnd>();
  }

  /**
   * Gets the repeats in this song.
   * @return the repeats
   */
  public List<Repeat> getRepeats() {
    return repeats;
  }

  /**
   * Add repeats to this song.
   * @param start the start of the repeat.
   * @param end the end of the repeat.
   */
  public void addRepeats(int start, int end) {
    this.repeats.add(new Repeat(start, end));
  }

  /**
   * Add repeats to this song using only and ending note.
   * @param end the ending note.
   */
  public void addRepeats(int end) {
    this.repeats.add(new Repeat(end));
  }

  /**
   * Get the list of endings for this song.
   * @return the list
   */
  public List<MultEnd> getEnds() {
    return endings;
  }

  /**
   * Add endings to this song.
   * @param start the start of the ending
   * @param endbegin the start of the ending portion
   * @param end the ending beat
   */
  public void addEnds(int start, int endbegin, int end) {
    this.endings.add(new MultEnd(start, endbegin, end));
  }
}
