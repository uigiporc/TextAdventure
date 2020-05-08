package exam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;
import engine.GameProgress;
import engine.Inventory;
import items.Item;
import items.Bomb;
import items.Bottle;
import items.Sword;
import map.Room;
import items.ItemNotFoundException;
import engine.Parser;

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
		Room area = GameProgress.getCurrentArea();
		
		Item bomb = new Bomb();
		bomb.use();
		int i=0;
		while(i<5) {
			System.out.println("Inizio scanner");
			Scanner scanner = new Scanner(System.in);
			Parser.parseCommand(scanner.nextLine().toUpperCase().trim());
			i++;
		}
	}
}