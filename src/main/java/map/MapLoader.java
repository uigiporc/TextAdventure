package map;

//import java.util.HashMap;

import java.io.*;
import java.util.HashMap;

public class MapLoader {

	private static FileInputStream roomFile = null;
	private static ObjectOutputStream roomProgressOut = null;
	private static FileOutputStream roomProgressStream = null;
	private static ObjectInputStream roomProgressIn = null;
	private static final File roomProgressFile = new File("src/main/resources/progress/temp_progress.dat");
	private static ObjectInputStream roomStream = null;

	static {
		try {
			roomFile = new FileInputStream("src/main/java/bundles/room.properties");
			roomStream = new ObjectInputStream(roomFile);
			roomProgressStream = new FileOutputStream(roomProgressFile, true);
			roomProgressOut = new ObjectOutputStream(new FileOutputStream(roomProgressFile));
			//roomProgressIn =

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	 public static Room loadRoom(int roomIDToLoad) {
	 	System.out.println(roomIDToLoad);


	 	Room room = null;
	 	try {
	 		roomFile.getChannel().position(getOffset(roomIDToLoad));

	 		room = (Room) roomStream.readObject();
	 		return room;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			/*try {
				roomStream.close();
				roomFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
		 return null;
	 }

	private static long getOffset(int roomIDToLoad){
		ObjectInputStream oos = null;
		try{
			oos = new ObjectInputStream(new FileInputStream("src/main/java/bundles/room_data.dat"));
			HashMap hm = (HashMap<Integer, Long>) oos.readObject();

			return (long) hm.get(roomIDToLoad);

		} catch (IOException | ClassNotFoundException ex) {
			return 0;
		}
	}

	public static void storeRoom(Room lastRoom) {
		try {
			roomProgressOut.writeObject(lastRoom);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
