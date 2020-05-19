package items;

import engine.GameOver;
import engine.GameProgress;
import engine.Inventory;
import map.Room;

public class Bomb extends Item {

	private static final long serialVersionUID = 892007324576614627L;

	static{
		reusable = false;
	}

	public void use() {
		Thread bombExplosion = new Thread(this);
		bombExplosion.start();
	}

	@Override
	public void run() {
		try {
			System.out.println("Bomba innescata: 8 secondi all'esplosione");
			Room roomWithBomb = GameProgress.getCurrentRoom();
			Thread.sleep(8000);
			System.out.println("BOOM");
			if (roomWithBomb.equals(GameProgress.getCurrentRoom())) {
				new GameOver();
			}
			Inventory.removeFromBag(this.getItemName());
		} catch (InterruptedException e) {
			//This thread should never be interrupted.
		}
	}
}
