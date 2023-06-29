/*
 *      ASSALTO AL CAVEAU
 */

package di.uniba.map.b.adventure.type;

import java.util.ArrayList;
import java.util.List;

/**
 *  Metodi x inventario
 */
public class Inventory {

    private List<AdvObject> list = new ArrayList<>(); // Lista degli oggetti nell'inventario

    // Lista degli oggetti nell'inventario
    public List<AdvObject> getList() {
        return list;
    }

    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    // Aggiunta e rimozione dell'oggetto dall'inventario
    public void add(AdvObject object) {
        list.add(object);
    }

    public void remove(AdvObject object) {
        list.remove(object);
    }
  
}
