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
import java.util.HashMap;
import java.util.Map;
import items.*;

public class Room {
	private int ID; 
	private RoomType setting;
	private String roomDescription;
	private LightStatus illumination;
	private Map<String, Item> roomItems = new HashMap<String, Item>();
	private Map<Direction, Room> adiacentRooms = new HashMap<Direction, Room>();
	private String help;
	
	public String getAreaDescription() {
		return roomDescription;
	}
	
	public LightStatus getIllumination() {
		return illumination;
	}
	
	public Item getItemInArea(String command) throws IllegalActionException{
		if(roomItems.containsKey(command)) {
			return roomItems.get(command);
		}
		else {
			throw new IllegalActionException();
		}
	}
	
	public void help() {
		if(illumination == LightStatus.BUIO) {
			System.out.println("È troppo buio. Prova ad illuminare la stanza prima.");
		}  
		else {
			System.out.println(help);
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
	
	public Room() {		//should be protected
		
		// Load from file the area with the given ID
		/* 
		illumination = LightStatus.ILLUMINATO;
		roomDescription = "Questa è l'aria iniziale. Enjoy.";
		roomItems.put("RACCOGLI", new Bottle());
		roomItems.put("S+", new Sword());
		adiacentRooms.put(Directions.NORD, this);*/
	}
}
