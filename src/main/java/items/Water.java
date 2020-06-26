package items;

import engine.GameEvent;
import engine.Inventory;

public class Water extends Item {

    @Override
    public void use() {
        GameEvent.startEvent("useWater");
        Inventory.getInventory().removeFromBag(this.getItemName());
    }
}
