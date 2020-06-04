package map;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import items.Bomb;
import items.Key;
import map.RoomContainer;
import map.RoomType;
import obstacles.ClosedDoor;
import util.Command;
import util.Direction;
import util.LightStatus;
import items.Item;

public class RoomBuilder {
	static File roomFilePath;
	static File roomDescriptionFileEN;
	static File roomDescriptionFileIT;
	static File roomHelpFileEN;
	static File roomHelpFileIT;

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



			ArrayList roomContainers = new ArrayList();
			Map<Command, ArrayList<Item>> items = new HashMap();
			Map<Direction, RoomTransition> adiacentRooms = new HashMap<Direction, RoomTransition>();
			RoomTransition roomTr = new RoomTransition(1, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			ArrayList<Item> item= new ArrayList<>();
			item.add(new Bomb());
			items.put(Command.GET, item);

			createdRoom = new Room(0, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					items, adiacentRooms);

			oos.writeObject(createdRoom);
			//System.out.println(roomFilePath.length() + " position: " + oosfos.getChannel().position());

			ArrayList<RoomContainer> roomContainers1 = new ArrayList<>();
			Map<Command, ArrayList<Item>> items1 = new HashMap();
			Map<Direction, RoomTransition> adiacentRooms1 = new HashMap<Direction, RoomTransition>();
			RoomTransition roomTr1 = new RoomTransition(2, new ClosedDoor(), new File("src/main/resources/doorOpen_2.ogg"));
			RoomTransition roomTr11 = new RoomTransition(0, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms1.put(Direction.EAST, roomTr1);
			adiacentRooms1.put(Direction.SOUTH, roomTr11);
			ArrayList<Item> item1= new ArrayList<>();
			item.add(new Key());
			items.put(Command.GET, item);

			createdRoom = new Room(1, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers1,
					items1, adiacentRooms1);
			oos.writeObject(createdRoom);
			//System.out.println(roomFilePath.length());
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

}
