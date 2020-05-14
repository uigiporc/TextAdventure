package obstacles;

import items.*;

public class ClosedDoor extends Obstacle {
	private boolean lock = true;
	private Item unlockItem;
	
	public boolean isLocked() {
		return lock;
	}
	
	public void unlock(Item usedItem) throws IllegalItemUsageException {
		if(unlockItem.equals(usedItem)) {
			lock = false;
		}
		else {
		    throw new IllegalItemUsageException();
		}
	}

	@Override
	public boolean move() {
		if(lock) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void helpObstacle() {
		//Print a short string about unlocking this sht
	}
	
}
