package JavaProjects.music.model;

import java.util.List;
import java.util.TreeMap;

/**
 * This is an interface to represent a Song.
 */
public interface ISong {
  /**
   * Gets all the beats in this song.
   *
   * @return  a list of all the beats in this song
   */
  List<Integer> getBeats();

  /**
   * Returns the highest Note in this song.
   *
   * @return highest Note in this Song
   */
  INote maxNote();

  /**
   * Gets all the notes in this song.
   *
   * @param beat the notes at this beat
   * @return  a list of all the notes in this song
   */
  List<INote> getNotes(int beat);

  /**
   * Gets this song.
   *
   * @return this song
   */
  TreeMap<Integer, List<INote>> getSong();

  /**
   * Checks if a given beat is in a song.
   *
   * @param beat the beat being checked
   * @return true if the beat is in this song, false if not
   */
  boolean hasBeat(int beat);

  /**
   * Puts a note in a song at a given beat.
   *
   * @param beat the beat for the note being placed in this song
   * @param n the note to be placed in this song
   */
  void put(int beat, List<INote> n);

  /**
   * Returns the lowest Note in this song.
   *
   * @return lowest Note in the this Song
   */
  INote minNote();

  /**
   * Get all the pitches in this song.
   *
   * @return a list of all the pitches
   */
  List<String> getPitches();


  /**
   * Get the tempo of this song.
   *
   * @return the tempo of this song
   */
  int getTempo();

  /**
   * Sets the tempo of this song.
   * @param tempo the new tempo
   */
  void setTempo(int tempo);

  /**
   * Adds a note to this song.
   * @param n the note to add
   * @param beat the beat to add the note
   */
  void addNote(INote n, int beat);

  /**
   * Gets the last beat of the last note.
   * @return the last beat.
   */
  int getLastBeat();

  /**
   * Gets the first beat of the last note.
   * @return the beat.
   */
  int getEndBeat();

  /**
   * Gets the first beat of the last note.
   * @return the beat.
   */
  int getBegBeat();

  /**
   * Gets the List of Notes starting at the given beat.
   *
   * @param i Beat to search for Notes.
   * @return List of INotes starting at that beat.
   */
  List<INote> getNotesAtBeat(int i);

  /**
   * Gets the list of repeats in this song.
   * @return the list
   */
  List<Repeat> getRepeats();

  /**
   * Gets the list of endings for this song.
   * @return the list
   */
  List<MultEnd> getEnds();
}
