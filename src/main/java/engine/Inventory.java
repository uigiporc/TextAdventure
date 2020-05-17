package engine;

import items.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
	private static ArrayList<Item> bag = new ArrayList<Item>();
	//private static short bagMaxItems;

	public static void addToBag(Item addedItem) {
		bag.add(addedItem);
		System.out.println(addedItem.getItemName() + "aggiunto allo zaino. N Item: " + bag.size());
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

	public static Item removeFromBag(String item) throws ItemNotFoundException {
		Iterator<Item> bagIterator = bag.iterator();

		while (bagIterator.hasNext()) {
			Item tempItem = bagIterator.next();
			if (tempItem.getItemName().equalsIgnoreCase(item)) {
				bag.remove(tempItem);
				return tempItem;
			}
		}
		throw new ItemNotFoundException();
	}

	public static void useItem(String itemToUseName) {
		if(bag.size() == 0){
		}
		for(int i = 0; i < bag.size(); i++) {
			if(bag.get(i).equals(itemToUseName)) {
				bag.get(i).use();
			}
		}
	}

	public static void print(){
		if(bag.size() == 0){
			System.out.println("Nessun oggetto nell'inventario");
		}
		else{
			for(int i = 0; i < bag.size(); i++){
				System.out.println(bag.get(i).getItemName());
				System.out.println(bag.get(i).getDescription());
			}
		}

	}
}

/*
 * This class will be the inventory of the player: it will be static (one inventory for game session).
 */
