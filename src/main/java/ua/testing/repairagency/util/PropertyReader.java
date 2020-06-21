package ua.testing.repairagency.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader(){}


    public static String getConstPropertyValue(String key){
        return getPropertyValue(Constants.CONSTS_PROPERTY_FILENAME,key);
    }

    public static String getMessagePropertyValue(String key){
        return getPropertyValue(Constants.MESSAGES_PROPERTY_FILENAME, key);
    }




    private static String getPropertyValue(String propertyFileName, String key){
        String propertyValue = null;
        try(InputStream inputStream = PropertyReader.class
                .getClassLoader().getResourceAsStream(Constants.CONSTS_PROPERTY_FILENAME)){
            Properties properties = new Properties();

            if (inputStream != null){
                properties.load(inputStream);
            }else {
                throw new FileNotFoundException();
            }
            propertyValue = properties.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }

}
