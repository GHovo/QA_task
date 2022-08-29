package org.testing.sources;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {

    private final Properties prop = new Properties();

    public PropertiesReader() {
        try {
            String propertiesFilePath = System.getProperty("user.dir") + "/src/resources/api.properties";
            prop.load( Files.newInputStream(Paths.get(propertiesFilePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getEndPointUrl(String endpoint) {
        return prop.getProperty("base_url") + prop.getProperty(endpoint);
    }

    public String getBaseUrl() {
        return prop.getProperty("base_url");
    }
    public String getToken() {
        return prop.getProperty("token");
    }

}
