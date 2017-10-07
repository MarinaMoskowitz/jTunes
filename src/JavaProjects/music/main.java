package JavaProjects.music;

import java.io.FileReader;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;

import JavaProjects.music.controller.IMusicController;
import JavaProjects.music.controller.MusicPlayerController;
import JavaProjects.music.model.MusicModel;
import JavaProjects.music.model.RepeatSong;
import JavaProjects.music.model.Song;
import JavaProjects.music.util.CompositionBuilder;
import JavaProjects.music.model.IMusicModel;
import JavaProjects.music.model.ISong;
import JavaProjects.music.util.MusicReader;
import JavaProjects.music.view.ExtraCreditView;

public class main {
  /**
   * Used to manually test the player.
   * @param args the input arguments
   * @throws IOException when there is an error with the input/output
   * @throws InvalidMidiDataException When the midi data is invalid
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    CompositionBuilder<ISong> b = new Song.Builder();
    ISong song = MusicReader.parseFile(new FileReader("mystery-2.txt"), b);

    RepeatSong repeatsong = new RepeatSong(song);
    repeatsong.addRepeats(12);
    repeatsong.addRepeats(20, 28);
    repeatsong.addEnds(repeatsong.getLastBeat() - 48, repeatsong.getLastBeat() - 28,
            repeatsong.getLastBeat() - 16);
    repeatsong.addEnds(repeatsong.getLastBeat() - 48, repeatsong.getLastBeat() - 16,
            repeatsong.getLastBeat());

    ExtraCreditView view = new ExtraCreditView(repeatsong);
    IMusicModel model = new MusicModel(repeatsong);
    IMusicController controller = new MusicPlayerController(model, view);
    controller.start();
  }
}
