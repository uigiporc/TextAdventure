package map;

import items.Item;
import util.Command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class RoomContainer implements Serializable {
    protected Item content;
    protected boolean open;

    public void open(Map items){
        open = true;
        items.put(Command.GET, content);
    }

    public void getContent(ArrayList items) {
        open = false;
        items.remove(content);
    }

    void close(){

    }
}
