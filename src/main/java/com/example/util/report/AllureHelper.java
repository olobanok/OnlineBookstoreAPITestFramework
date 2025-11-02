package com.example.util.report;

import com.example.config.ConfigManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureHelper {

    private AllureHelper() {
    }

    public static void addEnvironmentInfo() {
        Properties props = new Properties();
        props.setProperty("BaseURL", ConfigManager.getConfig().baseUrl());
        props.setProperty("JavaVersion", System.getProperty("java.version"));

        try (var out = new FileOutputStream("build/allure-results/environment.properties")) {
            props.store(out, "Allure environment");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
