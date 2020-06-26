package engine;

import gui.UIFrame;
import gui.UIHandler;
import items.SupremeKey;
import items.Sword;
import items.Water;
import map.IllegalMovementException;
import map.Room;
import obstacles.HinderedRoomException;
import util.Direction;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class GameEvent extends Thread {
    private static ResourceBundle eventOutputText = null;
    private static Object bunnyLock = new Object();
    private static final Object swordLock = new Object();
    private static final Object waterLock = new Object();
    private static final ThreadGroup eventThreadGroup = new ThreadGroup("events");

    private static final Thread countdown = new Thread(() -> {
        try {
            sleep(12_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        synchronized (swordLock) {
            swordLock.notify();
            return;
        }
    });
    
    private static final Thread credits = new Thread(() -> {
       UIHandler.disableInput();
       Document document = UIHandler.getDocument();
       while (document.getLength() > 0) {
           try {
               document.remove(document.getLength()-1, 1);
               sleep(5);
               UIHandler.printInFrame(eventOutputText.getString("credits"));
           } catch (BadLocationException e){
               //Can occur only if we try to remove from an empty document:
               //so we go ahead, since that's the situation we're trying to achieve.
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           }
       }
    });

    private static final Thread useSword = new Thread(() -> {
        synchronized (swordLock) {
            swordLock.notify();
        }
    });

    private static final Thread dragonWakeUp = new Thread (() -> {
        UIHandler.disableSave();
        UIHandler.printInFrame(eventOutputText.getString("dragonStart"));
        synchronized (swordLock) {
            try {
                Thread count = new Thread(eventThreadGroup, countdown);
                count.start();
                swordLock.wait();
                Thread.sleep(500);
                if (!count.isAlive()) {
                    UIHandler.printInFrame(eventOutputText.getString("dragonGameOver"));
                    GameProgress.gameOver();
                } else {
                    UIHandler.printInFrame(eventOutputText.getString("dragonPassed"));
                    UIHandler.enableSave();
                }
            } catch (InterruptedException e) {
                UIHandler.enableSave();
                Thread.currentThread().interrupt();
            }
        }
    });

    private static final Thread bossEvent = new Thread (() -> {
        UIHandler.disableSave();
       synchronized (waterLock) {
           UIHandler.printInFrame(eventOutputText.getString("bossStart"));
           try {
               Thread count = new Thread(eventThreadGroup, countdown);

               count.start();
               waterLock.wait();
               Thread.sleep(500);
               if (!count.isAlive()) {
                   UIHandler.printInFrame(eventOutputText.getString("bossBadEnd"));
               } else {
                   UIHandler.printInFrame(eventOutputText.getString("bossGoodEnd"));
               }
               sleep(10000);
               new Thread(credits).start();
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
           } finally {
               UIHandler.enableSave();
           }
       }
    });

    private static final Thread oldManEvent = new Thread(() -> {
        UIHandler.printInFrame(eventOutputText.getString("oldManSpeak"));
        GameProgress.dropItem(new Sword());
        UIHandler.printInFrame(eventOutputText.getString("oldManGone"));
    });

    private static final Thread explosion = new Thread(() -> {
        UIHandler.printInFrame(eventOutputText.getString("explosion"));
       GameProgress.gameOver();
    });

    private static final Thread doorEvent = new Thread(() -> {
        UIHandler.disableSave();
        try {
            Thread.sleep(15000);
            if (GameProgress.getCurrentRoom().roomInformation().length() + 1 < (UIHandler.getDocument().getLength())) {
                UIHandler.printInFrame(eventOutputText.getString("doorEventGameOver"));
                GameProgress.gameOver();
            } else {
                UIHandler.printInFrame(eventOutputText.getString("doorEventPassed"));
                GameProgress.dropItem(new SupremeKey());
                UIHandler.enableSave();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    });

    private static final Thread madScientist = new Thread(() -> {
        UIHandler.disableSave();
        Room tempRoom = GameProgress.getCurrentRoom();
        UIHandler.printInFrame(eventOutputText.getString("madScientist"));
        try {
            Thread.sleep(10000);
            if (tempRoom.equals(GameProgress.getCurrentRoom())
                    || tempRoom.move(Direction.SOUTH).equals(GameProgress.getCurrentRoom()) ) {
                UIHandler.printInFrame(eventOutputText.getString("madScientistGameOver"));
                GameProgress.gameOver();
            } else {
                UIHandler.printInFrame(eventOutputText.getString("madScientistGone"));
                UIHandler.enableSave();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (IllegalMovementException e) {
            e.printStackTrace();
        } catch (HinderedRoomException e) {
            e.printStackTrace();
        }
    });

    private static final Thread bunnyHunt = new Thread(new Runnable() {
        @Override
        public void run() {
            UIHandler.disableSave();
            boolean caughtBunny = false;
            Room bunnyRoom = null;
            Room bunnyRanAway = GameProgress.getCurrentRoom();
            bunnyLock = new ReentrantLock();
            try {
                bunnyRoom = GameProgress.getCurrentRoom().move(Direction.EAST).move(Direction.NORTH);
            } catch (IllegalMovementException | HinderedRoomException e) {
                //won't happen
            }
            UIHandler.printInFrame(eventOutputText.getString("bunnyStart"));
            synchronized (bunnyLock) {
                try {
                    while (!caughtBunny) {
                        bunnyLock.wait();
                        Direction tempDirection = getPlayerDirection(GameProgress.getCurrentRoom(), bunnyRoom);

                        if (tempDirection != null) {
                            Set<Direction> bunnyDirections = bunnyRoom.getAdjacentDirections();
                            bunnyDirections.remove(tempDirection);
                            try {
                                bunnyRoom = bunnyRoom.move(Direction.getOppositeDirection(tempDirection));
                                UIHandler.printInFrame(eventOutputText.getString("bunnyMoves") +
                                        Direction.getOppositeDirection(tempDirection).getName() + "\n");
                            } catch (IllegalMovementException | HinderedRoomException e) {
                                try {
                                    bunnyRoom = bunnyRoom.move(bunnyDirections.iterator().next());
                                    UIHandler.printInFrame(eventOutputText.getString("bunnyMoves") +
                                            bunnyDirections.iterator().next().getName() + "\n");
                                } catch (IllegalMovementException illegalMovementException) {
                                    //It won't happen because we use a set of directions specific of that room.
                                } catch (HinderedRoomException hinderedRoomException) {
                                    //In the specific game map, this doesn't happen: there's no obstacle
                                    //in the bunny's path.
                                }
                            }
                        } else if (bunnyRoom.equals(GameProgress.getCurrentRoom())) {
                            UIHandler.printInFrame(eventOutputText.getString("bunnyCaught"));
                            UIHandler.printInFrame(eventOutputText.getString("bunnySpeak"));
                            GameProgress.getCurrentRoom().dropItem(new Water());
                            UIHandler.printInFrame(eventOutputText.getString("bunnyGoesAway"));
                            caughtBunny = true;
                        }
                        if (bunnyRoom.equals(bunnyRanAway)) {
                            UIHandler.printInFrame(eventOutputText.getString("bunnyGoesAway"));
                            caughtBunny = true;
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            UIHandler.enableSave();
        }

        private Direction getPlayerDirection(Room playerRoom, Room bunnyRoom) {
            Set<Direction> bunnyDirections = bunnyRoom.getAdjacentDirections();
            for (Direction temp : bunnyDirections) {
                try {
                    if (bunnyRoom.move(temp).equals(playerRoom)) {
                        return temp;
                    }
                } catch (IllegalMovementException | HinderedRoomException e) {
                    continue;
                }
            }
            return null;
        }
    });

    private static final Thread bunnyRunAway = new Thread (() -> {
        synchronized (bunnyLock) {
            bunnyLock.notify();
        }

    });

    private static final Thread useWater = new Thread (() -> {
       synchronized (waterLock) {
           waterLock.notify();
       }
    });

    public static boolean startEvent(String eventName) {
        switch (eventName) {
            case "doorEvent": {
                new Thread(eventThreadGroup,doorEvent).start();
                return true;
            }
            case "madScientist": {
                new Thread(eventThreadGroup,madScientist).start();
                return true;
            }
            case "bunnyHunt": {
                new Thread(eventThreadGroup, bunnyHunt).start();
                return true;
            }
            case "bunnyRunAway": {
                new Thread(eventThreadGroup, bunnyRunAway).start();
                return !bunnyHunt.isAlive();
            }
            case "oldManEvent" : {
                new Thread(eventThreadGroup,oldManEvent).start();
                return true;
            }
            case "explosion" : {
                new Thread(eventThreadGroup, explosion).start();
                return true;
            }
            case "useSword": {
                new Thread(eventThreadGroup, useSword).start();
                return false;
            }
            case "useWater": {
                new Thread(eventThreadGroup, useWater).start();
                return false;
            }
            case "dragonWakeUp": {
                new Thread(eventThreadGroup, dragonWakeUp).start();
                return true;
            }
            case "bossEvent": {
                new Thread(eventThreadGroup, bossEvent).start();
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

    public static void interruptEvents() {
        eventThreadGroup.interrupt();
    }
}
