package items;

import engine.GameProgress;
import gui.UIHandler;

import java.util.ResourceBundle;

public class OldBook extends Item {
    @Override
    public void use() {
        UIHandler.printInFrame(ResourceBundle.getBundle("bundles.itemsUsage").getString("oldBook"));
        GameProgress.getBag().removeFromBag(this.getItemName());
    }
}
