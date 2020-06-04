package engine;

import java.sql.Time;
import java.util.BitSet;
import java.util.Locale;

import items.Item;
import map.*;
import obstacles.IllegalItemUsageException;
import util.Direction;
import util.LightStatus;

/**
 * GameProgress is a class containing the current game progress.
 */
public class GameProgress extends Thread{

	protected static long totalGameTime;
	protected static String sessionName;
	private static Room currentRoom;
	private static LightStatus playerLight = null;
	private static BitSet visitedRooms;
	static Thread clock = new Thread();

	static {
		//visitedRooms.clear();
	}

	public static void nextRoom() {
		currentRoom = MapLoader.loadRoom(0);
		StringPrinter.printString(currentRoom.getAreaDescription());
	}

	public static Room getCurrentRoom() {
		return currentRoom;
	}

	public static boolean setLang(Locale changedLocale) {
		try {
			Locale.setDefault(changedLocale);
			return true;

		} catch(SecurityException ex) {
			return false;
		}
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

	public static void moveRoom(Direction direction) throws IllegalMovementException {
		currentRoom = currentRoom.move(direction);
		StringPrinter.printString(currentRoom.getAreaDescription());
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
		visitedRooms.clear();
		currentRoom = MapLoader.loadRoom(0);
		ResourceHandler.loadResources();

		/*
		Needs to get a session name. Looking for a way to get that respecting ECB.
		 */
	}

	public static void unlockObstacle(Item item) throws IllegalItemUsageException {
		currentRoom.doesItUnlock(item);
	}
}
