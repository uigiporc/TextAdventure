package items;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.Thread;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import items.*;

import engine.GameProgress;

public abstract class Item extends Thread implements Serializable{
	
	transient protected static ResourceBundle nameBundle = ResourceBundle.getBundle("items.ItemNames", Locale.getDefault());
	transient protected static ResourceBundle descriptionBundle = ResourceBundle.getBundle("items.ItemDescriptions", Locale.getDefault());
	transient protected static boolean reusable;
	
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
		return this.getClass().equals(comparedItem.getClass());
	}
	
	public boolean equals(String comparedString) {		
		return this.getItemName().equalsIgnoreCase(comparedString);
	}
	
	public static Item getItem(String inputItem) {
		try {
			for(String searchingString : nameBundle.keySet()){
				if(nameBundle.getString(searchingString).equalsIgnoreCase(inputItem)) {
					Class<?> foundClass = Class.forName(Item.class.getPackageName()+ "." + searchingString);
					return (Item) foundClass.getConstructor().newInstance();
				}
			}
			return null;		//Item not found.
		}
		catch(Exception ex) {
			//Item not found.
			return null;
		}
	}
	
	public static boolean isItem(String inputItem) {
		try {
			System.out.println(inputItem);
			for(String searchingString : nameBundle.keySet()){
				if(nameBundle.getString(searchingString).equalsIgnoreCase(inputItem)) {
					return true;
				}
			}
			return false;		//Item not found.
		}
		catch(Exception ex) {
			//Item not found.
			return false;
		}
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
