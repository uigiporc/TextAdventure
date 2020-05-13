package items;

import engine.GameOver;

public class Bomb extends Item {
	
	private Thread bombExplosion = new Thread(this);
	
	static{
		/*nameIT = "Bomba";
		descriptionIT = "Questo strumento è altamente esplosivo. Può essere usato per far saltare in aria ostacoli"
				+ "resistenti. STA ATTENTO: potresti saltare in aria anche tu";
		nameEN = "Bomb";
		descriptionEN = "This item is highly explosive. It can be used to blow up a tough obstacle in just 5 seconds."
				+ "BE CAREFUL: you can blow up too.";*/
		reusable = false;
	}
	
	public void use() {
		bombExplosion.start();
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Bomba innescata: 5 secondi all'esplosione");
			Thread.sleep(5000);
			System.out.println("BOOM");
			new GameOver();
		} catch (InterruptedException e) {
			//This thread should never be interrupted. 
		}
	}
	
}
