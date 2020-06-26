/**
 * @author Luigi Porcelli, Computer Science
 */

package map;

import engine.GameEvent;
import engine.GameProgress;
import engine.MapLoader;
import gui.UIHandler;
import items.Item;
import obstacles.HinderedRoomException;
import obstacles.IllegalItemUsageException;
import util.Direction;
import util.IllegalActionException;
import util.LightStatus;

import java.io.Serializable;
import java.util.*;

public class Room implements Serializable {

	private static final long serialVersionUID = 2512059451189906531L;
	private Integer ID;
	transient private static ResourceBundle roomDescriptionBundle = null;
	private LightStatus illumination;
	private List<RoomContainer> roomContainers;
	private List<Item> roomItems = new ArrayList<Item>();
	private Map<Direction, RoomTransition> adjacentRooms;
	private String stepOnEvent;

	public String getAreaDescription() {
		return roomDescriptionBundle.getString(ID.toString());
	}

	public Item getItemInArea(String itemName) throws IllegalActionException{
		for(Item checkedItem : roomItems) {
			if(checkedItem.isSameItem(itemName)) {
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
		UIHandler.printInFrame(ResourceBundle.getBundle("bundles/engineOutText").getString("dropItem") +
				": " + droppedItem.getItemName() + "\n");
	}

	public boolean isIlluminated() {
		return illumination == LightStatus.BRIGHT;

	}

	public static void setRoomDescriptionBundle(Locale currentLocale) {
		roomDescriptionBundle = ResourceBundle.getBundle("bundles.RoomDescriptions", currentLocale);
	}

	public Room(int newID, LightStatus newIllumination,
				List<RoomContainer> newRoomContainers, List<Item> newRoomItems,
				Map<Direction, RoomTransition> newAdjacentRooms, String event) {		//should be protected

		this.ID = newID;
		this.illumination = newIllumination;
		this.roomContainers = newRoomContainers;
		this.roomItems = newRoomItems;
		this.adjacentRooms = newAdjacentRooms;
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

	public String roomInformation() {
		if (!this.isIlluminated() && !GameProgress.isPlayerIlluminated()) {
			return ResourceBundle.getBundle("bundles/engineOutText").getString("noLight");
		} else {
			StringBuilder info = new StringBuilder();
			info.append(this.getAreaDescription());
			info.append(ResourceBundle.getBundle("bundles/engineOutText").getString("directions") + ":");
			for (Direction direction : adjacentRooms.keySet()) {
				info.append(" " + direction.getName() + " -");
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
							+ "(" + roomContainers.get(i).getState().getName() + ")" + " -");
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

	/**
	 * Gets a set of all the room's possible directions, and returns a new HashSet.
	 * We don't return the room's possible directions because you could delete from that set,
	 * causing the impossibility to go that direction.
	 * @return Set of adjacent directions
	 */
	public Set getAdjacentDirections() {
		return new HashSet(adjacentRooms.keySet());
	}
}
