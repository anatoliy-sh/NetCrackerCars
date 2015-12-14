package autorepair;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Generator {
    private int id;

    public Generator(){
        id = 0;
    }

    public int getNextId(){
        return id++;
    }

}
