package com.fc572.selenium.pom;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Properties;


public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src/test/java/com/fc572/configs/Configuration.properties";
    private static final Logger logger = LogManager.getLogger();


    public ConfigFileReader(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                logger.error(String.valueOf(e));
            }
        } catch (
                FileNotFoundException e) {
            logger.error(String.valueOf(e));
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }
}