package map;

import items.Item;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class RoomContainer implements Serializable {
    protected ArrayList<Item> content;
    protected boolean open;

    public void open(ArrayList items){
        open = true;
        items.addAll(content);
    }

    public void getContent(ArrayList items) {
        open = false;
        items.removeAll(content);
    }

    void close(){

    }
}
