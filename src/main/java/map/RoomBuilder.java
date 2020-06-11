package map;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import items.*;
import map.RoomContainer;
import map.RoomType;
import obstacles.ClosedDoor;
import util.Command;
import util.Direction;
import util.LightStatus;

public class RoomBuilder {
	static File roomFilePath;
	static File roomDescriptionFileEN;
	static File roomDescriptionFileIT;
	static File roomHelpFileEN;
	static File roomHelpFileIT;
	static ArrayList roomContainers = new ArrayList();
	static Map<Direction, RoomTransition> adiacentRooms = new HashMap<Direction, RoomTransition>();
	static RoomTransition roomTr;
	static ArrayList<Item> item= new ArrayList<>();
	static ArrayList<Item> containerContent = new ArrayList<>();

	static {
		try {
			roomFilePath = new File("src/main/java/bundles/room.properties");
			roomFilePath.createNewFile();
			roomDescriptionFileEN = new File("src/main/resources/RoomDescriptions_en.properties");
			roomDescriptionFileEN.createNewFile();
			roomDescriptionFileIT = new File("src/main/resources/RoomDescriptions_it.properties");
			roomDescriptionFileIT.createNewFile();
			roomHelpFileEN = new File("src/main/resources/RoomHelp_en.properties");
			roomHelpFileEN.createNewFile();
			roomHelpFileIT = new File("src/main/resources/RoomHelp_it.properties");
			roomHelpFileIT.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ObjectOutputStream oos;
		ObjectOutputStream oosDescEN;
		ObjectOutputStream oosDescIT;
		ObjectOutputStream oosHelpEN;
		ObjectOutputStream oosHelpIT;

		Room createdRoom;
		try {
			FileOutputStream oosfos = new FileOutputStream(roomFilePath);
			oos = new ObjectOutputStream(oosfos);
			oosDescEN = new ObjectOutputStream(new FileOutputStream(roomDescriptionFileEN));
			oosDescIT = new ObjectOutputStream(new FileOutputStream(roomDescriptionFileIT));
			oosHelpEN = new ObjectOutputStream(new FileOutputStream(roomHelpFileEN));
			oosHelpIT = new ObjectOutputStream(new FileOutputStream(roomHelpFileIT));

			//--- Room 0 ---

			roomTr = new RoomTransition(1, new ClosedDoor(new Key()), new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			containerContent.add(new Key());
			roomContainers.add(new Chest(containerContent));
			createdRoom = new Room(0, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//System.out.println(roomFilePath.length() + " position: " + oosfos.getChannel().position());

			//--- Room 1 ---

			roomTr = new RoomTransition(2, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(0, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(5, new ClosedDoor(new Key()), new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			createdRoom = new Room(1, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 2 ---

			roomTr = new RoomTransition(3, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(1, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.WEST, roomTr);
			createdRoom = new Room(2, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 3 ---

			roomTr = new RoomTransition(4, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(6, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(2, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.WEST, roomTr);
			createdRoom = new Room(3, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "madScientist");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 4 ---

			roomTr = new RoomTransition(3, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			item.add(new OldBook());
			createdRoom = new Room(4, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 5 ---

			roomTr = new RoomTransition(1, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.SOUTH, roomTr);
			createdRoom = new Room(5, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 6 ---

			roomTr = new RoomTransition(7, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(8, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.EAST, roomTr);

			createdRoom = new Room(6, RoomType.OUTDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 7 ---

			roomTr = new RoomTransition(19, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(6, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(7, RoomType.OUTDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 8 ---

			roomTr = new RoomTransition(9, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(6, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(8, RoomType.OUTDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "rabbitHunt");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 9 ---

			roomTr = new RoomTransition(10, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(9, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.WEST, roomTr);

			//--- Room 10 ---

			roomTr = new RoomTransition(11, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(9, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(10, RoomType.OUTDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			//--- Room 11 ---

			roomTr = new RoomTransition(12, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(10, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(11, RoomType.OUTDOOR, LightStatus.ILLUMINATO, roomContainers,
					item, adiacentRooms, "");
			oos.writeObject(createdRoom);
			clear();

			oos.close();
			generateIndexes();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	private static void generateIndexes(){
		try {
			FileInputStream fis = new FileInputStream(roomFilePath);
			ObjectInputStream ois = new ObjectInputStream(fis);
			FileOutputStream fos = new FileOutputStream("src/main/java/bundles/room_data.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			HashMap<Integer, Long> mapIndexes = new HashMap<>();
			Room room;
			for(int i = 0; i<2; i++) {
				mapIndexes.put(i, fis.getChannel().position());
				room = (Room) ois.readObject();
			}
			oos.writeObject(mapIndexes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void clear() {
		roomContainers = new ArrayList();
		adiacentRooms = new HashMap<>();
		item = new ArrayList<>();
		containerContent = new ArrayList<>();
	}

}
