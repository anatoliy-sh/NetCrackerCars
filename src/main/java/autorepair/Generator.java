package autorepair;

import autorepair.enums.NamesOfCars;
import autorepair.enums.NamesOfOwners;
import autorepair.enums.Types;

import java.util.Random;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Generator {
    private int id;
    private Random rnd;


    public Generator(){
        id = 0;
        rnd = new Random();
    }

    public Car generateCar(){
        return new Car(id++,returnName(),returnOwnerName(),rnd.nextInt(6),returnType());
    }

    private String returnName(){
        return NamesOfCars.values()[rnd.nextInt(4)].toString();
    }

    private String returnOwnerName(){
        return NamesOfOwners.values()[rnd.nextInt(4)].toString();
    }

    private Types returnType(){
        return Types.values()[rnd.nextInt(3)];
    }
}
