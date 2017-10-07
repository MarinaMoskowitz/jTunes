package JavaProjects.music.view;

import JavaProjects.music.model.IMusicModel;
import JavaProjects.music.model.ISong;

/**
 * Interface for all the views in our Music-Editor Project.
 */
public interface IView {

  /**
   * Sets up the given views model.
   */
  void setModel(IMusicModel model);

  /**
   * Sets up the given views song.
   */
  void setSong(ISong song);

  /**
   * Plays the view.
   */
  void play();
}
