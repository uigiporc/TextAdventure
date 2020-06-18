package items;

import engine.GameEvent;

public class Sword extends Item {
	
	public void use() {
		GameEvent.startEvent("useSword");
	}
}
