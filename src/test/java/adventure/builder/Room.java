/**
 * @author Luigi Porcelli, Computer Science
 */

package adventure.builder;

import map.IllegalMovementException;
import map.RoomContainer;
import map.RoomTransition;
import map.RoomType;
import util.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import items.*;
import engine.GameProgress;
import util.Command;

public class Room implements Serializable {
	private Integer ID;
	private RoomType setting;
	transient private static ResourceBundle roomDescriptionBundle = null;
	private LightStatus illumination;
	private Map<Command, RoomContainer> roomContainers= new HashMap<Command, RoomContainer>();
	private Map<Command, ArrayList<Item>> roomItems = new HashMap<Command, ArrayList<Item>>();
	private Map<Direction, RoomTransition> adiacentRooms = new HashMap<Direction, RoomTransition>();
	transient private static ResourceBundle roomHelpBundle = null;



	public LightStatus getIllumination() {
		return illumination;
	}

	public Item getItemInArea(Command command) throws IllegalActionException{
		if(roomItems.containsKey(command)) {
			return roomItems.get(command).get(0);
		}
		else {
			throw new IllegalActionException();
		}
	}

	public void move(String direction) throws IllegalMovementException {

	}

	protected void setLight(LightStatus light) {
		illumination = light;
	}

	protected RoomType getSetting() {
		return setting;
	}

	protected Room(int newID, RoomType newSetting, LightStatus newIllumination,
				Map newRoomContainers, Map newRoomItems, Map newAdiacentRooms) {		//should be protected

		this.ID = newID;
		this.setting = newSetting;
		this.illumination = newIllumination;
		this.roomContainers= newRoomContainers;
		this.roomItems = newRoomItems;
		this.adiacentRooms = newAdiacentRooms;

		// Load from file the area with the given ID
		/*
		illumination = LightStatus.ILLUMINATO;
		roomDescription = "Questa è l'aria iniziale. Enjoy.";
		roomItems.put("RACCOGLI", new Bottle());
		roomItems.put("S+", new Sword());
		adiacentRooms.put(Directions.NORD, this);*/
	}
}
