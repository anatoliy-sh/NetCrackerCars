package autorepair;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Анатолий on 11.12.2015.
 */
public class MyConnection {
    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
        Properties prop = loadPropertiesFile();

        String driverClass = prop.getProperty("MYSQLJDBC.driver");
        String url = prop.getProperty("MYSQLJDBC.url");
        String username = prop.getProperty("MYSQLJDBC.username");
        String password = prop.getProperty("MYSQLJDBC.password");

        Class.forName(driverClass);
        return DriverManager.getConnection(url, username, password);
    }

    public Properties loadPropertiesFile() throws IOException {
        Properties prop = new Properties();
        InputStream in = new FileInputStream("./src/main/resources/database.properties");
        prop.load(in);
        in.close();
        return prop;
    }
}
