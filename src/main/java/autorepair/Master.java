package autorepair;

import org.apache.log4j.Logger;

import java.io.FileWriter;

import org.jdom.*;
import org.jdom.Element;
import org.jdom.output.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Анатолий on 14.12.2015.
 */
public class Master extends Thread {
    private static final Logger log = Logger.getLogger(Master.class);

    private Car car;
    private Parking parking;
    private int id;


    public void setCar(Car car) {
        this.car = car;
    }

    public Master(Parking parking, int id) {
        this.parking = parking;
        this.id = id;

    }


    public void run() {
        while (true) {
            car = parking.getCar();
            if (car != null) {
                try {
                    this.sleep(car.getHandling_time() * 1000);
                } catch (InterruptedException e) {

                }
                writeInformation();
                car = null;
            }
        }
    }
    //куда писать
    private void writeInformation() {
        switch (car.getType()) {
            case LOG:
                log.info("[Мастер " + id + "]" + car.getId() + " " + car.getName() + " " + car.getOwner() + " "
                        + car.getType() + " " + car.getHandling_time());
                break;
            case XML:
                createXML();
                break;
            case DATABASE:
                requestToDB();
                break;
        }

    }
    //xml
    private void createXML() {
        Element carXML = new Element("car");
        Document myDocument = new Document(carXML);

        carXML.addContent(new Element("id").addContent(Integer.toString(car.getId())));
        carXML.addContent(new Element("name").addContent(car.getName()));
        carXML.addContent(new Element("owner").addContent(car.getOwner()));
        carXML.addContent(new Element("handling_time").addContent(Integer.toString(car.getHandling_time())));
        carXML.addContent(new Element("type").addContent(car.getType().toString()));

        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter("outputInformation/XML/car" + car.getId() + ".xml");
            outputter.output(myDocument, fw);
            fw.close();
        } catch (java.io.IOException e) {
            log.error(e);
        }
    }
    //dataBase
    private void requestToDB() {
        Connection con = null;
        MyConnection myCon = new MyConnection();
        try {
            con = myCon.getConnection();
            if (con != null) {
                prepareStmt(con);
            } else
                System.out.println(" unable to create connection");
        } catch (SQLException e) {
            log.error(e.getMessage() + e.getErrorCode(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

        } finally {
            if (con != null) try {
                con.close();
            } catch (SQLException e) {
                log.error(e + e.getMessage() + e.getErrorCode(), e);
            }
        }
    }

    public void prepareStmt(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO "+car.getName()+" VALUES (?,?,?,?)");
        pstmt.setInt(1, car.getId());
        pstmt.setString(2, car.getName());
        pstmt.setString(3, car.getOwner());
        pstmt.setInt(4, car.getHandling_time());
        pstmt.execute();
        pstmt.close();
    }


}
