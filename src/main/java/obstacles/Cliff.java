package obstacles;

import engine.GameOver;

public abstract class Cliff extends Obstacle{

	@Override
	public boolean move() {
		if(!passed) {
			new GameOver(/*Cliff.class*/);
		}
		return false;
	}

}
