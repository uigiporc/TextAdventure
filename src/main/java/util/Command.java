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

import engine.ResourceHandler;

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
					Command command = commandAliases.get(commandAliasElement);
					return command;
				}
			}
		}
		return null;
	}
	
	public static Map getCommandAliasesMap() {
		return commandAliases;
	}
	
	public static void initAliases(String resourceFolderPath, Locale currentLocale) throws FileNotFoundException{
		String commandAliasesFilePath = resourceFolderPath + "/CommandAliases_" + currentLocale.getLanguage() + ".properties"; 
		commandAliases = (Map<String[], Command>) ResourceHandler.<Command>load(commandAliasesFilePath);
	}
}
