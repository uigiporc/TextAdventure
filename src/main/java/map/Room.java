/**
 * @author Luigi Porcelli, Computer Science
 */

package map;

/*
 * Maps areaItems and adiacentAreas should have a specific behavior for when we need a specific
 * item and a specific action (eg. "USE SWORD"). Maybe I could pass the object as a parameter
 * and use it as a KEY in those maps; another solution is to pass a string (eg the string "USE SWORD")
 * after having done the necessary actions.
 * areaItems could be pair<?,?>, where one of the parameters can be missing.
 */

import util.*;

import java.io.Serializable;
import java.util.*;

import items.*;

public class Room implements Serializable {
	private Integer ID;
	private RoomType setting;
	transient private static ResourceBundle roomDescriptionBundle = null;
	private LightStatus illumination;
	private Map<Command, RoomContainer> roomContainers= new HashMap<Command, RoomContainer>();
	private Map<Command, ArrayList<Item>> roomItems = new HashMap<Command, ArrayList<Item>>();
	private Map<Direction, RoomTransition> adiacentRooms = new HashMap<Direction, RoomTransition>();
	transient private static ResourceBundle roomHelpBundle = null;

	public String getAreaDescription() {
		return roomDescriptionBundle.getString("0");
	}

	public LightStatus getIllumination() {
		return illumination;
	}

	public Item getItemInArea(Command command, String itemName) throws IllegalActionException{
		if(roomItems.containsKey(command)) {
			for(Item checkedItem : roomItems.get(command)){
				if(checkedItem.equals(itemName)){
					return checkedItem;
				}
			}
		}
		//If the for-each ends without returning an Item, there's no such item in the Room
		//So the command is illegal
		throw new IllegalActionException();

	}

	public void dropItem(Item droppedItem){
		ArrayList<Item> tempItems = roomItems.get(Command.GET);
		tempItems.add(droppedItem);
		roomItems.put(Command.GET, tempItems);
	}

	public void help() {
		if(illumination == LightStatus.BUIO) {
			System.out.println("È troppo buio. Prova ad illuminare la stanza prima.");
		}
		else {
			System.out.println(roomHelpBundle.getString("0"));
		}

	}

	public void move(String direction) throws IllegalMovementException{

	}

	protected void setLight(LightStatus light) {
		illumination = light;
	}

	protected RoomType getSetting() {
		return setting;
	}

	public static void setRoomDescriptionBundle(Locale currentLocale) {
		roomDescriptionBundle = ResourceBundle.getBundle("bundles.RoomDescriptions", currentLocale);
	}

	public static void setRoomHelpBundle(Locale currentLocale) {
		roomHelpBundle = ResourceBundle.getBundle("bundles.RoomHelp", currentLocale);
	}

	public Room(int newID, RoomType newSetting, LightStatus newIllumination,
				Map newRoomContainers, Map newRoomItems, Map newAdiacentRooms) {		//should be protected

		this.ID = newID;
		this.setting = newSetting;
		this.illumination = newIllumination;
		this.roomContainers= newRoomContainers;
		this.roomItems = newRoomItems;
		this.adiacentRooms = newAdiacentRooms;
	}
}
