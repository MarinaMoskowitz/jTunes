package JavaProjects.music.model;

/**
 * This interface specifies the operation of a music editor.
 */
public interface IMusicModel {
  /**
   * Gets the current song.
   *
   * @return Song   this song.
   */
  Song getSong();

  /**
   * Remove note from song.
   *
   * @param n    the note to be removed
   * @throws     IllegalArgumentException if Note is null
   */
  void removeNote(INote n, int beat);

  /**
   * Add note to song.
   *
   * @param n   the note to be added
   * @throws    IllegalArgumentException if Note is null
   */
  void addNote(INote n, int beat);

  /**
   * Combines two notes in a song so the play simultaneously.
   *
   * @param model    the volume level of a song
   * @throws         IllegalArgumentException if Model is null
   */
  void playSimultaneously(IMusicModel model);

  /**
   * Combines two notes in a song so the play consecutively.
   *
   * @param model    the volume level of a song
   * @throws         IllegalArgumentException if Model is null
   */
  void playConsecutively(IMusicModel model);

  /**
   * The last note position in this song.
   *
   * @return Integer    the position of this song as an Integer
   */
  Integer getEndBeat();

  /**
   * The last beat in the song.
   *
   * @return Integer    the Integer value of the last beat in this song.
   */
  Integer getLastBeat();

  /**
   * A method so the view would have access to a read-only version of thus model.
   */
  IMusicModel getReadOnlyModel();

  /**
   * Returns this model's song as a String.
   */
  String showMusic();

  /**
   * Adds to current beat.
   */
  void addBeat();

  /**
   * Subs from current beat.
   */
  void subBeat();

  /**
   * Sets current beat.
   */
  void setBeat(int beat);

  /**
   * Gets current beat.
   */
  int getBeat();
}