package com.nextZen.Framework.ConfigReaderUtility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static String projectLocation;
    private static Properties properties;
    Logger logger = LogManager.getLogger(ConfigurationManager.class);

    public void loadPropertyFile(String fileName, String resourceScope) {
        projectLocation = System.getProperty("user.dir");
        properties = new Properties();
        String baseFolder = resourceScope.equalsIgnoreCase("main") ? "main" : "test";
        String filePath = projectLocation + File.separator + "src" + File.separator + baseFolder + File.separator + "resources" + File.separator + "Properties" + File.separator + fileName + ".properties";
        try {
            FileInputStream fileInputReader = new FileInputStream(filePath);
            properties.load(fileInputReader);
            logger.info("Successfully created the input reader for your config file at location :  " + filePath);
        } catch (IOException ioException) {
            logger.info("Failed to read the configuration file!... " + ioException.toString());
            throw new RuntimeException("Configuration file not found: " + filePath, ioException);
        }
    }

    public String getProperty(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        logger.info("For Property : " + propertyName.toUpperCase() + " It's value is : " + propertyValue);
        return propertyValue;
    }

    public void setProperty(String propertyName, String propertyValue) {
        properties.setProperty(propertyName, propertyValue);
        logger.info("You have set the Property: " + propertyName.toUpperCase() + "with Value: " + propertyValue);
    }

    public String getProjectLocation() {
        logger.info("Your project Location is: " + projectLocation);
        return projectLocation;
    }

    public Properties getPropertiesInstance() {
        return properties;
    }

}
