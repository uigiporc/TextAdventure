package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.Action;

public enum Command {
    GET,
    THROW,
    USE,
    JUMP,
    DROP,
    EAT,
    PUNCH,
    GO,
    BAG,
    OBSERVE,
    SEARCH;
	
	private static Map<String[], Command> aliases;
	private static ObjectInputStream commandAliasesStream = null;
	
	/*public static boolean equals(String s) throws IllegalActionException {
		for(Command temp : Command.values()) {
			if(s.equals(temp.toString())) {
				return true;
			}
		}
		throw new IllegalActionException();
	}*/
	
	public static boolean isCommand(String stringToCheck) {
		for(String[] commandAlias : aliases.keySet()) {
			for(String checkingString : commandAlias) {
				if(stringToCheck.equalsIgnoreCase(checkingString)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static void load() {
		File aliasFile = null;
		aliases = new HashMap<String[], Command>();
		
		try {
			aliasFile = new File("src/main/java/util/ActionsAliases_" + Locale.getDefault().getLanguage() + ".properties");
			commandAliasesStream = new ObjectInputStream(new FileInputStream(aliasFile));
			aliases = (HashMap<String[], Command>) commandAliasesStream.readObject();
		} catch (FileNotFoundException e) {
			//FATAL ERROR: It means that you can't use commands, which means you can't play.
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			//We are absolutely sure that the class IS actually found.
		} finally {
			try {
				commandAliasesStream.close();
			} catch (IOException e) {
				
			}
		}
	}
}
