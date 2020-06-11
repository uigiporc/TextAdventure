package util;

import engine.Inventory;
import gui.UIHandler;
import items.Item;
import engine.GameProgress;
import items.ItemNotFoundException;

import java.util.ResourceBundle;

public class Torch extends Item{

	private static final long serialVersionUID = -317659910826339075L;

	public void use() {
		Thread torchTurnOn = new Thread(this);
		torchTurnOn.start();
	}

	@Override
	public void run() {
		GameProgress.setPlayerLight(LightStatus.ILLUMINATO);
		UIHandler.printInFrame(ResourceBundle.getBundle("bundles/itemsUsage").getString("torchOn"));
		try {
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			//If the thread is interrupted, we just go ahead and set the light back to normal.
		} finally {
			UIHandler.printInFrame(ResourceBundle.getBundle("bundles/itemsUsage").getString("torchOff"));
			GameProgress.resetPlayerLight();
			GameProgress.getBag().removeFromBag(this.getItemName());
		}
	}
}
