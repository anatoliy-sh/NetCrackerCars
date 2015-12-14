package autorepair;

import java.util.concurrent.locks.Lock;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Master extends Thread{

    private Car car;
    private Lock lock;
    private Parking parking;
    private boolean emptyCar;
    private int id;


    public void setCar(Car car){
        this.car = car;
    }

    public void setLock (Car car){
        this.car = car;
    }

    public Master(Lock lock, Parking parking, int id){
        this.lock = lock;
        this.parking = parking;
        emptyCar = true;
        this.id = id;
    }


    public void run(){
        while (true){
            lock.lock();

            if (!parking.isEmty())
                car = parking.getCar();
            lock.unlock();

            if(car != null){
                try {
                    this.sleep(car.getHandling_time());
                }
                catch (InterruptedException e){

                }
                System.out.println("["+id + "] Починил" + car.getId());
                car = null;
            }
        }
    }

}
