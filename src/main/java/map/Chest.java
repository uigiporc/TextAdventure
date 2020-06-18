package map;

import items.Item;
import util.RoomContainersState;

import java.util.ArrayList;

public class Chest extends RoomContainer {

    private static final long serialVersionUID = -8395579860373165164L;

    public Chest(ArrayList<Item> items) {
        state = RoomContainersState.CLOSED;
        content = items;
    }

    public Chest(){
        state = RoomContainersState.CLOSED;
    }
}
