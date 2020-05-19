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

	transient protected static ResourceBundle nameBundle = null;
	transient protected static ResourceBundle descriptionBundle = null;
	transient protected static boolean reusable;

	public String getItemName() {
		return nameBundle.getString(this.getClass().getSimpleName());
	}

	public String getDescription() {
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

	public static void setNameBundle(Locale currentLocale) {
		nameBundle = ResourceBundle.getBundle("bundles.ItemNames", currentLocale);
	}

	public static void setDescriptionBundle(Locale currentLocale) {
		descriptionBundle = ResourceBundle.getBundle("bundles.ItemDescriptions", currentLocale);
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
