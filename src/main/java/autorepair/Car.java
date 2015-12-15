package autorepair;

import autorepair.enums.Types;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Car {

    private int id;
    private String name;
    private String owner;
    private int handling_time;
    private Types type;

    public Car(int id, String name, String owner, int handling_time, Types type){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.handling_time = handling_time;
        this.type = type;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getOwner(){
        return owner;
    }

    public int getHandling_time(){
        return handling_time;
    }

    public Types getType(){
        return type;
    }
}
