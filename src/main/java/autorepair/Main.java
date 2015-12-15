package autorepair;

import org.apache.log4j.Logger;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        Autorepair autorepair = new Autorepair();
        autorepair.start();
    }
}
