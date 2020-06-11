package engine;

import gui.UIHandler;
import map.IllegalMovementException;
import map.Room;
import obstacles.ObstacledRoomException;
import util.Direction;

import javax.swing.text.Document;
import java.util.Locale;
import java.util.ResourceBundle;

public class RoomEvent extends Thread {
    private static ResourceBundle eventOutputText = null;

    private static Thread doorEvent = new Thread(() -> {
        int startLenght = UIHandler.getDocument().getLength();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            //should never happen
        } finally {
            if (startLenght != (UIHandler.getDocument().getLength())) {
                new GameOver();
            }
        }
    });

    private static Thread madScientist = new Thread(() -> {
        Room tempRoom = GameProgress.getCurrentRoom();
        UIHandler.printInFrame(eventOutputText.getString("madScientist"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            //Never gets interrupted.
        } finally {
            if (tempRoom.equals(GameProgress.getCurrentRoom())) {
                new GameOver();
            } else {
                UIHandler.printInFrame(eventOutputText.getString("madScientistGone"));
            }
        }
    });

    public static boolean startEvent(String eventName) {
        switch (eventName) {
            case "doorEvent": {
                new Thread(doorEvent).start();
                return true;
            }
            case "madScientist": {
                new Thread(madScientist).start();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static void setEventText(Locale currentLocale) {
        eventOutputText = ResourceBundle.getBundle("bundles/eventOutText", currentLocale);
    }
}
