package JavaProjects.music.view;

import javax.sound.midi.InvalidMidiDataException;

import JavaProjects.music.model.ISong;

/**
 * An Interface to represent a MidiView.
 */
public interface IMidiView extends IView {
  /**
   * Plays the note.
   * @param pitch The pitch to play at
   * @param volume The volume of the note
   * @param length the length of the note
   * @param tempo The tempo of the note
   * @param instrument the instrument of the note
   * @throws InvalidMidiDataException when the note is invalid
   */
  void playNote(int pitch, int volume, int length, int tempo, int instrument)
           throws InvalidMidiDataException;

  /**
   * Plays the whole song.
   */
  void play();

  /**
   * Gets the song from the view.
   * @return ISong  this song being returned
   */
  ISong getSong();

  /**
   * Sets the beat of the MIDI.
   * @param beat the beat to set to
   */
  void setBeat(int beat);

  /**
   * Gets the current beat of the MIDI player.
   * @return the current beat
   */
  int getBeat();
}
