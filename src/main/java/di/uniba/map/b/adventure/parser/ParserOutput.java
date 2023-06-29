package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;

/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public class ParserOutput {

  /**
   * Comando Command.
   *
   * Questa variabile rappresenta un oggetto di tipo `Command`.
   * Viene utilizzata per tenere traccia di un comando specifico all'interno del contesto in cui è utilizzata.
   */
  private Command command;

  /**
   * Oggetto AdvObject.
   *
   * Questa variabile rappresenta un oggetto di tipo `AdvObject`.
   * Viene utilizzata per tenere traccia di un oggetto specifico all'interno del contesto in cui è utilizzata.
   */
  private AdvObject object;

  /**
   * Oggetto AdvObject nell'inventario.
   *
   * Questa variabile rappresenta un oggetto `AdvObject` che è presente nell'inventario.
   * Viene utilizzata per tenere traccia di un oggetto specifico nell'inventario.
   */
  private AdvObject invObject;

  /**
   * Costruttore della classe ParserOutput.
   * Questo costruttore crea un'istanza della classe ParserOutput e inizializza gli oggetti
   * command, object e invObject utilizzando i valori forniti come parametri.
   *
   * @param command l'oggetto Command associato all'output del parser
   * @param object l'oggetto AdvObject associato all'output del parser
   * @param invObject l'oggetto AdvObject nell'inventario associato all'output del parser
   */
  public ParserOutput(Command command, AdvObject object, AdvObject invObject) {
    this.command = command;
    this.object = object;
    this.invObject = invObject;
  }

  /**
   * Restituisce il comando corrente.
   *
   * @return il comando corrente
   */
  public Command getCommand() {
    return command;
  }

  /**
   * Restituisce l'oggetto corrente.
   *
   * @return il comando corrente
   */
  public AdvObject getObject() {
    return object;
  }

  /**
   * Restituisce l'oggetto dell'inventario corrente.
   *
   * @return il comando corrente
   */
  public AdvObject getInvObject() {
    return invObject;
  }


}

