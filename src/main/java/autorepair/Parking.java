package autorepair;

import java.util.Stack;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Parking {

    private static Stack<Car> parking;
    private int sizeOfParking;
    private int curSize;

    public Parking(){
        parking = new Stack<Car>();
        sizeOfParking = 10;
        curSize = 0;
    }

    public Car getCar(){
        if(!parking.empty()) {
            curSize--;
            return parking.pop();
        }
        else
            return null;
    }

    public boolean setCar(Car car){
        if (curSize >= sizeOfParking)
            return false;
        else{
            curSize++;
            parking.push(car);
            return true;
        }
    }

    public boolean isFul(){
        //System.out.println(curSize +" "+ sizeOfParking);
        return curSize >= sizeOfParking;
    }

    public boolean isEmty(){
        return parking.empty();
    }
}
