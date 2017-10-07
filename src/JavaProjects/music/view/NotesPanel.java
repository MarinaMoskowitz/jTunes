package JavaProjects.music.view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import JavaProjects.music.model.INote;
import JavaProjects.music.model.MultEnd;
import JavaProjects.music.model.MusicModel;
import JavaProjects.music.model.Repeat;
import JavaProjects.music.model.Song;
import JavaProjects.music.model.ISong;
import JavaProjects.music.model.Note;

/**
 * Display the Notes of the model.Song. The starting beat of a note
 * is displayed as a Black Box; the beats for which the note is extended through are displayed as
 * green rectangles.
 */
public class NotesPanel extends JPanel {
  private boolean practice;
  private ISong song;
  private int currentBeat;
  private List<Repeat> repeats;
  private List<MultEnd> endings;


  /**
   * A constructor for a notes panel.
   */
  public NotesPanel() {
    super();
    this.song = new Song();
    this.currentBeat = 0;
  }

  /**
   * A constructor for a notes panel that takes in a song.
   * @param s the song to be used
   */
  public NotesPanel(ISong s) {
    super();
    this.song = s;
    this.currentBeat = 0;
  }

  /**
   * Adds to the current beats of this panel.
   */
  protected void addToBeat() {
    this.currentBeat++;
  }

  /**
   * Subtracts from the current beats of this panel.
   */
  protected void subFromBeat() {
    this.currentBeat--;
  }

  protected void setBeat(int i) {
    this.currentBeat = i;
  }

  // some values to be used in creating the panel
  int leftnotemargin = 90;
  int rightnotemargin = 0;
  int rightpitchmargin = 90;
  int beatwidth = 30;
  int beatHeight = 18;

  /**
   * Creates the visuals for the notes panel.
   * @param g the graphics
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(Color.WHITE);
    MusicModel tempmodel = new MusicModel((Song)this.song);
    g2d.fillRect(0,0, 1100, 1000);

    g2d.setColor(Color.BLACK);
    Font notefont = new Font("Serif", Font.PLAIN, 20);
    g2d.setFont(notefont);
    if (!this.song.getSong().isEmpty()) {
      //flips the arraylist of pitches
      ArrayList<String> orig = new ArrayList<>(this.song.getPitches());
      ArrayList<String> pitchrange = new ArrayList<>();
      for (int i = orig.size() - 1 ; i >= 0 ; i--) {
        pitchrange.add(orig.get(i));
      }

      this.drawLines(g2d, pitchrange, tempmodel);
      this.drawNotes(g2d, pitchrange, tempmodel);
      this.paintRepeats(g2d, pitchrange);
      this.paintEnds(g2d, pitchrange);
      this.paintLine(g2d, pitchrange);

      this.drawPitch(g2d, pitchrange);

    }

    if (practice) {
      g2d.drawString("Practice", 10, 20);
    }
  }

  /**
   * Draws the notes.
   * @param g2d the Graphics
   */
  public void drawNotes(Graphics g2d, ArrayList<String> pitchrange, MusicModel tempmodel) {
    for (int i = 0 ; i <= tempmodel.getLastBeat() ; i++) {

      // for starting beats (black blocks)
      if (this.song.getSong().containsKey(i)) {
        for (INote n : this.song.getSong().get(i)) {
          g2d.setColor(Color.BLACK);
          g2d.fillRect(i * beatwidth + leftnotemargin, 34 + noteHeight(pitchrange, (Note)n),
                  beatwidth, beatHeight);
        }
      }

      // for trailing beats (green blocks)
      if (this.song.getSong().containsKey(i)) {
        for (INote n : this.song.getSong().get(i)) {
          int startingbeat = i + 1;
          int endbeat = i + n.getBeats();
          while (endbeat - startingbeat > 0) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect((startingbeat) * beatwidth + leftnotemargin, 35 +
                            noteHeight(pitchrange, (Note)n),
                    beatwidth, beatHeight - 1);
            startingbeat++;
          }
        }
      }
    }
  }

  /**
   * Draw lines and beat in 4 seconds.
   * @param g2d the Graphics
   */
  public void drawLines(Graphics g2d, ArrayList<String> pitchrange, MusicModel tempmodel) {
    // draw line vertical
    g2d.setColor(Color.BLACK);
    for (int i = 0 ; i <= tempmodel.getLastBeat() + 3 ; i = i + 4) {
      g2d.drawLine(leftnotemargin + i * beatwidth, 34, leftnotemargin + i * beatwidth, 34 +
              pitchrange.size() * 18);
      g2d.drawString(Integer.toString(i), leftnotemargin + i * beatwidth, 32);
      if (leftnotemargin + i * beatwidth > rightnotemargin) {
        rightnotemargin = leftnotemargin + i * beatwidth;
      }
    }
    // draw line horizontal
    for (int i = 0; i < pitchrange.size(); i++) {
      g2d.drawLine(leftnotemargin, 34 + i * 18, rightnotemargin, 34 + i * 18);
    }
    g2d.drawLine(leftnotemargin, 34 + pitchrange.size() * 18, rightnotemargin, 34 +
            pitchrange.size() * 18);

    // draw white rect on left side to cover scrolling lines
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, rightpitchmargin, 1000);
  }


  /**
   * Draws the pitches.
   * @param g2d the Graphics
   */
  public void drawPitch(Graphics g2d, ArrayList<String> pitchrange) {
    // draw white rect on left side to cover scrolling lines
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, rightpitchmargin, 1000);
    g2d.setColor(Color.BLACK);
    for (int i = 0; i < pitchrange.size(); i++) {
      g2d.drawString(pitchrange.get(i), 35, 50 + (i * 18));
    }
  }

  /**
   * Paints the red line at the current beat.
   * @param g2d the Graphics
   */
  public void paintLine(Graphics g2d, ArrayList<String> pitchrange) {
    g2d.setColor(Color.RED);
    g2d.drawLine(leftnotemargin + currentBeat * beatwidth, 34, leftnotemargin +
            currentBeat * beatwidth, 34 + pitchrange.size() * 18);
  }

  /**
   * Calculates the height of the placement of the note.
   * @param pitchrange the range of pitches to be used
   * @param n the note for the height to be calculated for
   * @return the vertical placement of the note representation in the visual.
   */
  private static int noteHeight(ArrayList<String> pitchrange, Note n) {
    int beatHeight = 18;
    int totalHeight = 0;
    for (int i = 0 ; i < pitchrange.size() ; i++) {
      if (pitchrange.get(i).equals(n.getPitch().toString() + n.getOctave())) {
        totalHeight = i * beatHeight;
      }
    }
    return totalHeight;
  }

  /**
   * Scrolls the notes and the beats one beat width to the left.
   */
  protected void scrollLeft() {
    this.leftnotemargin = this.leftnotemargin + beatwidth;
  }

  /**
   * Scrolls the notes and beats one beat width to the right.
   */
  protected void scrollRight() {
    this.leftnotemargin = this.leftnotemargin - beatwidth;
  }

  protected void setNotPractice() {
    this.practice = false;
  }

  protected void setPractice() {
    this.practice = true;
  }

  protected void setRepeats(List<Repeat> list) {
    this.repeats = list;
  }

  protected void setEndings(List<MultEnd> list) {
    this.endings = list;
  }
  /**
   * Paints the red line at the current beat.
   * @param g2d the Graphics
   */
  public void paintRepeats(Graphics g2d, ArrayList<String> pitchrange) {
    List<Integer> starts = new ArrayList<>();
    List<Integer> ends = new ArrayList<>();
    for (Repeat r : repeats) {
      starts.add(r.getStartbeat());
      ends.add(r.getEndbeat());
    }

    Graphics2D g2 = (Graphics2D) g2d;
    g2.setStroke(new BasicStroke(2));
    g2d.setColor(Color.ORANGE);
    for (int s : starts) {
      g2d.drawLine(leftnotemargin + s * beatwidth, 36, leftnotemargin +
              s * beatwidth, 34 + pitchrange.size() * 18);
    }

    g2d.setColor(Color.BLUE);
    for (int e : ends) {
      g2d.drawLine(leftnotemargin + e * beatwidth, 36, leftnotemargin +
              e * beatwidth, 34 + pitchrange.size() * 18);
    }
    g2.setStroke(new BasicStroke(1));
  }

  public void paintEnds(Graphics g2d, ArrayList<String> pitchrange) {
    List<Integer> starts = new ArrayList<>();
    List<Integer> endstarts = new ArrayList<>();
    List<Integer> ends = new ArrayList<>();
    for (MultEnd e : endings) {
      starts.add(e.getStartbeat());
      endstarts.add(e.getBeginEndbeat());
      ends.add(e.getEndbeat());
    }

    Graphics2D g2 = (Graphics2D) g2d;
    g2.setStroke(new BasicStroke(2));
    g2d.setColor(Color.PINK);
    for (int s : starts) {
      g2d.drawLine(leftnotemargin + s * beatwidth, 36, leftnotemargin +
              s * beatwidth, 34 + pitchrange.size() * 18);
    }

    g2d.setColor(Color.YELLOW);
    for (int es : endstarts) {
      g2d.drawLine(leftnotemargin + es * beatwidth, 36, leftnotemargin +
              es * beatwidth, 34 + pitchrange.size() * 18);
    }

    g2d.setColor(Color.DARK_GRAY);
    for (int e : ends) {
      g2d.drawLine(leftnotemargin + e * beatwidth, 36, leftnotemargin +
              e * beatwidth, 34 + pitchrange.size() * 18);
    }
    g2.setStroke(new BasicStroke(1));

  }
}