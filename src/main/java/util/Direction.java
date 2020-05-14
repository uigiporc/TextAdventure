package util;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import engine.ResourceHandler;

public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST,
	UP,
	DOWN;
	
	private static Map<String[], Direction> directionAliases;
	
	public static boolean isDirection(String checkDirection) {
		try {
			for(String[] searchingStringArray : directionAliases.keySet()){
				for(String searchingString : searchingStringArray) {
					if(((String) searchingString).equalsIgnoreCase(checkDirection)) {
						return true;
					}
				}
				
			}
			return false;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public static Direction getDirection(String checkDirection) {
		try {
			for(String[] searchingStringArray : directionAliases.keySet()){
				for(String searchingString : searchingStringArray) {
					if(((String) searchingString).equalsIgnoreCase(checkDirection)) {
						return directionAliases.get(searchingStringArray);
					}
				}
				
			}
			return null;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public static void initAliases(String resourceFolderPath, Locale currentLocale) throws FileNotFoundException{
		String directionAliasesFilePath = resourceFolderPath + "/DirectionAliases_" + currentLocale.getLanguage() + ".properties";
		directionAliases = ResourceHandler.<Direction>load(directionAliasesFilePath);
	}
}
