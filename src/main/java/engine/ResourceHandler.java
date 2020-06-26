package engine;

import items.Item;
import map.Room;
import map.RoomContainer;
import util.Command;
import util.Direction;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class ResourceHandler {
	static {
		try {
			MapLoader.loadMap(new File(ResourceHandler.class.getClassLoader().getResource("bin/intotheunknown.dat").getPath()), 0);
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("bundles/UIbundle").getString("errorMessage"), "", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void loadResources() {
		String resourceFolderPath = ResourceHandler.class.getClassLoader().getResource("bin/").getPath();
		try {
			//Get current JVM locale to initialize resources
			Locale currentLocale;
			currentLocale = Locale.getDefault();

			//Load Items name and description.
			Item.setDescriptionBundle(currentLocale);
			Item.setNameBundle(currentLocale);

			//Load room descriptions string
			Room.setRoomDescriptionBundle(currentLocale);

			//Load Commands and Directions aliases.
			Command.initAliases(resourceFolderPath, currentLocale);
			Direction.initAliases(resourceFolderPath, currentLocale);

			RoomContainer.setNameBundle(currentLocale);
			GameEvent.setEventText(currentLocale);
		}catch(MissingResourceException | IOException ex) {
			JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("bundles/UIbundle").getString("errorMessage"), "", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static <T> Map<String[], T> load(String filePath) throws IOException{
		ObjectInputStream aliasesStream = null;
		File aliasFile = null;
		Map <String[], T>loadingMap = null;

		try {
			aliasFile = new File(filePath);
			aliasesStream = new ObjectInputStream(new FileInputStream(aliasFile));
			loadingMap = (Map<String[], T>) aliasesStream.readObject();
			return loadingMap;

		} catch (ClassNotFoundException e) {
			//We are absolutely sure that the class IS actually found.
		} finally {
			if (aliasesStream != null) {
				aliasesStream.close();
			}
		}
		return loadingMap;
	}
}
