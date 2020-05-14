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

class Main {
	
	public static void main(String[] args) {
		int a = enumtest.a.getCode();
		System.out.println(enumtest.h.getCode());
		System.out.println(enumtest.getEnumCode("h"));
		
		
		System.out.println(a);
		ResourceHandler.loadResources();
		if(Direction.isDirection("RIGHT")) {
			System.out.println("meme");
		}
		Command.isCommand("USA");
		System.out.println(new Bomb().getItemName());
		
		int i=0;
		while(i<5) {
			Scanner scanner = new Scanner(System.in);
			Parser.parseCommand(scanner.nextLine().toUpperCase().trim());
			i++;
		}
	}
}