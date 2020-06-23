package items;

import engine.Inventory;
import gui.UIHandler;
import engine.GameProgress;
import util.LightStatus;

import java.util.ResourceBundle;

public class Torch extends Item{

	private static final long serialVersionUID = -317659910826339075L;

	public void use() {
		Thread torchTurnOn = new Thread(this);
		torchTurnOn.start();
	}

	@Override
	public void run() {
		GameProgress.setPlayerLight(LightStatus.BRIGHT);
		UIHandler.printInFrame(ResourceBundle.getBundle("bundles/itemsUsage").getString("torchOn"));
		try {
			Thread.sleep(180_000);
		} catch (InterruptedException e) {
			//If the thread is interrupted, we act like if the torch ran out, entering the finally block.
			Thread.currentThread().interrupt();
		} finally {
			UIHandler.printInFrame(ResourceBundle.getBundle("bundles/itemsUsage").getString("torchOff"));
			GameProgress.resetPlayerLight();
			Inventory.getInventory().removeFromBag(this.getItemName());
		}
	}
}
