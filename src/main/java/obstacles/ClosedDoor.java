package obstacles;

import gui.UIHandler;
import items.*;

import java.util.ResourceBundle;

public class ClosedDoor extends Obstacle {

	private static final long serialVersionUID = 2394726890544852907L;
	private Item unlockItem;

	@Override
	public boolean isPassed() {
		return passed;
	}

	@Override
	public boolean unlock(Item usedItem) {
		if(unlockItem.equals(usedItem)) {
			UIHandler.printInFrame(ResourceBundle.getBundle("bundles/ObstaclesOutput")
					.getString("unlockDoor"));
			passed = true;
		}
		return this.isPassed();
	}

	public static void helpObstacle() {
		//Print a short string about unlocking this sht
	}

	public ClosedDoor(Item unlock) {
		unlockItem = unlock;
	}

}
