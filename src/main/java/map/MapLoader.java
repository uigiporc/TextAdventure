package map;

import gui.UIHandler;
import java.io.*;
import java.util.ArrayList;


public class MapLoader {

	private static final ArrayList<Room> gameMap = new ArrayList<>();


	public static void loadMap() {
		ObjectInputStream inputStream;
		try {
			inputStream = new ObjectInputStream(new FileInputStream("src/main/java/bundles/room.properties"));
			while (true) {
				gameMap.add((Room) inputStream.readObject());
			}
		} catch (EOFException e) {
			return;
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
		UIHandler.printInFrame(nextRoom.roomInformations() + "\n");
		nextRoom.startEvent();
		return nextRoom;
	}
}
