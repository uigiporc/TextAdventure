package map;

import java.io.File;
import java.io.Serializable;

import obstacles.Obstacle;

public class RoomTransition implements Serializable{

	private int transitioningRoomId;
	private Obstacle transitionObstacle;
	transient private File transitionSound;

	public int moveToRoom() {
		if(transitionObstacle != null && transitionObstacle.isObstaclePassed()) {
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
