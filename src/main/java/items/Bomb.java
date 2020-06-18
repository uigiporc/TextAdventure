package items;

import engine.GameProgress;
import gui.UIHandler;
import map.Room;
import obstacles.IllegalItemUsageException;

import java.util.ResourceBundle;

public class Bomb extends Item {

	private static final long serialVersionUID = 892007324576614627L;

	public void use() {
		Thread bombExplosion = new Thread(this);
		bombExplosion.start();
	}

	@Override
	public void run() {
		try {
			UIHandler.printInFrame(ResourceBundle.getBundle("bundles/itemsUsage").getString("bomb"));
			Room roomWithBomb = GameProgress.getCurrentRoom();
			Thread.sleep(8000);
			UIHandler.printInFrame("BOOM\n");
			if (roomWithBomb.equals(GameProgress.getCurrentRoom())) {
				GameProgress.gameOver();
			} else {
				roomWithBomb.doesItUnlock(this);
			}

		} catch (InterruptedException e) {
			//This thread should never be interrupted.
		} catch (IllegalItemUsageException e) {
			//Nothing happens.
		} finally {
			GameProgress.getBag().removeFromBag(this.getItemName());
		}
	}
}
