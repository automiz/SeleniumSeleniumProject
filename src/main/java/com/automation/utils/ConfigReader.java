package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try (FileInputStream fis = new FileInputStream("./config.properties")) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("💥 Framework Core Error: The critical config.properties file could not be loaded!");
        }
    }

    /**
     * Fetches keys directly. Checks for a command-line environment override first (-D), 
     * then checks the config.properties file.
     */
    public static String getProperty(String key) {
        // 1. Command-line check (for Jenkins pipelines)
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.trim().isEmpty()) {
            return systemValue;
        }
        
        // 2. Fallback to properties file values
        if (properties != null && properties.containsKey(key)) {
            return properties.getProperty(key);
        }
        
        System.out.println("⚠️ Warning: Configuration key '" + key + "' was not found inside config.properties!");
        return null;
    }
}