package util;

import engine.ResourceHandler;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public enum Command {
    GET,
    THROW,
    USE,
    JUMP,
    EAT,
    PUNCH,
    GO,
    BAG,
	OPEN,
	CLOSE,
    OBSERVE;

	private static Map<String[], Command> commandAliases;

	public static boolean isCommand(String stringToCheck) {
		for(String[] commandAliasElement : commandAliases.keySet()) {
			for(String checkingString : commandAliasElement) {
				if(stringToCheck.equalsIgnoreCase(checkingString)) {
					return true;
				}
			}
		}
		return false;
	}

	public static Command getCommand(String stringToCheck) {
		for(String[] commandAliasElement : commandAliases.keySet()) {
			for(String checkingString : commandAliasElement) {
				if(stringToCheck.equalsIgnoreCase(checkingString)) {
					return commandAliases.get(commandAliasElement);
				}
			}
		}
		return null;
	}

	public static void initAliases(String resourceFolderPath, Locale currentLocale) throws IOException {
		String commandAliasesFilePath = resourceFolderPath + "CommandAliases_" + currentLocale.getLanguage() + ".dat";
		commandAliases = ResourceHandler.load(commandAliasesFilePath);
	}
}
