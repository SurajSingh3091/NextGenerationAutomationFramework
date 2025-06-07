package com.nextZen.Framework.ConfigReaderUtility;

import com.nextZen.Framework.LoggerUtility.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private static String projectLocation;
    private static Properties properties;

    public  void loadPropertyFile(String fileName, String resourceScope) {
        projectLocation = System.getProperty("user.dir");
        properties = new Properties();
        String baseFolder = resourceScope.equalsIgnoreCase("main") ? "main" : "test";
        String filePath = projectLocation + File.separator + "src" + File.separator + baseFolder + File.separator + "resources" + File.separator + "Properties" + File.separator + fileName+".properties";
        try {
            FileInputStream fileInputReader = new FileInputStream(filePath);
            properties.load(fileInputReader);
            Log.info("Successfully created the input reader for your config file at location :  " + filePath);
        } catch (IOException ioException) {
            Log.info("Failed to read the configuration file!... " + ioException.toString());
            throw new RuntimeException("Configuration file not found: " + filePath, ioException);
        }
    }

    public  String getProperty(String propertyName) {
        String propertyValue = properties.getProperty(propertyName);
        Log.info("For Property : " + propertyName.toUpperCase() + " It's value is : " + propertyValue);
        return propertyValue;
    }

    public  void setProperty(String propertyName, String propertyValue) {
        properties.setProperty(propertyName, propertyValue);
        Log.info("You have set the Property: " + propertyName.toUpperCase() + "with Value: " + propertyValue);
    }

    public  String getProjectLocation() {
        Log.info("Your project Location is: " + projectLocation) ;
        return projectLocation;
    }

    public  Properties getPropertiesInstance(){
        return properties;
    }

}
