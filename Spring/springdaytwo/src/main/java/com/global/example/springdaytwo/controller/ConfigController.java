package com.global.example.springdaytwo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${custom.welcome.message}")
    private String welcomeMessage;

    @GetMapping("/config")
    public String getConfig() {
        return String.format("%s (v%s) - %s", appName, appVersion, welcomeMessage);
    }

    @GetMapping("/welcome")
    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}