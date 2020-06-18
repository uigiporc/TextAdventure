package engine;

import gui.UIHandler;
import items.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class Inventory implements Serializable {
	private static final Inventory inventory = new Inventory();
	private ArrayList<Item> bag;
	private static final short bagMaxItems = 3;

	public void addToBag(Item addedItem) {
		bag.add(addedItem);
		UIHandler.printInFrame(addedItem.getItemName() + " "+ ResourceBundle.getBundle("bundles/engineOutText")
				.getString("itemIntoInventory") + "\n");
	}

	public Item getFromBag(String item) throws ItemNotFoundException {
		Iterator<Item> bagIterator = bag.iterator();

		while (bagIterator.hasNext()) {
			Item tempItem = bagIterator.next();
			if (tempItem.getItemName().equalsIgnoreCase(item)) {

				return tempItem;
			}
		}
		throw new ItemNotFoundException();
	}

	public Item removeFromBag(String item) {
		Iterator<Item> bagIterator = bag.iterator();

		while (bagIterator.hasNext()) {
			Item tempItem = bagIterator.next();
			if (tempItem.getItemName().equalsIgnoreCase(item)) {
				bag.remove(tempItem);
				return tempItem;
			}
		}
		return null;
	}

	public void useItem(String itemToUseName) throws ItemNotFoundException {
		getFromBag(itemToUseName).use();
	}

	public void print(){
		if(bag.size() == 0){
			UIHandler.printInFrame(ResourceBundle.getBundle("bundles/engineOutText").getString("emptyInventory") + "\n");
		}
		else{
			UIHandler.printInFrame(ResourceBundle.getBundle("bundles/engineOutText").getString("bagContent") + ":\n");
			for(int i = 0; i < bag.size(); i++){
				UIHandler.printInFrame(i+1 + ". " + bag.get(i).getItemName() + "\n");
			}
		}
	}

	private Inventory() {
		bag = new ArrayList<>();
	}

	public static Inventory getInventory() {
		return inventory;
	}
}

/*
 * This class will be the inventory of the player: it will be static (one inventory for game session).
 */
