package JavaProjects.music.model;

/**
 * This is an interface to represent a Note.
 */
public interface INote extends Comparable<INote> {
  /**
   * Get the pitch.
   *
   * @return int    the volume level of a note
   */
  Pitch getPitch();

  /**
   * Get the current octave.
   *
   * @return int    the octave of a note
   */
  int getOctave();

  /**
   * Get the current instrument.
   *
   * @return int    the instrument of a note
   */
  int getInstrument();

  /**
   * Get the current volume.
   *
   * @return int    the volume of a note
   */
  int getVolume();

  /**
   * Get the current beats.
   *
   * @return int    the beats of a note
   */
  int getBeats();

  /**
   * Get the duration of the current beat.
   *
   * @return int    the duratrion of a beat
   */
  int getBeatDuration();

  /**
   * Getter for the midi pitch of the INote.
   *
   * @return midi pitch of the INote
   */
  int midiPitch();

}

