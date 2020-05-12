package items;

import java.io.Serializable;
import java.lang.Thread;
import java.util.Locale;
import java.util.ResourceBundle;

import engine.GameProgress;

public abstract class Item extends Thread implements Serializable{
	
	protected static ResourceBundle nameBundle = ResourceBundle.getBundle("items.ItemNames", Locale.getDefault());
	protected static ResourceBundle descriptionBundle = ResourceBundle.getBundle("items.ItemDescriptions", Locale.getDefault());
	protected static boolean reusable;
	
	public String getItemName() {
		nameBundle = ResourceBundle.getBundle("items.ItemNames", Locale.getDefault());
		return nameBundle.getString(this.getClass().getSimpleName());
	}
	
	public String getDescription() {
		descriptionBundle = ResourceBundle.getBundle("items.ItemDescriptions", Locale.getDefault());
		return descriptionBundle.getString(this.getClass().getSimpleName());
	}
	
	public abstract void use();
	
	public boolean equals(Item comparedItem) {
		return this.getClass().equals(comparedItem.getClass()) ? true : false;
	}
	
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
