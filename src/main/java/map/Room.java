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

import engine.RoomEvent;
import obstacles.IllegalItemUsageException;
import obstacles.ObstacledRoomException;
import util.*;

import java.io.Serializable;
import java.util.*;

import items.*;

public class Room implements Serializable {

	private static final long serialVersionUID = 2512059451189906531L;
	private Integer ID;
	private RoomType setting;
	transient private static ResourceBundle roomDescriptionBundle = null;
	private LightStatus illumination;
	private ArrayList<RoomContainer> roomContainers;
	private ArrayList<Item> roomItems = new ArrayList<Item>();
	private Map<Direction, RoomTransition> adiacentRooms = new HashMap<Direction, RoomTransition>();
	private String stepOnEvent;

	public String getAreaDescription() {
		return roomDescriptionBundle.getString(ID.toString());
	}



	public Item getItemInArea(String itemName) throws IllegalActionException{
		roomItems.contains(itemName);
		for(Item checkedItem : roomItems) {
			if(checkedItem.equals(itemName)) {
				roomItems.remove(checkedItem);
				//roomItems.replace(command, roomItems.get(command), roomItems.get(command).remove(checkedItem));
				return checkedItem;
			}
		}

		//If the for-each ends without returning an Item, there's no such item in the Room
		//So the command is illegal
		throw new IllegalActionException();

	}

	public void dropItem(Item droppedItem) {
		roomItems.add(droppedItem);
	}

	public boolean isIlluminated() {
		if(illumination == LightStatus.BUIO) {
			return false;
		}
		else {
			return true;
		}

	}

	protected RoomType getSetting() {
		return setting;
	}

	public static void setRoomDescriptionBundle(Locale currentLocale) {
		roomDescriptionBundle = ResourceBundle.getBundle("bundles.RoomDescriptions");
	}

	public Room(int newID, RoomType newSetting, LightStatus newIllumination,
				ArrayList newRoomContainers, ArrayList newRoomItems, Map newAdiacentRooms, String event) {		//should be protected

		this.ID = newID;
		this.setting = newSetting;
		this.illumination = newIllumination;
		this.roomContainers = newRoomContainers;
		this.roomItems = newRoomItems;
		this.adiacentRooms = newAdiacentRooms;
		this.stepOnEvent = event;
	}

	public boolean doesItUnlock(Item item) throws IllegalItemUsageException {
		for(Direction tempDirection : adiacentRooms.keySet()){
			if(adiacentRooms.get(tempDirection).useItemToUnlock(item)){
				return true;
			}
		}
		throw new IllegalItemUsageException();
	}

	public Room move(Direction direction) throws IllegalMovementException, ObstacledRoomException{
		try{
			int movingRoomId = adiacentRooms.get(direction).moveToRoom();
			return MapLoader.getRoom(movingRoomId);
		} catch (NullPointerException e) {
			throw new IllegalMovementException();
		}
	}


	public void openContainer(RoomContainer searchedContainer) {
		for (RoomContainer tempContainer : roomContainers){
			if (tempContainer.equals(searchedContainer)) {
				tempContainer.open(roomItems);
			}
		}
	}

	public String roomInformations() {
		if(!this.isIlluminated()) {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("noLight");
		}
		String info = "";
		info = info + this.getAreaDescription();
		info = info + ResourceBundle.getBundle("bundles/engineOutText").getString("directions") + ":";
		for (Direction direction : adiacentRooms.keySet()) {
			info = info + " " + direction.toString() + " -";
		}
		info = info + "\n";
		info = info + (ResourceBundle.getBundle("bundles/engineOutText").getString("content")) + (":");
		if(this.roomItems.size() > 0) {
			for (int i = 0; i < roomItems.size(); i++) {
				info = info + (" " + roomItems.get(i).getItemName() + " -");
			}
		}
		if (this.roomContainers.size() > 0) {
			for (int i = 0; i < roomContainers.size(); i++) {
				info = info + " " +  roomContainers.get(i).getName()
						+ "(" + roomContainers.get(i).getState() + ")" + " -";
			}
		}
		return info;
	}

	public void startEvent() {
		if (RoomEvent.startEvent(stepOnEvent)) {
			stepOnEvent = "";
		}
	}

    public void closeContainer(RoomContainer searchedContainer) {
		for (RoomContainer tempContainer : roomContainers){
			if (tempContainer.equals(searchedContainer)) {
				tempContainer.close(roomItems);
			}
		}
    }

    public String directionInformation(Direction direction) {
	    try{
	        return adiacentRooms.get(direction).info();
        } catch (NullPointerException e) {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("noDirection");
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Room room = (Room) o;
		return ID.equals(room.ID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID);
	}
}
