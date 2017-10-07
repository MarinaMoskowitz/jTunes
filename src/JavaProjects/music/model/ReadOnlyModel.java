package JavaProjects.music.model;

/**
 * ReadOnly extension of the MusicModel class. Suppresses mutable methods.
 */
public class ReadOnlyModel extends MusicModel {

  public ReadOnlyModel(Song s) {
    super(s);
  }

  @Override
  public void removeNote(INote n, int beat) {
    throw new UnsupportedOperationException("Cannot call method on read only model");
  }

  @Override
  public void addNote(INote n, int beat) {
    throw new UnsupportedOperationException("Cannot call method on read only model");
  }

  @Override
  public void playSimultaneously(IMusicModel model) {
    throw new UnsupportedOperationException("Cannot call method on read only model");
  }

  @Override
  public void playConsecutively(IMusicModel model) {
    throw new UnsupportedOperationException("Cannot call method on read only model");
  }
}
