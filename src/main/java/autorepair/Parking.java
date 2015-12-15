package autorepair;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Parking {

    private static BlockingQueue<Car> parking;


    public Parking(int N){
        parking = new ArrayBlockingQueue<Car>(N);
    }

    public Car getCar(){

        if (!parking.isEmpty()) {
            try {
                return parking.take();
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null;

    }

    public boolean setCar(Car car){

        return parking.offer(car);

    }
    public boolean isEmty(){
        return parking.isEmpty();
    }
}
