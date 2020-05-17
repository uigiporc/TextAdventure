package exam;


import java.util.Scanner;
import engine.GameProgress;
import engine.Inventory;
import items.Item;
import items.Bomb;
import map.Room;
import util.ActionAliasesBuilder;
import util.Command;
import util.Direction;
import items.ItemNotFoundException;
import items.Sword;
import engine.Parser;
import engine.ResourceHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import exam.enumtest;

public class main {

	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void main(String[] args) {
		System.out.println("Gioco avviato.");
		ResourceHandler.loadResources();
		GameProgress.nextRoom();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			Parser.parseCommand(scanner.nextLine().toUpperCase().trim());
		}
	}
}
