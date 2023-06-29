package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.Utils;
import di.uniba.map.b.adventure.database.Database;
import di.uniba.map.b.adventure.gui.EngineGUI;
import di.uniba.map.b.adventure.gui.Keypad;
import di.uniba.map.b.adventure.gui.Music;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import static di.uniba.map.b.adventure.Utils.readFile;
import static di.uniba.map.b.adventure.type.CommandType.SAVE;

/**
 *
 * @author Leonardo Montemurro, Lorenzo Saracino, Vincenzo Sarra
 */
public class AssaltoAlCaveau extends GameDescription {

  //lettura file introduzione partita

  /**
   * Metodo che permette la lettura del
   * del file di introduzione.
   */
  @Override
  public void Intro() {
    String intro = readFile("./resources/txt/Intro.txt");
    EngineGUI.displayIntroTextSet(intro);
  }

  /**
   * Metodo per l'inizializzazione dei comandi,
   * delle stanze e degli oggetti presenti nel gioco.
   */
  @Override
  public void initGame() {

    //set stanza, con il metodo getRooms().add creo una lista di stanze
    Room bancomatRoom;
    bancomatRoom = new Room(1, "Sala Bancomat", """
                                                  Ti trovi nella sala bancomat, dopo aver fatto evacuare più civili possibile, le porte si chiudono attorno a te,
                                                  grazie ad un complice da remoto, adesso non si torna pi\u00f9 indietro.""");
    bancomatRoom.setLook("""
                         Prima che la rapina cominciasse hai fatto uscire fuori tutti i civili mentre, alcuni dipendenti,
                         si sono barricati nelle stanze interne della banca, uno di questi ha chiuso elettronicamente
                         la porta che separa la stanza bancomat dall'atrio principale.
                         Siete bloccati, il tempo stringe! Bisogna trovare un modo per sbloccare la porta!""");
    getRooms().add(bancomatRoom);

    Room mainHall = new Room(2, "Atrio Principale", "Ti trovi nell'atrio principale una stanza di grande dimensioni.");
    mainHall.setLook("Vedi delle cassette, simili a quelle di sicurezza, forza dai bisogna fare veloce.");
    getRooms().add(mainHall);

    Room bankTellerRooms = new Room(3, "Sala Sportelli Bancari", "Ti trovi nella sala sportelli bancari, "
            + "i dipendenti sono spaventati e sono tutti sotto le scrivanie.\n"
            + "Fra tutti noti un dirigente della banca, sta cercando di prendere tempo per farti arrestare.");
    bankTellerRooms.setLook("Le scrivanie sono sommerse di documenti, noti anche la presenza di due uffici.");
    getRooms().add(bankTellerRooms);

    Room northOffice = new Room(4, "Ufficio Nord", "Ti trovi nell'ufficio nord.");
    northOffice.setLook("Noti una grande scrivania, sembra essere un ufficio importante.");
    getRooms().add(northOffice);

    Room suthOffice = new Room(5, "Ufficio Sud", "Ti trovi nell'ufficio sud...");
    suthOffice.setLook("Scrivania e sedia sembrano essere molto comode.");
    getRooms().add(suthOffice);


    //corridoioP1, P1 sta per parte 1
    Room hallwayP1 = new Room(6, "Corridoio", "Ti trovi nel corridoio, ci sono diverse stanze, noti una porta chiusa.");
    hallwayP1.setLook("Vedi tante stanze, il corridoio si espande verso est.");
    getRooms().add(hallwayP1);

    //corridoioP2, P2 sta per parte 2
    Room hallwayP2 = new Room(7, "Corridoio", "E' davvero lungo.\nUn corridoio in cui ci sono le stanze più importanti della banca.....");
    hallwayP2.setLook("Ci sono tre quadri molto strani...");
    getRooms().add(hallwayP2);

    Room conferenceRoom = new Room(8, "Sala Conferenze", "Ti trovi nella sala conferenze le luci sono spente ed è difficile vedere qualcosa.");
    conferenceRoom.setLook("Sembra si sia tenuta una conferenza da poco, vedi un appendiabiti");
    getRooms().add(conferenceRoom);
    if (!Database.loadUsedObjects(123)) {
      conferenceRoom.setLookable(false);
    }

    Room directorOffice;
    directorOffice = new Room(9, "Ufficio Direttore", "Ti trovi nell'ufficio del direttore.\nIl direttore viene trattato bene ci sono dei pasticciotti sulla scrivania.");
    directorOffice.setLook("""
                           Una scrivania vintage in legno con un computer di ultima genarazione...
                           
                           Che stile contrastante, il direttore sar\u00e0 una persona strana.""");
    getRooms().add(directorOffice);

    Room documentRoom = new Room(10, "Sala Documenti", "Ti trovi nella sala documenti, qui c'è tanta polvere e un ammasso di carte.");
    documentRoom.setLook("Tra i vari documenti sparsi, vedi un busta.\nNoti anche scatola.");
    getRooms().add(documentRoom);


    Room securityRoom;
    securityRoom = new Room(13, "Zona di Sicurezza", "Ti trovi nella zona di sicurezza, sei vicino al tuo obbiettivo.");
    securityRoom.setLook("""
                         
                         A sud ci sono delle scale...
                         
                         ...sicuro quella \u00e8 la strada giusta.""");
    getRooms().add(securityRoom);

    Room finalHallway = new Room(14, "Corridoio", """
                                                  Ti trovi nella parte finale del corridoio, c'\u00e8 uno spiraglio di luce.
                                                  
                                                  Hai i soldi con te?""");
    finalHallway.setLook("Ad est c'è una luce, sarà l'uscita??.");
    getRooms().add(finalHallway);

    Room employeeParking = new Room(15, "Parcheggio Dipendenti", "Ti trovi nel parcheggio dei dipendenti.");
    employeeParking.setLook("Vedi un muro e un furgone.");
    getRooms().add(employeeParking);

    Room caveau = new Room(16, "Caveau", """
                                         ...stai scendendo le scale...


                                         Ti trovi nella stanza che precede la cassaforte.""");
    caveau.setLook("Vedi un tastierino, a sud c'è una grande cassaforte chiusa, manca poco"
            + " e troverai il bottino.");
    getRooms().add(caveau);

    Room safeCaveau = new Room(17, "Cassaforte Caveau", "Finalmente il denaro è qui!!!\n\nCosa aspetti prendilo...e fuggi da questa posto il prima possibile!!!");
    safeCaveau.setLook("Ci sono un sacco di contanti, anche dei lingotti, ma è difficile trasportarli.");
    getRooms().add(safeCaveau);

    // Stanza di INIZIO GIOCO
    setCurrentRoom(bancomatRoom);

    //setAccesible, stanza accessibile = true / stanza non accessibile = false
    mainHall.setAccessible(false); //stanza chiusa, usbhack per aprire
    bankTellerRooms.setAccessible(false); //stanza chiusa, badge per aprire
    northOffice.setAccessible(false); //stanza chiusa, spaventare responsabile per aprire

    hallwayP1.setAccessible(false);
    directorOffice.setAccessible(false); //stanza chiusa, apri porta con chiave
    securityRoom.setAccessible(false);
    safeCaveau.setAccessible(false);
    employeeParking.setAccessible(false);

    //set mappa del gioco
    bancomatRoom.setBorders(null, null, null, mainHall);
    mainHall.setBorders(null, null, bancomatRoom, bankTellerRooms);
    bankTellerRooms.setBorders(northOffice, suthOffice, mainHall, hallwayP1);
    northOffice.setBorders(null, bankTellerRooms, null, null);
    suthOffice.setBorders(bankTellerRooms, null, null, null);
    hallwayP1.setBorders(conferenceRoom, directorOffice, bankTellerRooms, hallwayP2);
    hallwayP2.setBorders(documentRoom, securityRoom, hallwayP1, finalHallway);

    directorOffice.setBorders(hallwayP1, null, null, null);
    conferenceRoom.setBorders(null, hallwayP1, null, null);

    documentRoom.setBorders(null, hallwayP2, null, null);

    securityRoom.setBorders(hallwayP2, caveau, null, null);
    caveau.setBorders(securityRoom, safeCaveau, null, null);
    safeCaveau.setBorders(caveau, null, null, null);
    finalHallway.setBorders(null, null, hallwayP2, employeeParking);
    employeeParking.setBorders(null, null, finalHallway, null);


    //set comandi del gioco
    Command north = new Command(CommandType.NORTH, "nord"); //si chiama nord
    north.setAlias(new String[]{"n", "N", "Nord", "NORD"}); //ed ha i suoi alias
    getCommands().add(north);

    Command south = new Command(CommandType.SOUTH, "sud");
    south.setAlias(new String[]{"s", "S", "Sud", "SUD"});
    getCommands().add(south);

    Command east = new Command(CommandType.EAST, "est");
    east.setAlias(new String[]{"e", "E", "Est", "EST"});
    getCommands().add(east);

    Command west = new Command(CommandType.WEST, "ovest");
    west.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
    getCommands().add(west);

    Command help = new Command(CommandType.HELPME, "helpme");
    help.setAlias(new String[]{"helpme", "help-me"});
    getCommands().add(help);

    Command inventory = new Command(CommandType.INVENTORY, "inventario"); //nome inventario
    inventory.setAlias(new String[]{"inv", "I", "i", "zaino", "borsone"}); //alis dell'inventario, inv
    getCommands().add(inventory);


    Command look = new Command(CommandType.LOOK, "osserva");
    look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"}); //alis comando osserva
    getCommands().add(look);

    Command examine = new Command(CommandType.EXAMINE, "esamina");
    examine.setAlias(new String[]{"ispeziona"});
    getCommands().add(examine);

    Command open = new Command(CommandType.OPEN, "apri");
    open.setAlias(new String[]{"aprire"});
    getCommands().add(open);

    Command use = new Command(CommandType.USE, "utilizza");
    use.setAlias(new String[]{"usa", "estrai", "utilizza"});
    getCommands().add(use);

    Command push = new Command(CommandType.PUSH, "premi");
    push.setAlias(new String[]{"spingi", "attiva"});
    getCommands().add(push); //ogni volta che creo un comando lo vado ad aggiungere il comando alla lista del comando

    Command take = new Command(CommandType.TAKE, "prendi");
    take.setAlias(new String[]{"raccogli"});
    getCommands().add(take);

    Command getIn = new Command(CommandType.GET_IN, "sali");
    getIn.setAlias(new String[]{"salta", "sali"});
    getCommands().add(getIn);

    Command save = new Command(CommandType.SAVE, "salva");
    save.setAlias(new String[]{"salvataggio"});
    getCommands().add(save);

    //set oggetti inventario

    AdvObject flipperZero = new AdvObject(0, "FlipperZero", """
                                                             Un dispositivo creato da hacker russi
                                                             con una serie di tool per bypassare sistemi informatici.""");
    flipperZero.setAlias(new String[]{"flipperzero", "flipperZero", "flipper"});
    getInventory().add(flipperZero);
    getObjects().add(flipperZero);

    AdvObject gun = new AdvObject(1, "Pistola", """
                                                 Una beretta 92Fs con il numero seriale abrasato,
                                                ricorda che serve per intimidire i dipendenti, non siete assassini.""");
    gun.setAlias(new String[]{"pistola",  "arma"});
    gun.setUsable(true);
    getInventory().add(gun);
    getObjects().add(gun);

    //set oggetti/oggettiContenitori in atrio Principale
    AdvObject slidingDoor = new AdvObject(2, "Porta", "Questa massicia porta scorrevole è stata chiusa da qualche dipendente"
            + " che ha lanciato l'allarme");
    slidingDoor.setAlias(new String[]{"porta"});
    bancomatRoom.getObjects().add(slidingDoor);
    slidingDoor.setOpenable(true);   //porta chiusa, ma apribile
    slidingDoor.setOpen(false);

    AdvObject badge = new AdvObject(4, "Badge", "Un badge di livello 1, chissà se la banca"
            + " avesse in programma di assumere personale. ");
    badge.setAlias(new String[]{"tessera", "tessera magnetica", "badge"});
    badge.setTakeable(true); //si può prendere
    badge.setUsable(true); //si può usare
    getObjects().add(badge);

    AdvObjectContainer safetyBoxes = new AdvObjectContainer(3, "Cassetta", """
                                                                           Sembrano delle cassette, ci sono molte scartoffie ma noti una cosa...
                                                                           
                                                                            Una cassetta sembra essere semi-aperta.""");
    safetyBoxes.setAlias(new String[]{"cassette", "cassetta"});
    mainHall.getObjects().add(safetyBoxes);
    safetyBoxes.setOpenable(true);    //cassetta di sicurezza apribile
    if (!Database.loadTakedObjects(4)) {
      safetyBoxes.add(badge);
    }

    AdvObject doorBankTellerRooms = new AdvObject(5, "Porta Sala Sportelli Bancari", "Questa porta non ha una serratura, ma un lettore di tessere");
    doorBankTellerRooms.setAlias(new String[]{"porta"});
    mainHall.getObjects().add(doorBankTellerRooms);
    doorBankTellerRooms.setOpenable(true);
    doorBankTellerRooms.setOpen(false);


    //set oggetti/oggettiContenitori in Sala Sportelli Bancari

    AdvObject desks = new AdvObject(6, "Scrivanie", "C'è un cassetto con la scritta 'UTENSILI'.");
    desks.setAlias(new String[]{"scrivania", "scrivanie"});
    bankTellerRooms.getObjects().add(desks);


    AdvObject stapleShooter = new AdvObject(8, "Sparagraffette", """
                                                                 Non credo ti possa servire.
                                                                 Purch\u00e8 non vuoi sparare qualcuno solo per divertimento.""");
    stapleShooter.setAlias(new String[]{"graffatrice", "sparagraffette", "spillatrice"});
    stapleShooter.setTakeable(true);
    stapleShooter.setUsable(true);
    getObjects().add(stapleShooter);

    AdvObjectContainer drawer = new AdvObjectContainer(7, "Cassetto", "Niente di interessante, ma magari c'è qualcosa che può servirmi.");
    drawer.setAlias(new String[]{"cassetto"});
    bankTellerRooms.getObjects().add(drawer);
    drawer.setOpen(true); //cassetto apribile
    drawer.setOpenable(true);     //cassetto aperto
    drawer.add(stapleShooter);

    AdvObject turnstile = new AdvObject(11, "Tornelli", "Tornelli a tutta altezza, impossibile passare.\nBisogna disattivarli.");
    turnstile.setAlias(new String[]{"tornello", "tornelli"});
    bankTellerRooms.getObjects().add(turnstile);
    turnstile.setOpenable(true); //tornello apribile ma,
    turnstile.setOpen(false);   //tornello chiuso
    
    AdvObject doorNorthOffice = new AdvObject(137, "Porta Ufficio Nord", "Questa porta ha una serratura, ma le chiavi non riesco a trovarle.");
    doorNorthOffice.setAlias(new String[]{"porta", "portone"});
    bankTellerRooms.getObjects().add(doorNorthOffice);
    doorNorthOffice.setOpenable(true); //porta apribile ma,
    doorNorthOffice.setOpen(false);   //tornello chiuso

    //set oggetti/oggettiContenitori in UfficioSud

    AdvObject deskOfficeSuth = new AdvObject(12, "Scrivania", "Questa scrivania è molto bella...\nAnche il computer non scherza...");
    deskOfficeSuth.setAlias(new String[]{"tavolo", "scrivania"});
    suthOffice.getObjects().add(deskOfficeSuth);

    AdvObject computer = new AdvObject(13, "Computer", "Il dipendente giocava a prato fiorito...");
    computer.setAlias(new String[]{"pc", "computer"});
    suthOffice.getObjects().add(computer);
    

    AdvObject officeChair = new AdvObject(14, "Sedia da ufficio", "La sedia è in pelle, ma non mi sembra il momento per pensare alle sedie...");
    officeChair.setAlias(new String[]{"sedia"});
    suthOffice.getObjects().add(officeChair);

    //set oggetti/oggettiContenitori in UfficioNord

    AdvObject deskOfficeNorth;
    deskOfficeNorth = new AdvObject(15, "Scrivania", """
                                                       Una normale scrivania d'ufficio... aspetta!
                                                       ...sotto la scrivania noti un pulsante rosso! Mi chiedo a che cosa possa servire...""");
    deskOfficeNorth.setAlias(new String[]{"scrivania", "tavolo"});
    northOffice.getObjects().add(deskOfficeNorth);

    AdvObject switchOn = new AdvObject(16, "Pulsante", "Il pulsante è impostato su ON, forse bisogna disattivarlo.");
    switchOn.setAlias(new String[]{"pulsante", "bottone"});
    northOffice.getObjects().add(switchOn);
    if (!Database.loadUsedObjects(16)) {
      switchOn.setPushable(true); //si può premere
    }
    getObjects().add(switchOn);

    //set oggetti/oggettiContenitori nella prima parte di corridoio (hallwayP1)

    AdvObject directorDoor = new AdvObject(17, "Porta Ufficio Direttore", "Sembra la porta dell'ufficio del direttore, c'è scritto anche sulla targhetta...\nE' qui il caveau?");
    directorDoor.setAlias(new String[]{"porta"});
    hallwayP1.getObjects().add(directorDoor);
    directorDoor.setOpenable(true); //porta apribile ma,
    directorDoor.setOpen(false);   //porta chiusa


    //set oggetti/oggettiContenitori nella seconda parte di corridoio (hallwayP2)


    AdvObject securityDoor = new AdvObject(21, "Porta Zona di Sicurezza", "Questa porta è super blindata, non c'è serratura.");
    securityDoor.setAlias(new String[]{"porta"});
    hallwayP2.getObjects().add(securityDoor);
    securityDoor.setOpenable(true); //porta apribile ma,
    securityDoor.setOpen(false);   //porta chiusa

    //set oggetti/oggettiContenitori nella sala conferenze
        
    AdvObject stand = new AdvObject(24, "Appendiabiti", "Qualcuno ha dimenticato un cappotto.");
    stand.setAlias(new String[]{"attaccapanni", "appendiabiti"});
    conferenceRoom.getObjects().add(stand);


    AdvObject coat = new AdvObject(25, "Cappotto", """
                                                   Un bel cappotto di pelle con una targhetta dorata....
                                                   
                                                   ...c'\u00e8 scritto Director, chissa se avr\u00e0 dei soldi in quei tasconi.""");
    coat.setAlias(new String[]{"giubbotto", "giacca", "cappotto"});
    conferenceRoom.getObjects().add(coat);

    AdvObject key = new AdvObject(27, "Chiave", "Una chiave in più, torna sempre utile...");
    key.setAlias(new String[]{"chiavi", "chiave"});
    key.setTakeable(true); //si può prendere quindi,
    key.setUsable(true); //si può usare
    getObjects().add(key);

    AdvObject keychain = new AdvObject(50, "Portachiave", """
                                                          Inutile portachiave con la faccia del direttore...
                                                          
                                                          
                                                          ...\u00e8 solito dei direttori di banca essere egocentrici...
                                                          
                                                          Lo terr\u00f2 con me per ricordo.""");
    keychain.setAlias(new String[]{"portachiavi", "portachiave"});
    keychain.setTakeable(true);
    getObjects().add(keychain);


    AdvObjectContainer pockets;
    pockets = new AdvObjectContainer(26, "Tasconi", """
                                                      Queste tasche sembrano essere capienti...
                                                      Ho sentito un rumore...
                                                      Cosa ci sarà dentro?""");
    pockets.setAlias(new String[]{"tasca", "tasche", "tasconi"});
    conferenceRoom.getObjects().add(pockets);
    pockets.setOpenable(true);
    pockets.setOpen(true);
    if (!Database.loadTakedObjects(27)) {
      pockets.add(key);
    }
    if (!Database.loadTakedObjects(50)) {
      pockets.add(keychain);
    }

    //set oggetti/oggettiContenitori nell'ufficio direttore
    AdvObject computerDirector = new AdvObject(28, "Computer", "Bisogna inserire la password per utilizzarlo.");
    computerDirector.setAlias(new String[]{"pc", "computer"});
    computerDirector.setUsable(true);  //si può usare
    directorOffice.getObjects().add(computerDirector);
    getObjects().add(computerDirector);
    
    AdvObject pasticciotto;
    pasticciotto = new AdvObject(29, "Pasticciotto", """
                                                       Mh...crema e amarena i miei preferiti..
                                                       anche se non \u00e8 il momento giusto per pensare al cibo.""");
    pasticciotto.setAlias(new String[]{"dolce", "dolcetto", "pasticciotti", "pasticciotto"});
    directorOffice.getObjects().add(pasticciotto);
    pasticciotto.setTakeable(true);
    getObjects().add(pasticciotto);

    AdvObject deskOfficeDirector;
    deskOfficeDirector = new AdvObject(51, "Scrivania", """
                                                          Una scrivania molto ordinata, ci sono diversi documenti...
                                                          
                                                          ...noti anche un post-it.
                                                          
                                                          
                                                          Inoltre guardi un computer, utilizzato poco e tenuto bene.
                                                          
                                                          """);
    deskOfficeDirector.setAlias(new String[]{"scrivania", "tavolo"});
    directorOffice.getObjects().add(deskOfficeDirector);

    AdvObject postItDirector = new AdvObject(52, "Post-it", """
                                                            
                                                            1 4
                                                            
                                                            2 5
                                                            
                                                            6 7
                                                            
                                                            Una serie di numeri, nel dubbio meglio portarselo con s\u00e8...""");
    postItDirector.setAlias(new String[]{"post-it", "post", "postit"});
    directorOffice.getObjects().add(postItDirector);
    postItDirector.setTakeable(true);
    getObjects().add(postItDirector);

    //set oggetti/oggettiContenitori nella sala documenti

    AdvObject sheetPaper = new AdvObject(31, "Foglio", """
                                                       Una sequenza di numeri...
                                                       In fondo c'\u00e8 scritto director Password: 
                                                       
                                                       - progettomap
                                                       
                                                       Che significa?""");
    sheetPaper.setAlias(new String[]{"foglio", "carta"});
    sheetPaper.setTakeable(true);
    getObjects().add(sheetPaper);

    AdvObjectContainer letterEnvelope = new AdvObjectContainer(30, "Busta da lettere", "Una busta da lettere sembra essere piena.");
    letterEnvelope.setAlias(new String[]{"busta", "busta da lettere", "busta da lettera", "lettera"});
    letterEnvelope.setOpenable(true);  //si può aprire
    letterEnvelope.setOpen(true);
    documentRoom.getObjects().add(letterEnvelope);
    if (!Database.loadTakedObjects(31)) {
      letterEnvelope.add(sheetPaper);
    }


    AdvObject pen = new AdvObject(131, "Penna", "Una penna montblanc, bella, ma inutile.\n\n");
    pen.setAlias(new String[]{"penna", "montblanc"});
    pen.setTakeable(true);
    getObjects().add(pen);


    AdvObjectContainer box = new  AdvObjectContainer(32, "Scatola", "Uno scatolino di piccole dimensioni.");
    box.setAlias(new String[]{"scatolo", "scatola", "scatolino"});
    box.setOpenable(true);  //si può aprire
    box.setOpen(true);
    documentRoom.getObjects().add(box);
    if (!Database.loadTakedObjects(131)) {
      box.add(pen);
    }

    //set oggetto tastierino nel Caveau

    AdvObject squareKeypad = new AdvObject(37, "Tastierino", """
                                                             Un tastierino particolare, la combinazione non \u00e8 scritta ma pu\u00f2 essere osservata...
                                                             
                                                             ...sicuramente ci sei passato gi\u00e0...
                                                             Se non capisci...guarda cosa ti \u00e8 rimasto nell'inventario.""");
    squareKeypad.setAlias(new String[]{"tastierino"});
    hallwayP2.getObjects().add(squareKeypad);
    squareKeypad.setUsable(true); //si può usare
    caveau.getObjects().add(squareKeypad);
    getObjects().add(squareKeypad);


    AdvObject money = new AdvObject(39, "Contanti", "Con tutti questi soldi puoi ritenerti ricco.");
    money.setAlias(new String[]{"contante", "denaro", "contanti", "soldi"});
    safeCaveau.getObjects().add(money);
    money.setTakeable(true); //si può prendere
    getObjects().add(money);

    AdvObject ingots = new AdvObject(110, "Lingotto", "Ci vuole un paio di occhiali da sole per guardare"
            + " tutti questi lingotti.");
    ingots.setAlias(new String[]{"lingotti", "lingotto"});
    safeCaveau.getObjects().add(ingots);
    ingots.setTakeable(true); //si può prendere
    getObjects().add(ingots);

    //set oggetti/oggettiContenitori nel corridoio finale

    AdvObject exitDoor;
    exitDoor = new AdvObject(40, "Porta", """
                                            Questa porta non sembra si possa aprire...
                                            La serratura sembra essere molto debole e arruginita.
                                            Trova un modo semplice e alternativo per aprire una porta...
                                            Ricorda che hai del potenziale nell'inventario.""");
    exitDoor.setAlias(new String[]{"porta"});
    finalHallway.getObjects().add(exitDoor);
    exitDoor.setOpenable(true); //porta apribile ma,
    exitDoor.setOpen(false);   //porta chiusa
    getObjects().add(exitDoor);

    //set oggetti/oggettiContenitori nel parcheggio dipendenti

    AdvObject wall = new AdvObject(41, "Muro", "Un muro alto tre metri...\nMa dove pensi di andare, ragiona meglio!");
    wall.setAlias(new String[]{"mura", "muro"});
    employeeParking.getObjects().add(wall);

    AdvObject van = new AdvObject(42, "Furgone", "Gino puntuale come un orologio svizzero vi aspetta all'uscita dopo ogni rapina.\nCompagno di mille avventure...\nCosa aspetti sali sul furgone.");
    van.setAlias(new String[]{"van", "furgoncino", "furgone"});
    employeeParking.getObjects().add(van);
    van.setGetIn(true); //si può salire

    AdvObject portraits = new AdvObject(43, "Quadri", "");
    portraits.setAlias(new String[]{"ritratti", "quadri"});
    hallwayP2.getObjects().add(portraits);

    AdvObject lightSwitch = new AdvObject(123, "Interruttore", "Un banale interruttore presente in ogni edificio...");
    lightSwitch.setAlias(new String[] {"interruttore", "pulsante"});
    lightSwitch.setPushable(true);
    if (!Database.loadUsedObjects(123)) {
      conferenceRoom.getObjects().add(lightSwitch);
    }
    getObjects().add(lightSwitch);

    if (GameDescription.loadedGame) {
      for (AdvObject object : getObjects()) {
        int objectId = object.getId();
        if (Database.loadTakedObjects(objectId) && !Database.loadUsedObjects(objectId)) {
          getInventory().add(object);
        } else if (Database.loadUsedObjects(objectId)) {
          switch (objectId) {

            case 0 -> {
              getInventory().remove(flipperZero);
              mainHall.setAccessible(true);
            }
            case 1 -> {
              northOffice.setAccessible(true);
              bankTellerRooms.setDescription("Dopo aver spaventato il titolare, i dipendenti sono "
                          + "ancora più terrorizzati ");
            }
            case 4 -> {
              getInventory().remove(badge);
              bankTellerRooms.setAccessible(true);
            }
            case 16 -> {
              switchOn.setPushable(false);
              hallwayP1.setAccessible(true);
            }
            case 27 -> {
              getInventory().remove(key);
              hallwayP1.setDescription("La parte iniziale del corridoio, a nord noti la sala"
                      + " conferenze, mentre a sud c'è l'ufficio del direttore.");
              directorOffice.setAccessible(true);
            }
            case 28 -> {
              computerDirector.setUsable(false);
              securityRoom.setAccessible(true);
            }
            case 37 -> {
              squareKeypad.setUsable(false);
              safeCaveau.setAccessible(true);
            }
            case 29 -> {
              pasticciotto.setTakeable(false);
              directorOffice.setDescription("Ti trovi nell'ufficio del direttore.");
            }
            case 123 -> {
              lightSwitch.setPushable(false);
              conferenceRoom.setDescription("Ti trovi nella sala conferenze, la banca"
                    + " avrà tenuto da poco un meeting.");
            }
            case 40 -> {
              employeeParking.setAccessible(true);
            }
          }
        }
      }
    }
  }

  /**
   * Metodo che analizza il risultato del parser
   * e agisce a seconda dei casi.
   * @param p
   */
  @Override
    public void nextMove(ParserOutput p) {
        
    byte move = 0; // 1: hai cambiato stanza, 2: è chiusa a chiave, 3: c'è un muro, 4: caso uscita porta sala bancomat
    boolean noItem = false; // Non ho digitato il nome dell'oggetto (true)
    switch (p.getCommand().getType()) {
      case NORTH -> {
        //se comando è di tipo Nord
        //se veramente c'è la stanza a nord la imposto come stanza corrente
        if (getCurrentRoom().getNorth() != null) {
          if (getCurrentRoom().getNorth().isAccessible()) { //verifico se la stanza è accessibile
            setCurrentRoom(getCurrentRoom().getNorth());

            if (getCurrentRoom().getId() == 16) {
              move = 7;
            } else {
              move = 1; // Hai cambiato stanza
              if (EngineGUI.isPortraitsVisible()) { //se i quadri sono visibili
                EngineGUI.hidePortraits(); //nascondili
              }
            }

          } else {
            move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
          }

        } else {
          move = 3;
        }
      }
      case SOUTH -> {
        //se comando è di tipo Sud
        //se veramente c'è la stanza a sud la imposto come stanza corrente
        if (getCurrentRoom().getSouth() != null) { // Se c'è una stanza a nord
          if (getCurrentRoom().getSouth().isAccessible()) { //verifico se la stanza è accessibile
            setCurrentRoom(getCurrentRoom().getSouth()); // Imposta la stanza a sud come attuale

            move = 1; // Hai cambiato stanza
            if (EngineGUI.isPortraitsVisible()) { //se i quadri sono visibili
              EngineGUI.hidePortraits(); //nascondili
            }

          } else {

            if (getCurrentRoom().getId() == 16) { //c'è la cassaforte del caveau da aprire
              move = 6;
            } else {
              move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
            }

          }

        } else {
          move = 3;
        }

      }
      case WEST -> {
        //se il comando è di tipo ovest
        if (getCurrentRoom().getWest() != null) {
          if (getCurrentRoom().getWest().isAccessible()) { //verifico se la stanza è accessibile
            setCurrentRoom(getCurrentRoom().getWest());
            move = 1;
            if (EngineGUI.isPortraitsVisible()) { //se i quadri sono visibili
              EngineGUI.hidePortraits(); //nascondili
            }

          } else {
            move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
          }

        } else {

          if (getCurrentRoom().getId() == 1) { // Se mi trovo nella BancomatRoom, stanza inizio gioco
            //(non posso uscire dalla banca)
            move = 4;
          } else {
            move = 3;
          }
        }
      }
      case EAST -> {
        //se il comando è di tipo est
        //se veramente c'è la stanza a est la imposto come stanza corrente
        if (getCurrentRoom().getEast() != null) {
          if (getCurrentRoom().getEast().isAccessible()) { //verifico se la stanza è accessibile
            setCurrentRoom(getCurrentRoom().getEast());

            move = 1; // Hai cambiato stanza
            if (EngineGUI.isPortraitsVisible()) { //se i quadri sono visibili
              EngineGUI.hidePortraits(); //nascondili
            }

          } else {

            if (getCurrentRoom().getId() == 3) { // Se mi trovo nella sala sportelli bancari ad est ci sono i tornelli
              move = 5; //messaggio peronalizzato per tornelli
            } else {
              move = 2; //se la stanza è chiusa e bisogna aprirla per andarci
            }
          }

        } else {
          move = 3;
        }
      }
      case HELPME -> {
        EngineGUI.displayTextSetDelay("Per visualizzare l'help premi il bottone in alto 'COMANDI'");
      }
      case LOOK -> {
        if (getCurrentRoom().getLook() != null) { // Se il comando "OSSERVA" della stanza attuale contiene una descrizione
          if (getCurrentRoom().isLookable()) {
            EngineGUI.displayTextSetDelay(getCurrentRoom().getLook()); // Contenuto del comando "OSSERVA"
            if(EngineGUI.isPortraitsVisible()){
                EngineGUI.hidePortraits();
            }
          } else {
            if (getCurrentRoom().getId() == 8) {
              EngineGUI.displayTextSetDelay("Non riesco a vedere nulla! Ma noti un interruttore vicino all'entrata...");
            } else {
              EngineGUI.displayTextSetDelay("Non riesco a vedere nulla!\n");
            }
          }
        } else { // Se la descrizione del comando "OSSERVA" è vuota
          EngineGUI.displayTextSetDelay("Non c'è niente di interessante qui.");
        }
      }
      case EXAMINE -> {
        if (p.getObject() != null) { // Se l'oggetto è nella stanza
          if (p.getObject().getDescription() != null && getCurrentRoom().isLookable()) { // Se l'oggetto ha una descrizione
            EngineGUI.displayTextSetDelay(p.getObject().getDescription());
            if (p.getObject().getId() == 43 && !EngineGUI.isPortraitsVisible()) {
              EngineGUI.showPortraits();
            }
            if (p.getObject().getId() == 26) {
              Music.playEffect("./resources/soundEffects/keyDirector.wav");
            }

          } else {
            EngineGUI.displayTextSetDelay("Non riesco a vedere nulla!");
          }
        } else if (p.getInvObject() != null) {
          if (EngineGUI.isPortraitsVisible()) {
            EngineGUI.hidePortraits();
          }
          AdvObject advObject = p.getInvObject();
          EngineGUI.displayTextSetDelay("- " + advObject.getName() + ": " + advObject.getDescription());

        } else {
          EngineGUI.displayTextSetDelay("Non c'è nulla da esaminare!");
        }
      }
      case PUSH -> {
        if (p.getObject() != null) {
          if (p.getObject().isPushable()) {
            switch (p.getObject().getId()) {
              case 16 -> { //bottone scrivania ufficio nord
                Music.playEffect("./resources/soundEffects/button.wav");
                EngineGUI.displayTextSetDelay("""
                                              Hai premuto il pulsante!
                                              senti qualcosa sbloccarsi!""");
                getCurrentRoom().getSouth().getEast().setAccessible(true);
                Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                Music.playEffect("./resources/soundEffects/turnstile.wav");
                p.getObject().setPushable(false);
              }
              case 123 -> {  //interruttore sala conferenza
                EngineGUI.displayTextSetDelay("""
                                              Hai accesso la luce nella stanza!
                                              
                                              ...adesso puoi guardare meglio!""");
                getCurrentRoom().setLookable(true);
                Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                getCurrentRoom().setDescription("Una sala conferenze utilizzata per"
                        + " i meeting dallo staff della banca.");
                p.getObject().setPushable(false);
              }
            }
          } else {
            EngineGUI.displayTextSetDelay("Hai già premuto questo pulsante.");
          }
        } else {
          EngineGUI.displayTextSetDelay("Non trovi nulla da poter premere!");
        }
      }
      case USE -> {

        AdvObject invObject = p.getInvObject();

        AdvObject object = p.getObject();

        if (p.getObject() != null) { // Se l'oggetto è nella stanza
          if (p.getObject().isUsable()) { // Se l'oggetto è utilizzabile
            switch (p.getObject().getId()) { // Switch basato sull'ID dell'oggetto
              case 28 -> {
                // Se l'ID dell'oggetto è 28 (computer)
                if (!p.getObject().isUsed() && getCurrentRoom().getId() == 9) { // Se l'oggetto non è stato utilizzato e la stanza corrente è l'ufficio del direttore
                  String password = JOptionPane.showInputDialog(null, "Inserisci la password:",
                                    "Computer del Direttore", JOptionPane.PLAIN_MESSAGE);

                  // Controllo della password inserita
                  if (password != null && password.equals("progettomap")) {
                    // Accesso consentito
                    Music.playEffect("./resources/soundEffects/login.wav");
                    EngineGUI.displayTextSetDelayOpen("""
                                                      Accesso consentito...Noti un file, con il titolo: 
                                                      
                                                      UnlockSecuriyZone.exe
                                                      
                                                      
                                                      Apri il file...
                                                      
                                                      
                                                      ...ONLY IN CASE OF EMERGENCY....
                                                      
                                                      ....010100010101010101010...101001011
                                                      
                                                      Zona di sicurezza aperta, allarme attivo.""");
                    Music.playEffect("./resources/soundEffects/doorSecurityZone.wav");
                    getCurrentRoom().getNorth().getEast().getSouth().setAccessible(true);
                    Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);

                    // Imposta lo stato di utilizzo dell'oggetto su true
                    p.getObject().setUsed(true);
                  } else {
                    // Accesso negato
                    EngineGUI.displayTextSetDelay("Accesso negato!");
                    Music.playEffect("./resources/soundEffects/wrongPassword.wav");
                  }
                } else {
                  // L'oggetto è già stato utilizzato o non si trova nella stanza corretta
                  EngineGUI.displayTextSetDelay("Non puoi utilizzare nuovamente l'oggetto.");
                }
              }
              case 37 ->    {
                // usa tastierino

                if (!p.getObject().isUsed() && getCurrentRoom().getId() == 16 && Utils.getRemainingTime() > 0) { // stanza caveau


                  Keypad keypad = new Keypad();
                  keypad.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                  keypad.setVisible(true);

                  String password = "426";

                  if (password.equals(keypad.getText().toString())) { //inserisco password e confronto con quella richiesta (462)
                    getCurrentRoom().getSouth().setAccessible(true); // La stanza safeCaveau diventa accessibile
                    Database.saveObject(p.getObject().getId(), p.getObject().getName(), true);
                    object.setUsed(true);
                    EngineGUI.displayTextSetDelay("La cassaforte è stata aperta!");
                    Music.playEffect("./resources/soundEffects/unlocked.wav");
                  } else {
                    Music.playEffect("./resources/soundEffects/error.wav");
                    EngineGUI.displayTextSetDelay("""
                                                  Codice Errato.
                                                  
                                                  
                                                  Ragiona meglio...""");
                  }

                } else {
                  EngineGUI.displayTextSetDelay("Non trovi nessuno oggetto simile!");
                }
                
              }
              case 8 -> {
                //usa sparagraffette
                if (getCurrentRoom().getId() == 3 && !Database.loadUsedObjects(8)) { //stanza atrio principale allora stanza corretta

                  EngineGUI.displayTextSetDelay("""
                                        Idiota, stai attento, stavi per sparare una graffetta in fronte al tuo collega.
                                        
                                        ...penso sia meglio gettarla via.
                                        
                                        
                                        Hai gettato: sparagraffette""");
                  invObject.setUsed(true);
                  getInventory().remove(object);
                  invObject.setTakeable(false);
                  invObject.setTaken(true);
                  Database.saveObject(p.getInvObject().getId(), p.getInvObject().getName(), true);

                } else {
                  EngineGUI.displayTextSetDelay("Non puoi usare la sparagraffette qui.");
                }
              }
              default -> EngineGUI.displayTextSetDelay("Non ho nulla in mano.");                             
            }
          // Switch basato sull'ID dell'oggetto

          } else { // Se l'oggetto non si può utilizzare
            EngineGUI.displayTextSetNormal("Non puoi utilizzare questo oggetto.");
            EngineGUI.displayTextSetDelay(""); //faccio ritorno la inputArea Editabile
          }

          //USARE OGGETTI NELL'INVENTARIO

        } else if (p.getInvObject() != null) { // Se l'oggetto è nell'inventario

          if (p.getInvObject().isUsable()) { // Se l'oggetto nell'inventario si può utilizzare

            switch (p.getInvObject().getId()) { // Se l'ID dell'oggetto nell'inventario corrisponde a...
              case 1 -> {
                //usa pistola

                if (getCurrentRoom().getId() == 3 && !Database.loadUsedObjects(1)) { //stanza atrio principale allora stanza corretta
                  Music.playEffect("./resources/soundEffects/panic.wav"); //riproduci suono di sblocco
                  getCurrentRoom().getNorth().setAccessible(true);
                  Database.saveObject(p.getInvObject().getId(), p.getInvObject().getName(), true);
                  EngineGUI.displayTextSetDelay("""
                                                 Ti rivolgi al responsabile:
                                                
                                                'Dai voglio uscire da qui dentro!!!' 
                                                
                                                
                                                Hai spaventando il responsabile, ti aprir\u00e0 l\u2019ufficio Nord\u2026
                                                
                                                L\u2019ufficio nord \u00e8 stato aperto.""");
                  getCurrentRoom().setDescription("Dopo aver spaventato il titolare, i dipendenti sono "
                          + "ancora più terrorizzati ");
                } else {
                  EngineGUI.displayTextSetDelay("Non puoi utilizzare la pistola qui.");
                }
              }
              default -> EngineGUI.displayTextSetDelay("Non capisco.");
            }
            // Se l'ID dell'oggetto nell'inventario corrisponde a...

          } else { // Se l'oggetto non si può utilizzare
            EngineGUI.displayTextSetDelay("Non puoi utilizzare questo oggetto.");
          }
        } else { // Se non ho digitato il nome dell'oggetto
          noItem = true;
        }
      }
      case INVENTORY -> {
        EngineGUI.displayTextSetDelay("""
                                       \nPremi sul borsone: 

                                                                   ___    
                                                                  |\\  \\   
                                        ____________  ____________\\ \\  \\  
                                       |\\____________\\\\____________\\ \\  \\ 
                                       \\|____________\\|____________|\\/  /|
                                                                    /  // 
                                                                   /_ //  
                                                                  |__|/   
                                                                          """);
      }
      case SAVE -> {
        try {
          Database.deletePlayerData();
          Database.savePlayerRoom(getCurrentRoom().getId(), getCurrentRoom().getName(), Utils.getRemainingTime());
          EngineGUI.displayTextSetDelay("Salvataggio eseguito!");
        } catch (Exception ex) {
          EngineGUI.displayTextSetDelay("Si è verificato un errore con il salvataggio!");
        }
      }
      case TAKE -> {

        AdvObject object = p.getObject();

        if (object == null) {
          EngineGUI.displayTextSetDelay("Non trovo nessun oggetto!");
          return;
        }

        if (object.isTakeable()) {

          if (!getInventory().contains(object)) {
            getInventory().add(object);
            //getCurrentRoom().getObjects().remove(object);
            Database.saveObject(object.getId(), object.getName(), false);
            EngineGUI.displayTextSetDelay("Hai preso: " + object.getName());
            object.setTaken(true);

            if (object.getId() == 29) {
              Database.saveObject(object.getId(), object.getName(), true);
              getCurrentRoom().setDescription("Ti trovi nell'ufficio del direttore.");
              EngineGUI.displayTextSetDelay("""
                                            Opss!! Non potevo resistere...
                                            
                                            Lo manger\u00f2 dopo...""");
            }

            if (object.getId() == 110) {
              Database.saveObject(object.getId(), object.getName(), false);
              EngineGUI.displayTextSetDelay("Non riesci a prenderli tutti...\n\nNe prenderai solo uno...");
            }

          } else {
            EngineGUI.displayTextSetDelay("Hai già preso questo oggetto!");
          }

        } else {

          if (object.getId() == 8 && object.isTaken()) { //se vuoi riprendere la sparagraffete dopo averle utilizzatta
            EngineGUI.displayTextSetDelay("Stai lontando da questo oggetto, stavi per fare danno.");

          } else {
            EngineGUI.displayTextSetDelay("Non puoi prendere questo oggetto.");
          }
        }
      }

      case OPEN -> {
        AdvObject object = p.getObject();

        if (object == null) {
          EngineGUI.displayTextSetDelay("Non trovo nessun oggetto!");
          return;
        }

        if (!object.isOpenable()) {
          EngineGUI.displayTextSetDelay("L'oggetto non può essere aperto.");
          return;
        }

        if (!getCurrentRoom().isLookable()) {
          EngineGUI.displayTextSetDelay("E' troppo buio, non vedo nulla.");
          return;
        }

        //APRIRE OGGETTO CONTENITORE
        if (object.isOpen() && getCurrentRoom().isLookable()) {
          if (object instanceof AdvObjectContainer container) {
            if (!container.getList().isEmpty()) {
              EngineGUI.displayTextSetNormal("Hai aperto: " + object.getName() + "\n\nAl suo interno vedi:");

              boolean isFound = false;
              for (AdvObject nextItem : container.getList()) {
                if (!nextItem.isTaken()) {
                  EngineGUI.displayTextAppend("\n\n- " + nextItem.getName());
                  isFound = true;
                  EngineGUI.displayTextSetDelay(""); //faccio ritorno la inputArea Editabile
                  if (!getCurrentRoom().getObjects().contains(nextItem)) {
                    getCurrentRoom().getObjects().add(nextItem);

                  }
                }
              }

              if (!isFound) { //se l'oggetto contenitore è vuoto

                EngineGUI.displayTextAppend("\n\n- È vuoto.");
                EngineGUI.displayTextSetDelay("");

              }

            } else {    //se gli oggetti sono stati già presi
              EngineGUI.displayTextSetDelay("Gli oggetti sono già stati presi");
            }

          } else {    //se hai sbloccato già una porta

            EngineGUI.displayTextSetDelay("La porta è già stata sbloccata!");
          }

        } else {

          AdvObject invObject = p.getInvObject();
          //APRIRE OGGETTO CON OGGETTOINVENTARIO
          if (invObject != null) {
            switch (object.getId()) {
                case 2 -> {
                  if (invObject.getId() == 0) {
                    object.setOpen(true);
                    getCurrentRoom().getEast().setAccessible(true);
                    getInventory().remove(invObject);
                    Database.saveObject(invObject.getId(), invObject.getName(), true);
                    Music.playEffect("./resources/soundEffects/unlocked1.wav");
                    EngineGUI.displayTextSetDelay("""
                                                  Il tuo amico informatico Roberto dopo aver collegato i pin ausiliari del flipperZero riesce bypassare la sicurezza. 
                                                  La porta si \u00e8 sbloccata!""");
                    invObject.setUsed(true);
                  } else if (invObject.getId() == 1) {
                    EngineGUI.displayTextSetDelay("La porta è chiusa, essa viene gestita da un apparecchio informatico.\nDubito che i proiettili possano scalfirla.");
                  }
                }
                case 5 -> {
                  if (invObject.getId() == 4) {
                    object.setOpen(true);
                    getCurrentRoom().getEast().setAccessible(true);
                    getInventory().remove(invObject);
                    Database.saveObject(invObject.getId(), invObject.getName(), true);
                    EngineGUI.displayTextSetDelay("Badge Riconosciuto...\nLa porta si apre.");
                    Music.playEffect("./resources/soundEffects/unlocked.wav");
                    invObject.setUsed(true);
                  } else if (invObject.getId() == 1) {
                    EngineGUI.displayTextSetDelay("Questa porta è blindata, una volta chiusa può essere sbloccata solo dai dipendenti. I proiettili non faranno nulla.");
                  }
                }
                case 17 -> {
                  if (invObject.getId() == 27){
                    object.setOpen(true);
                    getCurrentRoom().getSouth().setAccessible(true);
                    getInventory().remove(invObject);
                    Database.saveObject(invObject.getId(), invObject.getName(), true);
                    getCurrentRoom().setDescription("La parte iniziale del corridoio, a nord noti la sala"
                            + " conferenze, mentre a sud c'è l'ufficio del direttore.");
                    EngineGUI.displayTextSetDelay("La porta dell'ufficio del direttore è stata aperta.");
                    Music.playEffect("./resources/soundEffects/directorDoor.wav");
                    invObject.setUsed(true);
                  }  else if (invObject.getId() == 1) {
                    EngineGUI.displayTextSetDelay("La pistola non può sempre risolvere tutti i problemi...");
                  }
                }
                case 40 -> {
                  if (invObject.getId() == 1){
                    object.setOpen(true);
                    getCurrentRoom().getEast().setAccessible(true);
                    Database.saveObject(object.getId(), object.getName(), true);
                    Music.playEffect("./resources/soundEffects/gunShoot.wav");
                    EngineGUI.displayTextSetDelay("""
                                                      Bravo, bel colpo...

                                                      Hai aperto la porta sparando alla serratura.""");
                  }
                }
                default -> EngineGUI.displayTextSetDelay("Non puoi aprire questa porta con questo oggetto.");
              }
            } else {
                if (object.getId() == 2 && !object.isOpen()) {
                  EngineGUI.displayTextSetDelay("Affianco alla porta noti un pannello che contiene cavi di diversi colori,\nmi chiedo se possa essere hackerata.");
                } else if (object.getId() == 5 && !object.isOpen()) {
                  EngineGUI.displayTextSetDelay("Ti serve l'oggetto giusto per aprire questa porta.");

                } else if (object.getId() == 11 && !object.isOpen()) {
                    EngineGUI.displayTextSetDelay("Non hai forza per aprire con le mani questi tornelli.");
                } else if (object.getId() == 17 && !object.isOpen()) {
                    EngineGUI.displayTextSetDelay("Vorresti aprire la porta...ma con quale oggetto???");
                } else if (object.getId() == 137 && !object.isOpen()) {
                    EngineGUI.displayTextSetDelay("Non perdere troppo tempo, è la porta di un ufficio privato ed è chiusa");
                } else if (object.getId() == 21 && !object.isOpen()) {
                    EngineGUI.displayTextSetDelay("Una porta così blindata, sarà difficile da aprire.");
                } else if (object.getId() == 40 && !object.isOpen()) {
                    EngineGUI.displayTextSetDelay("Nel borsone avrò qualcosa di utile?");
                } 
            }
        }
      }
      case GET_IN -> {

        AdvObject keyObject = new AdvObject(39, "Contanti"); //keyObject, oggetto chiave
        //se hai questo oggetto il finale
        //è diverso, altrimenti
        if (p.getObject() != null) {
          if (p.getObject().isGetIn()) {
            switch (p.getObject().getId()) {
              case 42 -> { //salgo sul furgone
                if (Utils.getRemainingTime() > 0) {
                  Utils.setDisplayGameOver(false);
                  if (!getInventory().contains(keyObject)) { //se non ho i soldi nell'inventario

                    Music.playEffect("./resources/soundEffects/applausi.wav");
                    String gameoverend = readFile("./resources/txt/GameOverEnd.txt");
                    EngineGUI.displayTextSetDelayEnd(gameoverend);
                    EngineGUI.hideLabels();
                    EngineGUI.hideFileEnd();
                    Database.deletePlayerData();
                    Database.deleteUsedObjects();
                    Music.playEffect("./resources/soundEffects/songEndFail.wav");

                  } else {

                    Music.playEffect("./resources/soundEffects/vanSound.wav");
                    String win = readFile("./resources/txt/Win.txt");
                    EngineGUI.displayTextSetDelayEnd(win);
                    EngineGUI.hideLabels();
                    EngineGUI.hideFileEnd();
                    Database.deletePlayerData();
                    Database.deleteUsedObjects();
                    Music.playEffect("./resources/soundEffects/win.wav");

                  }
                }

              }
            }
          } else {
            EngineGUI.displayTextSetDelay("Non so cosa intendi dire.");
          }
        } else {
          EngineGUI.displayTextSetDelay("Non trovo questo oggetto");
        }
      }
    }

    switch (move) {


      case 1 -> {
        EngineGUI.displayTextSetDelay(getCurrentRoom().getDescription()); // Descrizione della stanza attuale
        EngineGUI.roomsDisplayTextSet("Stanza attuale: " + getCurrentRoom().getName()); // nome della stanza attuale
      }
      case 2 -> //Se ti sei mosso e c'è una porta chiusa
        EngineGUI.displayTextSetDelay("Dove vai? La porta è chiusa.");
      case 3 -> //Se ti sei mosso e c'è un muro
        randomMessageWall();
      case 4 -> {
        //Se ti trovi nella stanza iniziale e vai ad Ovest
        Music.playEffect("./resources/soundEffects/policeCarSiren.wav"); //riproduci suono polizia
        EngineGUI.displayTextSetDelay("Sono già qui... \nNon credo che sia una buona idea forzare la porta per tornare in strada e farsi arrestare...");
      }
      case 5 -> //Se ti trovi nella sala sportelli bancari e vai ad est ci sono i tornelli
        EngineGUI.displayTextSetDelay("Ma vedi bene? Ci sono dei tornelli alti 2 metri. \n\nDove pensi di andare?");
      case 6 -> {
          //se ti trovi nel caveau vai a sud e c'è la cassaforte
        EngineGUI.displayTextSetDelay("""
                                        La cassaforte \u00e8 chiusa dovresti aprirla, c'\u00e8 un tastierino...
                                        
                                        
                                        Ohhh noo...l'allarme....""");
        Music.playEffect("./resources/soundEffects/alarmSecurity.wav");
      }
      case 7 -> {
        EngineGUI.roomsDisplayTextSet("Stanza attuale: " + getCurrentRoom().getName()); // nome della stanza attuale
        EngineGUI.displayTextSetDelay("""
                                        ...stai salendo le scale...
                                        
                                        
                                        
                                        Ti trovi nella stanza che precede la cassaforte.""");
      }
    }

    if (noItem) {
      EngineGUI.displayTextSetDelay("Non capisco di cosa tu stia parlando.");

    }
  }

  /**Metodo d'istanza per la stampa di messaggi casuali
   * quando il giocatore tenta di muoversi in spazi
   * non permessi.
   */
  private void randomMessageWall() {

    Random randomMessage = new Random();
    int randomMessageWall = randomMessage.nextInt(3);

    switch (randomMessageWall) {
      case 0 -> EngineGUI.displayTextSetDelay("Quelle che vedi sono mura, tranquillo non c'è nessun passaggio segreto.\n");
      case 1 -> EngineGUI.displayTextSetDelay("OUCH!! Che male! Qui c'è un muro!.\n");
      case 2 -> EngineGUI.displayTextSetDelay("Inutile che ci sbatti contro, è difficile buttare giù il muro.\n");
    }
  }
}
