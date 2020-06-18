package items;

import engine.GameProgress;
import gui.UIHandler;
import obstacles.IllegalItemUsageException;

public class Key extends Item {


    @Override
    public void use() {
        try {
            GameProgress.unlockObstacle(this);
            GameProgress.getBag().removeFromBag(this.getItemName());
        } catch (IllegalItemUsageException e) {
            UIHandler.printInFrame(e.getMessage() + "\n");
        }
    }
}
