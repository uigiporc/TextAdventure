package obstacles;

import items.Item;

import java.io.Serializable;

public abstract class Obstacle implements Serializable {
	protected boolean passed;

	public boolean isObstaclePassed() {
		return passed;
	}

	public abstract boolean unlock(Item itemToUse);

	public abstract boolean isPassed();
}
