package items;

import engine.GameProgress;
import engine.Inventory;
import obstacles.IllegalItemUsageException;

public class Key extends Item{


    @Override
    public void use() {
        try {
            GameProgress.unlockObstacle(this);
            Inventory.removeFromBag(this.toString());
        } catch (IllegalItemUsageException e) {
            System.out.println("Can't use this item like that");
        }
    }
}
