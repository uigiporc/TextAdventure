package engine;

import map.Room;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveStream {
    public static void saveProgress(File file) throws IOException {
    	try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

			oos.writeObject(GameProgress.getCurrentRoom().getID());
			oos.writeObject(Inventory.getInventory().getBagContent());
			oos.writeObject(MapLoader.getMap());
			oos.close();
		} catch (IOException e) {
    		//Since we failed to add our content to the file, we delete it and propagate the exception.
    		file.delete();
    		throw new IOException();
		}


		//Save all areas, current area, inventory.
    }
    
    public static void loadProgress(File file) throws IOException, ClassNotFoundException {
    	try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);

			int currentRoomID = (Integer) ois.readObject();
			List tempBag = (List) ois.readObject();
			MapLoader.updateMap((List<Room>) ois.readObject());

			//We have to be sure that the whole save content is read from the file
			//So first we read it, then we update the current progress.
			//This way, we won't have inconsistent data if one of the methods throws an exception.
			Inventory.getInventory().setBagContent(tempBag);
			GameProgress.setCurrentRoom(MapLoader.getRoom(currentRoomID));

			ois.close();
			fis.close();
		} catch (IOException e) {
    		throw new IOException();
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