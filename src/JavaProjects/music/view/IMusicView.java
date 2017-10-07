package JavaProjects.music.view;

import JavaProjects.music.model.IMusicModel;
import JavaProjects.music.model.ISong;

/**
 * An interface for the Music player view.
 */
public interface IMusicView extends IView {

  /**
   * Makes the view visible.
   */
  void play();

  /**
   * Refreshes the visual part of the view.
   */
  void refresh();

  /**
   * Sets the Views internal model to the given model.
   *
   * @param model The model that the view will display data for.
   */
  void setModel(IMusicModel model);

  /**
   * Gets the song from the view.
   */
  ISong getSong();

  /**
   * Gets the current beat from the view.
   */
  int getCurrentBeat();
}
