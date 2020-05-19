package adventure.builder;

import obstacles.Obstacle;

import java.io.File;
import java.io.Serializable;

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

	public RoomTransition(int ID, Obstacle obstacle, File sound){
		this.transitioningRoomId = ID;
		this.transitionObstacle = obstacle;
		this.transitionSound = sound;

	}
}
