package items;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class Item extends Thread implements Serializable {

	private static final long serialVersionUID = -8481482740902320036L;
	protected static ThreadGroup itemUsedThreads = new ThreadGroup("items");
	transient protected static ResourceBundle nameBundle = null;
	transient protected static ResourceBundle descriptionBundle = null;

	public String getItemName() {
		return nameBundle.getString(this.getClass().getSimpleName());
	}

	public String getDescription() {
		return descriptionBundle.getString(this.getClass().getSimpleName());
	}

	public abstract void use();

	public boolean isSameItem(Item comparedItem) {
		return this.getClass().equals(comparedItem.getClass());
	}

	public boolean isSameItem(String comparedString) {
		return this.getItemName().equalsIgnoreCase(comparedString);
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

	public static void interruptUsages() {
		itemUsedThreads.interrupt();
	}
}