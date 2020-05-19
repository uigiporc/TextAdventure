package util;

import engine.Inventory;
import items.Item;
import engine.GameProgress;
import items.ItemNotFoundException;

public class Torch extends Item{
	static{
		reusable = false;
	}

	public void use() {
		Thread torchTurnOn = new Thread(this);
		torchTurnOn.start();
	}

	@Override
	public void run() {
		GameProgress.setPlayerLight(LightStatus.ILLUMINATO);
		try {
			Thread.sleep(5_000);
		} catch (InterruptedException e) {
			//If the thread is interrupted, we just go ahead and set the light back to normal.
		} finally {
			System.out.println("La torcia si è spenta");
			GameProgress.resetPlayerLight();
			Inventory.removeFromBag(this.getItemName());
		}
	}
}
