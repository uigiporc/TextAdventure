package obstacles;

public abstract class Obstacle {
	protected boolean passed;
	
	public boolean isObstaclePassed() {
		return passed;
	}
	
	public abstract boolean move();
}
