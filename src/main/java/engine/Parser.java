package engine;

import java.util.regex.Pattern;

import gui.UIHandler;
import items.Item;
import items.ItemNotFoundException;
import map.IllegalMovementException;
import map.RoomContainer;
import obstacles.HinderedRoomException;
import util.Command;
import util.Direction;
import util.IllegalActionException;

import java.util.regex.Matcher;

//group(0) = tutta la stringa; group(1) = azione; group(4) = oggetto/direzione
public class Parser {

	private static final String REGEX = "^([A-Z]{2,})((\\s[A-Z]{0,3})?(\\s[A-Z]{3,}))?(\\s[A-Z]{3,})*$";
	private static final Pattern regexPattern;

    static {
		regexPattern = Pattern.compile(REGEX);
	}

	public static void parseCommand(String inputCommand) {
        Matcher regexMatcher = regexPattern.matcher(inputCommand.toUpperCase().trim());
		Command commandToParse = null;
		String subjectToParse = null;
		try {
			UIHandler.printInFrame("> " + inputCommand + "\n");
			if(regexMatcher.matches()) {
				if(!(regexMatcher.group(1) == null) && !(regexMatcher.group(4) == null)) {
					if(Command.isCommand(regexMatcher.group(1))){
						commandToParse = Command.getCommand(regexMatcher.group(1));
						if(regexMatcher.group(5) != null) {
							subjectToParse = regexMatcher.group(4).trim() + regexMatcher.group(5);
						}else {
							subjectToParse = regexMatcher.group(4).trim();
						}
						handleCommandAndSubject(commandToParse, subjectToParse);
					} else {
						throw new IllegalCommandException();
					}
				} else if (!(regexMatcher.group(1) == null) && (regexMatcher.group(4) == null)){
					subjectToParse = regexMatcher.group(1);
					if(Direction.isDirection(subjectToParse)){
						handleCommandAndSubject(Command.GO, subjectToParse);
					} else if (Item.isItem(subjectToParse)){
						handleCommandAndSubject(Command.USE, subjectToParse);
					} else if(Command.isCommand(subjectToParse)){
						handleCommandAndSubject(Command.getCommand(subjectToParse), null);
					} else {
						throw new IllegalCommandException();
					}
				}
			} else if (!inputCommand.equals("")){
				throw new IllegalCommandException();
			}
		} catch (IllegalActionException | ItemNotFoundException | IllegalMovementException | IllegalCommandException | HinderedRoomException e) {
			UIHandler.printInFrame(e.getMessage() + "\n");
		}
	}

	private static void handleCommandAndSubject(Command userCommand, String inputSubject) throws IllegalActionException, ItemNotFoundException, IllegalMovementException, IllegalCommandException, HinderedRoomException {
		if(inputSubject == null){
			switch (userCommand){
				case BAG: {
					GameProgress.getBag().print();
					break;
				}
				case OBSERVE: {
					UIHandler.printInFrame(GameProgress.getCurrentRoom().roomInformations() + "\n");
					break;
				}
				default: {
					throw new IllegalCommandException();
				}
			}
		}
		if(Item.isItem(inputSubject)) {
			switch(userCommand) {
				case USE: {
					GameProgress.getBag().useItem(inputSubject);
					break;
				}
				case THROW: {
					GameProgress.dropItem(GameProgress.getBag().removeFromBag(inputSubject));
					break;
				}
				case GET: {
					Item getItem = GameProgress.getCurrentRoom().getItemInArea(inputSubject);
					GameProgress.getBag().addToBag(getItem);
					break;
				}
				case OBSERVE: {
					UIHandler.printInFrame(GameProgress.getBag().getFromBag(inputSubject).getDescription() + "\n");
					break;
				}
				default: {
					throw new IllegalCommandException();
				}
			}
		}
		else if(Direction.isDirection(inputSubject)) {
			switch(userCommand) {
				case GO: {
					GameProgress.moveRoom(Direction.getDirection(inputSubject));
					break;
				}
				case OBSERVE: {
					UIHandler.printInFrame(GameProgress.getCurrentRoom()
							.directionInformation(Direction.getDirection(inputSubject)) + "\n");
					break;
				}
				default: {
					throw new IllegalCommandException();
				}
			}
		} else if (RoomContainer.isContainer(inputSubject)){
			switch (userCommand) {
				case OPEN: {
					GameProgress.getCurrentRoom().openContainer(RoomContainer.getContainer(inputSubject));
					break;
				}
				case CLOSE: {
					GameProgress.getCurrentRoom().closeContainer(RoomContainer.getContainer(inputSubject));
					break;
				}
				default: {
					throw new IllegalActionException();
				}
			}
		} else {
			throw new IllegalCommandException();
		}
	}
}
