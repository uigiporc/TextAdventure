package map;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import items.Bomb;
import map.RoomContainer;
import adventure.builder.RoomTransition;
import map.RoomType;
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
			oos = new ObjectOutputStream(new FileOutputStream(roomFilePath));
			oosDescEN = new ObjectOutputStream(new FileOutputStream(roomDescriptionFileEN));
			oosDescIT = new ObjectOutputStream(new FileOutputStream(roomDescriptionFileIT));
			oosHelpEN = new ObjectOutputStream(new FileOutputStream(roomHelpFileEN));
			oosHelpIT = new ObjectOutputStream(new FileOutputStream(roomHelpFileIT));

			/*
			private int ID;
			private RoomType setting;
			private LightStatus illumination;
			private Map<Command, RoomContainer> roomContainers= new HashMap<Command, RoomContainer>();
			private Map<Command, Item[]> roomItems = new HashMap<Command, Item[]>();
			private Map<Direction, RoomTransition> adiacentRooms = new HashMap<Direction, RoomTransition>();
			 */

			Map<Command, RoomContainer> roomContainers = new HashMap<>();
			Map<Command, ArrayList<Item>> items = new HashMap();
			Map<Direction, RoomTransition> adiacentRooms = new HashMap<Direction, RoomTransition>();
			RoomTransition roomTr = new RoomTransition(1, null, new File("src/main/resources/doorOpen_2.ogg"));
			adiacentRooms.put(Direction.NORTH, roomTr);
			ArrayList<Item> item= new ArrayList<>();
			item.add(new Bomb());
			items.put(Command.GET, item);

			createdRoom = new Room(0, RoomType.INDOOR, LightStatus.ILLUMINATO, roomContainers,
					items, adiacentRooms);
			//	public Room(int newID, RoomType newSetting, LightStatus newIllumination,
			//				Map newRoomContainers, Map newRoomItems, Map newAdiacentRooms)



			oos.writeObject(createdRoom);
		}
		catch(IOException ex){
			ex.printStackTrace();
		} finally {
			System.out.println("finallyyy");

		}
	}
}
