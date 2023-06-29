package di.uniba.map.b.adventure;

import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Room;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public abstract class GameDescription {

  /**
   * Stampa l'introduzione del gioco.
   *
   * Questo metodo è astratto e deve essere implementato dalle classi derivate per gestire
   * la stampa dell'introduzione specifica del gioco.
   */
  public abstract void Intro();

  /**
   * Inizializza il gioco.
   *
   * Questo metodo è astratto e deve essere implementato dalle classi derivate per gestire
   * l'inizializzazione specifica del gioco.
   */
  public abstract void initGame(); // Inizializzazione del gioco

  /**
   * Flag che indica se il gioco è stato caricato.
   *
   * Questa variabile statica indica se il gioco è stato caricato o meno.
   * Il valore booleano `true` indica che il gioco è stato caricato, mentre
   * il valore `false` indica che il gioco non è ancora stato caricato.
   */
  public static boolean loadedGame = false;

  /**
   * Lista contenente le stanze.
   *
   * Questa variabile è una lista di oggetti Room e contiene le stanze del gioco.
   * Viene utilizzata per memorizzare le stanze e consentire l'accesso e la gestione
   * delle stanze all'interno del gioco.
   */
  private final List<Room> rooms = new ArrayList<>(); // Lista contenente le stanze

  /**
   * Lista contenente i comandi.
   *
   * Questa variabile è una lista di oggetti Command e contiene i comandi del gioco.
   * Viene utilizzata per memorizzare i comandi e consentire l'accesso e la gestione
   * dei comandi all'interno del gioco.
   */
  private final List<Command> commands = new ArrayList<>(); // Lista contenente i comandi

  /**
   * Lista degli oggetti nell'inventario.
   *
   * Questa variabile è una lista di oggetti AdvObject e rappresenta gli oggetti presenti nell'inventario del giocatore.
   * Viene utilizzata per memorizzare gli oggetti che il giocatore ha raccolto durante il gioco e consentire l'accesso
   * e la gestione degli oggetti nell'inventario.
   */
  private final List<AdvObject> inventory = new ArrayList<>(); // Lista degli oggetti nell'inventario

  /**
   * Lista degli oggetti.
   *
   * Questa variabile è una lista di oggetti AdvObject e rappresenta gli oggetti presenti nel gioco.
   * Viene utilizzata per memorizzare gli oggetti che possono essere interagiti o utilizzati durante il gioco.
   * Per accedere e gestire gli oggetti, si utilizza questa lista.
   */
  private final List<AdvObject> objects = new ArrayList<>(); //Lista degli oggetti

  /**
   * Stanza attuale.
   *
   * Questa variabile rappresenta la stanza attualmente occupata nel contesto del gioco.
   * Viene utilizzata per memorizzare e accedere alla stanza corrente in cui si trova il giocatore.
   */
  private Room currentRoom; // Stanza attuale

  /**
   * Restituisce l'elenco delle stanze.
   *
   * Questo metodo restituisce l'elenco completo delle stanze presenti nel gioco.
   * Le stanze sono rappresentate come oggetti Room.
   *
   * @return L'elenco delle stanze presenti nel gioco.
   */
  public List<Room> getRooms() { // Restituisci l'elenco delle stanze
    return rooms;
  }

  /**
   * Restituisce l'elenco dei comandi.
   *
   * Questo metodo restituisce l'elenco completo dei comandi disponibili nel gioco.
   * I comandi sono rappresentati come oggetti Command.
   *
   * @return L'elenco dei comandi disponibili nel gioco.
   */
  public List<Command> getCommands() { // Restituisci l'elenco dei comandi
    return commands;
  }

  /**
   * Restituisce l'elenco degli oggetti nell'inventario.
   *
   * Questo metodo restituisce l'elenco completo degli oggetti presenti nell'inventario del giocatore.
   * Gli oggetti sono rappresentati come oggetti AdvObject.
   *
   * @return L'elenco degli oggetti presenti nell'inventario del giocatore.
   */
  public List<AdvObject> getInventory() { // Restituisci l'elenco degli oggetti nell'inventario
    return inventory;
  }

  /**
   * Restituisce tutti gli oggetti.
   *
   * Questo metodo restituisce l'elenco completo di tutti gli oggetti presenti nel gioco.
   * Gli oggetti sono rappresentati come oggetti di tipo AdvObject.
   *
   * @return L'elenco completo di tutti gli oggetti presenti nel gioco.
   */
  public List<AdvObject> getObjects() { // Restituisce tutti gli oggetti
    return objects;
  }

  /**
   * Restituisce la stanza attuale.
   *
   * Questo metodo restituisce la stanza attualmente occupata nel contesto del gioco.
   * La stanza attuale è rappresentata come un oggetto di tipo Room.
   *
   * @return La stanza attuale nel contesto del gioco.
   */
  public Room getCurrentRoom() { // Restituisci la stanza attuale
    return currentRoom;
  }

  /**
   * Imposta la stanza attuale.
   *
   * Questo metodo permette di impostare la stanza attualmente occupata nel contesto del gioco.
   * La stanza attuale viene specificata come parametro, e deve essere un oggetto di tipo Room.
   *
   * @param currentRoom La stanza da impostare come stanza attuale nel contesto del gioco.
   */
  public void setCurrentRoom(Room currentRoom) { // Imposta la stanza attuale
    this.currentRoom = currentRoom;
  }

  /**
   * Prossima mossa.
   *
   * Questo metodo astratto viene chiamato per gestire la prossima mossa nel gioco.
   * Riceve in input un oggetto di tipo ParserOutput che contiene le informazioni
   * sull'input dell'utente analizzato dal parser.
   *
   * @param p L'oggetto ParserOutput che contiene le informazioni sull'input dell'utente.
   */
  public abstract void nextMove(ParserOutput p); // Prossima mossa

}
