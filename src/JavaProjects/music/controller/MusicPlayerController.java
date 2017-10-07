package JavaProjects.music.controller;

import JavaProjects.music.view.IView;
import JavaProjects.music.model.IMusicModel;

/**
 * Controller for the Music player that connects the model and the view.
 */
public class MusicPlayerController implements IMusicController {
  private IMusicModel model;
  private IView view;

  /**
   * Controller constructor.
   * @param model the music model to be used
   * @param view the view to be used
   */
  public MusicPlayerController(IMusicModel model, IView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Runs the controller.
   */
  @Override
  public void start() {
    this.view.setModel(this.model.getReadOnlyModel());
    view.play();
  }
}
