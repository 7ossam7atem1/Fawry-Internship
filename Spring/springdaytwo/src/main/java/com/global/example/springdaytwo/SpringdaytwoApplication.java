package com.global.example.springdaytwo;

import com.global.example.springdaytwo.config.SmsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SmsConfig.class)
public class SpringdaytwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdaytwoApplication.class, args);
    }

}
