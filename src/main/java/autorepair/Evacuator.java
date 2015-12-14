package autorepair;

import sun.awt.windows.ThemeReader;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Evacuator extends Thread{

    private Lock lock;
    private Parking parking;
    private Random rnd;
    private Generator generator;
    private Car curCar;

    public Evacuator(Lock lock, Parking parking){
        this.lock = lock;
        this.parking = parking;
        generator = new Generator();
        rnd = new Random();
    }

    public void run(){
        while (true){
            try{
                this.sleep(rnd.nextInt(3)*1000);
            }
            catch (InterruptedException e){

            }
            while (true){
                lock.lock();
                if(!parking.isFul()) {
                    lock.unlock();
                    break;
                }
                lock.unlock();
            }

            lock.lock();
            parking.setCar(new Car(generator.getNextId(),"Lada","Misha",1000));
            lock.unlock();
            System.out.println("Добавил");
        }
    }

}
