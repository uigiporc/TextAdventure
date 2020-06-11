package engine;

import java.sql.Time;
import java.util.BitSet;
import java.util.Locale;

import com.sun.jdi.ObjectCollectedException;
import gui.UIFrame;
import gui.UIHandler;
import items.Item;
import map.*;
import obstacles.IllegalItemUsageException;
import obstacles.ObstacledRoomException;
import util.Direction;
import util.LightStatus;

import javax.swing.text.Document;

/**
 * GameProgress is a class containing the current game progress.
 */
public class GameProgress extends Thread{

	protected static long totalGameTime;
	private static Room currentRoom;
	private static LightStatus playerLight = null;
	private static Thread clock = new Thread();
	private static Inventory bag;

	public static Room getCurrentRoom() {
		return currentRoom;
	}

	public static void setPlayerLight(LightStatus light) {
		playerLight = light;
	}

	public static void resetPlayerLight() {
		playerLight = null;
	}

	public static LightStatus getPlayerLight() {
		return playerLight;
	}

	public static void moveRoom(Direction direction) throws IllegalMovementException, ObstacledRoomException {
		currentRoom = currentRoom.move(direction);
	}

	public static void dropItem(Item droppedItem){
		currentRoom.dropItem(droppedItem);
	}

	@Override
	public void run(){
		Long startGameSession =  System.nanoTime();
		try {
			this.wait();
		} catch (InterruptedException e) {
			totalGameTime += System.nanoTime() - startGameSession;

		}
	}

	public static void newGame(){
		totalGameTime = 0;
		currentRoom = MapLoader.getRoom(0);
		bag = Inventory.getInventory();
		new Thread(clock).start();
	}

	public static void getItem() {

	}

	public static Inventory getBag() {
		return bag;
	}
	public static void unlockObstacle(Item item) throws IllegalItemUsageException {
		currentRoom.doesItUnlock(item);
	}
}
