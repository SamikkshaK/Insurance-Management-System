package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
    public static String getProperty(String key) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/db.properties")) {
            properties.load(fis);
            return properties.getProperty(key);
        } catch (IOException e) {
            System.out.println("Failed to load properties file: " + e.getMessage());
        }
        return null;
    }
}
