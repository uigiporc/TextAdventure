package map;

import java.io.*;
import java.util.*;

import items.*;
import obstacles.Boulder;
import obstacles.ClosedDoor;
import util.Direction;
import util.LightStatus;
import util.Torch;

public class RoomBuilder {
	static File roomFilePath;
	static File roomDescriptionFileEN;
	static File roomDescriptionFileIT;
	static File roomHelpFileEN;
	static File roomHelpFileIT;
	static ArrayList roomContainers = new ArrayList();
	static Map<Direction, RoomTransition> adjacentRooms = new HashMap<Direction, RoomTransition>();
	static RoomTransition roomTr;
	static ArrayList<Item> item= new ArrayList<>();
	static ArrayList<Item> containerContent = new ArrayList<>();

	static {
		try {
			roomFilePath = new File("src/main/resources/bin/intotheunknown.dat");
			roomFilePath.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ObjectOutputStream oos;

		Room createdRoom;
		try {
			FileOutputStream oosfos = new FileOutputStream(roomFilePath);
			oos = new ObjectOutputStream(oosfos);
			ArrayList<Room> rooms = new ArrayList<>();

			//--- Room 0 ---

			roomTr = new RoomTransition(1, new ClosedDoor(new Key()));
			adjacentRooms.put(Direction.NORTH, roomTr);
			containerContent.add(new Key());
			roomContainers.add(new Chest(containerContent));
			createdRoom = new Room(0, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//System.out.println(roomFilePath.length() + " position: " + oosfos.getChannel().position());

			//--- Room 1 ---

			roomTr = new RoomTransition(2, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(0, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(5, new ClosedDoor(new Key()));
			adjacentRooms.put(Direction.NORTH, roomTr);
			createdRoom = new Room(1, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 2 ---

			roomTr = new RoomTransition(3, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(1, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			createdRoom = new Room(2, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 3 ---

			roomTr = new RoomTransition(4, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(6, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(2, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			createdRoom = new Room(3, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "madScientist");
			rooms.add(createdRoom);
			clear();

			//--- Room 4 ---

			roomTr = new RoomTransition(3, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			item.add(new OldBook());
			item.add(new HairPin());
			createdRoom = new Room(4, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 5 ---

			roomTr = new RoomTransition(1, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			createdRoom = new Room(5, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 6 ---

			roomTr = new RoomTransition(7, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(8, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(6, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(6, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			createdRoom = new Room(6, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 7 ---

			roomTr = new RoomTransition(16, new Boulder(new Bomb()));
			adjacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(6, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(7, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(7, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			item.add(new Torch());

			createdRoom = new Room(7, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 8 ---

			roomTr = new RoomTransition(9, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(6, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(8, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bunnyHunt");
			rooms.add(createdRoom);
			clear();

			//--- Room 9 ---

			roomTr = new RoomTransition(10, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(14, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(8, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			createdRoom = new Room(9, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bunnyRunAway");
			rooms.add(createdRoom);
			clear();

			//--- Room 10 ---

			roomTr = new RoomTransition(11, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(9, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(10, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bunnyRunAway");
			rooms.add(createdRoom);
			clear();

			//--- Room 11 ---

			roomTr = new RoomTransition(10, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(12, null);
			adjacentRooms.put(Direction.EAST, roomTr);

			createdRoom = new Room(11, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bunnyRunAway");
			rooms.add(createdRoom);
			clear();

			//--- Room 12 ---

			roomTr = new RoomTransition(11, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(13, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(12, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bunnyRunAway");
			rooms.add(createdRoom);
			clear();

			//--- Room 13 ---

			roomTr = new RoomTransition(12, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(14, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(13, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bunnyRunAway");
			rooms.add(createdRoom);
			clear();

			//--- Room 14 ---

			roomTr = new RoomTransition(9, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(13, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(15, null);
			adjacentRooms.put(Direction.NORTH, roomTr);

			item.add(new Bomb());
			createdRoom = new Room(14, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bunnyRunAway");
			rooms.add(createdRoom);
			clear();

			//--- Room 15 ---

			roomTr = new RoomTransition(14, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(12, null);
			adjacentRooms.put(Direction.EAST, roomTr);

			createdRoom = new Room(15, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 16 ---

			roomTr = new RoomTransition(7, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(17, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(16, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 17 ---

			roomTr = new RoomTransition(18, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(17, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 18 ---

			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(19, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(18, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 19 ---

			roomTr = new RoomTransition(20, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(19, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 20 ---

			roomTr = new RoomTransition(21, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(20, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 21 ---

			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(22, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(21, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 22 ---

			roomTr = new RoomTransition(23, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(22, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 23 ---

			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(24, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(23, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 24 ---

			roomTr = new RoomTransition(25, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(24, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 25 ---

			roomTr = new RoomTransition(26, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(25, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 26 ---

			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(27, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(26, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 27 ---

			roomTr = new RoomTransition(28, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(27, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 28 ---

			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(29, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(28, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 29 ---

			roomTr = new RoomTransition(30, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(29, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 30 ---

			roomTr = new RoomTransition(31, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(16, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(30, LightStatus.DARK, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 31 ---

			roomTr = new RoomTransition(32, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(30, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(31, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 32 ---

			roomTr = new RoomTransition(39, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(31, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(33, null);
			adjacentRooms.put(Direction.EAST, roomTr);

			createdRoom = new Room(32, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 33 ---

			roomTr = new RoomTransition(33, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(38, new ClosedDoor(new Key()));
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(34, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(32, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(33, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 34 ---

			roomTr = new RoomTransition(35, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(37, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(36, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(33, null);
			adjacentRooms.put(Direction.WEST, roomTr);

			createdRoom = new Room(34, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 35 ---

			createdRoom = new Room(35, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "explosion");
			rooms.add(createdRoom);
			clear();

			//--- Room 36 ---

			createdRoom = new Room(36, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "explosion");
			rooms.add(createdRoom);
			clear();

			//--- Room 37 ---

			roomTr = new RoomTransition(34, null);
			adjacentRooms.put(Direction.NORTH, roomTr);

			item.add(new Key());
			createdRoom = new Room(37, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 38 ---

			roomTr = new RoomTransition(33, null);
			adjacentRooms.put(Direction.NORTH, roomTr);

			createdRoom = new Room(38, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "oldManEvent");
			rooms.add(createdRoom);
			clear();

			//--- Room 39 ---

			roomTr = new RoomTransition(32, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);
			roomTr = new RoomTransition(40, null);
			adjacentRooms.put(Direction.EAST, roomTr);

			createdRoom = new Room(39, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 40 ---

			roomTr = new RoomTransition(39, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(41, null);
			adjacentRooms.put(Direction.EAST, roomTr);
			roomTr = new RoomTransition(40, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(40, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "dragonWakeUp");
			rooms.add(createdRoom);
			clear();

			//--- Room 41 ---

			roomTr = new RoomTransition(42, null);
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(40, null);
			adjacentRooms.put(Direction.WEST, roomTr);
			roomTr = new RoomTransition(41, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(41, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "");
			rooms.add(createdRoom);
			clear();

			//--- Room 42 ---

			roomTr = new RoomTransition(43, new ClosedDoor(new SupremeKey()));
			adjacentRooms.put(Direction.NORTH, roomTr);
			roomTr = new RoomTransition(40, null);
			adjacentRooms.put(Direction.SOUTH, roomTr);

			createdRoom = new Room(42, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "doorEvent");
			rooms.add(createdRoom);
			clear();

			//--- Room 43 ---
			roomTr = new RoomTransition(44, new ClosedDoor(new HairPin()));
			adjacentRooms.put(Direction.SOUTH, roomTr);
			createdRoom = new Room(43, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bossEvent");
			rooms.add(createdRoom);
			clear();

			//--- Room 44 ---

			createdRoom = new Room(44, LightStatus.BRIGHT, roomContainers,
					item, adjacentRooms, "bossEvent");
			rooms.add(createdRoom);
			clear();

			oos.writeObject(rooms);
			oos.close();
			//generateIndexes();
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
		adjacentRooms = new HashMap<>();
		item = new ArrayList<>();
		containerContent = new ArrayList<>();
	}

}
