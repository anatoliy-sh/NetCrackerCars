package autorepair;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Car {

    private int id;
    private String name;
    private String owner;
    private int handling_time;
    private int type;

    public Car(int id, String name, String owner, int handling_time){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.handling_time = handling_time;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getHandling_time(){
        return handling_time;
    }


}
