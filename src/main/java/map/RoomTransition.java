package map;

import items.Item;
import obstacles.HinderedRoomException;
import obstacles.Obstacle;

import java.io.Serializable;
import java.util.ResourceBundle;

public class RoomTransition implements Serializable{

	private static final long serialVersionUID = 2948106030312829446L;

	private final int transitioningRoomId;
	private final Obstacle transitionObstacle;

	public int moveToRoom() throws HinderedRoomException {
		if(transitionObstacle == null || transitionObstacle.isPassed()) {
			return transitioningRoomId;
		} else {
			//If there isn't an obstacle, this branch will never activate. So, this branch is activeted only when
			//the obstacle isn't passed.
			throw new HinderedRoomException();
		}

	}

	public boolean useItemToUnlock(Item item) {
		if (transitionObstacle != null && !transitionObstacle.isPassed()) {
			return transitionObstacle.unlock(item);
		} else {
			return false;
		}
	}

	public RoomTransition(int ID, Obstacle obstacle){
		this.transitioningRoomId = ID;
		this.transitionObstacle = obstacle;
	}

	public String info() {
		if(transitionObstacle != null && !transitionObstacle.isPassed()) {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("hinderedPath") + ":" +
					ResourceBundle.getBundle("bundles/ObstaclesNames")
							.getString(transitionObstacle.getClass().getSimpleName());
		} else {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("noObstacle");
		}
	}
}
