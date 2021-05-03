package utils;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class PropertyReader {
    private Properties properties;
    private static PropertyReader propertyReader;

    static Logger logger = Logger.getLogger(PropertyReader.class.getName());

    private PropertyReader(String fileName) {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(fileName + ".properties"));
            logger.info("Loaded property file - " + fileName + ".properties successfully.");
        } catch (FileNotFoundException e) {
            logger.info(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static String getProperty(String propertyName) {
        return getPropertyReader("config").properties.getProperty(propertyName);
    }

    public static String getProperty(String fileName, String propertyName) {
        propertyReader = null;
        String returnValue = getPropertyReader(fileName).properties.getProperty(propertyName);
        propertyReader = null;
        return returnValue;
    }

    private static PropertyReader getPropertyReader(String fileName) {
        if (propertyReader == null) {
            propertyReader = new PropertyReader(fileName);
        }
        return propertyReader;
    }

}
