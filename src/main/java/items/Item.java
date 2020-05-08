package items;

import java.lang.Thread;

import engine.GameProgress;

public abstract class Item extends Thread{
	protected String nameIT;
	protected String nameEN;
	protected String descriptionIT;
	protected String descriptionEN;
	protected boolean reusable;
	
	public String getItemName() {
		if(GameProgress.getLang().equals("IT")) {
			return nameIT;
		}
		else {
			return nameEN;
		}
		
	}
	
	public String getDescription() {
		if(GameProgress.getLang().equals("IT")) {
			return descriptionIT;
		}
		else {
			return descriptionEN;
		}
	}
	
	public abstract void use();
	
}

/*TODO:
 *Vasetto vuoto 
 *Chiave vecchia
 *Graffetta
 *Spada 
 *Bomba
 *Torcia
 *Fiaccola
 *Pala
 *Acqua
 *
 * 
*/
