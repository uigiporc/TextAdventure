package items;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class ItemResources_it extends ResourceBundle{

	@Override
	protected Object handleGetObject(String key) {
		String[] keyList = key.split(".");
		if(keyList[1].equalsIgnoreCase("name")) {
			switch(keyList[0]) {
			case "Sword":
				return "Spada";
			
			case "Torch":
				return "Torcia";
			
			case "Flashlight":
				return "Torcia elettrica";
				
			case "Bottle": 
				return "Ampolla";
			}
		}
		return null;
	}

	@Override
	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
