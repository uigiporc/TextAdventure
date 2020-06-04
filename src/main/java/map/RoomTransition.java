package map;

import java.io.File;
import java.io.Serializable;

import items.Item;
import obstacles.Obstacle;

public class RoomTransition implements Serializable{

	private static final long serialVersionUID = 2948106030312829446L;

	private final int transitioningRoomId;
	private final Obstacle transitionObstacle;
	transient private File transitionSound;

	public int moveToRoom() throws IllegalMovementException{
		if(transitionObstacle == null || transitionObstacle.isObstaclePassed()) {
			return transitioningRoomId;
		}
		throw new IllegalMovementException();
	}

	public boolean useItemToUnlock(Item item) {
		return transitionObstacle.unlock(item);
	}

	public RoomTransition(int ID, Obstacle obstacle, File sound){
		this.transitioningRoomId = ID;
		this.transitionObstacle = obstacle;
		this.transitionSound = sound;
	}

}
