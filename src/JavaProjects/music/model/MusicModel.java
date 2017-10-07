package JavaProjects.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;


/**
 * Model of a music editor.
 */
public class MusicModel implements IMusicModel {
  //added in hw 8
  int currentbeat;
  // a Song
  private Song song;

  /**
   * Sets the song to the model.
   *
   * @param song a song
   * @throws IllegalStateException if song has invalid beats
   */
  public MusicModel(Song song)
          throws IllegalStateException {
    for (Integer i : song.getBeats()) {
      if (i < 0) {
        throw new IllegalStateException("Invalid Song");
      }
    }
    this.song = song;
  }

  /**
   * Default constructor.
   */
  public MusicModel() {
    this.song = new Song();
  }

  /**
   * Removes this note from the song.
   * @param note the note to be removed
   * @param beat the beat to remove from
   */
  @Override
  public void removeNote(INote note, int beat) {
    if (note == null) {
      throw new IllegalArgumentException("Invalid Note: Cannot be null");
    }
    if (!this.song.hasBeat(beat) || !this.song.getNotes(beat).contains(note)) {
      throw new IllegalArgumentException("Note does not exists");
    } else {
      this.song.getNotes(beat).remove(note);
    }
  }

  /**
   * Adds this note to the song.
   * @param note the note to be added
   * @param beat the beat at which to add the note
   */
  @Override
  public void addNote(INote note, int beat) {
    List<INote> n = new ArrayList<>();
    if (note == null) {
      throw new IllegalArgumentException("Invalid Note: Cannot be null");
    }
    if (!this.song.getBeats().contains(beat)) {
      n.add(note);
      song.put(beat, n);
    }
    else {
      n = song.getNotes(beat);
      if (!n.contains(note)) {
        n.add(note);
        song.put(beat, n);
      }
    }
  }

  /**
   * The last note position in this song.
   *
   * @return Integer    the position of this song as an Integer
   */
  public Integer getEndBeat() {
    return Collections.max(this.song.getBeats());
  }

  /**
   * Gets the song of this model.
   * @return the song
   */
  @Override
  public Song getSong() {
    return this.song;
  }

  /**
   * The last beat in the song.
   *
   * @return Integer    the Integer value of the last beat in this song.
   */
  public Integer getLastBeat() {
    int start = this.getEndBeat();
    List<INote> notes = song.getNotes(start);
    INote lastNote = notes.get(0);
    for (INote n : notes) {
      if (n.getBeats() > lastNote.getBeats()) {
        lastNote = n;
      }
    }
    return start + lastNote.getBeats();
  }

  /**
   * Plays two songs simultaneously by combining two models.
   * @param model    the music model to combine with
   */
  @Override
  public void playSimultaneously(IMusicModel model) {
    TreeMap<Integer, List<INote>> notes = new TreeMap<>();
    if (model == null) {
      throw new IllegalArgumentException("model must be initialized");
    }
    notes = model.getSong().getSong();
    for (int beat : notes.keySet()) {
      for (INote n : notes.get(beat)) {
        this.addNote(n, beat);
      }
    }
  }

  /**
   * Plays two pieces consecutively by combining two models.
   * @param model    the model to be combined with
   */
  @Override
  public void playConsecutively(IMusicModel model) {
    TreeMap<Integer, List<INote>> notes = new TreeMap<>();
    if (model == null) {
      throw new IllegalArgumentException("model must be initialized");
    }
    notes = model.getSong().getSong();
    int lastBeat = this.getLastBeat();
    for (int beat : notes.keySet()) {
      for (INote n : notes.get(beat)) {
        this.addNote(n, (beat + lastBeat));
      }
    }
  }

  /**
   * A method so the view would have access to a read-only version of thus model.
   */
  @Override
  public IMusicModel getReadOnlyModel() {
    return new ReadOnlyModel(this.song);
  }

  /**
   * This method was removed from the model and replaced with the console text output in the
   * factory method.
   * @return nothing
   */
  @Override
  public String showMusic() {
    return null;
  }

  /**
   * Add a beat to the model.
   */
  @Override
  public void addBeat() {
    currentbeat++;
  }

  /**
   * Subtract a beat from the model.
   */
  @Override
  public void subBeat() {
    currentbeat--;
  }

  /**
   * Set the beat of this model.
   * @param beat the beat to set to.
   */
  @Override
  public void setBeat(int beat) {
    currentbeat = beat;
  }

  /**
   * Get the beat of this model.
   * @return the beat
   */
  @Override
  public int getBeat() {
    return currentbeat;
  }
}
