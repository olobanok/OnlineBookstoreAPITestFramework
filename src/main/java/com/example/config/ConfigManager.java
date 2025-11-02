package com.example.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final String PATH = "fakeapi.properties";
    private static final String BASE_URL = "baseUrl";
    private static final String TIMEOUT_SEC = "timeoutSeconds";

    private static final Config CONFIG = load();

    private ConfigManager() {}

    private static Config load() {
        try (InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(PATH)) {
            Properties p = new Properties();
            p.load(in);
            return new Config(p.getProperty(BASE_URL),
                    Integer.parseInt(p.getProperty(TIMEOUT_SEC)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config");
        }
    }

    public static Config getConfig() {
        return CONFIG;
    }

}
