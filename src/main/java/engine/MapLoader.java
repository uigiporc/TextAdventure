package engine;

import gui.UIHandler;
import map.Room;

import java.io.*;
import java.util.ArrayList;


public class MapLoader {

	private static ArrayList<Room> gameMap;


	public static void loadMap(File loadingFile, long offset) {
		ObjectInputStream inputStream;
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(loadingFile);
			inputStream = new ObjectInputStream(fileInputStream);
			if (offset != 0) {
				fileInputStream.getChannel().position(offset);
			}
			System.out.println("ML:" + fileInputStream.getChannel().position());
			gameMap = (ArrayList<Room>) inputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

		}
	}



	public static Room getRoom(int ID) {
		Room nextRoom = gameMap.get(ID);
		UIHandler.cleanScreen();
		return nextRoom;
	}

	static ArrayList<Room> getMap() {
		return gameMap;
	}

	static void updateMap(ArrayList<Room> rooms) {
		gameMap.clear();
		gameMap = rooms;
	}
}
