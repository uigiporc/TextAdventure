package engine;

import gui.UIHandler;
import map.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class MapLoader {

	private static List<Room> gameMap;


	public static void loadMap(File loadingFile, long offset) throws IOException,  ClassNotFoundException {
		ObjectInputStream inputStream;
		FileInputStream fileInputStream;
		fileInputStream = new FileInputStream(loadingFile);
		inputStream = new ObjectInputStream(fileInputStream);
		if (offset != 0) {
			fileInputStream.getChannel().position(offset);
		}
		gameMap = (List<Room>) inputStream.readObject();
	}



	public static Room getRoom(int ID) {
		Room nextRoom = gameMap.get(ID);
		return nextRoom;
	}

	static List<Room> getMap() {
		return gameMap;
	}

	static void updateMap(List<Room> rooms) {
		gameMap.clear();
		gameMap = rooms;
	}
}
