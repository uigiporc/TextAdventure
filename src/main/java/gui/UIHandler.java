package gui;

import javax.swing.text.Document;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

public class UIHandler {
    private static UIFrame gameFrame = UIFrame.getUIFrame();
    private static LinkedBlockingQueue<Thread> stringQueue = new LinkedBlockingQueue();
    private static Thread printHandler = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (stringQueue) {
                while ((stringQueue.peek() != null) && stringQueue.peek().isAlive()){
                    try {
                        stringQueue.peek().join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (stringQueue.peek() != null) {
                    stringQueue.peek().start();
                    try {
                        Objects.requireNonNull(stringQueue.peek()).join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        stringQueue.poll();
                    }
                }
            }
        }

    });

    public static synchronized void printInFrame (String outString) {
        stringQueue.add(new Thread(() -> gameFrame.printString(outString)));
        new Thread(printHandler).start();
    }

    public static Document getDocument () {
        return gameFrame.getDocument();
    }

    public static synchronized void cleanScreen() {
        if (stringQueue.peek() != null) {
            stringQueue.peek().interrupt();
            stringQueue.clear();
        }
        gameFrame.resetText();
    }

    public static void disableInput() {
        gameFrame.disableInput();
    }

    public static void disableSave() {
        gameFrame.disableSave();
    }
}
