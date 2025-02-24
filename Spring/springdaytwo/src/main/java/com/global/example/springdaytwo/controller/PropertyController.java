package com.global.example.springdaytwo.controller;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
public class PropertyController {
    private final Environment env;

    public PropertyController(Environment env) {
        this.env = env;
    }

    @GetMapping("/properties/{key}")
    public ResponseEntity<?> getProperty(@PathVariable String key) {

        Set<String> allowedKeys = Set.of(
                "app.name",
                "app.version",
                "server.port",
                "custom.welcome.message"
        );

        if (!allowedKeys.contains(key)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Property access denied");
        }

        String value = env.getProperty(key);

        return value != null ?
                ResponseEntity.ok().body(Map.of(key, value)) :
                ResponseEntity.notFound().build();
    }
}