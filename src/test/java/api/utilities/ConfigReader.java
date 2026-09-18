package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        try {

            String path =
                    System.getProperty("user.dir")
                    + "/src/test/resources/config.properties";

            FileInputStream file =
                    new FileInputStream(path);

            properties = new Properties();

            properties.load(file);

            file.close();

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to load config.properties", e);
        }
    }

    public static String getProperty(String key) {

        return properties.getProperty(key);
    }
}