package gui;

import javax.swing.text.Document;
import java.util.concurrent.SynchronousQueue;

public class UIHandler {
    private static UIFrame gameFrame = UIFrame.getUIFrame();

    public static void printInFrame (String outString) {
        new Thread(() -> gameFrame.printString(outString)).start();
        //gameFrame.printString(outString);
    }

    public static Document getDocument () {
        return gameFrame.getDocument();
    }

    public static void cleanScreen() {
        gameFrame.resetText();
    }
}
