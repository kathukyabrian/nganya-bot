package tech.kitucode.telegram.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ApplicationProperties applicationProperties;

    public static void load() {
        Properties properties = new Properties();
        // Use the classloader to find the file in src/main/resources
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                return;
            }
            // Load the properties file
            properties.load(input);

            applicationProperties = new ApplicationProperties();
            applicationProperties.setBotToken(properties.getProperty("bot-token"));
            applicationProperties.setBotUsername(properties.getProperty("bot-username"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ApplicationProperties getProperties() {
        return applicationProperties;
    }
}
