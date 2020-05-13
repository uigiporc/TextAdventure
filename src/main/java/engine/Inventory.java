package engine;

import items.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
	private static ArrayList<Item> bag = new ArrayList<Item>();
	//private static short bagMaxItems;
	
	public static void addToBag(Item addedItem) {
		bag.add(addedItem);
	}

	public static Item getFromBag(String item) throws ItemNotFoundException { 
		Iterator<Item> bagIterator = bag.iterator();

		while (bagIterator.hasNext()) {
			Item tempItem = bagIterator.next();
			if (tempItem.getItemName().equalsIgnoreCase(item)) {

				return tempItem;
			}
		}
		throw new ItemNotFoundException();
	}
	
	public static void useItem(String itemToUseName) {
		bag.add(new Bomb());
		for(Item itemChecked : bag) {
			if(itemChecked.equals(itemToUseName)) {
				itemChecked.use();
			}
		}
	}
}

/*
 * This class will be the inventory of the player: it will be static (one inventory for game session).
 */