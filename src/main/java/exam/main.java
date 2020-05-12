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
import java.util.Locale;
import java.util.ResourceBundle;

class Main {
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	
	public static void main(String[] args) {
		
		try {
		System.out.println(Inventory.get("jar").getItemName());
		System.out.println(Inventory.get("jar").getDescription());
		} catch(ItemNotFoundException ex){
		    System.out.println(ex.getMessage());
		}
		GameProgress.nextArea();
		//Room area = GameProgress.getCurrentArea();
		
		Item bomb = new Bomb();
		Item sword = new Sword();
		if(!bomb.equals(sword)) {
			System.out.println("va bene");
		}
		else {
			System.out.println("bruh");
		}
		ActionAliasesBuilder.build();
		Command.load();
		
		if(Command.isCommand("USA")) {
			System.out.println("You did it, you crazy son of a bitch you did it");
		}
		else {
			System.out.println("sad song in the background");
		}
		
		/*System.out.println(Locale.getDefault());
		System.out.println(bomb.getItemName());
		System.out.println(bomb.getDescription());
		Locale.setDefault(Locale.US);;
		System.out.println(Locale.getDefault());
		System.out.println(bomb.getItemName());
		System.out.println(bomb.getDescription());
		Locale.setDefault(Locale.UK);
		System.out.println(Locale.getDefault());
		System.out.println(bomb.getItemName());
		System.out.println(bomb.getDescription());
		Locale.setDefault(Locale.CANADA);
		System.out.println(Locale.getDefault());
		System.out.println(bomb.getItemName());
		System.out.println(bomb.getDescription());
		System.out.println(Locale.getDefault().getLanguage());*/
		
		
		int i=0;
		while(i<5) {
			Scanner scanner = new Scanner(System.in);
			Parser.parseCommand(scanner.nextLine().toUpperCase().trim());
			i++;
		}
	}
}