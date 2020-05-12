package util;

import items.Item;
import engine.GameProgress;

public class Torch extends Item{
	static{
		reusable = false;
	}
	
	private Thread torchTurnOn = new Thread(this);
	
	public void use() {
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
		}
	}
}
