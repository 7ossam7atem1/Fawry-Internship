package com.global.example.springdaytwo.services;

import com.global.example.springdaytwo.config.AppConfig;
import com.global.example.springdaytwo.entities.ApplicationInfo;
import org.springframework.stereotype.Service;

@Service
public class ApplicationInfoService {

    private final AppConfig appConfig;

    public ApplicationInfoService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public ApplicationInfo getApplicationInfo() {
        return new ApplicationInfo(
                appConfig.getVersion(),
                appConfig.getBuild().getNumber(),
                appConfig.getBuild().getTimestamp(),
                appConfig.getGit().getCommit(),
                appConfig.getGit().getTag()
        );
    }
}