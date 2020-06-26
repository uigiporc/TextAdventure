package util;

import engine.ResourceHandler;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST,
	UP,
	DOWN;

	private static Map<String[], Direction> directionAliases;

	public String getName() {
		return ResourceBundle.getBundle("bundles/DirectionsBundle").getString(this.toString());
	}

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

	public static void initAliases(String resourceFolderPath, Locale currentLocale) throws IOException {
		String directionAliasesFilePath = resourceFolderPath + "DirectionAliases_" + currentLocale.getLanguage() + ".dat";
		directionAliases = ResourceHandler.<Direction>load(directionAliasesFilePath);
	}

	public static Direction getOppositeDirection(Direction direction) {
		switch (direction) {
			case EAST: {
				return WEST;
			}
			case WEST: {
				return EAST;
			}
			case NORTH: {
				return SOUTH;
			}
			case SOUTH: {
				return NORTH;
			}
			case UP: {
				return DOWN;
			}
			default: {
				return UP;
			}
		}
	}
}