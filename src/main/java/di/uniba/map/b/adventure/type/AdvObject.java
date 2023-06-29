/*
 *      ASSALTO AL CAVEAU
 */

package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AdvObject {
    
    private final int id; // Id item
    private String name; // Nome item
    private String description; // Descrizione item
    private Set<String> alias; // Alias item

    private boolean used = false; // Se è già stato utilizzato
    private boolean openable = false; // Se può essere aperto
    private boolean open = true; // Se è gia aperto
    private boolean takeable = false; // Se può essere preso
    private boolean usable = false; // Se può essere utilizzato
    private boolean pushable = false; // Se può essere premuto
    private boolean getIn = false; // Se puoi salire
    private boolean taken = false; //se è stato preso
    private boolean visible = false; //se può essere visto in una stanza

    
    // Id
    public AdvObject(int id) {
        this.id = id;
    }

    // Id + nome
    public AdvObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Id + nome + descrizione
    public AdvObject(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Id + nome + descrizione + alias
    public AdvObject(int id, String name, String description, Set<String> alias) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alias = alias;
    }

    // Id item
    public int getId() {
        return id;
    }

    // Nome item
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Descrizione item
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Alias item
    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    // Se l'oggetto è stato utilizzato
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    // Se si può aprire (oggetto contenitore)
    public boolean isOpenable() {
        return openable;
    }

    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    // Se è aperto (oggetto contenitore)
    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    // Se si può prendere (oggetto inventario)
    public boolean isTakeable() {
        return takeable;
    }

    public void setTakeable(boolean takeable) {
        this.takeable = takeable;
    }

    // Se si può utilizzare (oggetto/oggetto inventario)
    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    // Se si può premere
    public boolean isPushable() {
        return pushable;
    }

    public void setPushable(boolean pushable) {
        this.pushable = pushable;
    }
    
    public boolean isGetIn() {
        return getIn;
    }

    public void setGetIn(boolean getIn) {
        this.getIn = getIn;
    }
    
    
    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
   
       
    
    // EQUALS + HASH CODE
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final AdvObject other = (AdvObject) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
   
}
