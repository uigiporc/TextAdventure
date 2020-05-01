package engine;

import java.util.regex.Pattern;

import items.Item;
import items.Sword;
import map.Area;
import util.Commands;
import java.util.regex.Matcher;

//group(0) = tutta la stringa; group(1) = azione; group(4) = oggetto/direzione
public class Parser {
	
	public static void parseCommand(String inputCommand) {
		String regex = "^([A-Z]{3,})((\\s[A-Z]{1,3})?(\\s[A-Z]{4,}))?$";
		Pattern regexPattern = Pattern.compile(regex);
		Matcher matcher = regexPattern.matcher(inputCommand);
		
		if(matcher.matches()) {
			if(!matcher.group(0).isEmpty()) {
				System.out.println(matcher.group(1));
				try {
					
					if(!matcher.group(1).isEmpty()) {
						Commands action = Commands.toCommand(matcher.group(1));
						System.out.println("that's true bitch: " + action);
						//switch on the command, if it's USA call the inventory, if it's
						//OSSERVA call the areaItems etc.
						Area area = new Area();
						Item item = area.getItemInArea(matcher.group(1));
						System.out.println(item.getItemName());
					}
					else {
						System.out.println("lol");
						Inventory.add(new Sword());
						Item fetchedItem = Inventory.get(matcher.group(4));
						System.out.println(fetchedItem.getItemName());
					}
					
				} catch (Exception ex){
					System.out.println(ex.getMessage());
				}
			}
			
		}
		
	}
}
