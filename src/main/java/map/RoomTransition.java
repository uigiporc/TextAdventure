package map;

import java.io.Serializable;
import java.util.ResourceBundle;
import items.Item;
import obstacles.Obstacle;
import obstacles.HinderedRoomException;

public class RoomTransition implements Serializable{

	private static final long serialVersionUID = 2948106030312829446L;

	private final int transitioningRoomId;
	private final Obstacle transitionObstacle;

	public int moveToRoom() throws HinderedRoomException {
		if(transitionObstacle == null || transitionObstacle.isObstaclePassed()) {
			return transitioningRoomId;
		} else {
			//If there isn't an obstacle, this branch will never activate. So, this branch is activeted only when
			//the obstacle isn't passed.
			throw new HinderedRoomException();
		}

	}

	public boolean useItemToUnlock(Item item) {
		if (transitionObstacle != null && !transitionObstacle.isObstaclePassed()) {
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
