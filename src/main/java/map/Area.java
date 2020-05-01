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

public class Area {
	//private int ID; 
	//private String areaName;
	private String areaDescription;
	private LightStatus illumination;
	private Map<String, Item> areaItems = new HashMap<String, Item>();
	private Map<Directions, Area> adiacentAreas = new HashMap<Directions, Area>();
	private String help;
	
	public String getAreaDescription() {
		return areaDescription;
	}
	
	public LightStatus getIllumination() {
		return illumination;
	}
	
	public Item getItemInArea(String command) throws IllegalActionException{
		if(areaItems.containsKey(command)) {
			return areaItems.get(command);
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
	
	public Area() {		//should be protected
		
		// Load from file the area with the given ID
		 
		illumination = LightStatus.ILLUMINATO;
		areaDescription = "Questa è l'aria iniziale. Enjoy.";
		areaItems.put("RACCOGLI", new Jar());
		areaItems.put("S+", new Sword());
		adiacentAreas.put(Directions.NORD, this);
	}
}
