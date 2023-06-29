package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.Utils;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public class Parser {

  /**
   * Insieme di parole chiave da ignorare o escludere
   * durante l'analisi del testo.
   * Le parole chiave sono utilizzate per identificare
   * parole comuni o poco significative che non contribuiscono
   * alla comprensione del testo.
   * Questo insieme di parole chiave viene utilizzato per
   * filtrare e rimuovere tali parole durante le operazioni
   * di analisi del testo.
   */
  private final Set<String> stopwords;

  /**
   * Crea un nuovo oggetto Parser con l'insieme di parole chiave da ignorare durante l'analisi del testo.
   *
   * @param stopwords l'insieme di parole chiave da ignorare durante l'analisi del testo
   */
  public Parser(Set<String> stopwords) {
    this.stopwords = stopwords;
  }

  /**
   * Controlla se il token corrisponde a un comando nella lista dei comandi.
   *
   * @param token    il token da controllare
   * @param commands la lista dei comandi
   * @return l'indice del comando corrispondente se trovato, altrimenti -1
   */
  private int checkForCommand(String token, List<Command> commands) { //controllo se il token corrispponde al comando
    for (int i = 0; i < commands.size(); i++) {
      if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
        return i;
      }
    }
    return -1; //non trovato
  }

  /**
   * Controlla se il token corrisponde a un oggetto nella lista degli oggetti.
   *
   * @param token   il token da controllare
   * @param objects la lista degli oggetti
   * @return l'indice dell'oggetto corrispondente se trovato, altrimenti -1
   */
  private int checkForObject(String token, List<AdvObject> objects) { //controllo se il token corrisponde ad un oggetto
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).getName().equals(token) || objects.get(i).getAlias().contains(token)) {
        return i;
      }
    }
    return -1; //non trovato
  }

  /**
   * Eseguo il parsing dell'input
   *
   * @param command   Il comando digitato in input
   * @param commands  La lista dei comandi disponibili
   * @param objects   La lista degli oggetti disponibili
   * @param inventory L'inventario attuale
   *
   * @return Un oggetto ParserOutput il quale contiene il risulato del parsing
   */
  public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {

    List<String> tokens = Utils.parseString(command, stopwords);

    if (!tokens.isEmpty()) {
      int ic = checkForCommand(tokens.get(0), commands);
      if (ic > -1) {
        if (tokens.size() > 2) {
          int io1 = checkForObject(tokens.get(1), objects);
          int io2 = checkForObject(tokens.get(2), objects);
          int ioinv1 = -1; //indice oggetto1 inventario
          int ioinv2 = -1; //indice oggetto2 inventario

          // Controllo se i primi due oggetti sono nell'inventario
          if (io1 < 0) {
            io1 = checkForObject(tokens.get(1), inventory);
            if (io1 < 0 && tokens.size() > 3) {
              io1 = checkForObject(tokens.get(2), inventory);
            }
            ioinv1 = io1;  //l'indice dell'oggetto nell'inventario viene assegnato a ioinv
            io1 = -1; //indice dell'oggetto nell'elenco degli oggetti viene impostato su -1
            //assicura che venga scelto correttamente l'oggetto dall'inventario anziché dall'elenco degli oggetti.
          }
          if (io2 < 0) {
            io2 = checkForObject(tokens.get(2), inventory);
            if (io2 < 0 && tokens.size() > 3) {
              io2 = checkForObject(tokens.get(3), inventory);
            }
            ioinv2 = io2;
            io2 = -1;
          }

          if (io1 > -1 && io2 > -1 && ioinv1 < 0 && ioinv2 < 0) {
            // Restituisce il comando/oggetto1/oggetto2 corrispondenti
            return new ParserOutput(commands.get(ic), objects.get(io1), objects.get(io2));
          } else if (io1 > -1 && ioinv2 > -1 && ioinv1 < 0) {
            // Restituisce il comando/oggetto1/oggetto2 nell'inventario corrispondenti
            return new ParserOutput(commands.get(ic), objects.get(io1), inventory.get(ioinv2));
          } else if (ioinv1 > -1 && io2 > -1 && ioinv2 < 0) {
            // Restituisce il comando/oggetto1 nell'inventario/oggetto2 corrispondenti
            return new ParserOutput(commands.get(ic), inventory.get(ioinv1), objects.get(io2));
          } else if (ioinv1 > -1 && ioinv2 > -1) {
            // Restituisce il comando/oggetto1 nell'inventario/oggetto2 nell'inventario corrispondenti
            return new ParserOutput(commands.get(ic), inventory.get(ioinv1), inventory.get(ioinv2));
          } else {
            // Restituisce solo il comando/oggetto1 null/oggetto2 null
            return new ParserOutput(commands.get(ic), null, null);
          }
        } else if (tokens.size() > 1) {
          int io = checkForObject(tokens.get(1), objects);
          int ioinv = -1;

          // Controllo se l'oggetto è nell'inventario
          if (io < 0) {
            io = checkForObject(tokens.get(1), inventory);
            if (io < 0 && tokens.size() > 2) {
              io = checkForObject(tokens.get(2), inventory);
            }
            ioinv = io;
            io = -1;
          }

          if (io > -1 && ioinv < 0) {
            // Restituisce il comando/oggetto corrispondenti/oggetto nell'inventario è inesistente (null)
            return new ParserOutput(commands.get(ic), objects.get(io), null);
          } else if (ioinv > -1) {
            // Restituisce il comando/oggetto nell'inventario corrispondente/l'oggetto è inesistente (null)
            return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
          } else {
            // Restituisce solo il comando/oggetto null/oggetto nell'inventario null
            return new ParserOutput(commands.get(ic), null, null);
          }
        } else {
          // Restituisce solo il comando/oggetto null/oggetto nell'inventario null
          return new ParserOutput(commands.get(ic), null, null);
        }
      } else {
        // Comando non trovato
        return new ParserOutput(null, null, null);
      }
    } else {
      // Input dell'utente vuoto
      return null;
    }
  }
}