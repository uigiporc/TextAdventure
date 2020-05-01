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
}
