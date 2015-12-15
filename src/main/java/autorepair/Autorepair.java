package autorepair;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Анатолий on 15.12.2015.
 */
public class Autorepair {
    private static final Logger log = Logger.getLogger(Main.class);
    private static int N;
    private static int X;

    public void start(){
        //чтение properties
        fullNX();

        Parking parking = new Parking(N);
        Master[] masters = new Master[X];

        Evacuator evacuator = new Evacuator(parking);
        for (int i = 0; i < masters.length; i++) {
            masters[i] = new Master(parking,i);
        }
        makeThreads(masters,evacuator);

    }
    private static void makeThreads(Master[] masters, Evacuator evacuator){
        //создание потоков
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
        catch (InterruptedException e){
            log.error(e.getMessage()+e);
        }
    }

    //чтение properties
    private void fullNX(){
        try{
            Properties prop = loadPropertiesFile();
            N = Integer.parseInt(prop.getProperty("N"));
            X = Integer.parseInt(prop.getProperty("X"));
        }
        catch (IOException e){
            log.error(e);
        }


    }

    private Properties loadPropertiesFile() throws IOException {
        Properties prop = new Properties();
        InputStream in = new FileInputStream("./src/main/resources/autorepair.properties");
        prop.load(in);
        in.close();
        return prop;
    }


}
