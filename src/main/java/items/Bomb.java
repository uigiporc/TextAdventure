package items;

import engine.GameOver;
import engine.GameProgress;
import map.Room;

public class Bomb extends Item {

	static{
		reusable = false;
	}

	public void use() {
		System.out.println("ci siamo?");
		Thread bombExplosion = new Thread(this);
		bombExplosion.start();
	}

	@Override
	public void run() {
		System.out.println("nani");
		try {
			System.out.println("Bomba innescata: 8 secondi all'esplosione");
			Room roomWithBomb = GameProgress.getCurrentRoom();
			Thread.sleep(8000);
			System.out.println("BOOM");
			if(roomWithBomb.equals(GameProgress.getCurrentRoom())) {
				new GameOver();
			}
		} catch (InterruptedException e) {
			System.out.println("nani the fuck");
			//This thread should never be interrupted.
		}
	}

}
