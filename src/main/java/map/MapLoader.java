package map;

//import java.util.HashMap;

import java.io.*;

public class MapLoader {
	 public static Room loadRoom() {
		 ObjectInputStream oos = null;
		 Room room = null;
	 	try {
			oos = new ObjectInputStream(new FileInputStream("src/main/java/bundles/room.properties"));
			room = (Room) oos.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 return room;
	 }
}
