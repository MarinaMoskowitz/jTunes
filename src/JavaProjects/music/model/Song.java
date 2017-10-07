package JavaProjects.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import JavaProjects.music.util.CompositionBuilder;

/**
 * A class to represent a song.
 */
public class Song implements ISong {

  // A Song represented as a TreeMap with the keys represented as Integers for beats
  // and a List represented as values for the notes.
  protected TreeMap<Integer, List<INote>> song;

  // ADDED IN HW6
  // the tempo of a song
  protected int tempo;


  /**
   *  Constructor for a Song.
   *  INVARIANT:
   *  tempo cannot be negative
   */
  public Song() {
    this.song = new TreeMap<>();
    if (tempo < 0) {
      throw new IllegalArgumentException("Invalid tempo");
    }
    this.tempo = 10000;
  }

  /**
   * Gets all of the starting beats in the song.
   * @return the List of starting beats
   */
  @Override
  public List<Integer> getBeats() {
    return new ArrayList<Integer>(this.song.keySet());
  }

  /**
   * Returns the highest Note in this song.
   *
   * @return highest Note in this Song
   */
  @Override
  public INote maxNote() {
    Integer firstBeat = Collections.min(song.keySet());
    INote max = song.get(firstBeat).get(0);
    for (List<INote> beat : song.values()) {
      for (INote n : beat) {
        if (n.compareTo(max) > 0) {
          max = n;
        }
      }
    }
    return max;
  }

  /**
   * Gets all the notes in this song.
   *
   * @param beat the notes at this beat
   * @return  a list of all the notes in this song
   */
  @Override
  public List<INote> getNotes(int beat) {
    return this.song.get(beat);
  }

  /**
   * Gets this song.
   *
   * @return this song
   */
  @Override
  public TreeMap<Integer, List<INote>> getSong() {
    return this.song;
  }

  /**
   * Checks if a given beat is in a song.
   *
   * @param beat the beat being checked
   * @return true if the beat is in this song, false if not
   */
  @Override
  public boolean hasBeat(int beat) {
    return this.song.containsKey(beat);
  }

  /**
   * Puts a note in a song at a given beat.
   *
   * @param beat the beat for the note being placed in this song
   * @param n the note to be placed in this song
   */
  @Override
  public void put(int beat, List<INote> n) {
    this.song.put(beat, n);
  }

  /**
   * Returns the lowest Note in this song.
   *
   * @return lowest Note in the this Song
   */
  @Override
  public INote minNote() {
    Integer firstBeat = Collections.min(song.keySet());
    INote min = song.get(firstBeat).get(0);
    for (List<INote> beat : song.values()) {
      for (INote n : beat) {
        if (n.compareTo(min) < 0) {
          min = n;
        }
      }
    }
    return min;
  }

  /**
   * Get all the pitches in this song.
   *
   * @return a list of all the pitches
   */
  @Override
  public List<String> getPitches() {
    List<String> out = new ArrayList<>();
    INote min = minNote();
    INote max = maxNote();
    for (int i = minNote().getOctave(); i <= max.getOctave(); i++) {
      for (Pitch p : Pitch.values()) {
        if (i > min.getOctave() ||
                (i == min.getOctave() && p.ordinal() >= min.getPitch().ordinal())) {
          if (i < max.getOctave() ||
                  (i == max.getOctave() && p.ordinal() <= max.getPitch().ordinal())) {
            out.add(p.toString() + i);
          }
        }
      }
    }
    return out;
  }

  /**
   * ADDED IN HW6
   * Get the tempo of this song.
   *
   * @return the tempo of this song
   */
  public int getTempo() {
    return this.tempo;
  }

  /**
   * Sets the tempo of this song.
   * @param tempo the new tempo
   */
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public int getLastBeat() {
    int start = this.getEndBeat();
    List<INote> notes = this.getNotes(start);
    INote lastNote = notes.get(0);
    for (INote n : notes) {
      if (n.getBeats() > lastNote.getBeats()) {
        lastNote = n;
      }
    }
    return start + lastNote.getBeats();
  }

  @Override
  public int getEndBeat() {
    return Collections.max(this.getBeats());
  }

  @Override
  public int getBegBeat() {
    return Collections.min(this.getBeats());
  }

  /**
   * Adds a note.
   * @param note the note to add
   * @param beat the beat to add the note
   */
  @Override
  public void addNote(INote note, int beat) {
    List<INote> n = new ArrayList<>();
    if (note == null) {
      throw new IllegalArgumentException("Invalid Note: Cannot be null");
    }
    if (!this.getBeats().contains(beat)) {
      n.add(note);
      this.put(beat, n);
    }
    else {
      n = this.getNotes(beat);
      if (!n.contains(note)) {
        n.add(note);
        this.put(beat, n);
      }
    }
  }

  /**
   * Get notes at a beat.
   * @param i Beat to search for Notes.
   * @return the list of notes.
   */
  @Override
  public List<INote> getNotesAtBeat(int i) {
    return getSong().getOrDefault(i, new ArrayList<INote>());
  }

  /**
   * Gets the repeats for this song.
   * @return a new list.
   */
  @Override
  public List<Repeat> getRepeats() {
    return new ArrayList<>();
  }

  /**
   * Gets the endings for this song.
   * @return a new list.
   */
  public List<MultEnd> getEnds() {
    return new ArrayList<>();
  }

  /**
   * The Builder class for building a song.
   */
  public static final class Builder implements CompositionBuilder<ISong> {
    ISong tobuild;

    /**
     * Constructor for the Builder.
     */
    public Builder() {
      this.tobuild = new Song();
    }

    /**
     * Constructor for the Builder with a song.
     * @param s the song to start with.
     */
    public Builder(ISong s) {
      this.tobuild = s;
    }

    /**
     * Constructs an actual composition, given the notes that have been added.
     * @return The new composition
     */
    @Override
    public ISong build() {
      return tobuild;
    }

    /**
     * Sets the tempo of the piece.
     * @param tempo The speed, in microseconds per beat
     * @return This builder
     */
    @Override
    public CompositionBuilder<ISong> setTempo(int tempo) {
      tobuild.setTempo(tempo);
      return new Builder(tobuild);
    }

    /**
     * Adds a new note to the piece.
     * @param start The start time of the note, in beats
     * @param end The end time of the note, in beats
     * @param instrument The instrument number (to be interpreted by MIDI)
     * @param pitch The pitch (in the range [0, 127], where 60 represents C4,
     *              the middle-C on a piano)
     * @param volume The volume (in the range [0, 127])
     * @return the builder
     */
    @Override
    public CompositionBuilder<ISong> addNote(int start, int end, int instrument, int pitch,
                                             int volume) {
      MusicModel tempmodel = new MusicModel((Song)tobuild);
      int octave = (pitch - 12) / 12;
      Pitch p = Pitch.convertNumToPitch(pitch % 12);

      INote n = new Note(p, octave, end - start, instrument, volume);
      tempmodel.addNote(n, start);
      tobuild = tempmodel.getSong();
      return new Builder(tobuild);
    }
  }
}


