/*
 *      ASSALTO AL CAVEAU
 */

package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdvObjectContainer extends AdvObject{
    private List<AdvObject> list = new ArrayList<>(); // Lista degli oggetti contenitore

    // Id
    public AdvObjectContainer(int id) {
        super(id);
    }

    // Id + nome
    public AdvObjectContainer(int id, String name) {
        super(id, name);
    }

    // Id + nome + descrizione
    public AdvObjectContainer(int id, String name, String description) {
        super(id, name, description);
    }

    // Id + nome + descrizione + alias
    public AdvObjectContainer(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

    // Lista degli oggetti contenitore
    public List<AdvObject> getList() {
        return list;
    }

    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    // Aggiunta e rimozione dell'oggetto dal contenitore
    public void add(AdvObject object) {
        list.add(object);
    }

    public void remove(AdvObject object) {
        list.remove(object);
    }
}
