package obstacles;

import gui.UIHandler;
import items.Item;

import java.util.ResourceBundle;

public class Boulder extends Obstacle{
    private static final long serialVersionUID = 7413817848440921012L;
    private final Item unlockItem;

    @Override
    public boolean isPassed() {
        return passed;
    }

    @Override
    public boolean unlock(Item usedItem) {
        if(unlockItem.equals(usedItem)) {
            UIHandler.printInFrame(ResourceBundle.getBundle("bundles/ObstaclesOutput")
                    .getString("unlockBoulder"));
            passed = true;
        }
        return this.isPassed();
    }

    public Boulder(Item unlock) {
        unlockItem = unlock;
    }
}
