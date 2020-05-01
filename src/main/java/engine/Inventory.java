package engine;

import items.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
	private static ArrayList<Item> bag = new ArrayList<Item>();
	//private static short bagMaxItems;
	
	public static void add(Item addedItem) {
		bag.add(addedItem);
	}

	public static Item get(String item) throws ItemNotFoundException { 
		Iterator<Item> bagIterator = bag.iterator();

		while (bagIterator.hasNext()) {
			Item tempItem = bagIterator.next();
			if (tempItem.getItemName().equalsIgnoreCase(item)) {

				return tempItem;
			}
		}
		throw new ItemNotFoundException();
	}
}

/*
 * This class will be the inventory of the player: it will be static (one inventory for game session).
 */