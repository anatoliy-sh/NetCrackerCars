package autorepair;

import org.apache.log4j.Logger;

import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);



    public static void main(String[] args) {
        Parking parking = new Parking();
        Lock lock = new ReentrantLock();
        Master[] masters = new Master[4];
        Evacuator evacuator = new Evacuator(lock,parking);

        for (int i = 0; i < masters.length; i++) {
            masters[i] = new Master(lock,parking,i);
        }


        try {
            Thread[] threads = new Thread[masters.length + 1];
            for (int i = 0; i < masters.length; i++) {
                threads[i] = new Thread(masters[i]);
                threads[i].start();
            }
            threads[masters.length] = new Thread(evacuator);
            threads[masters.length].start();

            for (int i = 0; i < masters.length+1; i++) {
                threads[i].join();
            }
        }
        catch (Exception e){

        }
    }
}
