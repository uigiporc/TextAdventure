package engine;

import map.*;

/**
 * GameProgress is a class containing the current game progress. 
 */
public class GameProgress {
	protected static int timeSpent;
	protected static String sessionName;
	private static Area currentArea;
	
	public static void nextArea() {
		currentArea = MapLoader.load();
	}
	
	public static Area getCurrentArea() {
		return currentArea;
	}
}
