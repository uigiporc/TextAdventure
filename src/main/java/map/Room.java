/**
 * @author Luigi Porcelli, Computer Science
 */

package map;

/*
 * Maps areaItems and adjacentAreas should have a specific behavior for when we need a specific
 * item and a specific action (eg. "USE SWORD"). Maybe I could pass the object as a parameter
 * and use it as a KEY in those maps; another solution is to pass a string (eg the string "USE SWORD")
 * after having done the necessary actions.
 * areaItems could be pair<?,?>, where one of the parameters can be missing.
 */

import engine.GameProgress;
import engine.MapLoader;
import engine.GameEvent;
import obstacles.IllegalItemUsageException;
import obstacles.HinderedRoomException;
import util.*;

import java.io.Serializable;
import java.util.*;

import items.*;

public class Room implements Serializable {

	private static final long serialVersionUID = 2512059451189906531L;
	private final Integer ID;
	transient private static ResourceBundle roomDescriptionBundle = null;
	private final LightStatus illumination;
	private final ArrayList<RoomContainer> roomContainers;
	private ArrayList<Item> roomItems = new ArrayList<Item>();
	private Map<Direction, RoomTransition> adjacentRooms = new HashMap<Direction, RoomTransition>();
	private String stepOnEvent;

	public String getAreaDescription() {
		return roomDescriptionBundle.getString(ID.toString());
	}



	public Item getItemInArea(String itemName) throws IllegalActionException{
		for(Item checkedItem : roomItems) {
			if(checkedItem.equals(itemName)) {
				roomItems.remove(checkedItem);
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
		return illumination == LightStatus.BRIGHT;

	}

	public static void setRoomDescriptionBundle(Locale currentLocale) {
		roomDescriptionBundle = ResourceBundle.getBundle("bundles.RoomDescriptions");
	}

	public Room(int newID, LightStatus newIllumination,
				ArrayList newRoomContainers, ArrayList newRoomItems, Map newadjacentRooms, String event) {		//should be protected

		this.ID = newID;
		this.illumination = newIllumination;
		this.roomContainers = newRoomContainers;
		this.roomItems = newRoomItems;
		this.adjacentRooms = newadjacentRooms;
		this.stepOnEvent = event;
	}

	public void doesItUnlock(Item item) throws IllegalItemUsageException {
		for(Direction tempDirection : adjacentRooms.keySet()){
			if(adjacentRooms.get(tempDirection).useItemToUnlock(item)){
				return;
			}
		}
		throw new IllegalItemUsageException();
	}

	public Room move(Direction direction) throws IllegalMovementException, HinderedRoomException {
		try{
			int movingRoomId = adjacentRooms.get(direction).moveToRoom();
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
		if (!this.isIlluminated() && !GameProgress.isPlayerIlluminated()) {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("noLight");
		} else {
			StringBuilder info = new StringBuilder();
			info.append(this.getAreaDescription());
			info.append(ResourceBundle.getBundle("bundles/engineOutText").getString("directions") + ":");
			for (Direction direction : adjacentRooms.keySet()) {
				info.append(" " + direction.toString() + " -");
			}
			info.append("\n");
			info.append(ResourceBundle.getBundle("bundles/engineOutText").getString("content") + ":");
			if(this.roomItems.size() > 0) {
				for (int i = 0; i < roomItems.size(); i++) {
					info.append(" " + roomItems.get(i).getItemName() + " -");
				}
			}
			if (this.roomContainers.size() > 0) {
				for (int i = 0; i < roomContainers.size(); i++) {
					info.append(" " +  roomContainers.get(i).getName()
							+ "(" + roomContainers.get(i).getState() + ")" + " -");
				}
			}
			return info.toString();
		}


	}

	public void startEvent() {
		if (GameEvent.startEvent(stepOnEvent)) {
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

	public Integer getID() {
		return ID;
	}

	public String directionInformation(Direction direction) {
	    try{
	        return adjacentRooms.get(direction).info();
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

	public Set getAdjacentDirections() {
		return adjacentRooms.keySet();
	}
}
