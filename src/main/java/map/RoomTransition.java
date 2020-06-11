package map;

import java.io.File;
import java.io.Serializable;
import java.util.ResourceBundle;

import gui.UIHandler;
import items.Item;
import obstacles.Obstacle;
import obstacles.ObstacledRoomException;

public class RoomTransition implements Serializable{

	private static final long serialVersionUID = 2948106030312829446L;

	private final int transitioningRoomId;
	private final Obstacle transitionObstacle;
	transient private File transitionSound;

	public int moveToRoom() throws ObstacledRoomException {
		if(transitionObstacle == null || transitionObstacle.isObstaclePassed()) {
			return transitioningRoomId;
		} else {
			//If there isn't an obstacle, this branch will never activate. So, this branch is activeted only when
			//the obstacle isn't passed.
			throw new ObstacledRoomException();
		}

	}

	public boolean useItemToUnlock(Item item) {
		if (transitionObstacle != null && !transitionObstacle.isObstaclePassed()) {
			return transitionObstacle.unlock(item);
		} else {
			return false;
		}
	}

	public RoomTransition(int ID, Obstacle obstacle, File sound){
		this.transitioningRoomId = ID;
		this.transitionObstacle = obstacle;
		this.transitionSound = sound;
	}

	public String info() {
		if(transitionObstacle != null && !transitionObstacle.isPassed()) {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("obstacledPath") + ":" +
					ResourceBundle.getBundle("bundles/ObstaclesNames")
							.getString(transitionObstacle.getClass().getSimpleName());
		} else {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("noObstacle");
		}
	}
}
