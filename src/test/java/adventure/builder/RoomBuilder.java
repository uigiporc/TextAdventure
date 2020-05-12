package adventure.builder;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import items.Sword;
import util.IllegalActionException;
import util.LightStatus;
import items.Item;

public class RoomBuilder {
	//static File roomFilePath = new File("src/main/resources/room.dat");
	static File roomFile = new File("room.dat");
	
	public static void build() {
		RandomAccessFile raf = null;
		Room createdRoom;
		try {
			roomFile.createNewFile();
			raf = new RandomAccessFile(roomFile.getCanonicalPath(), "rw");
			Map<String, Item> items = new HashMap();
			items.put("USE", new Sword());
			
			Map<String, Room> rooms = new HashMap();
			rooms.put("NORD", null);
			System.out.println("fatto1");
			createdRoom = new Room(0, LightStatus.ILLUMINATO, "Stanza iniziale.", 
					items, new HashMap<String, Room>());
			System.out.println("fatto1.5");
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(roomFile));
			for(int i = 0; i < 5; i++)
				output.writeObject(createdRoom);
			System.out.println("fatto2");
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(roomFile));
			while(true) {
				Room inputroom = (Room)input.readObject();
				System.out.println(inputroom.getAreaDescription());
			}
			
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException");
		}
		finally {
			System.out.println("finallyyy");
			
		}
	}
}
