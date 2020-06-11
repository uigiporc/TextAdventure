package engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

import items.Item;
import map.MapLoader;
import map.Room;
import map.RoomContainer;
import util.Command;
import util.Direction;

public abstract class ResourceHandler {
	static {
		MapLoader.loadMap();
	}

	public static void loadResources() {
		String resourceFolderPath = "src/main/java/bundles";
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
			RoomEvent.setEventText(currentLocale);
		}catch(FileNotFoundException |MissingResourceException ex) {
			ex.printStackTrace();
		}
		//GameProgress.clock.start();
	}

	public static <T> Map<String[], T> load(String filePath) throws FileNotFoundException{
		ObjectInputStream aliasesStream = null;
		File aliasFile = null;
		Map <String[], T>loadingMap = new HashMap();

		try {
			aliasFile = new File(filePath);
			aliasesStream = new ObjectInputStream(new FileInputStream(aliasFile));
			loadingMap = (HashMap<String[], T>) aliasesStream.readObject();
			return loadingMap;

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		} catch (IOException e) {
			//FATAL
		} catch (ClassNotFoundException e) {
			//We are absolutely sure that the class IS actually found.
		} finally {
			try {
				aliasesStream.close();
			} catch (Exception e) {

			}
		}
		return loadingMap;
	}
}
