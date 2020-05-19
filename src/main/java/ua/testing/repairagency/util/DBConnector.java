package ua.testing.repairagency.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnector {
    public static Connection getConnection(){
        Connection con = null;

        String url = getDbPropertyValue("db.connection.url");
        String username= getDbPropertyValue("db.username");
        String password = getDbPropertyValue("db.password");

        try
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Post establishing a DB connection - "+con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return con;
    }

    private static String getDbPropertyValue(String propertyKey){
        String propertyValue = "";
        try(InputStream input = DBConnector.class.getClassLoader().getResourceAsStream("db.properties")){

            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            prop.load(input);
            propertyValue = prop.getProperty(propertyKey);

        }catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }
}

