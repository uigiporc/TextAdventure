package map;

import java.io.File;
import java.io.Serializable;

import obstacles.Obstacle;

public class RoomTransition implements Serializable{
	
	private int transitioningRoomId;
	private Obstacle transitionObstacle = null;
	transient private File transitionSound;
	
	public int moveToRoom() {
		if(!transitionObstacle.equals(null) && transitionObstacle.isObstaclePassed()) {
			return transitioningRoomId;
		}
		else {
			return -1;
		}
	}
}
