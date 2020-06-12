package ua.testing.repairagency.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader(){}


    public static String getPropertyValue(String name){
        String propertyValue = null;
        try(InputStream inputStream = PropertyReader.class
                .getClassLoader().getResourceAsStream(Constants.DEFAULT_PROPERTY_FILENAME)){
            Properties properties = new Properties();

            if (inputStream != null){
                properties.load(inputStream);
            }else {
                throw new FileNotFoundException();
            }
            propertyValue = properties.getProperty(name);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }

}
