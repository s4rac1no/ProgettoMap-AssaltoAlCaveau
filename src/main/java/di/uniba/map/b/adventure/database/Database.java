package di.uniba.map.b.adventure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public class Database {

  private static Connection connection;

  /**
   * Metodo d'istanza per la connessione al database.
   */
  public static void createConnection() {
    // Connessione al database
    try {
      Class.forName("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:./resources/Db/assaltoAlCaveau", "assalto", "caveau");
      System.out.println("Connessione con il db avvenuta!");
    } catch (ClassNotFoundException | SQLException e) {
    }
  }

  /**
   * Metodo d'istanza per la creazione delle tabelle
   * nel database.
   */
  public void createTable() {
    // Crea le tabelle se non esistono
    try {
      if (connection != null) {
        try (Statement statement = connection.createStatement()) {
          String player = "CREATE TABLE IF NOT EXISTS player (id INT PRIMARY KEY, current_room VARCHAR(255), remaining_time INT)";
          String game_state = "CREATE TABLE IF NOT EXISTS game_state (id INT PRIMARY KEY, object_name VARCHAR(255), used BOOLEAN)";
          statement.executeUpdate(player);
          statement.executeUpdate(game_state);
        }
      }
    } catch (SQLException e) {
        System.out.println(e);
    }
  }

  /**
   * Metodo d'istanza che permette il salvataggio
   * all'interno del database della stanza corrente
   * del giocatore, il nome della stanza in cui si trova
   * il giocatore e il tempo rimanente.
   * @param roomId Id della stanza dove si trova il giocatore
   * @param currentRoom Nome della stanza corrente
   * @param remaining_time tempo rimasto
   */
  public static void savePlayerRoom(int roomId, String currentRoom, int remaining_time) {
    // Salva la stanza corrente del giocatore
    try (PreparedStatement preparedStatement = connection.prepareStatement("MERGE INTO PLAYER (id, current_room, remaining_time) VALUES (?, ?, ?)")) {
      preparedStatement.setInt(1, roomId);
      preparedStatement.setString(2, currentRoom);
      preparedStatement.setInt(3, remaining_time);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
  }

  /**
   * Metodo d'istanza che permette di salvare gli
   * oggetti utilizzati o presi dal giocatore
   * all'interno del database.
   * @param objectId id oggetto
   * @param object_name nome oggetto
   * @param used stato dell'oggetto
   */
  public static void saveObject(int objectId, String object_name, boolean used) {
    try (PreparedStatement preparedStatement = connection.prepareStatement("MERGE INTO game_state (id, object_name, used) VALUES (?, ?, ?)")) {
      preparedStatement.setInt(1, objectId);
      preparedStatement.setString(2, object_name);
      preparedStatement.setBoolean(3, used);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  /**
   * Metodo d'istanza che permette di prelevare
   * gli oggetti che sono stati raccolti dal
   * giocatore.
   * @param objectId id oggetto da verificare
   * @return id oggetto se presente nel database
   */
  public static boolean loadTakedObjects(int objectId) {
    try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM game_state WHERE id = ?")) {
      statement.setInt(1, objectId);

      try (ResultSet resultSet = statement.executeQuery()) {
        return resultSet.next();
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return false;
  }

  /**
   * Metodo d'istanza che permette di verificare
   * se un oggetto è già stato utilizzato dal
   * giocatore.
   * @param objectId id oggetto da verificare
   * @return true se l'oggetto è stato usato, false altrimenti
   */
  public static boolean loadUsedObjects(int objectId) {
    try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM game_state WHERE id = ? AND used = true")) {
      statement.setInt(1, objectId);

      try (ResultSet resultSet = statement.executeQuery()) {
        return resultSet.next();
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return false;
  }

  /**
   * Metodo d'istanza che permette di selezionare
   * la stanza corrente di dove si trova il giocatore
   * presente nel database.
   * @return id stanza
   */
  public static int loadPlayerRoom() {
    int roomId = -1;
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT id FROM PLAYER ORDER BY id DESC LIMIT 1")) {

      if (resultSet.next()) {
        roomId = resultSet.getInt("id");
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return roomId;
  }

  /**
   * Metodo d'istanza che permette di
   * prelevare il tempo rimanente, espresso
   * in secondi dal database.
   * @return secondi rimanenti.
   */
  public static int getRemainingTime() {
    final String GET_REMAINING_TIME_QUERY = "SELECT remaining_time FROM PLAYER";

    try (PreparedStatement statement = connection.prepareStatement(GET_REMAINING_TIME_QUERY);
         ResultSet resultSet = statement.executeQuery()) {

      if (resultSet.next()) {
        return resultSet.getInt("remaining_time");
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return 0; // Ritorna un valore di default se non viene trovato il tempo rimanente nel database
  }

  /**
   * Metodo d'istanza che permette di
   * eliminare i dati del giocatore
   * nella tabella 'player'.
   */
  public static void deletePlayerData() {
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate("DELETE FROM PLAYER");
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  /**
   * Metodo d'istanza che permette di
   * eliminare i dati del giocatore
   * nella tabella 'game_state'.
   */
  public static void deleteUsedObjects() {
    try (Statement statement = connection.createStatement()) {
      statement.executeUpdate("DELETE FROM game_state");
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  /**
   * Metodo d'istanza che permette di verificare
   * se all'interno della tabella 'player' ci
   * sono righe, e quindi dati salvati.
   * @return true se ci sono dati, false altrimenti
   */
  public boolean checkSavedGame() {
    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM player")) {
      if (resultSet.next()) {
        int rowCount = resultSet.getInt("count");
        return rowCount > 0;
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return false;
  }
}