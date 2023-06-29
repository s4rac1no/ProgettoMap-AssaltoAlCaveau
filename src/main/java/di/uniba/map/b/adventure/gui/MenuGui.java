package di.uniba.map.b.adventure.gui;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.database.Database;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import javax.swing.*;



/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public class MenuGui extends javax.swing.JFrame {

  /**
   * Costruttore pubblico di MenuGui
   */

  public MenuGui() {
    initComponents();
    initFont();
    Music.startMusic();
  }

  private Font pricedowFont;
  private Font font;

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Title = new javax.swing.JLabel();
        ExitGame = new javax.swing.JButton();
        LoadGame = new javax.swing.JButton();
        NewGame = new javax.swing.JButton();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Assalto al Caveau");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setBackground(new java.awt.Color(204, 255, 255));
        Title.setFont(new java.awt.Font("C059", 0, 48)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("ASSALTO AL CAVEAU");
        getContentPane().add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 790, 150));

        ExitGame.setText("Esci dal gioco");
        ExitGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitGameActionPerformed(evt);
            }
        });
        getContentPane().add(ExitGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 250, 30));

        LoadGame.setText("Carica Partita");
        LoadGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadGameActionPerformed(evt);
            }
        });
        getContentPane().add(LoadGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 270, 40));

        NewGame.setFont(new java.awt.Font("Palatino", 0, 13)); // NOI18N
        NewGame.setText("Nuova Partita");
        NewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameActionPerformed(evt);
            }
        });
        getContentPane().add(NewGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 270, 40));

        background.setIcon(new javax.swing.ImageIcon("./resources/images/menuimage.jpg"));
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 490));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

  /**
   * Gestisce l'evento generato quando viene premuto il pulsante "Nuova Partita".
   * Effettua le operazioni necessarie per avviare una nuova partita.
   * Chiude la finestra corrente, ferma la riproduzione della musica, crea una nuova connessione al database,
   * crea la tabella nel database, elimina i dati del giocatore precedentemente salvati,
   * elimina gli oggetti utilizzati precedentemente nel gioco e avvia la finestra del motore di gioco.
   * @param evt l'evento generato
   */
  private void NewGameActionPerformed(java.awt.event.ActionEvent evt) {
    setVisible(false);
    dispose();
    Music.stopMusic();
    Database db = new Database();
    Database.createConnection();
    db.createTable();
    Database.deletePlayerData();
    Database.deleteUsedObjects();
    EngineGUI egui = new EngineGUI();
    egui.setVisible(true);

  }

  /**
   * Gestisce l'evento generato quando viene premuto il pulsante "Carica Partita".
   * Effettua le operazioni necessarie per caricare una partita salvata.
   * Crea una connessione al database, crea la tabella nel database,
   * verifica se esistono dati di gioco salvati per il giocatore.
   * Se esistono, chiude la finestra corrente, imposta il flag di partita caricata a true
   * e avvia la finestra del motore di gioco.
   * Altrimenti, mostra un messaggio di errore.
   * @param evt l'evento generato
   */
  private void LoadGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadGameActionPerformed
    Database db = new Database();
    Database.createConnection();
    db.createTable();
    boolean hasPlayerData = db.checkSavedGame();
    if(hasPlayerData){
      setVisible(false);
      dispose();
      GameDescription.loadedGame = true;
      EngineGUI egui = new EngineGUI();
      egui.setVisible(true);
    } else {
      JOptionPane.showMessageDialog(null, "Nessun salvataggio trovato !", "Errore", JOptionPane.INFORMATION_MESSAGE);
    }
  }//GEN-LAST:event_LoadGameActionPerformed

  /**
   * Gestisce l'evento generato quando viene premuto il pulsante "Esci dal Gioco".
   * Mostra una finestra di dialogo di conferma per chiedere all'utente se è sicuro di voler uscire dal gioco.
   * Se l'utente conferma l'uscita, il metodo termina l'applicazione.
   * @param evt l'evento generato
   */
  private void ExitGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitGameActionPerformed
    // TODO add your handling code here:
    Object[] message = {"Sei sicuro di voler uscire dal gioco?"};

    Object[] options = {"Si", "No"};
    JFrame yesnoOption = new JFrame();
    yesnoOption.setAlwaysOnTop(true);
    int selectedOption = JOptionPane.showOptionDialog(yesnoOption,
            message, "Assalto al Caveau",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
            options, options[1]);
    if (selectedOption == JOptionPane.YES_OPTION) { // si (risposta)
      System.exit(0);
    }
  }//GEN-LAST:event_ExitGameActionPerformed

/**
 * Metodo principale che avvia l'applicazione.
 *
 * @para
 **/
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(MenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(MenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(MenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(MenuGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {

        new MenuGui().setVisible(true);

      }
    });
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton ExitGame;
    private static javax.swing.JButton LoadGame;
    private static javax.swing.JButton NewGame;
    private static javax.swing.JLabel Title;
    private static javax.swing.JLabel background;
    // End of variables declaration//GEN-END:variables


  /**
   * Inizializza il font utilizzato nell'applicazione.
   * Carica il file del font personalizzato e imposta il font.
   */
  private void initFont() {

    try (InputStream is = new BufferedInputStream(new FileInputStream("resources//font//pricedow.ttf"))) {

      font = Font.createFont(Font.TRUETYPE_FONT, is);
      pricedowFont = font.deriveFont(Font.PLAIN, 40);

      this.setFont(pricedowFont);
      Title.setFont(pricedowFont);
      Title.setFont(pricedowFont);
      NewGame.setFont(pricedowFont);
      LoadGame.setFont(pricedowFont);
      ExitGame.setFont(pricedowFont);

    } catch (FontFormatException | IOException ex) {
      JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Font non caricato correttamente, imposterò un font di default", JOptionPane.ERROR_MESSAGE);
    }
  }
}
    
