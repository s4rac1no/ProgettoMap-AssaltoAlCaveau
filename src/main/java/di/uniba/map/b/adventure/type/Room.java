/*
 *      ASSALTO AL CAVEAU
 */

package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;


public class Room {

    private final int id; // Id della stanza
    private String name; // Nome della stanza
    private String description; // Descrizione della stanza
    private String look; // Contenuto del comando "OSSERVA"
    private String examine; //Contenuto del comando "ESAMINA"
    private boolean open = true; // La stanza è APERTA
    
    private boolean accessible = true; // La stanza è accessibile
    private boolean lookable = true;

    private Room north = null;
    private Room south = null;
    private Room west = null;
    private Room east = null;

    private final List<AdvObject> list = new ArrayList<>(); // Oggetti presenti nella stanza

    // Id
    public Room(int id) {
        this.id = id;
    }

    // Id + nome + descrizione
    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Id della stanza
    public int getId() {
        return id;
    }
    // Nome della stanza
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Descrizione della stanza
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Comando "GUARDA/OSSERVA"
    public String getLook() {
        return look;
    }
    public void setLook(String look) {
        this.look = look;
    }
    
    public void setLookable(boolean lookable){
        this.lookable = lookable;
    }
    
    public boolean isLookable() {
        return lookable;
    }

    // Comando "ESAMINA"
    public String getExamine() { return examine; }
   
    public void setExamine(String examine) { this.examine = examine; }


    // Se aperta
    public boolean isOpen() { return open; }
    public void setOpen(boolean open) { this.open = open; }
    
    
    public boolean isAccessible() {
	return accessible;
    }

    public void setAccessible(boolean accessible) {
	this.accessible = accessible;
    }
    
    
    // Stanze (N/S/W/E)
    public void setBorders(Room north, Room south, Room west, Room east) {
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
    }

    //  Room nord, sud, ovest, est
    public Room getNorth() {
        return north;
    }
    public Room getSouth() {
        return south;
    }
    public Room getWest() {
        return west;
    }
    public Room getEast() {
        return east;
    }

    // Item(oggetti) presenti nella stanza
    public List<AdvObject> getObjects() {
        return list;
    }

    // EQUALS + HASH CODE
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Room other = (Room) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


}
