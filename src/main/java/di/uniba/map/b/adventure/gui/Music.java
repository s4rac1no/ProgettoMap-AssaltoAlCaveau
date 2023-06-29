package di.uniba.map.b.adventure.gui;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public class Music {

  /**
   * Clip attributo utilizzato per riprodurre suoni.
   */
  private static Clip clip;

  /**
   * Riproduce un effetto sonoro a partire dal percorso specificato.
   *
   * @param path Il percorso del file audio da riprodurre.
   */
  public static void playEffect(String path) {
    try {
      File musicPath = new File(path);
      if (musicPath.exists()) {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
        clip = AudioSystem.getClip();
        clip.open(audioInput);
        clip.start();
      }
    } catch (Exception ex) {
      System.out.println("Errore nella riproduzione della musica!");
    }
  }

  /**
   * Avvia la riproduzione della musica di sottofondo.
   * Viene mostrato un messaggio di conferma all'utente per attivare la musica.
   * Se l'utente conferma, la musica viene avviata e riprodotta in loop continuo.
   * Se l'utente nega, la musica non viene avviata.
   * Se il file audio non viene trovato o si verificano errori durante la riproduzione, vengono stampati messaggi di errore sulla console.
   */
  public static void startMusic() {
      
      try {
      File musicPath = new File("./resources/music/Menu.wav");

      if (musicPath.exists()) {
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
        clip = AudioSystem.getClip();
        clip.open(audioInput);

        Object[] message = {"Per un esperienza completa attiva la musica"};

        Object[] options = {"Si, vai con la musica", "No, sarà per la prossima volta"};
        JFrame yesnoOption = new JFrame();
        yesnoOption.setAlwaysOnTop(true);
        int selectedOption = JOptionPane.showOptionDialog(yesnoOption,
                message, "Assalto al Caveau",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                options, options[1]);
        if (selectedOption == JOptionPane.OK_OPTION) { // si (risposta)
          clip.start();
          clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        if (selectedOption == JOptionPane.NO_OPTION) { // No, sarà per la prossima volta (risposta)
            stopMusic();
        }
      }
      else {
        System.out.println("____________________________________________________");
        System.err.println("Impossibile riprodurre la musica, file non trovato.");
        System.out.println("____________________________________________________");
      }
    } catch (HeadlessException | IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
      System.out.println("____________________________________________________");
      System.err.println("Impossibile riprodurre la musica.\nEccezione verificata: " + ex);
      System.out.println("____________________________________________________");
    }
  }

  /**
   * Metodo statico per interrompere la musica
   */
  public static void stopMusic() {
    clip.stop();
    clip.close();
  }
}
