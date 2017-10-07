package JavaProjects.music.view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;

import javax.swing.*;

import JavaProjects.music.model.INote;
import JavaProjects.music.model.MusicModel;
import JavaProjects.music.model.Song;
import JavaProjects.music.model.ISong;
import JavaProjects.music.model.Note;
import JavaProjects.music.model.Pitch;

/**
 * A new view that supports the extra credit features.
 */
public class ExtraCreditView extends FinalView{
  private ISong convertedpractice;
  private ISong practice = new Song();
  private final int tempoincrement = 10000;
  protected Timer noteAdd = new Timer(song.getTempo() / 1000, new NoteAdder());
  private int curx, cury, startbeat;

  /**
   * A constructor that takes in a song.
   * @param song the song to use
   */
  public ExtraCreditView(ISong song) {
    // the super gets the repeats and endings for the song.
    super(song);
    notesPanel.getInputMap().put(KeyStroke.getKeyStroke("UP"), "addtempo");
    notesPanel.getActionMap().put("addtempo", new IncTempoAction());
    notesPanel.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "subtempo");
    notesPanel.getActionMap().put("subtempo", new SubTempoAction());
    convertedpractice = practiceConvert(song);
    notesPanel.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "startpractice");
    notesPanel.getActionMap().put("startpractice", new StartPracticeAction());
  }

  /**
   * An action to increase the tempo.
   */
  public class IncTempoAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
      int orig = song.getTempo();
      if (orig - tempoincrement > 0) {
        song.setTempo(orig - tempoincrement);
        movingTimer.setDelay(song.getTempo() / 1000);
        beatTimer.setDelay(song.getTempo() / 1000);
        noteAdd.setDelay(song.getTempo() / 1000);
      }
    }
  }

  /**
   * An action to decrease the tempo.
   */
  public class SubTempoAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
      int orig = song.getTempo();
      song.setTempo(orig + tempoincrement);
      movingTimer.setDelay(song.getTempo() / 1000);
      beatTimer.setDelay(song.getTempo() / 1000);
      noteAdd.setDelay(song.getTempo() / 1000);
    }
  }

  /**
   * An action to add notes in a row.
   */
  protected class NoteAdder extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
      MouseEvent me = new MouseEvent(notesPanel, 0, 0, 0, curx, cury, 1, false);
      int whitexfromedge = me.getX() - keysPanel.whiteLeftPadding;
      int octavenum = (whitexfromedge / keysPanel.octaveWidth) + 1;
      if (me.getY() > keysPanel.blackHeight) {
        int pitchpixelnum = whitexfromedge % keysPanel.octaveWidth;
        int whitepitchnum = (pitchpixelnum / keysPanel.whiteWidth) + 1;
        int pitchnum = -1;
        pitchnum = keyConverter(whitepitchnum);
        Pitch realpitch = Pitch.convertNumToPitch(pitchnum);
        song.addNote(new Note(realpitch, octavenum, currentBeat - startbeat + 1), startbeat);
        new AddAction().actionPerformed(e);
      }
      if (me.getY() <= keysPanel.blackHeight) {
        int pitchcheck = (me.getX() - keysPanel.whiteLeftPadding) % keysPanel.octaveWidth;
        int blackxfromedge = me.getX() - keysPanel.blackLeftPadding;
        if (pitchcheck < keysPanel.whiteWidth * 3) {
          int pitchpixelnum1 = blackxfromedge % keysPanel.octaveWidth;
          if (pitchpixelnum1 % keysPanel.whiteWidth < keysPanel.blackWidth) {
            if (pitchpixelnum1 / keysPanel.whiteWidth == 0) {
              Pitch pitch = Pitch.CSharp;
              song.addNote(new Note(pitch, octavenum, currentBeat - startbeat + 1), startbeat);
              new AddAction().actionPerformed(e);
            }
            else if (pitchpixelnum1 / keysPanel.whiteWidth == 1) {
              Pitch pitch = Pitch.DSharp;
              song.addNote(new Note(pitch, octavenum, currentBeat - startbeat + 1), startbeat);
              new AddAction().actionPerformed(e);
            }
          }
        }
        else if ((pitchcheck < keysPanel.whiteWidth * 7) &&
                (pitchcheck > (keysPanel.whiteWidth * 3) + 14)) {
          int pitchpixelnum = (blackxfromedge % keysPanel.octaveWidth) - 14 -
                  (keysPanel.whiteWidth * 2);
          if (pitchpixelnum % keysPanel.whiteWidth < keysPanel.blackWidth) {
            if (pitchpixelnum / keysPanel.whiteWidth == 0) {
              Pitch pitch = Pitch.FSharp;
              song.addNote(new Note(pitch, octavenum, currentBeat - startbeat + 1), startbeat);
              new AddAction().actionPerformed(e);
            }
            if (pitchpixelnum / keysPanel.whiteWidth == 1) {
              Pitch pitch = Pitch.GSharp;
              song.addNote(new Note(pitch, octavenum, currentBeat - startbeat + 1), startbeat);
              new AddAction().actionPerformed(e);
            }
            if (pitchpixelnum / keysPanel.whiteWidth == 2) {
              Pitch pitch = Pitch.ASharp;
              song.addNote(new Note(pitch, octavenum, currentBeat - startbeat + 1), startbeat);
              new AddAction().actionPerformed(e);
            }
          }
        }
      }
    }
  }

  /**
   * What happens when the mouse is clicked.
   * @param e the mouse click triggering this method
   */
  public void mouseClicked(MouseEvent e) {
    // nothing should happen
  }

  /**
   * What happens when the mouse is pressed.
   * @param e the mouse click
   */
  @Override
  public void mousePressed(MouseEvent e) {
    curx = e.getX();
    cury = e.getY();
    startbeat = currentBeat;
    if (!practicemode) {
      noteAdd.start();
    }
    if (practicemode) {
      Note toadd;
      int whitexfromedge = e.getX() - keysPanel.whiteLeftPadding;
      int octavenum = (whitexfromedge / keysPanel.octaveWidth) + 1;
      if (e.getY() > keysPanel.blackHeight) {
        int pitchpixelnum = whitexfromedge % keysPanel.octaveWidth;
        int whitepitchnum = (pitchpixelnum / keysPanel.whiteWidth) + 1;
        int pitchnum = -1;
        pitchnum = keyConverter(whitepitchnum);
        Pitch realpitch = Pitch.convertNumToPitch(pitchnum);
        toadd = new Note(realpitch, octavenum, 1);
        if (convertedpractice.getNotes(currentBeat).contains(toadd)) {
          practice.addNote(toadd, currentBeat);
        }
      }
      if (e.getY() <= keysPanel.blackHeight) {
        int pitchcheck = (e.getX() - keysPanel.whiteLeftPadding) % keysPanel.octaveWidth;
        int blackxfromedge = e.getX() - keysPanel.blackLeftPadding;
        if (pitchcheck < keysPanel.whiteWidth * 3) {
          int pitchpixelnum1 = blackxfromedge % keysPanel.octaveWidth;
          if (pitchpixelnum1 % keysPanel.whiteWidth < keysPanel.blackWidth) {
            if (pitchpixelnum1 / keysPanel.whiteWidth == 0) {
              Pitch pitch = Pitch.CSharp;
              toadd = new Note(pitch, octavenum, 1);
              if (convertedpractice.getNotes(currentBeat).contains(toadd)) {
                practice.addNote(toadd, currentBeat);
              }
            }
            else if (pitchpixelnum1 / keysPanel.whiteWidth == 1) {
              Pitch pitch = Pitch.DSharp;
              toadd = new Note(pitch, octavenum, 1);
              if (convertedpractice.getNotes(currentBeat).contains(toadd)) {
                practice.addNote(toadd, currentBeat);
              }
            }
          }
        }
        else if ((pitchcheck < keysPanel.whiteWidth * 7) &&
                (pitchcheck > (keysPanel.whiteWidth * 3) + 14)) {
          int pitchpixelnum = (blackxfromedge % keysPanel.octaveWidth) - 14 -
                  (keysPanel.whiteWidth * 2);
          if (pitchpixelnum % keysPanel.whiteWidth < keysPanel.blackWidth) {
            if (pitchpixelnum / keysPanel.whiteWidth == 0) {
              Pitch pitch = Pitch.FSharp;
              toadd = new Note(pitch, octavenum, 1);
              if (convertedpractice.getNotes(currentBeat).contains(toadd)) {
                practice.addNote(toadd, currentBeat);
              }
            }
            if (pitchpixelnum / keysPanel.whiteWidth == 1) {
              Pitch pitch = Pitch.GSharp;
              toadd = new Note(pitch, octavenum, 1);
              if (convertedpractice.getNotes(currentBeat).contains(toadd)) {
                practice.addNote(toadd, currentBeat);
              }
            }
            if (pitchpixelnum / keysPanel.whiteWidth == 2) {
              Pitch pitch = Pitch.ASharp;
              toadd = new Note(pitch, octavenum, 1);
              if (convertedpractice.getNotes(currentBeat).contains(toadd)) {
                practice.addNote(toadd, currentBeat);
              }
            }
          }
        }
      }
    }
  }

  /**
   * What happens when the mouse is released.
   * @param e the mouse release
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    noteAdd.stop();
    // if all notes are correct
    if (practicemode && noteListComparer(practice.getSong().get(currentBeat),
            convertedpractice.getSong().get(currentBeat))) {
      MusicModel tempmodel = new MusicModel((Song)song);
      if (currentBeat < tempmodel.getLastBeat()) {
        if (currentBeat + 1 > rightBeat) {
          notesPanel.scrollRight();
          rightBeat++;
          leftBeat++;
        }
        keysPanel.addToBeat();
        notesPanel.addToBeat();
        currentBeat++;
        midiPlayer.setBeat(midiPlayer.getBeat() + 1);
        refresh();
      }
    }
  }

  /**
   * A helper method that compares two lists to see if they have the same notes.
   * @param first the first list
   * @param second the second list
   * @return the boolean
   */
  private boolean noteListComparer(List<INote> first, List<INote> second) {
    try {
      return (first.size() == second.size() &&
              first.containsAll(second) &&
              second.containsAll(first));
    }
    catch (NullPointerException e) {
      return false;
    }
  }

  /**
   * Converts a song to one with no sustains for the practice mode.
   * @param s the song to convert
   * @return the converted song
   */
  private ISong practiceConvert(ISong s) {
    ISong converted = new Song();
    Set<Integer> keys = s.getSong().keySet();
    for (int i : keys) {
      // each beat
      for (INote n : s.getSong().get(i)) {
        if (n.getBeatDuration() > 1) {
          for (int j = 0 ; j < n.getBeatDuration() ; j++) {
            converted.addNote(new Note(n.getPitch(), n.getOctave(), 1), i + j);
          }
        }
        else {
          converted.addNote(n, i);
        }
      }
    }
    return converted;
  }

  protected class StartPracticeAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
      practicemode = true;
      notesPanel.setPractice();
      practice = new Song();
      convertedpractice = practiceConvert(song);
      refresh();
    }
  }
}
