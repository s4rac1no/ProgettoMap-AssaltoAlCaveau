/*
 *      ASSALTO AL CAVEAU
 */

package di.uniba.map.b.adventure.type;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.io.Serializable;

/**
 *  metodi dei comandi usati
 */
public class Command {          //sono i verbi, cioè i comandi che può capire il parser

    private final CommandType type;     //nome comando

    private final String name;

    private Set<String> alias;      //insieme di sinomi del comando ad esemio prendi = raccogli..

   //costruttori 
    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }
    
    //get e set
    public String getName() {
        return name;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public CommandType getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.type);
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
        final Command other = (Command) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}





