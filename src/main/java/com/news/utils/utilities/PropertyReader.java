package com.news.utils.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {
    Properties prop;
    private static PropertyReader instance;


    public static PropertyReader getInstance() {
        if (instance == null)
            instance = new PropertyReader();
        return instance;
    }

    public InputStream load() {
        InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream("config.properties");
        return input;
    }

    public String getProperty(String key) {
        prop = new Properties();
        try {
            prop.load(load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }


}
