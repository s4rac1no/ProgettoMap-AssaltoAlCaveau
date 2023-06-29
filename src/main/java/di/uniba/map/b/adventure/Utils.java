package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.database.Database;
import di.uniba.map.b.adventure.gui.EngineGUI;
import di.uniba.map.b.adventure.gui.MenuGui;
import di.uniba.map.b.adventure.gui.Music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public class Utils extends Thread {

  /**
   * Indicatore di fine del gioco.
   *
   * Questa variabile indica se il gioco è terminato o meno. Ha un valore booleano,
   * inizialmente impostato a false. Quando il gioco termina, il valore viene impostato a true.
   */
  private static boolean gameOver = false;

  /**
   * Durata in secondi.
   *
   * Questa variabile rappresenta la durata in secondi di un intervallo di tempo.
   * Viene dichiarata come una costante finale (final int) e il suo valore viene
   * assegnato durante l'inizializzazione dell'oggetto.
   */
  private final int seconds;

  /**
   * Tempo rimanente.
   *
   * Questa variabile indica il tempo rimanente in una determinata operazione o intervallo di tempo.
   * È una variabile statica di tipo `int`, utilizzata per tenere traccia del tempo residuo.
   */
  private static int remainingTime;

  /**
   * Indicatore di visualizzazione del Game Over.
   *
   * Questa variabile booleana indica se il messaggio di Game Over deve essere visualizzato o meno.
   * Inizialmente, il valore è impostato su `true`, indicando che il messaggio di Game Over deve essere visualizzato.
   * Questo valore può essere modificato per gestire la visualizzazione del messaggio in base alle regole del gioco.
   */
  private static boolean displayGameOver = true;

  /**
   * Indicatore di esecuzione del thread.
   *
   * Questa variabile booleana indica lo stato di esecuzione del thread.
   * Quando il valore è `true`, indica che il thread è in esecuzione.
   * Il valore di questa variabile può essere modificato per controllare l'esecuzione o l'interruzione del thread.
   */
  private static boolean isRunning = true;

  /**
   * Oggetto di lock per la sincronizzazione.
   *
   * Questa variabile è un oggetto di lock utilizzato per la sincronizzazione di sezioni di codice.
   * Viene utilizzata per creare una sezione critica in cui solo un thread può accedere alla sezione alla volta.
   * Essendo dichiarata come `static final Object`, l'oggetto di lock è condiviso tra tutte le istanze della classe
   * ed è immutabile (non può essere modificato dopo la sua inizializzazione).
   */
  private static final Object LOCK = new Object();

  /**
   * Costruttore della classe Utils.
   *
   * Questo costruttore crea un'istanza della classe Utils e inizializza la durata in secondi
   * utilizzando il valore fornito come parametro.
   *
   * @param seconds la durata in secondi da utilizzare
   */
  public Utils(final int seconds){
    this.seconds = seconds;
  }

  /**
   * Legge il contenuto di un file e lo restituisce come una stringa.
   *
   * @param nomeFile il nome del file da leggere
   * @return una stringa contenente il contenuto del file
   */
  public static String readFile(String nomeFile) {

    //creo l'oggetto stringBuilder che verrà restituito alla fine
    StringBuilder stringBuilder = new StringBuilder();

    try {
      File file = new File(nomeFile); // Creo un oggetto File utilizzando il nome del file passato come argomento
      Scanner scanner = new Scanner(file); // Creo un oggetto Scanner per leggere il contenuto del file

      while (scanner.hasNextLine()) { // Continua finché ci sono altre righe nel file
        String linea = scanner.nextLine(); // Legge la riga successiva
        stringBuilder.append(linea).append("\n"); // Aggiunge la riga al StringBuilder
      }

      scanner.close(); // Chiude lo Scanner per rilasciare le risorse

    } catch (FileNotFoundException e) { // Gestisce l'eccezione se il file non viene trovato
      System.err.println("Errore durante il caricamento del file: " + e.getMessage());
    }

    return stringBuilder.toString(); // Restituisce il contenuto del file come una stringa
  }


  /**
   * Carica un elenco di stringhe da un file e restituisce un insieme di stringhe.
   *
   * @param file il file da cui caricare l'elenco di stringhe
   * @return un insieme di stringhe contenente l'elenco di stringhe caricate dal file
   * @throws IOException se si verifica un errore durante la lettura del file
   */
  public static Set<String> loadFileListInSet(File file) throws IOException {
    Set<String> set = new HashSet<>();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    while (reader.ready()) {
      set.add(reader.readLine().trim().toLowerCase());
    }
    reader.close();
    return set;
  }

  /**
   * Effettua il parsing di una stringa in una lista di token, escludendo le parole non significative.
   *
   * @param string la stringa da analizzare
   * @param stopwords un insieme di parole non significative da escludere
   * @return una lista di token risultanti dal parsing della stringa
   */
  public static List<String> parseString(String string, Set<String> stopwords) {
    List<String> tokens = new ArrayList<>();
    String[] split = string.toLowerCase().split("\\s+");
    for (String t : split) {
      if (!stopwords.contains(t)) {
        tokens.add(t);
      }
    }
    return tokens;
  }

  /**
   * Esegue il thread e gestisce il conteggio del tempo e le azioni associate al termine del tempo.
   */
  @Override
  public void run() {
    String time;
    remainingTime = seconds;
    try {
      for (int i = seconds; i >= 0; i--) {
        time = Integer.toString(i);
        if (!gameOver) {
          EngineGUI.displayTime(time);
        } else {
          EngineGUI.returnToMenu(time);
        }
        if (!getDisplayGameOver()) {
          isRunning = false;
          break;
        }
        remainingTime--;
        Thread.sleep(1000); // Pausa di 1 secondo
      }
      if (isRunning) {
        if (!gameOver) {
          gameOver = true;
          String gameOverText = readFile("./resources/txt/GameOver.txt");
          Music.playEffect("./resources/music/Busted.wav");
          EngineGUI.displayTextSetDelay(gameOverText);
          EngineGUI.disableInputArea();
          EngineGUI.hideLabels();
          Database.deletePlayerData();
          EngineGUI.restartTimer();

        } else {
          gameOver = false;
          EngineGUI.closeWindow();
          new MenuGui().setVisible(true);
        }
      }
    } catch (InterruptedException e) {
      // Il thread è stato interrotto
    }
  }

  /**
   * Restituisce il tempo rimanente.
   *
   * @return il tempo rimanente in secondi
   */
  public static int getRemainingTime(){
    return remainingTime;
  }

  /**
   * Restituisce lo stato corrente del flag "displayGameOver".
   *
   * @return true se la visualizzazione del game over è abilitata, false altrimenti
   */
  public static boolean getDisplayGameOver() {
    synchronized (LOCK) {
      return displayGameOver;
    }
  }

  /**
   * Imposta lo stato del flag "displayGameOver" con il valore specificato.
   *
   * @param value il valore booleano da impostare per il flag "displayGameOver"
   */
  public static void setDisplayGameOver(boolean value) {
    synchronized (LOCK) {
      displayGameOver = value;
    }
  }
}


