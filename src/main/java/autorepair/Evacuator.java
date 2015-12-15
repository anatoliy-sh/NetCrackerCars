package autorepair;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Evacuator extends Thread{
    private static final Logger log = Logger.getLogger(Evacuator.class);

    private Parking parking;
    private Random rnd;
    private Generator generator;

    public Evacuator(Parking parking){
        this.parking = parking;
        generator = new Generator();
        rnd = new Random();
    }

    public void run(){
        while (true){
            waitForSmth(rnd.nextInt(3));
            Car car = generator.generateCar();
            boolean flag = false;
            while (!flag){
                flag = parking.setCar(car);
                if(!flag) {
                    log.info("Evacuator: wait for parking");
                    waitForSmth(2);
                }
            }
        }
    }

    private void waitForSmth(int time){
        try{
            this.sleep(time*1000);
        }
        catch (InterruptedException e){
            log.error(e.getMessage()+e);
        }
    }

}
