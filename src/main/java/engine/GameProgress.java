package engine;

import gui.UIHandler;
import items.Item;
import map.*;
import obstacles.IllegalItemUsageException;
import obstacles.HinderedRoomException;
import util.Direction;
import util.LightStatus;

import java.util.ResourceBundle;

/**
 * GameProgress is a class containing the current game progress.
 */
public class GameProgress {

	private static Room currentRoom;
	private static LightStatus playerLight = null;

	public static Room getCurrentRoom() {
		return currentRoom;
	}

	public static void setPlayerLight(LightStatus light) {
		playerLight = light;
	}

	public static void resetPlayerLight() {
		playerLight = null;
	}

	public static boolean isPlayerIlluminated() {
		return playerLight == LightStatus.BRIGHT;
	}

	public static void moveRoom(Direction direction) throws IllegalMovementException, HinderedRoomException {
		currentRoom = currentRoom.move(direction);
		UIHandler.cleanScreen();
		currentRoom.startEvent();
		UIHandler.printInFrame(currentRoom.roomInformations() + "\n");
	}

	public static void setCurrentRoom(Room currentRoom) {
		GameProgress.currentRoom = currentRoom;
	}

	public static void dropItem(Item droppedItem){
		currentRoom.dropItem(droppedItem);
	}

	public static void newGame(){
		UIHandler.cleanScreen();
		currentRoom = MapLoader.getRoom(0);
		UIHandler.printInFrame(currentRoom.roomInformations() + "\n");
	}

	public static void unlockObstacle(Item item) throws IllegalItemUsageException {
		currentRoom.doesItUnlock(item);
	}

	public static void gameOver() {
		UIHandler.disableInput();
		UIHandler.disableSave();
	}
}
