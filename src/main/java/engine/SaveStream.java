package engine;

import map.Room;

import java.io.*;
import java.util.ArrayList;

public class SaveStream {
    public static void saveProgress(File file) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(GameProgress.getCurrentRoom().getID());
			oos.writeObject(GameProgress.getBag());
			oos.writeObject(MapLoader.getMap());
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Save all areas, current area, inventory.
    }
    
    public static void loadProgress(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int currentRoomID = (Integer) ois.readObject();
			GameProgress.setBag((Inventory) ois.readObject());
			MapLoader.updateMap((ArrayList<Room>) ois.readObject());
			GameProgress.setCurrentRoom(MapLoader.getRoom(currentRoomID));
			ois.close();
			fis.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
/**
		 * This function saves the progress of the current game, 
		 * including the current Area, the items in the 
		 * inventory, the variables (probably booleans) 
		 * necessary to continue the game, the name and time of the
		 * current saving.
		 */