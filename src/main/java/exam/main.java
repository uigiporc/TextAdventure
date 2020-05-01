package exam;

import java.util.Scanner;
import engine.GameProgress;
import engine.Inventory;
import items.Item;
import items.Jar;
import items.Sword;
import map.Area;
import items.ItemNotFoundException;
import engine.Parser;

class Main {
	public static void main(String[] args) {
		//Item sword = new Sword();
		//Inventory.add(sword);
		//Item other = Inventory.get("sword");
		//System.out.println(other.getDescription());
		//Item jar = new Jar();
		//Inventory.add(jar);
		//Inventory.add(new Jar());
		try {
		System.out.println(Inventory.get("jar").getItemName());
		System.out.println(Inventory.get("jar").getDescription());
		} catch(ItemNotFoundException ex){
		    System.out.println(ex.getMessage());
		}
		GameProgress.nextArea();
		Area area = GameProgress.getCurrentArea();
		while(true) {
			System.out.println("io");
			Scanner scanner = new Scanner(System.in);
			Parser.parseCommand(scanner.nextLine().toUpperCase().trim());
		}
		//Area area = new Area();
		//System.out.println(area.getItemInArea("use").getItemName());
	}
}