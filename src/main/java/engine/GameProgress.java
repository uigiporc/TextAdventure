package engine;

import map.*;

/**
 * GameProgress is a class containing the current game progress. 
 */
public class GameProgress {
	protected static int timeSpent;
	protected static String sessionName;
	private static Room currentArea;
	private static final String language = "IT";
	
	public static void nextArea() {
		currentArea = MapLoader.load();
	}
	
	public static Room getCurrentArea() {
		return currentArea;
	}
	
	public static String getLang() {
		return language;
	}
}
