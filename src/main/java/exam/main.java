package exam;


import java.util.Scanner;
import engine.GameProgress;
import engine.Inventory;
import items.Item;
import items.Bomb;
import map.Room;
import util.ActionAliasesBuilder;
import util.Command;
import items.ItemNotFoundException;
import items.Sword;
import engine.Parser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

class Main {
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		int i=0;
		while(i<5) {
			Scanner scanner = new Scanner(System.in);
			Parser.parseCommand(scanner.nextLine().toUpperCase().trim());
			i++;
		}
	}
}