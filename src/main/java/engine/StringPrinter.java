package engine;

import java.util.concurrent.ConcurrentLinkedQueue;

public class StringPrinter extends Thread {



    /*private static final Thread stringPrinting = new Thread (new Runnable() {
        @Override
        public void run() {
            String stringToPrint = stringsQueue.peek();
            while (true) {
                synchronized (this) {
                    if (stringToPrint == null) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    for (int i = 0; i < stringToPrint.length(); i++) {
                        System.out.print(stringToPrint.charAt(i));
                        try {
                            sleep(50);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                    System.out.println();
                }
            }

        }
    });

    static {
        stringPrinting.start();
    }*/

    private static final ConcurrentLinkedQueue<String> stringsQueue = new ConcurrentLinkedQueue<>();

    public static void printString(String string) {
        //stringsQueue.add(string);
        for (int i = 0; i < string.length(); i++) {
            try {
                sleep(15);
            } catch (InterruptedException e) {
                return;
            }
        }
        //stringPrinting.notify();
    }


}
