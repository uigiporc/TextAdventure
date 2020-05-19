package obstacles;

import items.*;

public class ClosedDoor extends Obstacle {

	private static final long serialVersionUID = 2394726890544852907L;
	private boolean lock = true;
	private Item unlockItem;

	@Override
	public boolean isPassed() {
		return !lock;
	}

	@Override
	public boolean unlock(Item usedItem) {
		if(unlockItem.equals(usedItem)) {
			lock = false;
		}
		return this.isPassed();
	}

	public static void helpObstacle() {
		//Print a short string about unlocking this sht
	}

}
