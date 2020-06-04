package engine;

import java.util.regex.Pattern;
import items.Item;
import items.ItemNotFoundException;
import items.Sword;
import map.IllegalMovementException;
import map.Room;
import util.Command;
import util.Direction;
import util.IllegalActionException;

import java.util.regex.Matcher;

//group(0) = tutta la stringa; group(1) = azione; group(4) = oggetto/direzione
public class Parser {

	public static void parseCommand(String inputCommand) {
		String regex = "^([A-Z]{2,})((\\s[A-Z]{1,3})?(\\s[A-Z]{4,}))?$";
		Pattern regexPattern = Pattern.compile(regex);
		Matcher matcher = regexPattern.matcher(inputCommand);
		Command commandToParse = null;
		String subjectToParse = null;

		if(matcher.matches()) {
			if(!matcher.group(0).isEmpty()) {
				try {

					if(!(matcher.group(1) == null) && !(matcher.group(4) == null)) {
						if(Command.isCommand(matcher.group(1))){
							commandToParse = Command.getCommand(matcher.group(1));
							subjectToParse = matcher.group(4).trim();
							handleCommandAndSubject(commandToParse, subjectToParse);
						}

					}
					else if (!(matcher.group(1) == null) && (matcher.group(4) == null)){
						subjectToParse = matcher.group(1);
						if(Direction.isDirection(subjectToParse)){
							handleCommandAndSubject(Command.GO, subjectToParse);
						} else if (Item.isItem(subjectToParse)){
							handleCommandAndSubject(Command.USE, subjectToParse);
						} else if(Command.isCommand(subjectToParse)){
							handleCommandAndSubject(Command.getCommand(subjectToParse), null);
						}
					}
				} catch (IllegalActionException | ItemNotFoundException | IllegalMovementException e) {
					System.out.println(e.getMessage());
				}
			}

		}
	}

	private static void handleCommandAndSubject(Command userCommand, String inputSubject) throws IllegalActionException, ItemNotFoundException, IllegalMovementException {
		if(inputSubject == null){
			switch (userCommand){
				case BAG: {
					Inventory.print();
					break;
				}
				default: {
					throw new IllegalActionException();
				}
			}
		}
		if(Item.isItem(inputSubject)) {
			switch(userCommand) {
			case USE: {
				Inventory.useItem(inputSubject);
				break;
			}
			case THROW: {
				GameProgress.dropItem(Inventory.removeFromBag(inputSubject));
				break;
			}
			case GET: {
				Item getItem = GameProgress.getCurrentRoom().getItemInArea(userCommand, inputSubject);
				Inventory.addToBag(getItem);
				break;
			}
			case EAT: {
				break;
			}
			}
		}
		else if(Direction.isDirection(inputSubject)) {
			switch(userCommand) {
			case GO: {
				GameProgress.moveRoom(Direction.getDirection(inputSubject));
				break;
			}
				default: {
					throw new IllegalActionException();
				}
			}
		}
	}
}
