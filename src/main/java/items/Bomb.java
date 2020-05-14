package items;

import engine.GameOver;
import engine.GameProgress;
import map.Room;

public class Bomb extends Item {
	
	private Thread bombExplosion = new Thread(this);
	
	static{
		reusable = false;
	}
	
	public void use() {
		bombExplosion.start();
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Bomba innescata: 8 secondi all'esplosione");
			Room roomWithBomb = GameProgress.getCurrentRoom();
			Thread.sleep(8000);
			System.out.println("BOOM");
			/*if(roomWithBomb.equals(GameProgress.getCurrentRoom())) {
				new GameOver();
			}*/
		} catch (InterruptedException e) {
			//This thread should never be interrupted. 
		}
	}
	
}
