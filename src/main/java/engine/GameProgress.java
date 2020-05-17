package engine;

import java.util.Locale;

import items.Item;
import map.*;
import util.Direction;
import util.LightStatus;

/**
 * GameProgress is a class containing the current game progress.
 */
public class GameProgress {

	protected static int timeSpent;
	protected static String sessionName;
	private static Room currentRoom;
	private static LightStatus playerLight = null;


	public static void nextRoom() {
		currentRoom = MapLoader.loadRoom();
		System.out.println(currentRoom.getAreaDescription());
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

	public static void moveRoom(Direction direction) {
	}

	public static void dropItem(Item droppedItem){
		currentRoom.dropItem(droppedItem);
	}

}
